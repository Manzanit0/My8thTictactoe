
import core.Game;
import core.ai.RulesAI;
import core.board.Board;
import core.exceptions.TicTacToeException;
import core.players.Computer;
import core.players.Human;
import core.players.Player;
import org.apache.commons.cli.*;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsoleApp {
    /**
     * Color constants to display the player symbols with different colours.
     */
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";

    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        Game game;

        // Initialize the game with its players and symbols from the CLI.
        try{
            game = initGame(args);
        }
        catch (ParseException ex){
            System.out.println("An error has been found with the arguments: " + ex.getMessage());
            return;
        }

        // Define the initial player. If an exception arises, Player 1 is set as default.
        System.out.print("Choose starting player: ");
        String symbol = input.nextLine();

        try {
            game.chooseInitialPlayer(symbol);
        }
        catch (TicTacToeException ex){
            System.out.println(ex.getMessage());
        }

        // Initial board display.
        printGameBoard(game.getBoard());

        do {
            try {

                // Make the next move, be it human or computer.
                if (game.getCurrentPlayer() instanceof Human) {
                    System.out.println("Enter the row and column of the tile you want to check:\n");
                    int[] move = getHumanInput();
                    game.makeMove(move[0], move[1]);

                } else {
                    System.out.println("Please wait - the computer is thinking its next move.");
                    game.makeMove();
                    Thread.sleep(2000); // UX :-)
                }
            }
            catch (Exception ex) {
                System.out.println(ex.getMessage());
                continue;
            }

            // Re-print board after the move.
            clearConsole();
            printGameBoard(game.getBoard());

            // End-game results.
            if(game.hasCurrentPlayerWon()){
                System.out.println("Player " + game.getCurrentPlayer().getSymbol() + " has won!");
                break;
            }
            if(game.isTie()){
                System.out.println("The game is tied!");
                break;
            }

            game.changeTurn();
        } while (!game.isBoardComplete());

        input.close();
    }

    private static Game initGame(String[] args) throws ParseException {
        CommandLineParser parser = new DefaultParser();
        Options cmdOptions = getCmdOptions();
        CommandLine commandLine = parser.parse(cmdOptions, args);

        String p1Marker = commandLine.getOptionValue("p1");
        Player player1 = commandLine.hasOption("p1IsComputer") ?
                new Computer(p1Marker, new RulesAI()) :
                new Human(p1Marker);

        String p2Marker = commandLine.getOptionValue("p2");
        Player player2 = commandLine.hasOption("p2IsComputer") ?
                new Computer(p2Marker, new RulesAI()) :
                new Human(p2Marker);

        return new Game(player1, player2);
    }

    private static Options getCmdOptions(){
        Options options = new Options();

        //CLI options.
        options.addRequiredOption("p1", "player1Token", true, "Player1's token/marker.");
        options.addRequiredOption("p2", "player2Token", true, "Player2's token/marker.");
        options.addOption("p1IsComputer", false, "Sets player1 as controlled by the AI.");
        options.addOption("p2IsComputer", false, "Sets player2 as controlled by the AI.");

        return options;
    }

    private static void printGameBoard(Board board){
        String firstPlayer = board.getTile(0,0).getCheck();

        System.out.println(ANSI_RESET + "-------------");
        for(int i = 0; i < board.getRowCount(); i++){
            for(int j = 0; j < board.getColumnCount(); j++){
                System.out.print(ANSI_RESET + "| ");
                if(board.getTile(i, j).getCheck() == firstPlayer){
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

    public final static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static int[] getHumanInput(){
        Scanner scanner = new Scanner(System.in);

        Matcher matcher;

        do{
            String input = scanner.next();

            String patternString = "^([0-9]),([0-9])$"; // NOTE: The number range (1-3) is being validated in the backend.
            Pattern pattern = Pattern.compile(patternString);
            matcher = pattern.matcher(input);

            if(!matcher.matches()){
                System.out.println("Invalid input. Please give the coordinates of the tile you want to check with the format \"row,column\"");
            }

        }while (!matcher.matches());

        return new int[]{ Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)) };
    }
}
