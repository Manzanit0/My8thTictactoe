package core.ai;

import core.board.Board;
import core.board.Tile;
import core.players.Player;

/**
 * Implements a rule-based algorithm to make the best possible move in a Tic-Tac-Toe board.
 */
public class RulesAI implements ComputerAI {

    //TODO: fucking tic tac toe algorithm...

    public void makeBestMove(Board board, Player currentPlayer){
        Tile[] availableTiles = board.getAvailableTiles();

        // If there is a winning move, take it.
        for (Tile availableTile : availableTiles){
            availableTile.check(currentPlayer);
            if(isWinningMove(board, availableTile)) return;
            availableTile.uncheck();
        }

        // If the opponent has a winning move, block it.
        for (Tile availableTile : availableTiles){
            availableTile.check(currentPlayer);
            if(isBlockingOpponentWinningMove(board, availableTile)) return;
            availableTile.uncheck();
        }

        availableTiles[0].check(currentPlayer);

        // If you can create a fork, do it.
        // TODO

        // If your opponent can create a fork, block it.
        // TODO

        // Otherwise just choose a random one.
    }

    private boolean isWinningMove(Board board, Tile tileToCheck){
        Player currentPlayer = tileToCheck.getCheckingPlayer();

        if (board.hasWon(currentPlayer) || board.isBoardComplete()) {
            return true;
        }

        return false;
    }

    private boolean isBlockingOpponentWinningMove(Board board, Tile tileToCheck){
        Player currentPlayer = tileToCheck.getCheckingPlayer();

        // game.changeTurn(); //FIXME - not needed pls
        tileToCheck.check(currentPlayer);

        if (board.hasWon(currentPlayer) || board.isBoardComplete()) {
            tileToCheck.uncheck();
            // game.changeTurn(); //FIXME
            return true;
        }

        tileToCheck.uncheck();
        return false;
    }
}
