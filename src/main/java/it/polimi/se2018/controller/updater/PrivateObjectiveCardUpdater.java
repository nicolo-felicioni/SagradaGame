package it.polimi.se2018.controller.updater;

import it.polimi.se2018.controller.ViewUpdaterInterface;
import it.polimi.se2018.model.PrivateObjectiveCard;
import it.polimi.se2018.view.View;

public class PrivateObjectiveCardUpdater implements ViewUpdaterInterface {

    /**
     * The player identifier.
     */
    private String playerId;

    /**
     * The private objective card.
     */
    private PrivateObjectiveCard card;

    /**
     * Constructor.
     * @param playerId the player identifier.
     * @param card the private objective card.
     */
    public PrivateObjectiveCardUpdater(String playerId, PrivateObjectiveCard card) {
        this.playerId = playerId;
        this.card = card;
    }

    /**
     * Update a view.
     *
     * @param view the object that have to be modified by this command.
     */
    @Override
    public void update(View view) {
        view.updatePrivateObjectiveCard(playerId, card);
    }
}
