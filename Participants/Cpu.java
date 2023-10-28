package Participants;

import Cards.UnoCard;

public class Cpu extends Player{
    public Cpu(String playerName){
        this.playerName = playerName;
    }
    @Override
    public UnoCard throwCard(UnoCard fromTable) {
        UnoCard toThrow = null;
        for(UnoCard u : myCards){
            if(validCard(u, fromTable)){
                toThrow = u;
                myCards.remove(toThrow);
                break;
            }
        }
        return toThrow;
    }
    
}
