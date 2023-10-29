package UnoGameFiles.Cards;

public class NormalCard extends UnoCard{
    private int faceValue;

    public NormalCard(Color c, int v){
        color = c;
        faceValue = v;
    }

    public int getFaceValue(){
        return faceValue;
    }

    public String showCard(){
        if(faceValue == 99)
            return super.showCard() + " ANY";
        else
            return super.showCard() + " " + faceValue;
    }
}
