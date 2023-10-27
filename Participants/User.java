package Participants;

import Cards.UnoCard;

public class User extends Player implements ThrowCardStrategery{

    @Override
    public UnoCard throwCard(UnoCard fromTable) {
        UnoCard u = null;
        showMyCards();
        do{
            int index = getInput("Choose card");
            u = myCards.get(index);
        }
        while(!validCard(u, fromTable));
        return u;
    }
}
