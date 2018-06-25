package it.polimi.se2018.view.cli;

public class EndTurnOption extends SimpleOption {


    public EndTurnOption(CommandLineInterface cli) {
        super(cli);
    }

    @Override
    public int executeOption() {
        return 0;
    }
}
