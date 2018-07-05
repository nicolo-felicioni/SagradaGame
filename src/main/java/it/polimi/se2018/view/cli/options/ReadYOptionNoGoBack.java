package it.polimi.se2018.view.cli.options;

import it.polimi.se2018.model.WindowPattern;
import it.polimi.se2018.view.cli.CommandLineInterface;
import it.polimi.se2018.view.cli.Printer;

public class ReadYOptionNoGoBack extends SimpleOption {

    private static final String READ_Y_MESSAGE = "Enter the second coordinate.";
    private static final String WRONG_CHOICE_MESSAGE = "Wrong coordinate.";

    /**
     * constructor.
     * @param cli the command line interface
     */
    public ReadYOptionNoGoBack(CommandLineInterface cli) {
        super(cli);
    }

    /**
     * returns the second coordinate of the space that the user has chosen
     * @return the second coordinate of the space that the user has chosen
     */
    @Override
    public int execute() {
        int choice;

        boolean validChoice;
        do {
            Printer.println(READ_Y_MESSAGE);
            choice = cli.getKeyboard().readInt();
            if (choice < 0|| choice >= WindowPattern.SPACES_LENGTH) { //if the choice isn't in the range
                Printer.println(WRONG_CHOICE_MESSAGE);
                validChoice = false;
            } else
                validChoice = true;
        } while (!validChoice);

        return choice;
    }
}
