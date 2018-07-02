package it.polimi.se2018.view.cli.options;

import it.polimi.se2018.view.cli.CommandLineInterface;

public class EndGameOption extends SimpleOption {


    public EndGameOption(CommandLineInterface cli) {
        super(cli);
        this.name = "End game.";
    }


    @Override
    public int execute() {
        return CommandLineInterface.END_GAME_CODE;
    }
}
