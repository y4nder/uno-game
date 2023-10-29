package Participants;
import Cards.*;

public class UnoGame {
    private UnoDeck unoDeck;
    private Table table;
    private Player startingPlayer;
    private Rotation r;

    public UnoGame(Player startingPlayer){
        this.startingPlayer = startingPlayer;
        unoDeck = new UnoDeck();
        table = new Table();
        r = new RotateLeft();
    }

    //early game
    public void testStart(){
        unoDeck.shuffeDeck();
        giveStartingCards(startingPlayer);
        UnoCard startCard = drawFirstCard();
        PlayUno(startingPlayer, startCard);
    }

    public void giveStartingCards(Player start){
        Player first = start;
        Player current = first;
        do{
            // unoDeck.giveCards(7, current);
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

    public void PlayUno(Player player, UnoCard top){
        //determine if player is user or cpu
        Player currentPlayer = player;
        UnoCard currentTop = top;

        type("\n--------------------------------------------------");
        
        if(aNormalCard(currentTop)){
            type("Top card is a " + currentTop.showCard());
            type();
            type(currentPlayer.getPlayerName().toUpperCase() + "'s turn");
            UnoCard toThrow = currentPlayer.throwCard(currentTop);
            if(toThrow != null){
                currentTop = toThrow;
                type(currentPlayer.getPlayerName() + " throws a " + toThrow.showCard());

                if(aDrawFour(currentTop)){
                    Color choice = currentPlayer.chooseColor();
                    currentTop = new SpecialCard(choice, SpecialType.DRAWFOUR);
                }

                if(currentPlayer.hasNoMoreCards()){
                    type("\n" + currentPlayer.getPlayerName() + " won!!");
                    return;
                }

                if(currentPlayer.uno()){
                    type(currentPlayer.getPlayerName() + " yells UNO!");
                }

            }
            else{
                type(currentPlayer.getPlayerName() + " does not have any playable cards");
                type(currentPlayer.getPlayerName() + " will draw 1 card");
                // unoDeck.giveCards(1, currentPlayer);
                distributeCards(1, currentPlayer);
                
                if(currentPlayer.hasThrowableCard(currentTop)){
                    PlayUno(currentPlayer, currentTop);
                    return;
                }
            }

        PlayUno(r.rotate(currentPlayer), currentTop);

        }
        else{    
            //if draw two
            if(aDrawTwo(currentTop)){
                type("\n" + currentPlayer.getPlayerName() + " draws 2 cards and LOSES their Turn");
                // unoDeck.giveCards(2, currentPlayer);
                distributeCards(2, currentPlayer);
                PlayUno(r.rotate(currentPlayer), new NormalCard(currentTop.getColor(), 99));
            }

            //if reverse
            if(aReverse(currentTop)){
                type("\nThe rotation is reversed 🔀");
                if(r instanceof RotateLeft){
                    r = new RotateRight();
                }
                else{
                    r = new RotateLeft();
                }
                PlayUno(r.rotate(r.rotate(currentPlayer)), new NormalCard(currentTop.getColor(), 99));
            }
            
            //if skip
            if(aSkip(currentTop)){
                type("\n" + currentPlayer.getPlayerName() + " was skipped, they LOSE their turn");
                PlayUno(r.rotate(r.rotate(currentPlayer)), new NormalCard(currentTop.getColor(), 99));
            }
            
            //if wild draw four
            if(aDrawFour(currentTop)){
                type("\n" +  currentPlayer.getPlayerName() + " draws 4 cards and LOSES their turn");
                //unoDeck.giveCards(4, currentPlayer);
                distributeCards(5, currentPlayer);
                currentTop = new NormalCard(currentTop.getColor(), 99);
                PlayUno(r.rotate(currentPlayer), currentTop);
            }
        }

        return;
    }

    //helper method
    public boolean validStartCard(UnoCard u){
        if(u instanceof SpecialCard){
            System.out.println("first draw was a " + u.showCard());
            type();
            type("Drawing first card again");
            return false;
        }
        return true;
    }

    public void showCardFromTable(){
        System.out.println("Top Card is a " + table.getTopCard().showCard());
        type();
    }

    //helper methods
    public void type(String string){
        for(int i = 0; i < string.length(); i++)
        {
            System.out.print(string.charAt(i));
            try{
                Thread.sleep(0); 
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
}
