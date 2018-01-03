package core.players;

import core.ai.ComputerAI;
import core.board.Board;

public class Computer extends Player {
    private ComputerAI internalAI;

    public Computer(String symbol, ComputerAI ai){
        super(symbol);
        internalAI = ai;
    }

    @Override
    public void makeMove(Board board){
        internalAI.makeBestMove(board, this);
    }
}
