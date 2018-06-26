package it.polimi.se2018.controller.updater;

import it.polimi.se2018.controller.ViewUpdaterInterface;
import it.polimi.se2018.view.View;

public class ErrorMessageUpdater implements ViewUpdaterInterface {

    /**
     * The player identifier
     */
    private String playerId;

    /**
     * The error message;
     */
    private String message;

    /**
     * Constructor.
     * @param playerId the player identifier.
     * @param message the error message.
     */
    public ErrorMessageUpdater(String playerId, String message) {
        this.playerId = playerId;
        this.message = message;
    }

    /**
     * Update a view.
     *
     * @param view the object that have to be modified by this command.
     */
    @Override
    public void update(View view) {

    }
}
