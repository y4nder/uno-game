package Participants;

import Cards.UnoCard;

public class User extends Player{
    public User(String playerName){
        this.playerName = playerName;
    }
    @Override
    public UnoCard throwCard(UnoCard fromTable) {
        boolean validity = false;
        if(!hasThrowableCard(fromTable)){
            return null;
        }
        UnoCard u = null;
        showMyCards();
        do{
            int index = getInput("  Choose card: ");
            u = myCards.get(index);
            if(!validCard(u, fromTable)){
                System.out.println("    Cannot throw this card");
                validity = false;
            }
            else{
                myCards.remove(index);
                validity = true;
            }
        }
        while(validity == false);
        return u;
    }
}
