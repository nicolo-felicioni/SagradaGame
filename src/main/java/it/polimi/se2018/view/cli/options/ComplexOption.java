package it.polimi.se2018.view.cli.options;

import it.polimi.se2018.view.cli.CommandLineInterface;
import it.polimi.se2018.view.cli.Printer;

import java.util.ArrayList;
import java.util.List;

public abstract class ComplexOption extends Option {
    protected List<Option> subOptions;
    protected String selectMessage;


    /**
     * constructor.
     * @param cli the command line interface
     */
    public ComplexOption(CommandLineInterface cli, String name, String selectMessage) {
        super(cli);
        setSelectMessage(selectMessage);
        setName(name);
    }


    /**
     * setter of the name of the option.
     */
    protected void setName(String name){
        this.name = name;
    }

    /**
     * setter of the message that will be printed before the initial selection.
     */
    private void setSelectMessage(String selectMessage){
        this.selectMessage = selectMessage;
    }



    /**
     * this method will return a list of choices made by the user.
     * the list of choices is a list of integer, the user shall enter his choice as an int following the
     * instructions printed on the screen.
     *
     * @return a list of choices.
     */
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


    /**
     * this method reads the choices made by the user and throws the event to the observers.
     * @return zero if the procedure went right
     */
    @Override
    public abstract int execute();
}
