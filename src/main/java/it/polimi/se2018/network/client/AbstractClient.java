package it.polimi.se2018.network.client;

import it.polimi.se2018.controller.CommandInterface;
import it.polimi.se2018.controller.ViewUpdaterInterface;
import it.polimi.se2018.exceptions.NetworkException;
import it.polimi.se2018.network.utils.NetworkCommandObserver;
import it.polimi.se2018.network.utils.NetworkViewUpdaterObserver;

import java.rmi.RemoteException;

/**
 * @author davide yi xian hu
 */
public abstract class AbstractClient implements ClientInterface{

	/**
	 * Server session. It handle the requests to the server.
	 */
	private NetworkCommandObserver serverSession;

	/**
	 * Session controller. It handle the requests from the server.
	 */
	private NetworkViewUpdaterObserver controller;

	/**
	 * {@inheritDoc}
	 * Add a server session.
	 */
	@Override
	public void addCommandObserverver(NetworkCommandObserver observer) throws RemoteException, NetworkException {
		this.serverSession = observer;
	}

	/**
	 * {@inheritDoc}
	 * Add a client network controller.
	 */
	@Override
	public void addViewUpdaterObserverver(NetworkViewUpdaterObserver observer) throws RemoteException, NetworkException {
		this.controller = observer;
	}

	/**
	 * {@inheritDoc}
	 * Notify a command to the server session.
	 */
	@Override
	public void notify(CommandInterface command) throws RemoteException, NetworkException {
		this.serverSession.handle(command);
	}

	/**
	 * {@inheritDoc}
	 * Notify a view updater to the client controller;
	 */
	@Override
	public void notify(ViewUpdaterInterface updater) throws RemoteException, NetworkException {
		this.controller.handle(updater);
	}

	/**
	 * {@inheritDoc}
	 * Forward the command to the server session.
	 */
	public void handle(CommandInterface command) throws RemoteException, NetworkException {
		this.serverSession.handle(command);
	}

	/**
	 * {@inheritDoc}
	 * Forward the view updater to the controller.
	 */
	public void handle(ViewUpdaterInterface updater) throws RemoteException, NetworkException {
		this.controller.handle(updater);
	}
}
