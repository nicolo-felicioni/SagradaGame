package it.polimi.se2018.view.cli.options;

import it.polimi.se2018.view.cli.CommandLineInterface;
import it.polimi.se2018.view.cli.Printer;

import java.util.Arrays;

public class ShowToolCardsOption extends SimpleOption {
    private static final String SHOW_TOOL_CARDS = "Show the tool cards.";


    public ShowToolCardsOption(CommandLineInterface cli) {
        super(cli);
        this.name = SHOW_TOOL_CARDS;
    }

    @Override
    public int execute() {
        Arrays.stream(cli.getToolCards()).forEach(Printer::print);
        return 0;
    }
}
