package it.polimi.se2018.view.cli;

import it.polimi.se2018.model.PlayerState;

import java.util.ArrayList;
import java.util.List;

public class PlayerMenu extends Menu {
    private static final String ERROR_CHOICE = "Impossible choice.";
    private static final String FIRST_MESSAGE = "Press 'm' for menu.";
    private static final String SELECT_MESSAGE = "Select an option.";
    private static final String MENU_CHOICE = "m";

    public PlayerMenu(CommandLineInterface cli) {
        super(cli);
    }


    @Override
    void executeMenu() {

        boolean validChoice = true;
        int choice;
        String firstInput;

        Printer.println(FIRST_MESSAGE);
        firstInput = cli.getKeyboard().readLine();
        if (!firstInput.equals(MENU_CHOICE)) {
            Printer.println(ERROR_CHOICE);
        } else {
            do { //if it was pressed the right key

                this.options = buildOptions(cli.getPlayer().getState());
                Printer.println(SELECT_MESSAGE);
                Printer.print(options);
                choice = cli.getKeyboard().readInt();

                if (choice == Option.EXIT_CODE)
                    return;
                if (choice <= 0 || choice > options.size()) {
                    validChoice = false;
                    Printer.print(ERROR_CHOICE);
                } else {
                    options.get(choice - 1).executeOption();
                }
            } while (!validChoice);
        }


    }

    private List<Option> buildOptions(PlayerState state) {
        List<Option> options = new ArrayList<>();
        int i = 0;
        if (state.hasChosenWindowPattern()) { //if the player already chose a window pattern

            //todo debug
            Printer.println("DEBUG: ");

            Printer.print("Your state : ");
            Printer.println(cli.getPlayer().getState().getClass().getSimpleName());

            Printer.print("canPlaceDie : ");
            Printer.println(state.canPlaceDie());

            Printer.print("canUseTool : ");
            Printer.println(state.canUseTool());

            if (state.canPlaceDie() || state.canUseTool()) {//if it's this player's turn
                if (state.canPlaceDie()) {
                    options.add(i, new PlaceDieOption(cli));
                    i++;
                }
                if (state.canUseTool()) {
                    options.add(i, new UseToolOption(cli));
                    i++;
                }
                if(state.canEndTurn()){
                    options.add(i, new EndTurnOption(cli));
                }
            } else {
                //if it's not this player's turn
                return options; //return an empty list of options
            }
        } else {
            //if the player hasn't chosen a window yet he has to choose one
            options.add(i, new ChooseWindowOption(cli));
        }

        return options;
    }
}
