package it.polimi.se2018.view.cli;

import it.polimi.se2018.view.cli.options.Option;
import it.polimi.se2018.view.cli.options.OptionFactory;

public class PlayerMenu extends Menu {
    private static final String ERROR_CHOICE = "Impossible choice.";
    private static final String FIRST_MESSAGE = "Press 'm' for menu.";
    private static final String SELECT_MESSAGE = "Select an option.";
    private static final String MENU_CHOICE = "m";
    private volatile boolean exit = false;


    /**
     * constructor
     * @param cli the command line interface
     */
    public PlayerMenu(CommandLineInterface cli) {
        super(cli);
    }


    /**
     * this method set the exit option.
     * if it is set, the menu will allow the player to end the game
     */
    public synchronized void setExit(){
        this.exit = true;
    }

    /**
     * getter of the exit flag
     * @return a boolean flag
     */
    private synchronized boolean getExit(){
        return exit;
    }

    /**
     * {@inheritDoc}
     */
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
                    this.options = OptionFactory.buildPlayerMenuOptions(cli, cli.getPlayer().getState(), getExit());
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
