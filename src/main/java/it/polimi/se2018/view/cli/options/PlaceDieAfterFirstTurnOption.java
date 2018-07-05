package it.polimi.se2018.view.cli.options;

import it.polimi.se2018.event.game.DraftAndPlaceAgainGameEvent;
import it.polimi.se2018.exceptions.NotValidPointException;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.Point;
import it.polimi.se2018.view.cli.CommandLineInterface;

import java.util.List;

public class PlaceDieAfterFirstTurnOption extends ComplexOption {



    /**
     * constructor.
     * @param cli the command line interface
     */
    public PlaceDieAfterFirstTurnOption(CommandLineInterface cli, String name, String select) {
        super(cli, name, select);
        subOptions = OptionFactory.buildPlaceDieOptions(cli);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int execute() {
        Die selectedDie;
        Point selectedPoint;

        List<Integer> choiceList = makeChoices();

        if(!choiceList.isEmpty()){
            selectedDie = cli.getDraftPool().getAllDice().get(choiceList.get(0) - 1);
            try {
                selectedPoint = new Point(choiceList.get(1), choiceList.get(2));
            } catch (NotValidPointException e) {
                return ERROR_CODE;
            }
            cli.notifyObservers(new DraftAndPlaceAgainGameEvent(selectedDie, selectedPoint, cli.getPlayer().getId()));
        }

        return 0;
    }
}
