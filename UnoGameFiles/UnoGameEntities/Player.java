package UnoGameFiles.UnoGameEntities;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import UnoGameFiles.Cards.UnoCard;
import UnoGameFiles.Cards.CardAttributes.Color;
import UnoGameFiles.Misc.CardValidity;

public abstract class Player extends CardValidity{
    protected String playerName;
    protected List<UnoCard> myCards;
    protected Player playerLeft;
    protected Player playerRight;
    static Scanner scan = new Scanner(System.in);

    public Player(String playerName) {
        this.playerName = playerName;
        this.myCards = new ArrayList<>();
        this.playerLeft = null;
        this.playerRight = null;
    }

    //getters
    public String getPlayerName() {
        return playerName;
    }

    public Player getPlayerLeft() {
        return playerLeft;
    }

    public Player getPlayerRight() {
        return playerRight;
    }

    public List<UnoCard> getMyCards() {
        return myCards;
    }


    //setters
    public void setPlayerName(String playerName){
        this.playerName = playerName;
    }

    public void setPlayerLeft(Player playerLeft) {
        this.playerLeft = playerLeft;
    }

    public void setPlayerRight(Player playerRight) {
        this.playerRight = playerRight;
    }

    public void setMyCards(List<UnoCard> myCards) {
        this.myCards = myCards;
    }

    public void addACard(UnoCard u){
        this.myCards.add(u);
    }


    //methods
    public void showMyCards(){
        if(myCards.size() == 0){
            System.out.println("\n" + playerName + " has no more cards (winner)");
            return;
        }

        System.out.println("\n***** " + playerName + "'s cards *****");
        int i = 0;
        for(UnoCard u : myCards){
            System.out.println("  [" + (i + 1) + "] - " + u.toString());
            i++;
        }

    }

    public void giveBackCard(UnoCard u){
        myCards.add(u);
    }

    public void drawCard(List<UnoCard> drawnCards){
        myCards.addAll(drawnCards);
    }

    public abstract UnoCard throwCard(UnoCard fromTable);

    public abstract Color chooseColor();

    public int getCurrentCardCount(){
        return myCards.size();
    }

    public boolean hasNoMoreCards(){
        return myCards.size() == 0;
    }

    public boolean uno(){
        return myCards.size() == 1;
    }

    //helper method
    public int getInput(String instructions){
        int choice;
        do{
            System.out.print(instructions);
            choice = scan.nextInt();
            if(choice == 0) return 'x';
        }
        while(!validChoice(choice - 1));
        return choice - 1;
    }

    private boolean validChoice(int choice){
        if(choice < 0 || choice > myCards.size() - 1){
            System.out.println("Invalid choice");
            return false;
        }
        return true;

    }

    public boolean hasThrowableCard(UnoCard fromTable){
        for(UnoCard u : myCards){
            if(isValid(u, fromTable)){
                return true;
            }
        }
        return false;
    }
}

