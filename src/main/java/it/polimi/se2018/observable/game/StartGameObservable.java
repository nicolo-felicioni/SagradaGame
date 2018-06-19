package it.polimi.se2018.observable.game;

import it.polimi.se2018.event.game.StartGameEvent;
import it.polimi.se2018.observer.game.StartGameObserver;

public interface StartGameObservable {

    /**
     * Add a StartGameObserver.
     * @param observer the StartGameObserver.
     */
    void addObserver(StartGameObserver observer);

    /**
     * Remove a StartGameObserver.
     * @param observer the StartGameObserver.
     */
    void removeObserver(StartGameObserver observer);

    /**
     * Notify the StartGameObserver an StartGameEvent.
     * @param event the StartGameEvent.
     */
    void notifyObservers(StartGameEvent event);

}
