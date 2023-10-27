package Participants;
import java.util.ArrayList;
import java.util.List;
import Cards.UnoCard;

import java.util.Scanner;

abstract class Player {
    protected List<UnoCard> myCards; 
    protected boolean user; 
    protected Player playerLeft;
    protected Player playerRight;
    
    //action strategy

    public Player(){
        this.myCards = new ArrayList<>();
        this.user = false;
        this.playerLeft = null;
        this.playerRight = null;
    }

    //getters
    public Player getPlayerLeft() {
        return playerLeft;
    }

    public Player getPlayerRight() {
        return playerRight;
    }

    public List<UnoCard> getMyCards() {
        return myCards;
    }

    public boolean isUser() {
        return user;
    }

    
    //setters
    public void setPlayerLeft(Player playerLeft) {
        this.playerLeft = playerLeft;
    }

    public void setPlayerRight(Player playerRight) {
        this.playerRight = playerRight;
    }

    public void setMyCards(List<UnoCard> myCards) {
        this.myCards = myCards;
    }

    public void setUser(boolean user) {
        this.user = user;
    }

    
    //methods
    public void showMyCards(){
        if(myCards.size() == 0){
            System.out.println("no cards");
            return;
        }

        int i = 0;
        for(UnoCard u : myCards){
            System.out.print(i + ": ");
            u.showCard();
            i++;
            System.out.println();
        }
    }

    public void giveBackCard(UnoCard u){
        myCards.add(u);
    }

    public void drawCard(List<UnoCard> drawnCards){
        myCards.addAll(drawnCards);
    }

    //helper method
    public int getInput(String instructions){
        Scanner scan = new Scanner(System.in);
        int choice;
        do{            
            System.out.println(instructions);
            choice = scan.nextInt();
        }
        while(choice < 0 || choice > myCards.size());
        return choice;
    }
}
