package it.polimi.se2018.view.cli.options;


import it.polimi.se2018.event.game.UseToolCardGameEvent;
import it.polimi.se2018.model.CardPosition;
import it.polimi.se2018.model.ToolCard;
import it.polimi.se2018.view.cli.CommandLineInterface;
import it.polimi.se2018.view.cli.Printer;

import java.util.ArrayList;
import java.util.List;

public class UseToolOption extends SimpleOption {



    private static final String USE_TOOL_NAME = "Use a tool card";
    private static final String USE_TOOL_MESSAGE = "Select a tool card";
    private static final String ERROR_USE_TOOL = "There isn't any tool card with that number.";
    private static final String ERROR_COST_TOOL = "Not enough tokens!";

    public UseToolOption(CommandLineInterface cli) {
        super(cli);
        this.name = USE_TOOL_NAME;
    }


    @Override
    public int execute() {
        int choice;
        do{
            showOptions();
            choice=readChoice();
            if(choice == ERROR_CODE)
                Printer.println(ERROR_USE_TOOL);
        }while(choice == ERROR_CODE);

        if(choice == EXIT_CODE)
            return EXIT_CODE;


        CardPosition position = CardPosition.fromInt(choice - 1 );

        if(cli.getPlayer().getTokens() < cli.getToolCards()[choice - 1].cost()){
            Printer.println(ERROR_COST_TOOL);
            return ERROR_CODE;
        }

        cli.notifyObservers(new UseToolCardGameEvent(position, cli.getPlayer().getId()));
        Printer.println("DEBUG: Mandato evento useTool"); //todo
        Printer.println(position.name()); //todo

        return 0;
    }

    private void showOptions(){
        Printer.println(USE_TOOL_MESSAGE);
        for (int i = 0; i < cli.getToolCards().length; i++) {
            int n = i + 1;
            Printer.print(n);
            Printer.println(":");
            Printer.print(cli.getToolCards()[i]);
        }
    }


    private int readChoice(){
        int choice = cli.getKeyboard().readInt();
        if ((choice <= 0 && choice != EXIT_CODE) || choice > cli.getToolCards().length)
            return ERROR_CODE;
        return choice;
    }
}
