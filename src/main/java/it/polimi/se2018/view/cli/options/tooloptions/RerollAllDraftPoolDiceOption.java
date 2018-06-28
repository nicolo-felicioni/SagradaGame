package it.polimi.se2018.view.cli.options.tooloptions;

import it.polimi.se2018.event.game.RerollAllDraftDiceGameEvent;
import it.polimi.se2018.view.cli.CommandLineInterface;
import it.polimi.se2018.view.cli.Printer;
import it.polimi.se2018.view.cli.options.ReadDieOption;
import it.polimi.se2018.view.cli.options.SimpleOption;

public class RerollAllDraftPoolDiceOption extends SimpleOption {

    private static final String REROLL_NAME = "Reroll all the dice in the draft pool.";


    public RerollAllDraftPoolDiceOption(CommandLineInterface cli) {
        super(cli);
        this.name = REROLL_NAME;
    }


    @Override
    public int execute() {
        cli.notifyObservers(new RerollAllDraftDiceGameEvent(cli.getPlayer().getId()));
        return 0;
    }
}
