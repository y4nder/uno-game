package UnoGameFiles.UnoGameEntities;

import java.util.*;
import UnoGameFiles.Cards.*;
import UnoGameFiles.Cards.CardAttributes.*;
import UnoGameFiles.Misc.CardAdder;
import UnoGameFiles.Misc.CardAdder.AddNormalCard;
import UnoGameFiles.Misc.CardAdder.AddSpecialCard;

public class UnoDeck {
    private Deque<UnoCard> unoDeck;
    private CardAdder cAdder;

    private static final int ONE_COPY = 1;
    private static final int TWO_COPIES = 2;
    private static final int FOUR_COPIES = 4;
    private static final int ZERO = 0;
    private static final int NINE = 9;
    private static final int ONE = 1;

    public UnoDeck(){

        /*
         * all card counts were based from the official uno website
         * https://www.unovariations.com/official-uno-rules#:~:text=A%20standard%20deck%20of%20Uno,blue%2C%20green%2C%20and%20yellow)
        */

        this.unoDeck = new ArrayDeque<>();
        generateAllCards();
    }
    
    private void generateAllCards(){
        EnumSet<Color> colors = EnumSet.complementOf(EnumSet.of(Color.WILD));
        EnumSet<SpecialType> specialTypes = EnumSet.complementOf(EnumSet.of(SpecialType.DRAWFOUR));
        for(Color ofColored : colors){

            this.cAdder = new AddNormalCard();
            cAdder.addTo(unoDeck, ONE_COPY, ofColored, ZERO);                   
            for(int faceValue = ONE; faceValue <= NINE; faceValue++){
                cAdder.addTo(unoDeck, TWO_COPIES, ofColored, faceValue);        
            }
            
            this.cAdder = new AddSpecialCard();
            for(SpecialType specialType : specialTypes){  
                cAdder.addTo(unoDeck, TWO_COPIES, ofColored, specialType);                
            }
        }
        cAdder.addTo(unoDeck, FOUR_COPIES, Color.WILD, SpecialType.DRAWFOUR);
    }
    
    public List<String> displayDeck(){
        List<String> deck = new ArrayList<>();
        for(UnoCard u : unoDeck){
            deck.add(u.toString() + "\n");
        }
        return deck;
    }

    public void shuffeDeck(){
        List<UnoCard> tempList = new ArrayList<>(unoDeck);
        Collections.shuffle(tempList);
        unoDeck = new ArrayDeque<>(tempList);
    }

    public int cardCount(){
        return unoDeck.size();
    }

    public void giveCards(int howMany, Player player){
        if(isEmpty()) {
            return;
        }

        if(notEnoughCards(howMany)){
            return;
        }

        List<UnoCard> l = new ArrayList<>();
        for(int i = 0; i < howMany; i++){
            l.add(unoDeck.pollFirst());
        }
        player.drawCard(l);
    }

    public void retrieveCards(Deque<UnoCard> u){
        unoDeck.addAll(u);
        shuffeDeck();
    }

    public UnoCard drawOne(){
        return unoDeck.pollFirst();
    }


    //helper methods
    private boolean isEmpty(){
        return unoDeck.size() == 0;
    }

    public boolean notEnoughCards(int howMany){
        return unoDeck.size() < howMany;
    }

    // public static void main(String[] args) {
    //     UnoDeck u = new UnoDeck();
    //     System.out.println(u.displayDeck());
    //     System.out.println(u.cardCount());
    // }
}
