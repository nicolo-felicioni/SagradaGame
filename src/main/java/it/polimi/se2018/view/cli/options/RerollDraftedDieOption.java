package it.polimi.se2018.view.cli.options;

import it.polimi.se2018.event.game.RerollDraftDieGameEvent;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.view.cli.CommandLineInterface;

import java.util.ArrayList;
import java.util.List;

public class RerollDraftedDieOption extends ComplexOption {

    private static final int DIE_CHOICE = 0;


    /**
     * constructor.
     * @param cli the command line interface
     */
    public RerollDraftedDieOption(CommandLineInterface cli, String name, String select) {
        super(cli, name, select);
        this.subOptions = new ArrayList<>();
        this.subOptions.add(new ReadDraftPoolDieOption(cli));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int execute() {
        List<Integer> choices = makeChoices();

        if(!choices.isEmpty()){
            Die die = cli.getDraftPool().getAllDice().get(choices.get(DIE_CHOICE ) - 1);
            cli.notifyObservers(new RerollDraftDieGameEvent(die, cli.getPlayer().getId()));
        }

        return 0;
    }
}
