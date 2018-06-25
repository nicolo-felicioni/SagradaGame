package it.polimi.se2018.view.cli;

import it.polimi.se2018.exceptions.GameException;
import it.polimi.se2018.model.WindowPattern;

public class ReadXOption extends SimpleOption {
    final static String READ_X_MESSAGE = "Enter the first coordinate of the space in which you want to place the die."
            + "\nEnter " + EXIT_CODE + "for go back.";
    final static String INFO = "Coordinates starts from 0.";
    final static String WRONG_CHOICE_MESSAGE = "Wrong coordinate.";

    public ReadXOption(CommandLineInterface cli) {
        super(cli);
    }

    @Override
    public int executeOption() {
        int choice;

        boolean validChoice;
        do {

            try {
                Printer.print(cli.getPlayer().getPattern());
            } catch (GameException e) {
                e.printStackTrace();
            }

            Printer.println(READ_X_MESSAGE);
            Printer.println(INFO);
            choice = cli.getKeyboard().readInt();
            if ((choice < 0 && choice != EXIT_CODE) || choice >= WindowPattern.SPACES_HEIGHT) { //if the choice isn't in the range
                Printer.println(WRONG_CHOICE_MESSAGE);
                validChoice = false;
            } else
                validChoice = true;
        } while (!validChoice);

        return choice;
    }
}
