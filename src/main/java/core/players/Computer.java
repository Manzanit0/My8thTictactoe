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

        // This is for UX purposes. Nonetheless, the exception should never be thrown due to the lack of concurrency.
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
