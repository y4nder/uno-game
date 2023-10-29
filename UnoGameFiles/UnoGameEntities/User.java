package UnoGameFiles.UnoGameEntities;

import UnoGameFiles.Cards.UnoCard;
import UnoGameFiles.Cards.CardAttributes.Color;

public class User extends Player{

    public User(String playerName) {
        super(playerName);
    }

    @Override
    public UnoCard throwCard(UnoCard fromTable) {
        boolean validity = false;
        UnoCard u = null;
        showMyCards();
        do{
            int index = getInput("  Choose card: ");
            if(index == 'x') return null;
            u = myCards.get(index);

            if(!isValid(u, fromTable)){
                System.out.println("    Cannot throw this card");
                validity = false;
            }
            else{
                myCards.remove(index);
                validity = true;
            }
            
        }while(validity == false);
        return u;
    }

    @Override
    public Color chooseColor() {
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
        while(choice < 0 && choice > 3);

        for(Color c: Color.values()){
            if(c.ordinal() == choice){
                System.out.println(playerName + " chose the color " + c);
                return c;
            }
        }

        return null;
    }
}
