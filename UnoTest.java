import java.util.ArrayList;
import java.util.List;
import UnoGameFiles.*;
import UnoGameFiles.UnoGameEntities.*;
public class UnoTest {
    public static void main(String[] args){
        //max players 10
        //min players 2
        List<Player> allPlayers = new ArrayList<>();
        allPlayers.add(new Cpu("Russel"));
        allPlayers.add(new Cpu("Bianca"));
        Player p1 = new Cpu("Russell");
        Player p2 = new Cpu("Bianca");
        Player p3 = new Cpu("Eren");
        Player p4 = new Cpu("Red");
        Player p5 = new Cpu("Yams");
        Player p6 = new Cpu("Wenz");
        Player p7 = new Cpu("Leander");
        Player p8 = new Cpu("Herv");
        Player p9 = new Cpu("Mirai");
        Player p10 = new Cpu("Micah");

        UnoGame unoGame = new UnoGame(allPlayers);
        unoGame.startUnoGame();
    }
}
