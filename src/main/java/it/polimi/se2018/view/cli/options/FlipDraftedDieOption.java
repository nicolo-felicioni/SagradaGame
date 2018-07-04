package it.polimi.se2018.view.cli.options;

import it.polimi.se2018.event.game.FlipDraftDieGameEvent;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.view.cli.CommandLineInterface;

import java.util.ArrayList;
import java.util.List;

public class FlipDraftedDieOption extends ComplexOption {

    private static final String NAME = "Flip a drafted die.";
    private static final String SELECT = "Select the die you want to flip.";
    private static final int DIE_CHOICE = 0;

    /**
     * constructor
     * @param cli the command line interface
     */
    public FlipDraftedDieOption(CommandLineInterface cli) {
        super(cli);
        subOptions = new ArrayList<>();
        subOptions.add(new ReadDraftPoolDieOption(cli));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setName() {
        this.name = NAME;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    protected void setSelectMessage() {
        this.selectMessage = SELECT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int execute() {
        List<Integer> choices = makeChoices();

        if(!choices.isEmpty()){
            Die die = cli.getDraftPool().getAllDice().get(choices.get(DIE_CHOICE) - 1);
            cli.notifyObservers(new FlipDraftDieGameEvent(die, cli.getPlayer().getId()));
        }

        return 0;
    }
}
