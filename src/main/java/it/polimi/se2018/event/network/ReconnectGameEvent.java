package it.polimi.se2018.event.network;

public class ReconnectGameEvent {

    /**
     * The identifier of the player.
     */
    private String playerId;

    /**
     * Constructor.
     * @param id the identifier of the player.
     */
    public ReconnectGameEvent(String id) {
        this.playerId = id;
    }

    /**
     * Getter of the identifier of the player.
     * @return the identifier of the player.
     */
    public String getPlayerId() {
        return playerId;
    }



}
