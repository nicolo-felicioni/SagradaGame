package it.polimi.se2018.view.cli.options;

import it.polimi.se2018.view.cli.CommandLineInterface;
import it.polimi.se2018.view.cli.Printer;

public class ReadDraftPoolDieOption extends SimpleOption {

    static final String WRONG_CHOICE_MESSAGE = "This die doesn't exists.";

    /**
     * constructor.
     * @param cli the command line interface
     */
    public ReadDraftPoolDieOption(CommandLineInterface cli) {
        super(cli);
    }

    /**
     * this method returns the number of the die in the draft pool that the user has chosen
     * @return the number of the die in the draft pool that the user has chosen
     */
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

    /**
     * shows the draft pool to the user
     */
    private void showDraftpool() {
        Printer.print(cli.getDraftPool());
    }

    /**
     * shows the available options to the user
     */
    protected void showOptions() {
        showDraftpool();
        showGoBackOption();
    }

    /**
     * this method read the choice of the user
     * @return the choice of the user
     */
    private int readChoice() {
        int choice = cli.getKeyboard().readInt();

        if ((choice <= 0 && choice != EXIT_CODE) || choice > cli.getDraftPool().size()) { //if the choice isn't in the range
            return ERROR_CODE;
        } else
            return choice;
    }

}
