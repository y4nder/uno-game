package Participants;

import Cards.UnoCard;

public class Cpu extends Player implements ThrowCardStrategery{
    @Override
    public UnoCard throwCard(UnoCard fromTable) {
        UnoCard toThrow = null;
        int index = myCards.indexOf(fromTable);
        if(index != -1){
            toThrow = myCards.get(index);
        }
        return toThrow;
    }
    
}
