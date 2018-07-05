package it.polimi.se2018.view.cli.options;

import it.polimi.se2018.event.game.MoveDieIgnoreValueRestrictionGameEvent;
import it.polimi.se2018.exceptions.NotValidPointException;
import it.polimi.se2018.model.Point;
import it.polimi.se2018.view.cli.CommandLineInterface;

import java.util.List;

public class MoveDieIgnoreValueOption extends ComplexOption {
    private static final int READ_FIRST_X_CHOICE = 0;
    private static final int READ_FIRST_Y_CHOICE = 1;
    private static final int READ_SECOND_X_CHOICE = 2;
    private static final int READ_SECOND_Y_CHOICE = 3;

    /**
     * constructor.
     * @param cli the command line interface
     */
    public MoveDieIgnoreValueOption(CommandLineInterface cli, String name, String select) {
        super(cli, name, select);
        subOptions = OptionFactory.buildMoveDieIgnoreValueOptions(cli);
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
