package it.polimi.se2018.view.cli.options;

import it.polimi.se2018.event.game.MoveDieRespectAllRestrictionsGameEvent;
import it.polimi.se2018.exceptions.NotValidPointException;
import it.polimi.se2018.model.Point;
import it.polimi.se2018.view.cli.CommandLineInterface;

import java.util.List;

public class MoveADieOption extends ComplexOption {

    protected static final int READ_FIRST_X_CHOICE = 0;
    protected static final int READ_FIRST_Y_CHOICE = 1;
    protected static final int READ_SECOND_X_CHOICE = 2;
    protected static final int READ_SECOND_Y_CHOICE = 3;

    /**
     * constructor.
     * @param cli the command line interface
     */
    public MoveADieOption(CommandLineInterface cli, String name, String select) {
        super(cli, name, select);
        this.subOptions = OptionFactory.buildMoveDieOptions(cli);
    }


    /**
     * {@inheritDoc}
     */
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
