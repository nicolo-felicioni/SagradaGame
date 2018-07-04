package it.polimi.se2018.view.cli.options;

import it.polimi.se2018.model.Player;
import it.polimi.se2018.view.cli.CommandLineInterface;
import it.polimi.se2018.view.cli.Printer;

import java.util.List;

public class ShowOtherWindowPatternOption extends SimpleOption{

    private static final String SHOW_OTHER_WINDOW_NAME = "Show other players' window pattern.";

    public ShowOtherWindowPatternOption(CommandLineInterface cli) {
        super(cli);
        this.name = SHOW_OTHER_WINDOW_NAME;
    }

    /**
     * this method shows the window pattern of the other players to the user.
     */
    @Override
    public int execute() {
        List<Player> players = cli.getPlayers();

        players.stream().filter(player -> !player.equalsPlayer(cli.getPlayer()))
                .forEach(player -> {
                    Printer.print("Player: ");
                    Printer.println(player.getId());
                    Printer.println("Window pattern: ");
                    Printer.print(player.getPattern());
                });

        return 0;
    }
}
