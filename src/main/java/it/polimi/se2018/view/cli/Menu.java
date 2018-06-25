package it.polimi.se2018.view.cli;

import java.util.ArrayList;
import java.util.List;

public abstract class Menu {

    List<Option> options;
    CommandLineInterface cli;



    public Menu(CommandLineInterface cli){
        this.cli = cli;
    }


    abstract void executeMenu();




}
