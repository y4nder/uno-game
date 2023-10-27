package Participants;

import java.util.ArrayDeque;
import java.util.Deque;

import Cards.UnoCard;

public class Table {
    private Deque<UnoCard> tableDeck = new ArrayDeque<>();
    
    public void addToTableDeck(UnoCard u){
        tableDeck.push(u);
    }

    public void showTopCard(){
        System.out.println(tableDeck.peekLast());
    }

    public Deque<UnoCard> returnCards(){
        Deque<UnoCard> toReturn = new ArrayDeque<>();
        toReturn.addAll(tableDeck);
        toReturn.pollFirst();
        return toReturn;
    }
}
