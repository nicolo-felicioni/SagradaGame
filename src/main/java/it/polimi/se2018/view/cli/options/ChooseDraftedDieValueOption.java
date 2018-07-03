package it.polimi.se2018.view.cli.options;

import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.DieColor;
import it.polimi.se2018.model.DieValue;
import it.polimi.se2018.model.WindowPattern;
import it.polimi.se2018.view.cli.CommandLineInterface;
import it.polimi.se2018.view.cli.Printer;

public class ChooseDraftedDieValueOption extends SimpleOption {

    private static final String WRONG_CHOICE_MESSAGE = "Impossible choice.";
    private Die draftedDie;
    private static final String SELECT = "Select a value.";

    public ChooseDraftedDieValueOption(CommandLineInterface cli, Die draftedDie) {
        super(cli);
        this.draftedDie = draftedDie;
    }

    @Override
    public int execute() {
        Printer.print("The die from the dice bag is : ");
        Printer.print(cli.getDraftPool().getDraftedDie());
        Printer.print("\n");


        int choice;

        boolean validChoice;
        do {
            Printer.println(SELECT);
            choice = cli.getKeyboard().readInt();
            if (choice < DieValue.ONE.toInt() || choice >= DieValue.SIX.toInt()) { //if the choice isn't in the range
                Printer.println(WRONG_CHOICE_MESSAGE);
                validChoice = false;
            } else
                validChoice = true;
        } while (!validChoice);

        return choice;
    }
}
