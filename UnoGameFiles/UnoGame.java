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
    private Rotation r;
    static int gameSpeed = 0;

    public UnoGame(List<Player> allPlayers){
        RotationSetup rSetup = new RotationSetup();
        rSetup.setupPlayers(allPlayers);
        startingPlayer = rSetup.getFirstPlayer();
        unoDeck = new UnoDeck();
        table = new Table();
        r = new RotateLeft();
    }

    //early game
    public void startUnoGame(){
        unoDeck.shuffeDeck();
        giveStartingCards(startingPlayer);
        UnoCard startCard = drawFirstCard();
        PlayUno(startingPlayer, startCard);
    }

    public void PlayUno(Player currentPlayer, UnoCard currentCard){
        while (true) {
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
                        return;
                    }
                    if(currentPlayer.uno()){
                        type(currentPlayer.getPlayerName() + " yells UNO!");
                    }
                }
                else{
                    type(currentPlayer.getPlayerName() + " does not have any playable cards");
                    type(currentPlayer.getPlayerName() + " will draw 1 card");
                    UnoCard drawn = distributeOneCard();
                    currentPlayer.addACard(drawn);
                    if(isValid(drawn, currentCard)){
                        continue;
                    }
                }
                currentPlayer = r.rotate(currentPlayer);
            }
            else{
                if(aDrawTwo(currentCard)){
                    distributeCards(2, currentPlayer);
                    type("\n" + currentPlayer.getPlayerName() + " draws 2 cards and LOSES their Turn");
                    currentCard = new NormalCard(currentCard.getColor(), 99);
                    currentPlayer = r.rotate(currentPlayer);
                }
                else if(aReverse(currentCard)){
                    type("\nThe rotation is reversed 🔀");
                    if(r instanceof RotateLeft){
                        r = new RotateRight();
                    }
                    else{
                        r = new RotateLeft();
                    }
                    currentPlayer = r.rotate(r.rotate(currentPlayer));
                    currentCard = new NormalCard(currentCard.getColor(), 99);
                }
                else if(aSkip(currentCard)){
                    type("\n" + currentPlayer.getPlayerName() + " was skipped, they LOSE their turn");
                    currentPlayer = r.rotate(r.rotate(currentPlayer));
                    currentCard = new NormalCard(currentCard.getColor(), 99);
                }
                else if(aDrawFour(currentCard)){
                    distributeCards(4, currentPlayer);
                    type("\n" +  currentPlayer.getPlayerName() + " draws 4 cards and LOSES their turn");
                    currentCard = new NormalCard(currentCard.getColor(), 99);
                    currentPlayer = r.rotate(currentPlayer);
                }
            }
        }
    }

    //helper method
    public void giveStartingCards(Player start){
        Player first = start;
        Player current = first;
        do{
            distributeCards(7, current);
            current = current.getPlayerRight();
        }
        while(!current.equals(first));
    }

    public UnoCard drawFirstCard(){
        UnoCard firstDraw = null;
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
            System.out.println("first draw was a " + u.toString());
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

    public void type(String string){
        for(int i = 0; i < string.length(); i++)
        {
            System.out.print(string.charAt(i));
            try{
                Thread.sleep(gameSpeed); 
            } 
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println();
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
            type("retrieving cards");
            unoDeck.retrieveCards(table.returnCards());
        }
        unoDeck.giveCards(howMany, currentPlayer);
    }

    public UnoCard distributeOneCard(){
        if(unoDeck.notEnoughCards(1)){
            type("retrieving cards");
            unoDeck.retrieveCards(table.returnCards());
        }
        return unoDeck.drawOne();
    }
}
