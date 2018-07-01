package it.polimi.se2018.view.cli.options;

import it.polimi.se2018.event.game.MoveDieIgnoreColorRestrictionGameEvent;
import it.polimi.se2018.exceptions.NotValidPointException;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.Point;
import it.polimi.se2018.view.cli.CommandLineInterface;
import it.polimi.se2018.view.cli.Printer;
import it.polimi.se2018.view.cli.options.ComplexOption;
import it.polimi.se2018.view.cli.options.ReadXOption;
import it.polimi.se2018.view.cli.options.ReadYOption;
import it.polimi.se2018.view.cli.options.SimpleOption;

import java.util.ArrayList;
import java.util.List;

public class MoveDieIgnoreColorOption extends ComplexOption {

    private static final String NAME = "Move a die ignoring value restriction.";

    private static final String MOVE_DIE_MESSAGE = "Select the two points on the window pattern.";
    private static final int READ_FIRST_X_CHOICE = 0;
    private static final int READ_FIRST_Y_CHOICE = 1;
    private static final int READ_SECOND_X_CHOICE = 2;
    private static final int READ_SECOND_Y_CHOICE = 3;

    public MoveDieIgnoreColorOption(CommandLineInterface cli) {
        super(cli);
        subOptions = OptionFactory.buildMoveDieIgnoreColorOptions(cli);
    }

    @Override
    protected void setName() {
        this.name = NAME;
    }

    @Override
    protected void setSelectMessage() {
        this.selectMessage = MOVE_DIE_MESSAGE;
    }

    @Override
    public int execute() {

        List<Integer> choices = makeChoices();

        if(!choices.isEmpty()){
            Point firstPoint, secondPoint;
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