package it.polimi.se2018.view.cli.options;

import it.polimi.se2018.view.cli.CommandLineInterface;
import it.polimi.se2018.view.cli.Printer;

public abstract class SimpleOption extends Option {

    static final String GO_BACK_MESSAGE = "go back";
    static final String GO_BACK_OPTION = EXIT_CODE + ": " + GO_BACK_MESSAGE;

    public SimpleOption(CommandLineInterface cli) {
        super(cli);
    }



    protected void showGoBackOption(){
        Printer.println(GO_BACK_OPTION);
    }
}
