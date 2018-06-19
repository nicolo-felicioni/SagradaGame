package it.polimi.se2018.event.game;

import it.polimi.se2018.observer.game.GameEventObserver;

import java.util.ArrayList;
import java.util.List;

public class StartGameEvent implements GameEvent {

    /**
     * List of player identifiers.
     */
    private final List<String> playerIds;

    /**
     * Constructor.
     * @param playerIds a list of player identifiers.
     */
    public StartGameEvent(List<String> playerIds) {
        this.playerIds = new ArrayList<>();
        this.playerIds.addAll(playerIds);
    }

    /**
     * Getter of the player identifiers.
     * @return a list of the player identifiers.
     */
    public List<String> getPlayerIds() {
        List<String> ids = new ArrayList<>();
        ids.addAll(playerIds);
        return ids;
    }

    /**
     * Accept an observer. Visitor pattern.
     *
     * @param observer the observer to be called.
     */
    @Override
    public void accept(GameEventObserver observer) {

    }

}
