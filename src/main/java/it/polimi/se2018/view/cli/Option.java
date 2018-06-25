package it.polimi.se2018.view.cli;

public abstract class Option {
    static final int EXIT_CODE = -1;
    static final int ERROR_CODE = -100;
    protected String name;
    CommandLineInterface cli;

    public Option(CommandLineInterface cli){
        this.cli=cli;
    }

    public String getName(){
        return name;
    }



    public abstract int executeOption();


}
