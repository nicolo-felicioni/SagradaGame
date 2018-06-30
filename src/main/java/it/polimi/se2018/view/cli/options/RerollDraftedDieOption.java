package it.polimi.se2018.view.cli.options;

import it.polimi.se2018.event.game.RerollAllDraftDiceGameEvent;
import it.polimi.se2018.event.game.RerollDraftDieGameEvent;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.view.cli.CommandLineInterface;

import java.util.List;

public class RerollDraftedDieOption extends ComplexOption {

    private static final String SELECT_MESSAGE = "Select a die you want to roll.";
    private static final String NAME = "Roll a drafted die.";
    private static final int DIE_CHOICE = 0;


    public RerollDraftedDieOption(CommandLineInterface cli) {
        super(cli);
        this.subOptions.add(new ReadDraftPoolDieOption(cli));
    }

    @Override
    protected void setName() {
        this.name = NAME;
    }

    @Override
    protected void setSelectMessage() {
        this.selectMessage = SELECT_MESSAGE;
    }

    @Override
    public int execute() {
        List<Integer> choices = makeChoices();

        if(!choices.isEmpty()){
            Die die = cli.getDraftPool().getAllDice().get(choices.get(DIE_CHOICE));
            cli.notifyObservers(new RerollDraftDieGameEvent(die, cli.getPlayer().getId()));
        }


        return 0;
    }
}
