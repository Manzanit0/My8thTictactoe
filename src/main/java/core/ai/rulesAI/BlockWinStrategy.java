package core.ai.rulesAI;

import core.board.Board;
import core.board.Tile;
import core.exceptions.TicTacToeException;
import core.players.Player;

/**
 * Strategy to mark an available tile only if it blocks an opponent win.
 */
public class BlockWinStrategy extends MoveStrategy {
    @Override
    public boolean checkTile(Board board, Player player) {
        Player opponent = getOpponent(board, player);
        if (opponent == null) return false;

        Tile[] availableTiles = board.getAvailableTiles();
        for (Tile availableTile : availableTiles) {
            availableTile.check(opponent);
            if (isWinningMove(board, availableTile)) {
                availableTile.check(player);
                return true;
            }
            availableTile.uncheck();
        }

        return false;
    }
}
