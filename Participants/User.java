package Participants;

import Cards.UnoCard;

public class User extends Player implements ThrowCardStrategery{

    @Override
    public UnoCard throwCard(UnoCard fromTable) {
        UnoCard u = null;
        showMyCards();
        int index = getInput("Choose card");
        u = myCards.get(index);
        return u;
    }
}
