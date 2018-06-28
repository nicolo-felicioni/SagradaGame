package it.polimi.se2018.view.cli.options;

import it.polimi.se2018.model.WindowPattern;
import it.polimi.se2018.view.cli.CommandLineInterface;
import it.polimi.se2018.view.cli.Printer;

public class ReadYOption extends SimpleOption {
    private static final String READ_Y_MESSAGE = "Enter the second coordinate."
            + "\nEnter " + EXIT_CODE + "for go back.";
    private static final String WRONG_CHOICE_MESSAGE = "Wrong coordinate.";

    public ReadYOption(CommandLineInterface cli) {
        super(cli);
    }

    @Override
    public int execute() {
        int choice;

        boolean validChoice;
        do {
            Printer.println(READ_Y_MESSAGE);
            choice = cli.getKeyboard().readInt();
            if ((choice < 0 && choice != EXIT_CODE) || choice >= WindowPattern.SPACES_LENGTH) { //if the choice isn't in the range
                Printer.println(WRONG_CHOICE_MESSAGE);
                validChoice = false;
            } else
                validChoice = true;
        } while (!validChoice);

        return choice;
    }
}
