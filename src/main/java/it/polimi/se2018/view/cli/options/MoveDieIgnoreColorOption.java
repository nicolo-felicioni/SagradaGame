package it.polimi.se2018.view.cli.options;

import it.polimi.se2018.event.game.MoveDieIgnoreColorRestrictionGameEvent;
import it.polimi.se2018.exceptions.NotValidPointException;
import it.polimi.se2018.model.Point;
import it.polimi.se2018.view.cli.CommandLineInterface;

import java.util.List;

public class MoveDieIgnoreColorOption extends ComplexOption {

    private static final int READ_FIRST_X_CHOICE = 0;
    private static final int READ_FIRST_Y_CHOICE = 1;
    private static final int READ_SECOND_X_CHOICE = 2;
    private static final int READ_SECOND_Y_CHOICE = 3;

    /**
     * constructor.
     * @param cli the command line interface
     */
    public MoveDieIgnoreColorOption(CommandLineInterface cli, String name, String select) {
        super(cli, name, select);
        subOptions = OptionFactory.buildMoveDieIgnoreColorOptions(cli);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int execute() {

        List<Integer> choices = makeChoices();

        if(!choices.isEmpty()){
            Point firstPoint;
            Point secondPoint;
            try {
                firstPoint = new Point(choices.get(READ_FIRST_X_CHOICE), choices.get(READ_FIRST_Y_CHOICE));
                secondPoint = new Point(choices.get(READ_SECOND_X_CHOICE), choices.get(READ_SECOND_Y_CHOICE));

                cli.notifyObservers(new MoveDieIgnoreColorRestrictionGameEvent
                        (firstPoint, secondPoint, cli.getPlayer().getId()));

            } catch (NotValidPointException e) {
                return ERROR_CODE;
            }
        }
        return 0;
    }



}
