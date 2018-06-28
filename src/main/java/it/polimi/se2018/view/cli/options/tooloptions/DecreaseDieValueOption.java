package it.polimi.se2018.view.cli.options.tooloptions;

import it.polimi.se2018.event.game.DecreaseDieValueGameEvent;
import it.polimi.se2018.event.game.IncreaseDieValueGameEvent;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.DieValue;
import it.polimi.se2018.view.cli.CommandLineInterface;
import it.polimi.se2018.view.cli.Printer;
import it.polimi.se2018.view.cli.options.ReadDieOption;
import it.polimi.se2018.view.cli.options.SimpleOption;

public class DecreaseDieValueOption extends SimpleOption {

    private ReadDieOption readDieOption;
    private static final String ERROR_MESSAGE = "You can't decrease this die.";
    private static final String DECREASE_DIE_VALUE_NAME = "Decrease the value of a die.";

    public DecreaseDieValueOption(CommandLineInterface cli) {
        super(cli);
        this.name = DECREASE_DIE_VALUE_NAME;
        readDieOption = new ReadDieOption(cli);
    }

    @Override
    public int execute() {
        int choice;

        do{
            showOptions();
            choice = readChoice();
            if(choice == ERROR_CODE)
                Printer.print(ERROR_MESSAGE);
        }while(choice == ERROR_CODE);

        if(choice == EXIT_CODE)
            return choice;

        Die die = cli.getDraftPool().getAllDice().get(choice - 1);
        cli.notifyObservers(new DecreaseDieValueGameEvent(die, cli.getPlayer().getId()));
        return 0;
    }

    protected void showOptions() {
        Printer.print(cli.getDraftPool());
        showGoBackOption();
    }

    private int readChoice(){
        int choice = readDieOption.execute();
        if(cli.getDraftPool().getAllDice().get(choice - 1).getValue() == DieValue.ONE)
            return ERROR_CODE;

        return choice;
    }
}
