package core.players;

import core.board.Board;
import core.exceptions.TicTacToeException;

public abstract class Player {
    private String symbol;

    public Player(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public abstract void makeMove(Board board) throws TicTacToeException;

    @Override
    public boolean equals(Object player) {
        return this.symbol == ((Player) player).getSymbol();
    }
}
