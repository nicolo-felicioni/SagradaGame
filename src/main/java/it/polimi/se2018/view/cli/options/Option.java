package it.polimi.se2018.view.cli.options;

import it.polimi.se2018.view.cli.CommandLineInterface;

public abstract class Option {
    public static final int EXIT_CODE = -1;
    public static final int ERROR_CODE = -100;
    protected String name;
    protected CommandLineInterface cli;

    public Option(CommandLineInterface cli){
        this.cli=cli;
    }

    public String getName(){
        return name;
    }


    public abstract int execute();


}
