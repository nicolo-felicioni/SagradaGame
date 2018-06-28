package it.polimi.se2018.view.cli;

import it.polimi.se2018.view.cli.options.Option;

import java.util.List;

public abstract class Menu {

    List<Option> options;
    CommandLineInterface cli;



    public Menu(CommandLineInterface cli){
        this.cli = cli;
    }


    abstract int executeMenu();




}
