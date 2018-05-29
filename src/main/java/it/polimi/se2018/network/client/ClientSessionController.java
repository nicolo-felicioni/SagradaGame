package it.polimi.se2018.network.client;

import it.polimi.se2018.controller.CommandInterface;
import it.polimi.se2018.controller.ViewUpdaterInterface;
import it.polimi.se2018.exceptions.NetworkException;
import it.polimi.se2018.network.server.SessionControllerInterface;
import it.polimi.se2018.network.utils.NetworkCommandObserver;
import it.polimi.se2018.network.utils.NetworkViewUpdaterObserver;

import java.rmi.RemoteException;

/**
 * @author davide yi xian hu
 */
public class ClientSessionController implements SessionControllerInterface {

	/**
	 * The network client. It observe command created from the controller.
	 */
	private NetworkCommandObserver client;

	@Override
	public void addCommandObserverver(NetworkCommandObserver observer) throws RemoteException, NetworkException {
		this.client = observer;
	}

	@Override
	public void notify(CommandInterface command) throws RemoteException, NetworkException {
		this.client.handle(command);
	}

	@Override
	public String getUID() {
		return null;
	}

	@Override
	public void handle(CommandInterface command) throws RemoteException, NetworkException {
		this.notify(command);
	}

	@Override
	public void addViewUpdaterObserverver(NetworkViewUpdaterObserver observer) throws RemoteException, NetworkException {

	}

	@Override
	public void notify(ViewUpdaterInterface updater) throws RemoteException, NetworkException {

	}

	@Override
	public void handle(ViewUpdaterInterface updater) throws RemoteException, NetworkException {

	}
}
