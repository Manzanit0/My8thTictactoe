package core.ai.rulesAI;

import core.board.Board;
import core.board.Tile;
import core.players.Player;

/**
 * Strategy to mark an available tile if it opens a fork win condition.
 * def. Fork: opens two different win paths.
 */
public class ForkStrategy extends MoveStrategy {
    @Override
    public boolean checkTile(Board board, Player player) {
        Tile[] availableTiles = board.getAvailableTiles();
        int winConditions = 0;

        for (Tile availableTile : availableTiles) {
            availableTile.check(player);
            Tile[] futureAvailableTiles = board.getAvailableTiles();

            for (Tile futureAvailableTile : futureAvailableTiles) {
                futureAvailableTile.check(player);

                if (isWinningMove(board, availableTile)) {
                    winConditions++;
                    if (winConditions > 1) {
                        futureAvailableTile.uncheck();
                        return true;
                    }
                }

                futureAvailableTile.uncheck();
            }

            availableTile.uncheck();
            winConditions = 0;
        }

        return false;
    }
}
