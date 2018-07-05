package it.polimi.se2018.view.cli.options;

import it.polimi.se2018.view.cli.CommandLineInterface;

public abstract class Option {

    /**
     * the exit code
     */
    public static final int EXIT_CODE = -1;

    /**
     * the error code
     */
    public static final int ERROR_CODE = -100;

    /**
     * the name of the option that will be shown on the cli
     */
    protected String name;

    /**
     * the command line interface class
     */
    protected CommandLineInterface cli;


    /**
     * constructor
     * @param cli the command line interface that has this option.
     *
     */
    Option(CommandLineInterface cli){
        this.cli=cli;
    }

    /**
     * getter of the name of this option.
     * @return the name of this option.
     */
    public String getName(){
        return name;
    }


    /**
     * this method execute the option.
     *
     * @return a code corresponding the input of the user
     */
    public abstract int execute();


}
