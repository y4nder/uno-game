package UnoGameFiles.Misc;

import UnoGameFiles.Cards.NormalCard;
import UnoGameFiles.Cards.SpecialCard;
import UnoGameFiles.Cards.UnoCard;
import UnoGameFiles.Cards.CardAttributes.Color;

public abstract class CardValidity {
    public boolean isValid(UnoCard u, UnoCard fromTable){
        if(u == null){
            return false;
        }

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
}
