package it.polimi.se2018.view.cli;

public abstract class SimpleOption extends Option {
    static final String GO_BACK_MESSAGE = "go back";

    public SimpleOption(CommandLineInterface cli) {
        super(cli);
    }
}
