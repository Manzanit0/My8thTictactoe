package core.ai;

import core.board.Board;
import core.players.Player;

public interface ComputerAI {
    public void makeBestMove(Board board, Player player);

    //TODO: maybe minmax?
    // https://www.ntu.edu.sg/home/ehchua/programming/java/JavaGame_TicTacToe_AI.html
    // http://www.letscodepro.com/tic-tac-toe-with-ai-minmax-algorithm-in-java/

}
