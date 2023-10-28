package Cards;

public class NormalCard extends UnoCard{
    private int faceValue;

    public NormalCard(Color c, int v){
        color = c;
        faceValue = v;
    }

    public int getFaceValue(){
        return faceValue;
    }

    public void showCard(){
        super.showCard();
        if(faceValue == 99)
            System.out.print(" ANY");
        else
            System.out.print(" " + faceValue);
    }
}
