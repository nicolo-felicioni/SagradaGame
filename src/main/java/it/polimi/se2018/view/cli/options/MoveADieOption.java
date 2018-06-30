package it.polimi.se2018.view.cli.options;

import it.polimi.se2018.event.game.MoveDieRespectAllRestrictionsGameEvent;
import it.polimi.se2018.exceptions.NotValidPointException;
import it.polimi.se2018.model.Point;
import it.polimi.se2018.view.cli.CommandLineInterface;

import java.util.List;

public class MoveADieOption extends ComplexOption {
    private static final String NAME = "Move a die respecting all restrictions.";
    private static final String SELECT = "Select the point of the die you want to move, then select the space where you want to put it.";

    private static final int READ_FIRST_X_CHOICE = 0;
    private static final int READ_FIRST_Y_CHOICE = 1;
    private static final int READ_SECOND_X_CHOICE = 2;
    private static final int READ_SECOND_Y_CHOICE = 3;

    public MoveADieOption(CommandLineInterface cli) {
        super(cli);
        this.subOptions = OptionFactory.buildMoveDieOptions(cli);
    }

    @Override
    protected void setSelectMessage() {
        this.selectMessage = SELECT;
    }

    @Override
    protected void setName() {
        this.name = NAME;
    }


    @Override
    public int execute() {
        List<Integer> choices = makeChoices();
        if(!choices.isEmpty()){
            try {
                Point firstPoint = new Point(choices.get(READ_FIRST_X_CHOICE), choices.get(READ_FIRST_Y_CHOICE));
                Point secondPoint = new Point(choices.get(READ_SECOND_X_CHOICE), choices.get(READ_SECOND_Y_CHOICE));
                cli.notifyObservers(new MoveDieRespectAllRestrictionsGameEvent(firstPoint, secondPoint, cli.getPlayer().getId()));
            } catch (NotValidPointException e) {
                return ERROR_CODE;
            }


        }

        return 0;
    }
}
