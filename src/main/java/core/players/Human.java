package core.players;

import core.board.Board;
import core.exceptions.TicTacToeException;

public class Human extends Player {
    private int inputRow;
    private int inputCol;

    public Human(String symbol) {
        super(symbol);
    }

    @Override
    public void makeMove(Board board) throws TicTacToeException {
        board.checkTile(inputRow, inputCol, this);
    }

    public void setMoveInput(int row, int column) {
        inputRow = row;
        inputCol = column;
    }
}
