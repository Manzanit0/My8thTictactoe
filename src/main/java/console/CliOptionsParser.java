package console;

import core.Game;
import core.ai.RulesAI;
import core.players.Computer;
import core.players.Player;
import org.apache.commons.cli.*;

@Deprecated
public class CliOptionsParser {
    @Deprecated
    public static Game parseOptions(String[] args) throws ParseException {
        CommandLineParser parser = new DefaultParser();
        Options cmdOptions = getCmdOptions();
        CommandLine commandLine = parser.parse(cmdOptions, args);

        String p1Marker = commandLine.getOptionValue("p1");
        Player player1 = commandLine.hasOption("p1IsComputer") ?
                new Computer(p1Marker, new RulesAI()) :
                new ConsolePlayer(p1Marker);

        String p2Marker = commandLine.getOptionValue("p2");
        Player player2 = commandLine.hasOption("p2IsComputer") ?
                new Computer(p2Marker, new RulesAI()) :
                new ConsolePlayer(p2Marker);

        return new Game(player1, player2);
    }

    @Deprecated
    private static Options getCmdOptions(){
        Options options = new Options();

        //CLI options.
        options.addRequiredOption("p1", "player1Token", true, "Player1's token/marker.");
        options.addRequiredOption("p2", "player2Token", true, "Player2's token/marker.");
        options.addOption("p1IsComputer", false, "Sets player1 as controlled by the AI.");
        options.addOption("p2IsComputer", false, "Sets player2 as controlled by the AI.");

        return options;
    }
}
