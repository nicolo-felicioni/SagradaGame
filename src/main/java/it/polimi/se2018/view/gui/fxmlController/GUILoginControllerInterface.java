package it.polimi.se2018.view.gui.fxmlController;

import it.polimi.se2018.observable.GameEventObservable;
import it.polimi.se2018.observable.network.ConnectRMIObservable;
import it.polimi.se2018.observable.network.ConnectSocketObservable;
import it.polimi.se2018.observable.network.LoginObservable;
import it.polimi.se2018.observer.network.ConnectSocketObserver;

public interface GUILoginControllerInterface extends GameEventObservable, LoginObservable, ConnectRMIObservable, ConnectSocketObservable {
}
