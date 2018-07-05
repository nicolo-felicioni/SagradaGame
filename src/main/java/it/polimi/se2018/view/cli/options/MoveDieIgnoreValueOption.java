package it.polimi.se2018.view.cli.options;

import it.polimi.se2018.event.game.MoveDieIgnoreColorRestrictionGameEvent;
import it.polimi.se2018.event.game.MoveDieIgnoreValueRestrictionGameEvent;
import it.polimi.se2018.exceptions.NotValidPointException;
import it.polimi.se2018.model.Point;
import it.polimi.se2018.view.cli.CommandLineInterface;

import java.util.List;

public class MoveDieIgnoreValueOption extends ComplexOption {
    private static final String MOVE_DIE_MESSAGE = "Select the two points on the window pattern.";
    private static final String NAME = "Move a die ignoring value restriction.";
    private static final int READ_FIRST_X_CHOICE = 0;
    private static final int READ_FIRST_Y_CHOICE = 1;
    private static final int READ_SECOND_X_CHOICE = 2;
    private static final int READ_SECOND_Y_CHOICE = 3;

    /**
     * constructor.
     * @param cli the command line interface
     */
    public MoveDieIgnoreValueOption(CommandLineInterface cli) {
        super(cli);
        subOptions = OptionFactory.buildMoveDieIgnoreValueOptions(cli);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setName() {
        this.name = NAME;
        this.selectMessage = MOVE_DIE_MESSAGE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setSelectMessage() {
        this.selectMessage = MOVE_DIE_MESSAGE;
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

                cli.notifyObservers(new MoveDieIgnoreValueRestrictionGameEvent
                        (firstPoint, secondPoint, cli.getPlayer().getId()));

            } catch (NotValidPointException e) {
                return ERROR_CODE;
            }
        }
        return 0;
    }
}
