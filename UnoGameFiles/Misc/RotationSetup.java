package UnoGameFiles.Misc;

import java.util.List;

import UnoGameFiles.UnoGameEntities.Player;

public class RotationSetup {
    private Player firstPlayer;
    private Player lastPlayer;

    public RotationSetup(){
        firstPlayer = lastPlayer = null;
    }

    public void setTheRotationOf(List<Player> allPlayers){
        if(allPlayers == null) return;

        firstPlayer = lastPlayer = allPlayers.get(0);
        allPlayers.get(0).setPlayerName("p" + 1 + " " + allPlayers.get(0).getPlayerName());                   //set first player as p1
        
        for(int i = 1; i < allPlayers.size(); i++){
            lastPlayer.setPlayerLeft(allPlayers.get(i));
            allPlayers.get(i).setPlayerRight(lastPlayer);
            lastPlayer = allPlayers.get(i);
            lastPlayer.setPlayerName("p" + (i+1) + " " + lastPlayer.getPlayerName()); 
        }

        lastPlayer.setPlayerLeft(firstPlayer);                          //set last player's left as the first player to rotate
        firstPlayer.setPlayerRight(lastPlayer);
    }

    public Player getFirstPlayer(){
        return firstPlayer;
    }
}
