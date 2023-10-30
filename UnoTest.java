import java.util.List;
import UnoGameFiles.*;
import UnoGameFiles.UnoGameEntities.*;
public class UnoTest {
    public static void main(String[] args){
        //max players 10
        //min players 2
        Player p1 = new User("Russell");
        Player p2 = new Cpu("Bianca");
        Player p3 = new Cpu("Eren");
        Player p4 = new Cpu("Red");
        Player p5 = new Cpu("Yams");
        Player p6 = new Cpu("Wenz");
        Player p7 = new Cpu("Aaron");
        Player p8 = new Cpu("Herv");
        Player p9 = new Cpu("Mirai");
        Player p10 = new Cpu("Micah");

        UnoGame unoGame = new UnoGame(List.of(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10));
        unoGame.startUnoGame();
    }
}
