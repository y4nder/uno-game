package Participants;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Random;

import Cards.Color;
import Cards.NormalCard;
import Cards.SpecialCard;
import Cards.SpecialType;
import Cards.UnoCard;

public class UnoDeck {
    private Deque<UnoCard> UnoDeck = new ArrayDeque<>();

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

        UnoDeck.addAll(redFaceValueCards);
        UnoDeck.addAll(greenFaceValueCards);
        UnoDeck.addAll(blueFaceValueCards);
        UnoDeck.addAll(yellowFaceValueCards);
        UnoDeck.addAll(skips);
        UnoDeck.addAll(reverse);
        UnoDeck.addAll(draw2s);
        UnoDeck.addAll(wild4);
    }

    public void displayDeck(){
        for(UnoCard u : UnoDeck){
            u.showCard();
            System.out.println();
        }
    }

    public void shuffeDeck(){
        Random random = new Random();
        UnoCard[] templist = UnoDeck.toArray(new UnoCard[UnoDeck.size()]);
        int n = templist.length;

        for(int i = n-1 ; i > 0; i--){
            int j = random.nextInt(i + 1);

            UnoCard temp = templist[i];
            templist[i] = templist[j];
            templist[j] = temp;
        }
        
        this.UnoDeck = new ArrayDeque<>();

        for(UnoCard u : templist){
            this.UnoDeck.add(u);
        }
    }

    public int cardCount(){
        return UnoDeck.size();
    }

    public List<UnoCard> giveCards(int howMany){
        List<UnoCard> l = new ArrayList<>();
        for(int i = 0; i < howMany; i++){
            l.add(UnoDeck.pollFirst());
        }
        return l;
    }

    public void retrieveCards(Deque<UnoCard> u){
        UnoDeck.addAll(u);
    }

    //helper methods
    private boolean isEmpty(){
        return UnoDeck.size() == 0;
    }

    private boolean notEnoughCards(int howMany){
        return UnoDeck.size() < howMany;
    }
}
