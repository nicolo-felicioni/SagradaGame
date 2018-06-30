package it.polimi.se2018.view.cli.options;

import it.polimi.se2018.view.cli.CommandLineInterface;
import it.polimi.se2018.view.cli.Printer;

import java.util.ArrayList;
import java.util.List;

public abstract class ComplexOption extends Option {
    protected List<Option> subOptions;
    protected String selectMessage;

    public ComplexOption(CommandLineInterface cli) {
        super(cli);
        setSelectMessage();
        setName();
    }

    protected abstract void setName();
    protected abstract void setSelectMessage();

    protected List<Integer> makeChoices(){
        int choice;
        List<Integer> choiceList = new ArrayList<>();
        Printer.println(selectMessage);
        int i = 0;
        while (i >= 0 && i < subOptions.size()) {
            choice = subOptions.get(i).execute();
            if (choice == EXIT_CODE) { //go back
                if(!choiceList.isEmpty())
                    choiceList.remove(i-1);
                i--;
            }
            else {
                choiceList.add(i, choice);
                i++;
            }
        }
        return choiceList;
    }
}
