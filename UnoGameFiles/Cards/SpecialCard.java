package UnoGameFiles.Cards;
import UnoGameFiles.Cards.CardAttributes.*;

public class SpecialCard extends UnoCard{
    private SpecialType type;
    
    public SpecialCard(Color c, SpecialType t){
        setColor(c);
        type = t;
    }
    
    public SpecialType getType(){
        return type;
    }

    public void setType(SpecialType type) {
        this.type = type;
    }

    @Override
    public String toString(){
        if(color == Color.WILD){
            return "WILD " + type; 
        }
        return getColor() + " " + type;
    }
}
