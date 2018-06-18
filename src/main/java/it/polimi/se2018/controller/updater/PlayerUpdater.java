package it.polimi.se2018.controller.updater;

import it.polimi.se2018.controller.ViewUpdaterInterface;
import it.polimi.se2018.view.View;

public class PlayerUpdater implements ViewUpdaterInterface {

    /**
     * The player identifier.
     */
    private String playerId;

    /**
     * The amount of favor tokens.
     */
    private int favorTokens;

    /**
     * Constructor.
     * @param playerId the player identifier.
     * @param favorTokens the amount of favor tokens.
     */
    public PlayerUpdater(String playerId, int favorTokens) {
        this.playerId = playerId;
        this.favorTokens = favorTokens;
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
