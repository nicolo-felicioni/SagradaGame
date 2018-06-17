package it.polimi.se2018.view.cli;

public abstract class Option {
    static final int EXIT_CODE = -1;
    public static final int ERROR_CODE = -100;
    protected String name;

    public String getName(){
        return name;
    }



    public abstract int executeOption();


}
