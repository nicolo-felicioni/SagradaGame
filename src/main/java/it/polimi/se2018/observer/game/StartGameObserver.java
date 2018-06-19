package it.polimi.se2018.observer.game;

import it.polimi.se2018.event.game.StartGameEvent;

public interface StartGameObserver {

    /**
     * Handle a StartGameEvent.
     * @param event the StartGameEvent.
     */
    void handle(StartGameEvent event);

}
