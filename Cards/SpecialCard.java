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
}
