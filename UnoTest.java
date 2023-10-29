import java.util.List;
import Participants.*;
public class UnoTest {
    public static void main(String[] args){
        Player p1 = new Cpu("Russell");
        Player p2 = new Cpu("Bianca");
        Player p3 = new Cpu("Eren");
        Player p4 = new Cpu("Red");
        Player p5 = new Cpu("Yams");
        Player p6 = new Cpu("Wenz");
        Player p7 = new Cpu("Aaron");
        Player p8 = new Cpu("Herv");
        Player p9 = new Cpu("Mirai");
        Player p10 = new Cpu("Micah");
        Player p11 = new Cpu("Eishi");
        Player p12 = new Cpu("Effie");
        Player p13 = new Cpu("Changi");
        Player p14 = new Cpu("Kobe");
        Player p15 = new Cpu("Maple");


        RotationSetup set = new RotationSetup();
        set.setupPlayers(List.of(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15));

        UnoGame unoGame = new UnoGame(set.getFirstPlayer());
        unoGame.testStart();
        
    }
}
