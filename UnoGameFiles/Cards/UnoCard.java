package UnoGameFiles.Cards;
import UnoGameFiles.Cards.CardAttributes.Color;

public abstract class UnoCard {
    protected Color color;

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor(){
        return color;
    }

    abstract public String toString();
}
