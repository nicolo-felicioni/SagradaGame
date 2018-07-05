package it.polimi.se2018.view.cli;

import it.polimi.se2018.view.cli.options.Option;

import java.util.List;

public abstract class Menu {

    List<Option> options;
    CommandLineInterface cli;


    /**
     * constructor
     * @param cli the command line interface
     */
    public Menu(CommandLineInterface cli){
        this.cli = cli;
    }


    /**
     * this method will execute the menu.
     *
     * Once the menu is executed, it will show the available option and permit to choose one of them.
     * After the choice, the menu will execute the chosen option.
     *
     */
    abstract int executeMenu();




}
