package it.polimi.se2018.view.cli;

public class ReadDieOption extends SimpleOption {

    final static String WRONG_CHOICE_MESSAGE = "This die doesn't exists.";

    public ReadDieOption(CommandLineInterface cli) {
        super(cli);
    }

    @Override
    public int executeOption() {
        int choice;
        int n = 0;
        boolean validChoice;

        do {
            for (int i = 0; i < cli.getDraftPool().size(); i++) {
                n = i + 1;
                Printer.print(" " + n + ": ");
                Printer.print(cli.getDraftPool().getAllDice().get(i));
            }
            Printer.print("\n");
            Printer.println(EXIT_CODE + GO_BACK_MESSAGE);


            choice = cli.getKeyboard().readInt();
            if ((choice <= 0 && choice != EXIT_CODE) || choice > n) { //if the choice isn't in the range
                Printer.println(WRONG_CHOICE_MESSAGE);
                validChoice = false;
            } else
                validChoice = true;

        } while (!validChoice);

        return choice;
    }
}
