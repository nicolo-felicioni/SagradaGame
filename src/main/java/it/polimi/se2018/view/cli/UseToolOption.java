package it.polimi.se2018.view.cli;

public class UseToolOption extends Option {


    //TODO - TUTTA LA CLASSE ANCORA DA RIFARE


    private static final String USE_TOOL_NAME = "Use a tool card";
    private static final String USE_TOOL_MESSAGE = "Select a tool card";
    private static final String ERROR_USE_TOOL = "There isn't any tool card with that number.";

    public UseToolOption(CommandLineInterface cli) {
        super(cli);
        this.name = USE_TOOL_NAME;
    }

    @Override
    public int executeOption() {
        return 0;
    }

    private int selectToolCard() {
        boolean goodChoice = true;
        int choice;
        do {

            Printer.println(USE_TOOL_MESSAGE);
            for (int i = 0; i < cli.getToolCards().length; i++) {
                int n = i + 1;
                Printer.print(n);
                Printer.println(":");
                Printer.print(cli.getToolCards()[i]);
            }
            choice = cli.getKeyboard().readInt();
            if (choice <= 0 || choice > cli.getToolCards().length) {
                Printer.println(ERROR_USE_TOOL);
                goodChoice = false;
            }
        } while (!goodChoice);

        return (choice - 1);
    }
}
