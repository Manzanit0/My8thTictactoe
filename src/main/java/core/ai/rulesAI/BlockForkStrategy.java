package core.ai.rulesAI;

import core.board.Board;
import core.board.Tile;
import core.players.Player;

import java.util.ArrayList;

/**
 * Strategy to mark an available tile only if it blocks an opponent fork option.
 * def. Fork: opens two different win paths.
 */
public class BlockForkStrategy extends MoveStrategy {
    @Override
    public boolean checkTile(Board board, Player player) {
        Player opponent = getOpponent(board, player);
        if (opponent == null) return false;

        Tile[] availableTiles = board.getAvailableTiles();
        ArrayList<Tile> forkMoves = new ArrayList<Tile>();

        for (Tile availableTile : availableTiles) {
            availableTile.check(opponent);
            Tile[] futureAvailableTiles = board.getAvailableTiles();

            for (Tile futureAvailableTile : futureAvailableTiles) {
                futureAvailableTile.check(opponent);

                if (isWinningMove(board, availableTile)) {
                    forkMoves.add(availableTile);
                }

                futureAvailableTile.uncheck();
            }

            availableTile.uncheck();
        }

        // This covers the scenario in which the opponent will have the option to fork in two different places
        // and if the machine tries to block any of those forks, the opponent will effectively block his win
        // and at the same time create a new fork.
        if (forkMoves.size() == 1) {
            forkMoves.get(0).check(player);
            return true;
        } else if (forkMoves.size() > 1) {
            for (Tile tile : availableTiles) {
                if (!forkMoves.contains(tile)) {
                    tile.check(player);
                    return true;
                }
            }
        }

        return false;
    }
}
