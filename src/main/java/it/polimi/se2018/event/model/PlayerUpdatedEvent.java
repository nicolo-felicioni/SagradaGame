package it.polimi.se2018.event.model;

public class PlayerUpdatedEvent {

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
    public PlayerUpdatedEvent(String playerId, int favorTokens) {
        this.playerId = playerId;
        this.favorTokens = favorTokens;
    }

    /**
     * Player identifier getter.
     * @return the player identifier.
     */
    public String getPlayerId() {
        return playerId;
    }

    /**
     * Favor tokens getter.
     * @return the amount of favor tokens.
     */
    public int getFavorTokens() {
        return favorTokens;
    }
}
