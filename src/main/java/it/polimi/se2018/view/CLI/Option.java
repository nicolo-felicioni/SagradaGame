package it.polimi.se2018.view.CLI;

public abstract class Option {
    String name;

    public String getName(){
        return name;
    }

    public abstract void executeOption();


}
