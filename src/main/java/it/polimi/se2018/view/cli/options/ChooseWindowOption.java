package it.polimi.se2018.view.cli.options;

import it.polimi.se2018.event.game.WindowPatternChosenGameEvent;
import it.polimi.se2018.model.WindowPattern;
import it.polimi.se2018.view.cli.CommandLineInterface;
import it.polimi.se2018.view.cli.Printer;

public class ChooseWindowOption extends SimpleOption {
    private static final String CHOOSE_WINDOW_NAME = "Choose a window pattern.";
    private static final String CHOOSE_WINDOW_MESSAGE = "Select a window pattern";
    private static final String WRONG_CHOICE_MESSAGE = "There isn't any window with that number.";

    /**
     * constructor
     * @param cli the command line interface
     */
    public ChooseWindowOption(CommandLineInterface cli) {
        super(cli);
        this.name = CHOOSE_WINDOW_NAME;
    }

    /**
     * this method shows the initial window patterns of the player
     * and asks the user to choice one of them.
     * @return the number of the chosen pattern.
     */
    @Override
    public int execute() {
        int choice;
        int n = 0;
        boolean validChoice;
        WindowPattern patternChosen;

        do {
            Printer.println(CHOOSE_WINDOW_MESSAGE);
            for (int i = 0; i < cli.getPatterns().length; i++) {
                n = i + 1;
                Printer.println(n + ":");
                Printer.print(cli.getPatterns()[i]);
            }
            Printer.print("\n");
            Printer.println(EXIT_CODE + GO_BACK_MESSAGE);

            choice = cli.getKeyboard().readInt();
            if ((choice <= 0 && choice != EXIT_CODE) || choice > n) { //if the choice isn't in the range
                Printer.println(WRONG_CHOICE_MESSAGE);
                validChoice = false;
            } else{
                validChoice = true;
                if(choice!=EXIT_CODE){//if the choice is valid and it's not the exit code
                    patternChosen = cli.getPatterns()[choice-1];
                    //throw the event
                    cli.notifyObservers(new WindowPatternChosenGameEvent(patternChosen, cli.getPlayer().getId()));
                }

            }


        } while (!validChoice);

        return choice;
    }
}
