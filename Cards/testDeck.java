package Cards;

public class testDeck {
    public static void main(String[] args){
        UnoDeck u = new UnoDeck();
        System.out.println("UNSHUFFLED...");
        u.displayDeck();
        System.out.println("total cards: " + u.cardCount());
        System.out.println("\n\n\nSHUFFLED...");
        u.shuffeDeck();
        u.displayDeck();
        System.out.println("total cards: " + u.cardCount());
    }
}
