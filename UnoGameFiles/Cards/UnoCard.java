package UnoGameFiles.Cards;

public abstract class UnoCard {
    protected Color color;

    public Color getColor(){
        return color;
    }

    public String showCard(){
        return color.toString();
    }
}
