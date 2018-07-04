package it.polimi.se2018.view.cli.options;

import it.polimi.se2018.view.cli.CommandLineInterface;

public class EndGameOption extends SimpleOption {


    /**
     * constructor.
     * @param cli the command line interface
     */
    public EndGameOption(CommandLineInterface cli) {
        super(cli);
        this.name = "End game.";
    }


    /**
     * this method will return the end game code
     * @return the end game code
     */
    @Override
    public int execute() {
        return CommandLineInterface.END_GAME_CODE;
    }
}
