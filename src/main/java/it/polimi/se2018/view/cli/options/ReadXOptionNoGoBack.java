package it.polimi.se2018.view.cli.options;

import it.polimi.se2018.model.DieColor;
import it.polimi.se2018.model.WindowPattern;
import it.polimi.se2018.view.cli.CommandLineInterface;
import it.polimi.se2018.view.cli.Printer;

public class ReadXOptionNoGoBack extends SimpleOption {

    private static final String READ_X_MESSAGE = "Enter the first coordinate of the space.";
    private static final String INFO = "Coordinates starts from 0.";
    private static final String WRONG_CHOICE_MESSAGE = "Wrong coordinate.";


    /**
     * constructor.
     *
     * @param cli the command line interface
     */
    public ReadXOptionNoGoBack(CommandLineInterface cli) {
        super(cli);
    }

    /**
     * returns the first coordinate of the space that the user has chosen
     * @return the first coordinate of the space that the user has chosen
     */
    @Override
    public int execute() {
        int choice;

        boolean validChoice;
        do {

            WindowPattern pattern = cli.getPlayer().getPattern();
            if(pattern != null)
                Printer.printOnlySpaces(pattern);
            else
                Printer.printlnColor("DEBUG: pattern is null", DieColor.RED);

            Printer.println(READ_X_MESSAGE);
            Printer.println(INFO);
            choice = cli.getKeyboard().readInt();
            if (choice < 0 || choice >= WindowPattern.SPACES_HEIGHT) { //if the choice isn't in the range
                Printer.println(WRONG_CHOICE_MESSAGE);
                validChoice = false;
            } else
                validChoice = true;
        } while (!validChoice);
        return choice;
    }
}
