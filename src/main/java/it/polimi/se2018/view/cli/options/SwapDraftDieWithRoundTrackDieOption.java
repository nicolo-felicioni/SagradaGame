package it.polimi.se2018.view.cli.options;

import it.polimi.se2018.event.game.SwapDraftDieWithRoundTrackDieGameEvent;
import it.polimi.se2018.exceptions.NotValidRoundException;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.view.cli.CommandLineInterface;
import it.polimi.se2018.view.cli.Printer;

import java.util.ArrayList;
import java.util.List;

public class SwapDraftDieWithRoundTrackDieOption extends ComplexOption {

    private static final int READ_DRAFT_DIE_CHOICE = 0;
    private static final int READ_ROUND_CHOICE = 1;
    private static final int READ_ROUND_DIE_CHOICE = 2;


    /**
     * constructor.
     * @param cli the command line interface
     */
    public SwapDraftDieWithRoundTrackDieOption(CommandLineInterface cli, String name, String select) {
        super(cli, name, select);
        this.subOptions = OptionFactory.buildSwapDraftDieOptions(cli);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int execute() {
        if (cli.getRoundTrack().isEmpty()) {
            Printer.println("Roundtrack empty!");
            return EXIT_CODE;
        } else {

            List<Integer> choices = makeChoices();

            if (!choices.isEmpty()) {

                try {
                    Die draftDie = cli.getDraftPool().getAllDice().get(choices.get(READ_DRAFT_DIE_CHOICE));
                    int round = choices.get(READ_ROUND_CHOICE);
                    Die roundDie = cli.getRoundTrack().getDice(round).get(choices.get(READ_ROUND_DIE_CHOICE));
                    cli.notifyObservers(new SwapDraftDieWithRoundTrackDieGameEvent(draftDie, roundDie, round, cli.getPlayer().getId()));

                } catch (NotValidRoundException e) {
                    return ERROR_CODE;
                }
            }
            return 0;
        }
    }

    /**
     *{@inheritDoc}
     */
    @Override
    protected List<Integer> makeChoices() {
        int choice;
        int round;
        List<Integer> choiceList = new ArrayList<>();
        Printer.println(selectMessage);
        int i = 0;
        while (i >= 0 && i < subOptions.size()) {
            choice = subOptions.get(i).execute();
            if (choice == EXIT_CODE) { //go back
                if (!choiceList.isEmpty())
                    choiceList.remove(i - 1);
                i--;
            } else {
                choiceList.add(i, choice);
                if (i == READ_ROUND_CHOICE) {
                    round = choice;
                    subOptions.add(i + 1, new ReadRoundTrackDieOption(cli, round));
                }
                i++;

            }
        }
        return choiceList;
    }
}
