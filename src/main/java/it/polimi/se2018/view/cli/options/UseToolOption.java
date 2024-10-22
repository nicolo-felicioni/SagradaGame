package it.polimi.se2018.view.cli.options;


import it.polimi.se2018.event.game.UseToolCardGameEvent;
import it.polimi.se2018.model.CardPosition;
import it.polimi.se2018.view.cli.CommandLineInterface;
import it.polimi.se2018.view.cli.Printer;

public class UseToolOption extends SimpleOption {



    private static final String USE_TOOL_NAME = "Active a tool card";
    private static final String USE_TOOL_MESSAGE = "Select a tool card";
    private static final String ERROR_USE_TOOL = "There isn't any tool card with that number.";
    private static final String ERROR_COST_TOOL = "Not enough tokens!";

    /**
     * constructor.
     * @param cli the command line interface
     */
    public UseToolOption(CommandLineInterface cli) {
        super(cli);
        this.name = USE_TOOL_NAME;
    }


    /**
     * this method choose the tool card to activate.
     * @return the number of which tool card has been activated.
     */
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

        return 0;
    }

    /**
     * this method shows the possible tool cards.
     */
    private void showOptions(){
        Printer.println(USE_TOOL_MESSAGE);
        for (int i = 0; i < cli.getToolCards().length; i++) {
            int n = i + 1;
            Printer.print(n);
            Printer.println(":");
            Printer.print(cli.getToolCards()[i]);
        }
    }


    /**
     * this method reads the input of the user
     * @return the input of the user
     */
    private int readChoice(){
        int choice = cli.getKeyboard().readInt();
        if ((choice <= 0 && choice != EXIT_CODE) || choice > cli.getToolCards().length)
            return ERROR_CODE;
        return choice;
    }
}
