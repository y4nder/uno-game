package UnoGameFiles.Misc;

import UnoGameFiles.UnoGameEntities.Player;

public class RightRotation implements RotationState{

    @Override
    public Player rotate(Player currentPlayer) {
        return currentPlayer.getPlayerRight();
    }    
}
