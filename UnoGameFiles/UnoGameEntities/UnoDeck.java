package UnoGameFiles.UnoGameEntities;

import java.util.*;
import UnoGameFiles.Cards.*;
import UnoGameFiles.Cards.CardAttributes.*;

public class UnoDeck {
    private Deque<UnoCard> unoDeck = new ArrayDeque<>();

    public UnoDeck(){

        /*
         * all card counts were based from the official uno website
         * https://www.unovariations.com/official-uno-rules#:~:text=A%20standard%20deck%20of%20Uno,blue%2C%20green%2C%20and%20yellow)
        */

        //red
        UnoCard r0 = new NormalCard(Color.RED, 0);
        List<UnoCard> redFaceValueCards = new ArrayList<>();
        redFaceValueCards.add(r0);
        for(int j = 0; j < 2; j++){
            for(int i = 1; i <= 9; i++){
                redFaceValueCards.add(new NormalCard(Color.RED, i));
            }
        }
    
        //blue
        NormalCard b0 = new NormalCard(Color.BLUE, 0);
        List<UnoCard> blueFaceValueCards = new ArrayList<>();
        blueFaceValueCards.add(b0);
        for(int i = 0; i < 2; i++){
            for(int j = 1; j <= 9; j++){
                blueFaceValueCards.add(new NormalCard(Color.BLUE, j));
            }
        }
        //green
        NormalCard g0 = new NormalCard(Color.GREEN, 0);
        List<UnoCard> greenFaceValueCards = new ArrayList<>();
        greenFaceValueCards.add(g0);
        for(int i = 0; i < 2; i++){
            for(int j = 1; j <= 9; j++){
                greenFaceValueCards.add(new NormalCard(Color.GREEN, j));
            }
        }
        
        //yellow
        NormalCard y0 = new NormalCard(Color.YELLOW, 0);
        List<UnoCard> yellowFaceValueCards = new ArrayList<>();
        yellowFaceValueCards.add(y0);
        for(int i = 0; i < 2; i++){
            for(int j = 1; j <= 9; j++){
                yellowFaceValueCards.add(new NormalCard(Color.YELLOW, j));
            }
        }
    
        //special cards
        //skip
        List<UnoCard> skips = new ArrayList<>();
        for(Color c : Color.values()){
            for(int i = 0; i < 2; i++){
                if(c.equals(Color.WILD)) continue;
                skips.add(new SpecialCard(c, SpecialType.SKIP));
            }
        }
        
        //draw 2
        List<UnoCard> draw2s = new ArrayList<>();
        for(Color c : Color.values()){
            for(int i = 0; i < 2; i++){
                if(c.equals(Color.WILD)) continue;
                draw2s.add(new SpecialCard(c, SpecialType.DRAWTWO));
            }
        }
        
        //reverse
        List<SpecialCard> reverse = new ArrayList<>();
        for(Color c: Color.values()){
            for(int i = 0; i < 2; i++){
                if(c.equals(Color.WILD)) continue;
                reverse.add(new SpecialCard(c, SpecialType.REVERSE));
            }
        }
        
        //draw 4
        List<SpecialCard> wild4 = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            wild4.add(new SpecialCard(Color.WILD, SpecialType.DRAWFOUR));
        }

        unoDeck.addAll(redFaceValueCards);
        unoDeck.addAll(greenFaceValueCards);
        unoDeck.addAll(blueFaceValueCards);
        unoDeck.addAll(yellowFaceValueCards);
        unoDeck.addAll(skips);
        unoDeck.addAll(reverse);
        unoDeck.addAll(draw2s);
        unoDeck.addAll(wild4);
    }

    public void displayDeck(){
        for(UnoCard u : unoDeck){
            System.out.println(u.toString());
            System.out.println();
        }
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
}
