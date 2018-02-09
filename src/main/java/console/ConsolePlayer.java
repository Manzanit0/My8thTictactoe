package console;

import core.board.Board;
import core.exceptions.TicTacToeException;
import core.players.Player;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsolePlayer extends Player {
    public ConsolePlayer(String symbol) {
        super(symbol);
    }

    public void makeMove(Board board) throws TicTacToeException {
        System.out.println("Enter the row and column of the tile you want to check:\n");
        int[] move = getInput();

        if (move[0] > 3 || move[0] < 1 || move[1] > 3 || move[1] < 1) {
            throw new TicTacToeException("The row and column don't correspond to any coordinates in the board.");
        }

        board.checkTile(move[0] - 1, move[1] - 1, this);
    }

    private static int[] getInput() {
        Scanner scanner = new Scanner(System.in);

        Matcher matcher;

        do {
            String input = scanner.next();

            String patternString = "^([0-9]),([0-9])$"; // NOTE: The number range (1-3) is being validated in the backend.
            Pattern pattern = Pattern.compile(patternString);
            matcher = pattern.matcher(input);

            if (!matcher.matches()) {
                System.out.println("Invalid input. Please give the coordinates of the tile you want to check with the format \"row,column\"");
            }

        } while (!matcher.matches());

        return new int[]{Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2))};
    }
}
