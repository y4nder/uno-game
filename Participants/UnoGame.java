package Participants;
import java.util.Scanner;
import Cards.*;

public class UnoGame {
    private UnoDeck unoDeck;
    private Table table;
    private Player startingPlayer;
    private Rotation r;
    private static Scanner scan = new Scanner(System.in);

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
            unoDeck.giveCards(7, current);
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
        
        if(top instanceof NormalCard){
            System.out.println("\n--------------------------------------------------");
            System.out.println(currentPlayer.getPlayerName() + "'s turn");
            System.out.print("Top card is a ");
            currentTop.showCard();
            System.out.println();
            UnoCard toThrow = currentPlayer.throwCard(currentTop);
            if(toThrow != null){
                currentTop = toThrow;
                if(currentPlayer.getCurrentCardCount() == 0){
                    System.out.println("\n" + currentPlayer.getPlayerName() + " won!!");
                    return;
                }
            }
            else{
                System.out.println(currentPlayer.getPlayerName() + " does not have any playable cards");
                System.out.println(currentPlayer.getPlayerName() + " will draw 1 card");
                unoDeck.giveCards(1, currentPlayer);
            }
            PlayUno(r.rotate(currentPlayer), currentTop);
        }
        else{    
            //if draw two
            if(((SpecialCard)currentTop).getType().equals(SpecialType.DRAWTWO)){
                System.out.println(currentPlayer.getPlayerName() + "draws 2 cards");
                unoDeck.giveCards(2, currentPlayer);
                PlayUno(r.rotate(currentPlayer), new NormalCard(currentTop.getColor(), 99));
            }

            //if reverse
            if(((SpecialCard)currentTop).getType().equals(SpecialType.REVERSE)){
                System.out.println("\nThe rotation is reversed");
                if(r instanceof RotateLeft){
                    r = new RotateRight();
                }
                else{
                    r = new RotateLeft();
                }
                PlayUno(r.rotate(r.rotate(currentPlayer)), new NormalCard(currentTop.getColor(), 99));
            }
            
            //if skip
            if(((SpecialCard)currentTop).getType().equals(SpecialType.SKIP)){
                System.out.println("\n" + currentPlayer.getPlayerName() + " was skipped");
                PlayUno(r.rotate(r.rotate(currentPlayer)), new NormalCard(currentTop.getColor(), 99));
            }
            
            //if wild draw four
            if(((SpecialCard)currentTop).getType().equals(SpecialType.DRAWFOUR)){
                Color choice = getColorChoice();
                System.out.println("\n" +  currentPlayer.getPlayerName() + "draws 4 cards");
                unoDeck.giveCards(4, currentPlayer);
                currentTop = new NormalCard(choice, 99);
                PlayUno(r.rotate(currentPlayer), currentTop);
            }
        }
        return;
    }

    //helper method
    public boolean validStartCard(UnoCard u){
        if(u instanceof SpecialCard){
            System.out.print("first draw was a ");
            u.showCard();
            System.out.println();
            System.out.println("Drawing first card again");
            return false;
        }
        return true;
    }

    public void showCardFromTable(){
        System.out.print("Top Card is a ");
        table.getTopCard().showCard();
        System.out.println();
    }

    public Color getColorChoice(){
        int choice;
        System.out.println();
        for(Color c : Color.values()){
            if(c == Color.WILD) continue;
            System.out.println(c.ordinal() + ": " + c);
        }

        do{
            System.out.print("  Please Choose a color to play: ");
            choice = scan.nextInt();
        }
        while(choice < 0 && choice > 2);

        for(Color c: Color.values()){
            if(c.getPosition() == choice);
            return c;
        }

        return null;
    }

}
