package UnoGameFiles.Cards;
import UnoGameFiles.Cards.CardAttributes.Color;

public class NormalCard extends UnoCard{
    private static final int ANY_FACE_VALUE = 99;
    private final int faceValue;

    public NormalCard(Color c, int v){
        setColor(c);
        faceValue = v;
    }

    public int getFaceValue(){
        return faceValue;
    }

    @Override
    public String toString(){
        if(faceValue == ANY_FACE_VALUE)
            return getColor() + " ANY";
        else
            return getColor() + " " + faceValue;
    }
}
