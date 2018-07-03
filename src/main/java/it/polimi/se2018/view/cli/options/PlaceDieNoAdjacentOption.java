package it.polimi.se2018.view.cli.options;

import it.polimi.se2018.event.game.DraftAndPlaceGameEvent;
import it.polimi.se2018.event.game.DraftAndPlaceNoAdjacentGameEvent;
import it.polimi.se2018.exceptions.NotValidPointException;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.Point;
import it.polimi.se2018.view.cli.CommandLineInterface;

import java.util.List;

public class PlaceDieNoAdjacentOption extends ComplexOption {

    private static final String NAME = "Place a die in a space without adjacent dice";
    private static final String SELECT = "Select a die.";


    public PlaceDieNoAdjacentOption(CommandLineInterface cli) {
        super(cli);
        this.subOptions = OptionFactory.buildPlaceDieOptions(cli);
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
            cli.notifyObservers(new DraftAndPlaceNoAdjacentGameEvent(selectedDie, selectedPoint, cli.getPlayer().getId()));
        }

        return 0;    }
}
