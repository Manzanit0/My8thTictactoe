package core.ai.rulesAI;

import core.board.Board;
import core.board.Tile;
import core.players.Player;

/**
 * Strategy to mark an available tile only if it secures a win.
 */
public class WinStrategy extends MoveStrategy {
    @Override
    public boolean checkTile(Board board, Player player) {
        Tile[] availableTiles = board.getAvailableTiles();

        for (Tile availableTile : availableTiles) {
            availableTile.check(player);
            if (isWinningMove(board, availableTile)) {
                return true;
            }

            availableTile.uncheck();
        }

        return false;
    }
}
