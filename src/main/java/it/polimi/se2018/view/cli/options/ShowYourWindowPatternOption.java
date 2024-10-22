package it.polimi.se2018.view.cli.options;

import it.polimi.se2018.view.cli.CommandLineInterface;
import it.polimi.se2018.view.cli.Printer;

public class ShowYourWindowPatternOption extends SimpleOption{
    private static final String SHOW_OTHER_WINDOW_NAME = "Show your window pattern.";


    /**
     * constructor.
     * @param cli the command line interface
     */
    public ShowYourWindowPatternOption(CommandLineInterface cli) {
        super(cli);
        this.name = SHOW_OTHER_WINDOW_NAME;
    }

    /**
     * this method shows the user's pattern.
     */
    @Override
    public int execute() {
        Printer.print(cli.getPlayer().getPattern());
        return 0;
    }
}
