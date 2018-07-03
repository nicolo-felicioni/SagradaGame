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

        //TODO DEBUG
        Arrays.stream(cli.getToolCards()).forEach(card -> {
            Printer.println(card.getClass().getSimpleName());
            Printer.print(card);
        });
        return 0;
    }
}
