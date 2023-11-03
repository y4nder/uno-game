package UnoGameFiles;
import java.util.List;
import UnoGameFiles.Cards.*;
import UnoGameFiles.Cards.CardAttributes.Color;
import UnoGameFiles.Cards.CardAttributes.SpecialType;
import UnoGameFiles.Misc.*;
import UnoGameFiles.UnoGameEntities.*;

public class UnoGame extends CardValidity {
    private UnoDeck unoDeck;
    private Table table;
    private Player startingPlayer;

    private static final int GAME_SPEED = 0;
    private static final int START_CARD_COUNT = 7;
    private static final int ANY_CARD = 99;

    public UnoGame(List<Player> allPlayers){
        this.startingPlayer = getFirstPlayerInRotationFrom(allPlayers);
        this.unoDeck = new UnoDeck();
        this.table = new Table();
    }

    public Player getFirstPlayerInRotationFrom(List<Player> allPlayers){
        RotationSetup rSetup = new RotationSetup();
        rSetup.setTheRotationOf(allPlayers);
        return rSetup.getFirstPlayer();
    }

    //early game
    public void startUnoGame(){
        unoDeck.shuffeDeck();
        giveStartingCards(startingPlayer);
        UnoCard startCard = drawFirstCard();
        PlayUno(startingPlayer, startCard);
    }

    public void PlayUno(Player currentPlayer, UnoCard currentCard){
        RotationState rState = new LeftRotation();
        boolean gameFinished = false;
        while (!gameFinished) {
            type("\n--------------------------------------------------"); 
            if(aNormalCard(currentCard)){
                type("Top card is a " + currentCard.toString());
                type();
                type(currentPlayer.getPlayerName().toUpperCase() + "'s turn");
                UnoCard toThrow = currentPlayer.throwCard(currentCard);
                if(toThrow != null){
                    currentCard = toThrow;
                    table.addToTableDeck(currentCard);
                    type(currentPlayer.getPlayerName() + " throws a " + toThrow.toString());
                    if(aDrawFour(currentCard)){
                        Color choice = currentPlayer.chooseColor();
                        currentCard = new SpecialCard(choice, SpecialType.DRAWFOUR);
                    }
                    if(currentPlayer.hasNoMoreCards()){
                        type("\n" + currentPlayer.getPlayerName() + " won!");
                        gameFinished = true;
                    }
                    if(currentPlayer.uno()){
                        type(currentPlayer.getPlayerName() + " yells UNO!");
                    }
                }
                else{
                    type(currentPlayer.getPlayerName() + " does not have any playable cards");
                    type(currentPlayer.getPlayerName() + " will draw 1 card");
                    UnoCard drawn = distributeOneCard();
                    currentPlayer.addACard(distributeOneCard());
                    if(isValid(drawn, currentCard)){
                        continue;
                    }
                }
                currentPlayer = rState.rotate(currentPlayer);
            }
            else{
                if(aDrawTwo(currentCard)){
                    distributeCards(2, currentPlayer);
                    type("\n" + currentPlayer.getPlayerName() + " draws 2 cards and LOSES their Turn");
                    currentCard = new NormalCard(currentCard.getColor(), ANY_CARD);
                    currentPlayer = rState.rotate(currentPlayer);
                }
                else if(aReverse(currentCard)){
                    type("\nThe rotation is reversed");
                    if(rState instanceof LeftRotation){
                        rState = new RightRotation();
                    }
                    else{
                        rState = new LeftRotation();
                    }
                    currentPlayer = rState.rotate(rState.rotate(currentPlayer));
                    currentCard = new NormalCard(currentCard.getColor(), ANY_CARD);
                }
                else if(aSkip(currentCard)){
                    type("\n" + currentPlayer.getPlayerName() + " was skipped, they LOSE their turn");
                    currentPlayer = rState.rotate(rState.rotate(currentPlayer));
                    currentCard = new NormalCard(currentCard.getColor(), ANY_CARD);
                }
                else if(aDrawFour(currentCard)){
                    distributeCards(4, currentPlayer);
                    type("\n" +  currentPlayer.getPlayerName() + " draws 4 cards and LOSES their turn");
                    currentCard = new NormalCard(currentCard.getColor(), ANY_CARD);
                    currentPlayer = rState.rotate(currentPlayer);
                }
            }
        }
        type();
        viewPlayersCards();
        return;
    }

    //helper method
    public void giveStartingCards(Player start){
        Player first = start;
        Player current = first;
        do{
            type("distributing 7 cards to " + current.getPlayerName());
            distributeCards(START_CARD_COUNT, current);
            current = current.getPlayerLeft();
        }
        while(!current.equals(first));
    }

    public UnoCard drawFirstCard(){
        type("Drawing First Card....");
        UnoCard firstDraw;
        do{
            firstDraw = unoDeck.drawOne();
            table.addToTableDeck(firstDraw);            
        }
        while(!validStartCard(firstDraw));
        showCardFromTable();
        return firstDraw;
    }

    public boolean validStartCard(UnoCard u){
        if(u instanceof SpecialCard){
            type("first draw was a " + u.toString());
            type();
            type("Drawing first card again");
            return false;
        }
        return true;
    }

    public void showCardFromTable(){
        System.out.println("Top Card is a " + table.getTopCard().toString());
        type();
    }

    public void viewPlayersCards(){
        Player first = startingPlayer;
        Player current = first;
        type("----PLAYER CARDS----");
        do{
            current.showMyCards();
            current = current.getPlayerLeft();
        }
        while(!current.equals(first));
    }

    public void type(String string){
        System.out.println(string);
        try{
            Thread.sleep(GAME_SPEED); 
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void type(){
        System.out.println();
    }

    //comparison methods
    public boolean aNormalCard(UnoCard u){
        return u instanceof NormalCard;
    }

    public boolean aDrawFour(UnoCard u){
        return u instanceof SpecialCard && ((SpecialCard) u).getType().equals(SpecialType.DRAWFOUR);
    }

    public boolean aDrawTwo(UnoCard u){
        return ((SpecialCard)u).getType().equals(SpecialType.DRAWTWO);
    }

    public boolean aReverse(UnoCard u){
        return ((SpecialCard)u).getType().equals(SpecialType.REVERSE);
    }

    public boolean aSkip(UnoCard u){
        return ((SpecialCard)u).getType().equals(SpecialType.SKIP);
    }

    public void distributeCards(int howMany, Player currentPlayer){
        if(unoDeck.notEnoughCards(howMany)){
            type("\n not enough cards in deck to distribute " + howMany);
            type("retrieving cards from table");
            unoDeck.retrieveCards(table.returnCards());
        }
        unoDeck.giveCards(howMany, currentPlayer);
    }

    public UnoCard distributeOneCard(){
        if(unoDeck.notEnoughCards(1)){
            type("\n not enough cards in deck to distribute one");
            type("retrieving cards");
            unoDeck.retrieveCards(table.returnCards());
        }
        return unoDeck.drawOne();
    }
}
