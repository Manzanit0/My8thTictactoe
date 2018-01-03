package core.board;

import core.exceptions.TicTacToeException;
import core.players.Player;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private static final int ROW_COUNT = 3;
    private static final int COLUMN_COUNT = 3;

    private Tile[][] board;

    public Board(){
        board = new Tile[ROW_COUNT][COLUMN_COUNT];

        for(int i = 0; i < ROW_COUNT; i++){
            for(int j = 0; j < COLUMN_COUNT; j++) {
                board[i][j] = new Tile();
            }
        }
    }

    public Tile getTile(int row, int column){
        return board[row][column];
    }

    public void checkTile(int row, int column, Player player) throws TicTacToeException {
        Tile tileToCheck = board[row][column];

        if(tileToCheck.isChecked()){
            throw new TicTacToeException("The chosen tile [" + (row+1) + ", " + (column+1) + "] is already checked.");
        }

        tileToCheck.check(player);
    }

    public Tile[] getAvailableTiles(){
        List<Tile> availableTiles = new ArrayList<Tile>();
        for(int i = 0; i < ROW_COUNT; i++){
            for(int j = 0; j < ROW_COUNT; j++){
                if(!board[i][j].isChecked()){
                    availableTiles.add(board[i][j]);
                }
            }
        }

        return availableTiles.toArray(new Tile[0]); // Just for the sake of returning always arrays.
    }

    public Tile[] getRow(int row){
        return board[row];
    }

    public Tile[] getColumn(int column){
        Tile[] tiles = new Tile[ROW_COUNT];

        for(int row = 0; row < ROW_COUNT; row++){
            tiles[row] = board[row][column];
        }

        return tiles;
    }

    public Tile[] getMainDiagonal(){
        return new Tile[]{ board[0][0], board[1][1], board[2][2] };
    }

    public Tile[] getAntiDiagonal(){
        return new Tile[]{ board[0][2], board[1][1], board[2][0] };
    }

    public int getRowCount(){
        return ROW_COUNT;
    }

    public int getColumnCount(){
        return COLUMN_COUNT;
    }

    public Boolean isBoardComplete(){
        for(int i = 0; i < ROW_COUNT; i++){
            for(int j = 0; j < COLUMN_COUNT; j++){
                Tile tile = board[i][j];
                if(!tile.isChecked()){
                    return false;
                }
            }
        }
        return true;
    }

    public Boolean hasWon(Player player){
        Tile[] diagonal = getMainDiagonal();
        Tile[] antiDiagonal = getAntiDiagonal();
        if(areCheckedBySamePlayer(diagonal,player) || areCheckedBySamePlayer(antiDiagonal, player)){
            return true;
        }

        for(int i = 0; i <= 2; i++){
            Tile[] row = getRow(i);
            Tile[] column = getColumn(i);
            if(areCheckedBySamePlayer(row, player) || areCheckedBySamePlayer(column, player)){
                return true;
            }
        }

        return false;
    }

    /**
     * Util method to re-use the logic. //TODO: does it really belong here?
     * @param tiles tiles to check
     * @return Boolean
     */
    private static Boolean areCheckedBySamePlayer(Tile[] tiles, Player player){
        String marker = player.getSymbol();
        return marker == tiles[0].getCheck() && tiles[0].getCheck() == tiles[1].getCheck() && tiles[0].getCheck() == tiles[2].getCheck();
    }
}
