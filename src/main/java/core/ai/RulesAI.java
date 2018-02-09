package core.ai;

import core.ai.rulesAI.*;
import core.board.Board;
import core.players.Player;

/**
 * Implements a rule-based algorithm to make the best possible move in a Tic-Tac-Toe board.
 * The approach taken is an implementation of the Chain-of-responsibility pattern.
 */
public class RulesAI implements ComputerAI {
    /**
     * Set of rules/strategies to decide the next move in priority order.
     */
    private static final MoveStrategy[] STRATEGIES = {
            new WinStrategy(),
            new BlockWinStrategy(),
            new ForkStrategy(),
            new BlockForkStrategy(),
            new RandomMoveStrategy()
    };

    /**
     * Apply the different tile-checking strategies available in priority order in order to make the first
     * available move.
     *
     * @param board         the board in its current status.
     * @param currentPlayer the player to move next.
     */
    public void makeBestMove(Board board, Player currentPlayer) {
        if (!board.getTile(1, 1).isChecked()) {
            // This would only be "the best move" in a 3x3 board.
            board.getTile(1, 1).check(currentPlayer);
            return;
        }

        for (MoveStrategy strategy : STRATEGIES) {
            if (strategy.checkTile(board, currentPlayer)) {
                break;
            }
        }
    }
}
