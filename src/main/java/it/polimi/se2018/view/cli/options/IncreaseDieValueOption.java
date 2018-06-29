package it.polimi.se2018.view.cli.options;

import it.polimi.se2018.event.game.IncreaseDieValueGameEvent;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.DieValue;
import it.polimi.se2018.view.cli.CommandLineInterface;

import java.util.ArrayList;
import java.util.List;

public class IncreaseDieValueOption extends ComplexOption {

    private static final String INCREASE_DIE_VALUE_NAME = "Increase the value of a die.";
    private static final String ERROR_MESSAGE = "You can't increase this die";
    private static final int READ_DIE_CHOICE = 1;

    public IncreaseDieValueOption(CommandLineInterface cli) {
        super(cli);
        this.name = INCREASE_DIE_VALUE_NAME;
        subOptions = new ArrayList<>();
        subOptions.add(new ReadDraftPoolDieOption(cli));
    }


    @Override
    public int execute() {
        List<Integer> choiceList = makeChoices();

        if(!choiceList.isEmpty()){
            Die die = cli.getDraftPool().getAllDice().get(choiceList.get(READ_DIE_CHOICE) - 1);
            cli.notifyObservers(new IncreaseDieValueGameEvent(die, cli.getPlayer().getId()));
        }

        return 0;
    }

}
