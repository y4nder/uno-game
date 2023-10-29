package UnoGameFiles.UnoGameEntities;

import java.util.ArrayDeque;
import java.util.Deque;

import UnoGameFiles.Cards.UnoCard;

public class Table {
    private Deque<UnoCard> tableDeck = new ArrayDeque<>();
    
    public void addToTableDeck(UnoCard u){
        tableDeck.push(u);
    }

    public UnoCard getTopCard(){
        return tableDeck.peekFirst();
    }

    public Deque<UnoCard> returnCards(){
        Deque<UnoCard> toReturn = new ArrayDeque<>();
        toReturn.addAll(tableDeck);
        tableDeck = new ArrayDeque<>();
        tableDeck.add(toReturn.pollFirst());
        return toReturn;
    }

    public void showTable(){
        System.out.println(tableDeck);
    }

    public Deque<UnoCard> getTableDeck(){
        return tableDeck;
    }
}
