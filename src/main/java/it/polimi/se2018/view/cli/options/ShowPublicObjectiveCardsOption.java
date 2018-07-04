package it.polimi.se2018.view.cli.options;

import it.polimi.se2018.view.cli.CommandLineInterface;
import it.polimi.se2018.view.cli.Printer;

import java.util.Arrays;

public class ShowPublicObjectiveCardsOption extends SimpleOption {

    private static final String SHOW_PUBLIC_OBJECTIVE_CARDS_NAME= "Show public objective cards.";

    public ShowPublicObjectiveCardsOption(CommandLineInterface cli) {
        super(cli);
        this.name = SHOW_PUBLIC_OBJECTIVE_CARDS_NAME;
    }

    /**
     * this method shows the public objective cards on the table to the user.
     */
    @Override
    public int execute() {
        Arrays.stream(cli.getPublicObjectiveCards()).forEach(Printer::print);
        return 0;
    }
}
