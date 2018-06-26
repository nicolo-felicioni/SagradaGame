package it.polimi.se2018.view.cli;

import it.polimi.se2018.event.game.EndTurnGameEvent;

public class EndTurnOption extends SimpleOption {

    private static final String END_TURN_NAME = "End turn.";


    public EndTurnOption(CommandLineInterface cli) {
        super(cli);
        this.name=END_TURN_NAME;
    }

    @Override
    public int executeOption() {
        cli.notifyObservers(new EndTurnGameEvent(cli.getPlayer().getId()));
        return 0;
    }
}
