package it.polimi.se2018.view.cli;

import it.polimi.se2018.event.game.DraftAndPlaceGameEvent;
import it.polimi.se2018.exceptions.NotValidPointException;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.Point;

import java.util.ArrayList;
import java.util.List;

public class PlaceDieOption extends ComplexOption {

    private static final String PLACE_DIE_NAME = "Place a die.";
    private static final int N_OF_CHOICES = 3;
    private int[] choiceVector;

    public PlaceDieOption(CommandLineInterface cli) {
        super(cli);
        this.name = PLACE_DIE_NAME;
        this.subOptions = buildPlaceDieOptions();
    }


    private List<Option> buildPlaceDieOptions() {
        List<Option> options = new ArrayList<>();
        options.add(0, new ReadDieOption(cli));
        options.add(1, new ReadXOption(cli));
        options.add(2, new ReadYOption(cli));
        return options;
    }

    @Override
    public int executeOption() {
        int choice;
        Die selectedDie;
        Point selectedPoint;
        int[] choiceVector = new int[3]; //this vector contains the choice of the die and of the two coord.


        int i = 0;
        while (i >= 0 && i < subOptions.size()) {
            choice = subOptions.get(i).executeOption();
            if (choice == EXIT_CODE) //go back
                i--;
            else {
                choiceVector[i] = choice;
                i++;
            }
        }
        selectedDie = cli.getDraftPool().getAllDice().get(choiceVector[0]);
        try {
            selectedPoint = new Point(choiceVector[1], choiceVector[2]);
        } catch (NotValidPointException e) {
            return ERROR_CODE;
        }


        cli.notifyObservers(new DraftAndPlaceGameEvent(selectedDie, selectedPoint, cli.getPlayer().getId()));

        return 0;

    }
}
