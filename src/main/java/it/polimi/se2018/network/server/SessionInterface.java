package it.polimi.se2018.network.server;

import it.polimi.se2018.controller.ViewUpdaterObserver;
import it.polimi.se2018.observable.GameEventObservable;
import it.polimi.se2018.observer.GameEventObserver;

import java.rmi.Remote;

/**
 * @author davide yi xian hu
 */
public interface SessionInterface extends ViewUpdaterObserver, GameEventObservable {

	String getUID();
}
