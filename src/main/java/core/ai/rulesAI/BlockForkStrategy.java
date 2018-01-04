package core.ai.rulesAI;

import core.board.Board;
import core.board.Tile;
import core.players.Player;

/**
 * Strategy to mark an available tile only if it blocks an opponent fork option.
 * def. Fork: opens two different win paths.
 */
public class BlockForkStrategy extends MoveStrategy {
    @Override
    public boolean checkTile(Board board, Player player) {
        Player opponent = getOpponent(board, player);
        if(opponent == null) return false;

        Tile[] availableTiles = board.getAvailableTiles();
        int winConditions = 0;

        for (Tile availableTile : availableTiles){
            availableTile.check(opponent);
            Tile[] futureAvailableTiles = board.getAvailableTiles();

            for(Tile futureAvailableTile : futureAvailableTiles){
                futureAvailableTile.check(opponent);

                if(isWinningMove(board, availableTile)){
                    winConditions++;
                    if(winConditions > 1){
                        futureAvailableTile.uncheck();
                        availableTile.check(player);
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
