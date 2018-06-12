package it.polimi.se2018.network.server;

import it.polimi.se2018.network.utils.*;
import it.polimi.se2018.observable.GameEventObservable;
import it.polimi.se2018.observer.GameEventObserver;
import it.polimi.se2018.observer.NetworkGameEventObserver;

import java.io.Serializable;
import java.rmi.Remote;

/**
 * @author davide yi xian hu
 */
public interface SessionInterface extends NetworkViewUpdaterObserver, NetworkGameEventObserver, NetworkViewUpdaterObservable, GameEventObservable, Remote{

	String getUID();
}
