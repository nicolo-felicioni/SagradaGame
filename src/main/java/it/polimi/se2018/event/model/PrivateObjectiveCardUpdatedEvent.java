package it.polimi.se2018.event.model;

import it.polimi.se2018.model.PrivateObjectiveCard;

public class PrivateObjectiveCardUpdatedEvent {

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
    public PrivateObjectiveCardUpdatedEvent(String playerId, PrivateObjectiveCard card) {
        this.playerId = playerId;
        this.card = card;
    }

    /**
     * Player identifier getter.
     * @return the player identifier.
     */
    public String getPlayerId() {
        return playerId;
    }

    /**
     * Private objective card getter.
     * @return the private objetive card.
     */
    public PrivateObjectiveCard getCard() {
        return card;
    }
}
