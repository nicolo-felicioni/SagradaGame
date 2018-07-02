package it.polimi.se2018.view.cli;

import it.polimi.se2018.view.cli.options.Option;
import it.polimi.se2018.view.cli.options.OptionFactory;

public class PlayerMenu extends Menu {
    private static final String ERROR_CHOICE = "Impossible choice.";
    private static final String FIRST_MESSAGE = "Press 'm' for menu.";
    private static final String SELECT_MESSAGE = "Select an option.";
    private static final String MENU_CHOICE = "m";
    private volatile boolean exit = false;


    public PlayerMenu(CommandLineInterface cli) {
        super(cli);
    }



    public synchronized void setExit(){
        this.exit = true;
    }

    private synchronized boolean getExit(){
        return exit;
    }

    @Override
    int executeMenu() {

        boolean validChoice;
        int choice;
        String firstInput;

        do{
            Printer.println(FIRST_MESSAGE);
            firstInput = cli.getKeyboard().readLine();
            if (!firstInput.equals(MENU_CHOICE)) {
                Printer.println(ERROR_CHOICE);
                validChoice = false;
            } else {//if it was pressed the right key

                do {
                    this.options = OptionFactory.buildOptions(cli, cli.getPlayer().getState(), getExit());
                    Printer.println(SELECT_MESSAGE);
                    Printer.print(options);
                    choice = cli.getKeyboard().readInt();

                    if (choice == Option.EXIT_CODE)
                        return Option.EXIT_CODE;

                    if (choice <= 0 || choice > options.size()) {
                        validChoice = false;
                        Printer.print(ERROR_CHOICE);
                    } else {
                        return options.get(choice - 1).execute();
                    }
                } while (!validChoice);
            }
        }while(!validChoice);

        return 0;
    }

}
