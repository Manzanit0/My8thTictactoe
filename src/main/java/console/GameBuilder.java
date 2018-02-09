package console;

import core.Game;
import core.ai.RulesAI;
import core.exceptions.TicTacToeException;
import core.players.Computer;
import core.players.Player;

import java.util.Scanner;

public class GameBuilder {
    private Player player1;
    private Player player2;
    private Scanner input;

    public GameBuilder(Scanner input){
        this.input = input;
    }

    public Game buildGame(){
        startGameConfigMenu();
        Game game = new Game(player1, player2);
        chooseInitialPlayer(game);
        return game;
    }

    private void startGameConfigMenu() {
        boolean pendingConfiguration = true;

        do {
            printMenu();
            int option = input.nextInt();

            switch (option) {
                case 1: {
                    player1 = getPlayerConfiguration();
                    break;
                }
                case 2: {
                    player2 = getPlayerConfiguration();
                    break;
                }
                case 3: {
                    if(player1 == null || player2 == null) {
                        System.out.println("You must first configure both players!");
                    }
                    else if(player1.getSymbol().equals(player2.getSymbol())){
                        System.out.println("Both players can't have the same symbol!");
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

    private Player getPlayerConfiguration() {
        Player player;
        System.out.println("Will the player be computer-controlled? (Y/N)");
        String isComputer = input.next();

        System.out.println("What's the player's symbol?");
        String symbol = input.next();

        if(isComputer.equalsIgnoreCase("Y") || isComputer.equalsIgnoreCase("yes")) {
            player = new Computer(symbol, new RulesAI());
        }
        else {
            player = new ConsolePlayer(symbol);
        }

        return player;
    }

    private void chooseInitialPlayer(Game game){
        System.out.print("Choose starting player: ");
        String symbol = input.next();

        try {
            game.chooseInitialPlayer(symbol);
        }
        catch (TicTacToeException ex){
            System.out.println(ex.getMessage());
        }
    }
}
