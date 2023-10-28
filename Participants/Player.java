package Participants;
import java.util.ArrayList;
import java.util.List;

import Cards.Color;
import Cards.NormalCard;
import Cards.SpecialCard;
import Cards.UnoCard;

import java.util.Scanner;

public abstract class Player {
    protected String playerName;
    protected List<UnoCard> myCards; 
    protected Player playerLeft;
    protected Player playerRight;
    
    public Player(){
        this.playerName = "";
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
            System.out.println("no cards");
            return;
        }

        System.out.println("\n***** " + playerName + "'s cards *****");
        int i = 0;
        for(UnoCard u : myCards){
            System.out.println("  " + i + " - " + u.showCard());
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

    public int getCurrentCardCount(){
        return myCards.size();
    }

    //helper method
    public int getInput(String instructions){
        Scanner scan = new Scanner(System.in);
        int choice;
        do{            
            System.out.print(instructions);
            choice = scan.nextInt();
        }
        while(!validChoice(choice));
        return choice;
    }

    public boolean validChoice(int choice){
        if(choice < 0 || choice > myCards.size() - 1){
            System.out.println("Invalid choice");
            return false;
        }
        return true;

    }

    public boolean validCard(UnoCard u, UnoCard fromTable){
        if(u.getColor() == fromTable.getColor()){
            return true;
        }

        if(fromTable instanceof NormalCard){
            if(u instanceof SpecialCard && ((SpecialCard)u).getColor().equals(Color.WILD)){
                return true;
            }

            if(u instanceof NormalCard && ((NormalCard)u).getFaceValue() == ((NormalCard)fromTable).getFaceValue()){
                return true;
            }   
        }

        if(fromTable instanceof SpecialCard){
            if(u instanceof SpecialCard && ((SpecialCard)u).getColor().equals(Color.WILD)){
                return true;
            }

            if(u instanceof SpecialCard && ((SpecialCard)u).getType().equals(((SpecialCard)fromTable).getType())){
                return true;
            }
        }

        return false;
    }

    public boolean hasThrowableCard(UnoCard fromTable){
        for(UnoCard u : myCards){
            if(validCard(u, fromTable)){
                return true;
            }
        }
        return false;
    }
}

