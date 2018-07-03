package it.polimi.se2018.view.cli.options;

import it.polimi.se2018.event.game.ChooseDraftDieValueGameEvent;
import it.polimi.se2018.event.game.DraftAndPlaceGameEvent;
import it.polimi.se2018.event.game.SwapDraftDieWithDiceBagDieGameEvent;
import it.polimi.se2018.exceptions.NotValidPointException;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.DieValue;
import it.polimi.se2018.model.Point;
import it.polimi.se2018.view.cli.CommandLineInterface;
import it.polimi.se2018.view.cli.Printer;

import java.util.ArrayList;
import java.util.List;

public class ReturnDieAndGetNewOption extends ComplexOption {

    private static final String NAME = "Return a die and get a new one from the dice bag.";
    private static final String SELECT = "Select the die to return.";
    private static final int READ_DIE_TO_SWAP_CHOICE = 0;
    private static final int READ_DIE_VALUE = 1;
    private static final int READ_X = 2;
    private static final int READ_Y = 3;
    private Die draftedDie;


    public ReturnDieAndGetNewOption(CommandLineInterface cli) {
        super(cli);
        subOptions = new ArrayList<>();
        subOptions.add(new ReadDraftPoolDieOption(cli));
    }


    @Override
    protected void setName() {
        this.name = NAME;
    }

    @Override
    protected void setSelectMessage() {
        this.selectMessage = SELECT;
    }

    @Override
    public int execute() {
        List<Integer> choices = makeChoices();
        if(!choices.isEmpty()){
            int value = choices.get(READ_DIE_VALUE);
            int x = choices.get(READ_X);
            int y = choices.get(READ_Y);

            try {
                Point p = new Point(x, y);
                Die newDie = new Die(draftedDie.getColor(), DieValue.fromInt(choices.get(READ_DIE_VALUE)));

                cli.notifyObservers(new DraftAndPlaceGameEvent(newDie, p, cli.getPlayer().getId()));

            } catch (NotValidPointException e) {
                e.printStackTrace();
            }

        }

        return 0;

    }


    @Override
    protected List<Integer> makeChoices() {
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
                if(i == READ_DIE_TO_SWAP_CHOICE){
                    cli.notifyObservers(new SwapDraftDieWithDiceBagDieGameEvent(cli.getDraftPool().getAllDice().get(choice-1),cli.getPlayer().getId()));

                    while(cli.getDraftPool().getDraftedDie()==null){
                        synchronized (cli.getDraftedDieLock()){
                            try {
                                cli.getDraftedDieLock().wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                OptionFactory.addReturnDieAndGetNewOptions(subOptions, cli, cli.getDraftPool().getDraftedDie());

                }else if(i == READ_DIE_VALUE){
                    cli.notifyObservers(new ChooseDraftDieValueGameEvent(cli.getDraftPool().getDraftedDie(), DieValue.fromInt(choice), cli.getPlayer().getId()));
                }else if(i == READ_X){
                    draftedDie = cli.getDraftPool().getDraftedDie();
                }

                i++;
            }
        }
        return choiceList;
    }

}
