package it.polimi.se2018.view.cli.options;

import it.polimi.se2018.event.game.DecreaseDieValueGameEvent;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.view.cli.CommandLineInterface;

import java.util.ArrayList;
import java.util.List;

public class DecreaseDieValueOption extends ComplexOption {

    private static final String ERROR_MESSAGE = "You can't decrease this die.";
    private static final String DECREASE_DIE_VALUE_NAME = "Decrease the value of a die.";
    private static final String DECREASE_DIE_VALUE_MESSAGE = "Select the die you want to decrease.";
    private static final int READ_DIE_CHOICE = 0;


    /**
     * constructor
     * @param cli the command line interface
     */
    public DecreaseDieValueOption(CommandLineInterface cli) {
        super(cli);
        this.subOptions = new ArrayList<>();
        this.subOptions.add(new ReadDraftPoolDieOption(cli));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setName() {
        this.name = DECREASE_DIE_VALUE_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setSelectMessage() {
        this.selectMessage = DECREASE_DIE_VALUE_MESSAGE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int execute() {
        List<Integer> choiceList = makeChoices();

        if(!choiceList.isEmpty()){
            Die die = cli.getDraftPool().getAllDice().get(choiceList.get(READ_DIE_CHOICE) - 1);
            cli.notifyObservers(new DecreaseDieValueGameEvent(die, cli.getPlayer().getId()));
        }

        return 0;
    }
}
