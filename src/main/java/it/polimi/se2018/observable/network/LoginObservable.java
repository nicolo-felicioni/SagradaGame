package it.polimi.se2018.observable.network;

import it.polimi.se2018.event.network.LoginEvent;
import it.polimi.se2018.observer.network.LoginObserver;

public interface LoginObservable {

    /**
     * Add a LoginObserver.
     * @param observer the LoginObserver.
     */
    void addObserver(LoginObserver observer);

    /**
     * Remove a LoginObserver.
     * @param observer the LoginObserver.
     */
    void removeObserver(LoginObserver observer);

    /**
     * Notify the LoginObservers an LoginEvent.
     * @param event the LoginEvent.
     */
    void notifyObservers(LoginEvent event);
}
