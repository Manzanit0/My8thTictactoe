package core.ai.rulesAI;

import core.board.Board;
import core.board.Tile;
import core.players.Player;

public abstract class MoveStrategy {
    public abstract boolean checkTile(Board board, Player player);

    protected boolean isWinningMove(Board board, Tile tileToCheck){
        Player currentPlayer = tileToCheck.getCheckingPlayer();

        if (board.hasWon(currentPlayer) || board.isBoardComplete()) {
            return true;
        }

        return false;
    }

    protected Player getOpponent(Board board, Player player){
        for(Player iteratedPlayer : board.getPlayers()){
            if(!iteratedPlayer.equals(player)){
                return iteratedPlayer;
            }
        }

        return null;
    }
}
