package UnoGameFiles.Misc;

import UnoGameFiles.UnoGameEntities.Player;

public class RotateLeft implements Rotation{

    @Override
    public Player rotate(Player currentPlayer) {
        return currentPlayer.getPlayerLeft();
    }
}
