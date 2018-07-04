package it.polimi.se2018.view.cli.options;

import it.polimi.se2018.view.cli.CommandLineInterface;
import it.polimi.se2018.view.cli.Printer;

public class ShowDraftPoolOption extends SimpleOption {

    private static final String SHOW_DRAFT_POOL_NAME = "Show draft pool.";

    /**
     * @param cli
     */
    public ShowDraftPoolOption(CommandLineInterface cli) {
        super(cli);
        this.name = SHOW_DRAFT_POOL_NAME;
    }

    /**
     * this method shows the draft pool to the user.
     */
    @Override
    public int execute() {
        Printer.print(cli.getDraftPool());
        return 0;
    }
}
