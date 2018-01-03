package core.board;

import core.players.Player;

/**
 * Represents a tile of the tic-tac-toe board.
 */
public class Tile {
    /**
     * The player who has checked the tile.
     */
    private Player player;

    public void check(Player player){
        this.player = player;
    }

    public void uncheck(){
        player = null;
    }

    public Boolean isChecked(){
        return player != null;
    }

    public String getCheck(){
        return isChecked() ? player.getSymbol() :  " ";
    }

    public Player getCheckingPlayer() {
        return player;
    }
}
