import java.util.List;
import Participants.*;
public class UnoTest {
    public static void main(String[] args){
        User p1 = new User("Lebron");
        Cpu p2 = new Cpu("Jimmy");
        Cpu p3 = new Cpu("Curry");
        Cpu p4 = new Cpu("Giannis");
        Cpu p5 = new Cpu("Harden");

        RotationSetup set = new RotationSetup();
        set.setupPlayers(List.of(p1, p2, p3, p4, p5));

        UnoGame unoGame = new UnoGame(set.getFirstPlayer());
        unoGame.testStart();
        
    }
}
