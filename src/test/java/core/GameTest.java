package core;

import core.ai.RulesAI;
import core.board.Tile;
import core.exceptions.TicTacToeException;
import core.players.Computer;
import core.players.Human;
import core.players.Player;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * System test for the whole application.
 *
 * //TODO: There is some repeated code. It could be refactored.
 * //TODO: Although the coverage is high, it does not cover all the possible use cases.
 */
public class GameTest {

    //TODO: check -  http://www.vogella.com/tutorials/JUnit/article.html

    @Test
    public void testDefaultPlayerIsPlayer1() {
        Player player1 = new Computer("X", new RulesAI());
        Player player2 = new Computer("O", new RulesAI());
        Game game = new Game(player1, player2);

        assertEquals(player1, game.getCurrentPlayer());
    }

    @Test
    public void testSetInitialPlayer() {
        Player player1 = new Computer("X", new RulesAI());
        Player player2 = new Computer("O", new RulesAI());
        Game game = new Game(player1, player2);
        game.chooseInitialPlayer(player2);

        assertEquals(player2, game.getCurrentPlayer());
    }

    @Test
    public void testChangeTurn() {
        Player player1 = new Computer("X", new RulesAI());
        Player player2 = new Computer("O", new RulesAI());
        Game game = new Game(player1, player2);
        game.changeTurn();

        assertEquals(player2, game.getCurrentPlayer());
    }

    @Test
    public void testHumanMove() throws TicTacToeException{
        Player player1 = new Human("X");
        Player player2 = new Computer("O", new RulesAI());
        Game game = new Game(player1, player2);

        game.makeMove(2, 2);

        // Take into account that the board works from 0 to 2, but the game expects values from 1 to 3.
        assertEquals(player1, game.getBoard().getTile(1, 1).getCheckingPlayer());
    }

    @Test
    public void testComputerMove() throws TicTacToeException{
        Player player1 = new Human("X");
        Player player2 = new Computer("O", new RulesAI());
        Game game = new Game(player1, player2);
        game.chooseInitialPlayer(player2);

        game.makeMove();

        Tile[] avaliableTiles = game.getBoard().getAvailableTiles();
        assertEquals(8, avaliableTiles.length);
    }
}
