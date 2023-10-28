package Participants;
import java.util.Scanner;

public class UnoGame {
    private UnoDeck unoDeck;
    private Table table;
    private Player startingPlayer;
    private static Scanner scan = new Scanner(System.in);

    public UnoGame(Player startingPlayer){
        this.startingPlayer = startingPlayer;
        unoDeck = new UnoDeck();
        table = new Table();
    }

    //early game
    public void testStart(){
        unoDeck.shuffeDeck();
        giveStartingCards(startingPlayer);
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


    //tester methods
    public void rotationTest(Player p){
        System.out.println("\n\n" + p.getPlayerName() + "'s turn");
        int choice;
        do{
            System.out.println("Choose where to rotate");
            System.out.println("[1] left");
            System.out.println("[2] right");
            System.out.println("[0] exit");
            choice = scan.nextInt();
        }
        while(!validRotate(choice));

        if(choice == 0){
            System.out.println("bye");
            return;
        }

        if(choice == 1){
            System.out.println("going left");
            rotationTest(p.getPlayerLeft());
        }
        else{
            System.out.println("going right");
            rotationTest(p.getPlayerRight());
        }

        return;

    }

    public boolean validRotate(int choice){
        if(choice >= 0 && choice <= 2){
            return true;
        }
        System.out.println("invalid");
        return false;
    }

    public void showAllPlayerCards(){
        Player first = startingPlayer;
        Player currentPlayer = first;
        do{
            System.out.println("\n" + currentPlayer.getPlayerName() + "'s cards");
            currentPlayer.showMyCards();
            currentPlayer = currentPlayer.getPlayerRight();
        }
        while(!currentPlayer.equals(first));
    }
}
