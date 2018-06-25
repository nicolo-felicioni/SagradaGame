package it.polimi.se2018.event.game;

import it.polimi.se2018.observer.game.GameEventObserver;

public class ReconnectGameEvent extends AbstractPlayerGameEvent{

    /**
     * Constructor.
     * @param id the identifier of the player.
     */
    public ReconnectGameEvent(String id) {
        super(id);
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
