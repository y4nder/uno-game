package UnoGameFiles.Misc;

import UnoGameFiles.UnoGameEntities.Player;

public class LeftRotation implements RotationState{

    @Override
    public Player rotate(Player currentPlayer) {
        return currentPlayer.getPlayerLeft();
    }
}
