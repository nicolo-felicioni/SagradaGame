package it.polimi.se2018.network.server;

import it.polimi.se2018.controller.ViewUpdaterObserver;
import it.polimi.se2018.observable.game.GameEventObservable;
import it.polimi.se2018.observable.network.DisconnectObservable;
import it.polimi.se2018.observer.game.GameEventObserver;

/**
 * @author Davide Yi Xian Hu
 */
public interface SessionInterface extends ViewUpdaterObserver, GameEventObservable, GameEventObserver, DisconnectObservable {

	String getUID();
}
