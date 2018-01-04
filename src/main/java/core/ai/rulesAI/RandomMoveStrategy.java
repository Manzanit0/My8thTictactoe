package core.ai.rulesAI;

import core.board.Board;
import core.board.Tile;
import core.players.Player;

/**
 * Strategy to mark the first available tile in the board.
 * //TODO - It serves its purpose, but it's not really random.
 */
public class RandomMoveStrategy extends MoveStrategy{
    @Override
    public boolean checkTile(Board board, Player player) {
        Tile[] availableTiles = board.getAvailableTiles();

        for (Tile availableTile : availableTiles){
            availableTile.check(player);
            return true;
        }

        return false;
    }
}
