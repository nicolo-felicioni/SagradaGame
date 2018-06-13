package it.polimi.se2018.controller.updater;

import it.polimi.se2018.controller.ViewUpdaterInterface;
import it.polimi.se2018.view.View;

public class PlayerUpdater implements ViewUpdaterInterface {

    /**
     * The player identifier.
     */
    private String playerId;

    /**
     * Constructor.
     * @param playerId the player identifier.
     */
    public PlayerUpdater(String playerId) {
        this.playerId = playerId;
    }

    /**
     * Update a view.
     *
     * @param view the object that have to be modified by this command.
     */
    @Override
    public void update(View view) {
        view.updatePlayer(this.playerId);
    }
}
