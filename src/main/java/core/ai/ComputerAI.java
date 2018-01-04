package core.ai;

import core.board.Board;
import core.players.Player;

public interface ComputerAI {
    public void makeBestMove(Board board, Player player);
}
