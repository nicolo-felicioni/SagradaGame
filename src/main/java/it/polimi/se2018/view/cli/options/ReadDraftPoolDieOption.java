package it.polimi.se2018.view.cli.options;

import it.polimi.se2018.view.cli.CommandLineInterface;
import it.polimi.se2018.view.cli.Printer;

public class ReadDraftPoolDieOption extends SimpleOption {

    static final String WRONG_CHOICE_MESSAGE = "This die doesn't exists.";

    public ReadDraftPoolDieOption(CommandLineInterface cli) {
        super(cli);
    }

    @Override
    public int execute() {
        int choice;

        do {
            showOptions();
            choice = readChoice();
            if (choice == ERROR_CODE)   //if the choice isn't in the range
                Printer.println(WRONG_CHOICE_MESSAGE);
        } while (choice == ERROR_CODE);

        return choice;
    }

    private void showDraftpool() {
        Printer.print(cli.getDraftPool());
    }

    protected void showOptions() {
        showDraftpool();
        showGoBackOption();
    }

    private int readChoice() {
        int choice = cli.getKeyboard().readInt();

        if ((choice <= 0 && choice != EXIT_CODE) || choice > cli.getDraftPool().size()) { //if the choice isn't in the range
            return ERROR_CODE;
        } else
            return choice;
    }

}
