package console;

import core.Game;
import core.board.Board;
import core.exceptions.TicTacToeException;
import core.players.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Application {
    /**
     * Color constants to display the player symbols with different colours.
     */
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RESET = "\u001B[0m";
    private static Map<Player, String> colorConfig;

    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        GameBuilder gBuilder = new GameBuilder(input);
        Game game = gBuilder.buildGame();

        colorConfig = new HashMap<Player, String>();
        colorConfig.put(game.getPlayers()[0], ANSI_GREEN);
        colorConfig.put(game.getPlayers()[1], ANSI_RED);

        printGameBoard(game.getBoard());

        do {
            try {
                game.makeMove();
            }
            catch (Exception ex) {
                System.out.println(ex.getMessage());
                continue;
            }

            ConsoleUtils.clearConsole();
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

    private static void printGameBoard(Board board){
        System.out.println(ANSI_RESET + "-------------");
        for(int i = 0; i < board.getRowCount(); i++){
            for(int j = 0; j < board.getColumnCount(); j++){
                System.out.print(ANSI_RESET + "| ");
                Player currentPlayer = board.getTile(i, j).getCheckingPlayer();
                String color = currentPlayer == null ? ANSI_RESET : colorConfig.get(currentPlayer);
                System.out.print(color + board.getTile(i, j).getCheck());
                System.out.print(" ");
            }

            System.out.print(ANSI_RESET + "|\n");
            System.out.println("-------------");
        }
    }
}
