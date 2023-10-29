package UnoGameFiles.Misc;

import UnoGameFiles.UnoGameEntities.Player;

public class RotateRight implements Rotation{

    @Override
    public Player rotate(Player currentPlayer) {
        return currentPlayer.getPlayerRight();
    }    
}
