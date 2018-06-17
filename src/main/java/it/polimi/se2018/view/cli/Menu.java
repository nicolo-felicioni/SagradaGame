package it.polimi.se2018.view.cli;

import java.util.ArrayList;
import java.util.List;

public abstract class Menu {

    List<Option> options;
    static final String FIRST_MESSAGE = "Select an option.";



    abstract void executeMenu();




}
