package it.polimi.se2018.view.cli.options;

import it.polimi.se2018.exceptions.NotValidRoundException;
import it.polimi.se2018.exceptions.RoundTrackEmptyException;
import it.polimi.se2018.model.DieColor;
import it.polimi.se2018.model.RoundTrack;
import it.polimi.se2018.model.WindowPattern;
import it.polimi.se2018.view.cli.CommandLineInterface;
import it.polimi.se2018.view.cli.Printer;

public class ReadRoundOption extends SimpleOption {

    private static final String MESSAGE = "Select the round on the round track.";
    private static final String WRONG_CHOICE_MESSAGE = "This round doesn't exist.";

    public ReadRoundOption(CommandLineInterface cli) {
        super(cli);
    }

    @Override
    public int execute() {
        int choice;

        boolean validChoice;
        do {

            RoundTrack roundTrack = cli.getRoundTrack();

            Printer.println(MESSAGE);
            Printer.print(roundTrack);

            choice = cli.getKeyboard().readInt();
            if ((choice <= 0 && choice != EXIT_CODE) || choice > roundTrack.SIZE) { //if the choice isn't in the range
                Printer.println(WRONG_CHOICE_MESSAGE);
                validChoice = false;
            } else
                validChoice = true;
        } while (!validChoice);
        return choice;
    }
}