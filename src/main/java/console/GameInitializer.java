package console;

import core.Game;
import core.ai.RulesAI;
import core.players.Computer;
import core.players.Human;
import core.players.Player;

import java.util.Scanner;

public class GameInitializer {
    private Scanner input = new Scanner(System.in); // TODO - this needs to be closed at some point.

    private Player player1;
    private Player player2;

    // TODO -  this doesn't really initialize the game... it's just the wizard.
    public Game initGame() {
        boolean pendingConfiguration = true;

        do {
            printMenu();
            int option = input.nextInt();

            switch (option) {
                case 1: {
                    player1 = configurePlayer();
                    break;
                }
                case 2: {
                    player2 = configurePlayer();
                    break;
                }
                case 3: {
                    if(player1 == null || player2 == null) {
                        System.out.println("You must first configure both players!");
                    }
                    else {
                        pendingConfiguration = false;
                    }
                    break;
                }
                default: {
                    System.out.println("You must select option 1, 2 or 3!");
                }
            }

            // TODO: this is in a function in Application.java => encapsulate it.
            System.out.print("\033[H\033[2J");
            System.out.flush();
        } while(pendingConfiguration);

        return new Game(player1, player2);
    }

    private void printMenu(){
        System.out.println("#########################");
        System.out.println("##  The 8th Tictactoe  ##");
        System.out.println("#########################\n");
        System.out.println("1. Configure player 1");
        System.out.println("2. Configure player 2");
        System.out.println("3. Start game!\n");
        System.out.print("Select an option: ");
    }

    private Player configurePlayer() {
        Player player;
        System.out.println("Will the player be computer-controlled?");
        String isComputer = input.next();

        System.out.println("What's the player's symbol?"); // TODO - verify that the symbol is different to the other player.
        String symbol = input.next();

        if(isComputer.equalsIgnoreCase("yes")) { // TODO - The user doesn't know if he must input yes, true, 1... UX pls.
            player = new Computer(symbol, new RulesAI());
        }
        else {
            player = new ConsolePlayer(symbol);
        }

        return player;
    }
}
