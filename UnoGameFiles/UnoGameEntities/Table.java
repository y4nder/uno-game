package UnoGameFiles.UnoGameEntities;

import java.util.ArrayDeque;
import java.util.Deque;

import UnoGameFiles.Cards.UnoCard;

public class Table {
    private final Deque<UnoCard> tableDeck = new ArrayDeque<>();

    public void addToTableDeck(UnoCard u){
        tableDeck.push(u);
    }

    public UnoCard getTopCard(){
        return tableDeck.peekFirst();
    }

    public Deque<UnoCard> returnCards(){
        Deque<UnoCard> toReturn = new ArrayDeque<>(tableDeck);
        tableDeck.clear();
        tableDeck.add(toReturn.pollFirst());
        return toReturn;
    }

    public String showTable(){
        return tableDeck.toString();
    }

    public Deque<UnoCard> getTableDeck(){
        return tableDeck;
    }
}
