package Cards;

abstract class UnoCard {
    protected Color color;

    public Color getColor(){
        return color;
    }

    public void showCard(){
        System.out.print(color);
    }
}
