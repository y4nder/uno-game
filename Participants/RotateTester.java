package Participants;

import java.util.List;

public class RotateTester {
    public static void main(String[] args){
        User p1 = new User("lebron");
        Cpu p2 = new Cpu("Jimmy");
        Player p3 = new User("Curry");
        Cpu p4 = new Cpu("Luka");

        RotationSetup set = new RotationSetup();
        set.setupPlayers(List.of(p1, p2, p3, p4));

        UnoGame unoGame = new UnoGame(set.getFirstPlayer());
        unoGame.testStart();
        unoGame.showAllPlayerCards();
    }
}
