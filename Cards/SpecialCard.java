package Cards;

public class SpecialCard extends UnoCard{
    private SpecialType type;

    public SpecialCard(Color c, SpecialType t){
        color = c;
        type = t;
    }

    public SpecialType getType(){
        return type;
    }

    public String showCard(){
        if(color.equals(Color.WILD)){
            return "WILD " + type; 
        }
        return super.showCard() + " " + type;
    }
}
