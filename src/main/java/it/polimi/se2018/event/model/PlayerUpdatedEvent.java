package it.polimi.se2018.event.model;

public class PlayerUpdatedEvent {

    /**
     * Player identifier.
     */
    private String id;

    /**
     * Constructor
     * @param id the player identifier.
     */
    public PlayerUpdatedEvent(String id) {
        this.id = id;
    }

    /**
     * Player identifier getter
     * @return the player identifier.
     */
    public String getId() {
        return id;
    }
}
