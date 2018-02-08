package console;

import core.Game;
import core.board.Board;
import core.exceptions.TicTacToeException;

import java.util.Scanner;

public class Application {
    /**
     * Color constants to display the player symbols with different colours.
     */
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RESET = "\u001B[0m";

    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        GameBuilder gBuilder = new GameBuilder(input);
        Game game = gBuilder.buildGame();

        printGameBoard(game.getBoard());

        do {
            try {
                game.makeMove();
            }
            catch (Exception ex) {
                System.out.println(ex.getMessage());
                continue;
            }

            clearConsole();
            printGameBoard(game.getBoard());

            // Print end-game results.
            if(isGameEnded(game)) break;

            game.changeTurn();
        } while (!game.isBoardComplete());

        input.close();
    }

    private static boolean isGameEnded(Game game){
        if(game.hasCurrentPlayerWon()){
            System.out.println("Player " + game.getCurrentPlayer().getSymbol() + " has won!");
            return true;
        }
        if(game.isTie()){
            System.out.println("The game is tied!");
            return true;
        }

        return false;
    }

    // FIXME - sometimes the first two symbols painted have the same colour.
    private static void printGameBoard(Board board){
        String firstPlayer = board.getTile(0,0).getCheck();

        System.out.println(ANSI_RESET + "-------------");
        for(int i = 0; i < board.getRowCount(); i++){
            for(int j = 0; j < board.getColumnCount(); j++){
                System.out.print(ANSI_RESET + "| ");
                if(board.getTile(i, j).getCheck().equals(firstPlayer)){
                    System.out.print(ANSI_GREEN + board.getTile(i, j).getCheck());
                }
                else{
                    System.out.print(ANSI_RED + board.getTile(i, j).getCheck());
                }
                System.out.print(" ");
            }
            System.out.print(ANSI_RESET + "|\n");
            System.out.println("-------------");
        }
    }

    private static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
