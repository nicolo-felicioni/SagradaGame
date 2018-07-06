package it.polimi.se2018.view.cli.options;

import it.polimi.se2018.view.cli.CommandLineInterface;
import it.polimi.se2018.view.cli.Printer;

public class ShowRoundTrackOption extends SimpleOption {
    private static final String SHOW_ROUND_CARDS = "Show the round track.";


    /**
     * constructor.
     * @param cli the command line interface
     */
    public ShowRoundTrackOption(CommandLineInterface cli) {
        super(cli);
        this.name = SHOW_ROUND_CARDS;
    }

    /**
     * this method shows the round track to the user.
     */
    @Override
    public int execute() {
        Printer.print(cli.getRoundTrack());
        return 0;
    }
}
