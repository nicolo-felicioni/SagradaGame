package it.polimi.se2018.view.cli.options;

import it.polimi.se2018.view.cli.CommandLineInterface;
import it.polimi.se2018.view.cli.Printer;

public class ShowPrivateObjectiveOption extends SimpleOption {
    private static final String NAME = "Show your private objective card.";


    /**
     * constructor.
     * @param cli the command line interface
     */
    public ShowPrivateObjectiveOption(CommandLineInterface cli) {
        super(cli);
        this.name = NAME;
    }

    /**
     * this method shows the user's private objective card.
     */
    @Override
    public int execute() {
        Printer.print(cli.getPrivateObjectiveCard());
        return 0;
    }
}
