package it.polimi.se2018.view.cli.options;

import it.polimi.se2018.exceptions.NotValidRoundException;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.view.cli.CommandLineInterface;
import it.polimi.se2018.view.cli.Printer;

import java.util.List;

public class ReadRoundTrackDieOption extends SimpleOption {

    private int round;
    private static final String WRONG_CHOICE_MESSAGE = "This die doesn't exists.";

    public ReadRoundTrackDieOption(CommandLineInterface cli, int round) {
        super(cli);
        this.round = round;
    }


    /**
     * returns the number of the die present on the round track that the user has chosen
     * @return the number of the die present on the round track that the user has chosen
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

    private void showRoundTrackDice() {
        List<Die> dice;
        try {
            dice = cli.getRoundTrack().getDice(round);
            for(int i = 0; i<dice.size(); i++){
                Printer.print(i+1);
                Printer.print(": ");
                Printer.print(dice.get(i));
            }
            Printer.print("\n");

        } catch (NotValidRoundException e) {
            Printer.print("Not valid round.");
        }
    }

    protected void showOptions() {
        showRoundTrackDice();
        showGoBackOption();
    }

    private int readChoice() {
        int choice = cli.getKeyboard().readInt();

        try {
            if ((choice <= 0 && choice != EXIT_CODE) || choice > cli.getRoundTrack().getDice(round).size()) { //if the choice isn't in the range
                return ERROR_CODE;
            } else
                return choice;
        } catch (NotValidRoundException e) {
            Printer.print("Not valid round.");
            return ERROR_CODE;
        }
    }

}
