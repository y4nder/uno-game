package UnoGameFiles.UnoGameEntities;

import java.util.Random;

import UnoGameFiles.Cards.UnoCard;
import UnoGameFiles.Cards.CardAttributes.Color;

public class Cpu extends Player{

    public Cpu(String playerName) {
        super(playerName);
    }

    @Override
    public UnoCard throwCard(UnoCard fromTable) {
        UnoCard toThrow = null;
        for(UnoCard u : myCards){
            if(isValid(u, fromTable)){
                toThrow = u;
                myCards.remove(toThrow);
                break;
            }
        }
        return toThrow;
    }

    @Override
    public Color chooseColor() {
        int choice;
        Random ran = new Random();
        choice = ran.nextInt(4);
        for(Color c : Color.values()){
            if(c.ordinal()  == choice){
                System.out.println(playerName + " chose the color " + c);
                return c;
            }
        }
        return null;
    }
    
}
