package core;

import core.board.Board;
import core.exceptions.TicTacToeException;
import core.players.Human;
import core.players.Player;

public class Game {
    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;

    public Game(Player player1, Player player2){
        this.player1 = player1;
        this.player2 = player2;

        // Default - player 1 goes first unless changed later.
        chooseInitialPlayer(player1);

        board = new Board();
    }

    public void chooseInitialPlayer(Player player){
        currentPlayer = player;
    }

    public void chooseInitialPlayer(String symbol) throws TicTacToeException {
        if(player1.getSymbol().equals(symbol)){
            chooseInitialPlayer(player1);
        }
        else if(player2.getSymbol().equals(symbol)){
            chooseInitialPlayer(player2);
        }
        else{
            throw new TicTacToeException("The input symbol doesn't correspond to any current player."
                    + "\n Player 1 is set as the default starting player.");
        }
    }

    public void changeTurn(){
        if(currentPlayer == player1){
            currentPlayer = player2;
        }
        else{
            currentPlayer = player1;
        }
    }

    public void makeMove(int row, int column) throws TicTacToeException {
        if(row > 3 || row < 1 || column > 3 || column < 1){
            throw new TicTacToeException("The row and column don't correspond to any coordinates in the board.");
        }

        ((Human) currentPlayer).setMoveInput(row-1, column-1);
        currentPlayer.makeMove(board);
    }

    public void makeMove() throws TicTacToeException {
        currentPlayer.makeMove(board);
    }

    public Boolean isBoardComplete(){
        return board.isBoardComplete();
    }

    public Boolean hasCurrentPlayerWon(){
        return board.hasWon(currentPlayer);
    }

    public Boolean isTie(){
        return isBoardComplete() && !hasCurrentPlayerWon();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player[] getPlayers(){
        return new Player[]{ player1, player2 };
    }

    public Board getBoard() {
        return board;
    }
}
