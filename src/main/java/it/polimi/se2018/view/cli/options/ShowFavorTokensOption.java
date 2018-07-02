package it.polimi.se2018.view.cli.options;

import it.polimi.se2018.view.cli.CommandLineInterface;
import it.polimi.se2018.view.cli.Printer;

public class ShowFavorTokensOption extends SimpleOption{
    private static final String NAME = "Show your favor tokens.";


    public ShowFavorTokensOption(CommandLineInterface cli) {
        super(cli);
        this.name = NAME;
    }

    @Override
    public int execute() {
        Printer.println("These are your favor tokens:");
        Printer.printFavorTokens(cli.getPlayer().getTokens());
        return 0;
    }
}
