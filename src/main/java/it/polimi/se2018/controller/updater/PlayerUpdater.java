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
     * The player connection state.
     */
    private boolean connected;

    /**
     * Constructor.
     * @param playerId the player identifier.
     * @param favorTokens the amount of favor tokens.
     * @param connected the player connection state.
     */
    public PlayerUpdater(String playerId, int favorTokens, boolean connected) {
        this.playerId = playerId;
        this.favorTokens = favorTokens;
        this.connected = connected;
    }

    /**
     * Update a view.
     *
     * @param view the object that have to be modified by this command.
     */
    @Override
    public void update(View view) {
        view.updatePlayer(this.playerId, favorTokens, connected);
    }
}
