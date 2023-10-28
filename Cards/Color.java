package Cards;
public enum Color{
    RED(0),
    GREEN(1),
    BLUE(2),
    YELLOW(3),
    WILD(4);

    private int position;

    Color(int position){
        this.position = position;
    }

    public int getPosition(){
        return position;
    }
}