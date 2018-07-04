package it.polimi.se2018.view.cli.options;

import it.polimi.se2018.event.game.RerollAllDraftDiceGameEvent;
import it.polimi.se2018.view.cli.CommandLineInterface;

public class RerollAllDraftPoolDiceOption extends SimpleOption {

    private static final String REROLL_NAME = "Reroll all the dice in the draft pool.";


    public RerollAllDraftPoolDiceOption(CommandLineInterface cli) {
        super(cli);
        this.name = REROLL_NAME;
    }


    /**
     * this method notifies the observers that the user wants to re-roll all the dice in the draft pool
     */
    @Override
    public int execute() {
        cli.notifyObservers(new RerollAllDraftDiceGameEvent(cli.getPlayer().getId()));
        return 0;
    }
}
