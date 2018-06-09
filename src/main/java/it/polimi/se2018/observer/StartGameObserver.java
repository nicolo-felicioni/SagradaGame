package it.polimi.se2018.observer;

import it.polimi.se2018.event.StartGameEvent;

public interface StartGameObserver {

    /**
     * Handle a StartGameEvent.
     * @param event the StartGameEvent.
     */
    void handle(StartGameEvent event);

}
