import java.util.List;
import Cards.*;
import Participants.*;
public class Tester {
    public static void main(String[] args){
        User p1 = new User("lebron");
        Cpu p2 = new Cpu("Jimmy");
        Player p3 = new User("Curry");
        Cpu p4 = new Cpu("Luka");

        RotationSetup set = new RotationSetup();
        set.setupPlayers(List.of(p1, p2, p3, p4));

        UnoGame unoGame = new UnoGame(set.getFirstPlayer());
        unoGame.testStart();
        
    }
}
