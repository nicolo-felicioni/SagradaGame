package it.polimi.se2018.view.cli.options;

import it.polimi.se2018.event.game.MoveDieMatchColorRoundTrackGameEvent;
import it.polimi.se2018.exceptions.NotValidPointException;
import it.polimi.se2018.model.Point;
import it.polimi.se2018.view.cli.CommandLineInterface;

import java.util.List;

public class MoveDieMatchColorOnRoundTrackOption extends MoveADieOption {



    /**
     * constructor.
     * @param cli the command line interface
     */
    public MoveDieMatchColorOnRoundTrackOption(CommandLineInterface cli, String name, String select) {
        super(cli, name, select);
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
                cli.notifyObservers(new MoveDieMatchColorRoundTrackGameEvent(firstPoint, secondPoint, cli.getPlayer().getId()));
            } catch (NotValidPointException e) {
                return ERROR_CODE;
            }
            return 0;
        }

        return 1;
    }
}
