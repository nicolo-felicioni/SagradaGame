package it.polimi.se2018.event.model;

import it.polimi.se2018.model.WindowPattern;
import it.polimi.se2018.model.WindowPatternPosition;

public class WindowPatternUpdatedEvent {

    /**
     * The player identifier.
     */
    private final String playerId;

    /**
     * The window pattern.
     */
    private final WindowPattern windowPattern;

    /**
     * Window pattern position;
     */
    private final WindowPatternPosition position;

    /**
     * Constructor.
     * @param playerId the player identifier.
     * @param windowPattern the window pattern.
     */
    public WindowPatternUpdatedEvent(String playerId, WindowPattern windowPattern){
        this.playerId = playerId;
        this.windowPattern=windowPattern.cloneWindowPattern();
        this.position = WindowPatternPosition.CHOSEN;
    }

    /**
     * Constructor.
     * @param playerId the player identifier.
     * @param windowPattern the window pattern.
     */
    public WindowPatternUpdatedEvent(String playerId, WindowPattern windowPattern, WindowPatternPosition position){
        this.playerId = playerId;
        this.windowPattern=windowPattern.cloneWindowPattern();
        this.position = position;
    }

    /**
     * Player identifier getter.
     * @return the player identifier.
     */
    public String getPlayerId() {
        return playerId;
    }

    /**
     * Window pattern getter.
     * @return the window pattern.
     */
    public WindowPattern getWindowPattern() {
        return windowPattern.cloneWindowPattern();
    }

    /**
     * Window pattern position getter.
     * @return the window pattern position.
     */
    public WindowPatternPosition getPosition() {
        return position;
    }
}
