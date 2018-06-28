package it.polimi.se2018.view.cli.options;

import it.polimi.se2018.event.game.EndTurnGameEvent;
import it.polimi.se2018.view.cli.CommandLineInterface;

public class EndTurnOption extends SimpleOption {

    private static final String END_TURN_NAME = "End turn.";


    public EndTurnOption(CommandLineInterface cli) {
        super(cli);
        this.name=END_TURN_NAME;
    }

    @Override
    public int execute() {
        cli.notifyObservers(new EndTurnGameEvent(cli.getPlayer().getId()));
        return 0;
    }
}
