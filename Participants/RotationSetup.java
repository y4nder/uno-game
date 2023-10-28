package Participants;

import java.util.List;

public class RotationSetup {
    private Player head;
    private Player tail;

    public RotationSetup(){
        head = tail = null;
    }

    public void setupPlayers(List<Player> allPlayers){
        head = tail = allPlayers.get(0);                    //set first player as p1
        for(int i = 1; i < allPlayers.size(); i++){
            tail.setPlayerRight(allPlayers.get(i));
            allPlayers.get(i).setPlayerLeft(tail);
            tail = allPlayers.get(i);
            tail.setPlayerRight(null);
        }
        tail.setPlayerRight(head);                          //set last player's left as the first player to rotate
        head.setPlayerLeft(tail);
    }

    public Player getFirstPlayer(){
        return head;
    }
}
