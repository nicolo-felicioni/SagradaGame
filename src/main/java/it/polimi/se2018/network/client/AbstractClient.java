package it.polimi.se2018.network.client;

import it.polimi.se2018.controller.ViewUpdaterInterface;
import it.polimi.se2018.exceptions.NetworkException;
import it.polimi.se2018.network.utils.NetworkViewUpdaterObserver;

import java.rmi.RemoteException;

/**
 * @author davide yi xian hu
 */
public abstract class AbstractClient implements ClientInterface{

	/**
	 * Session controller. It handle the requests from the server.
	 */
	private NetworkViewUpdaterObserver controller;

	/**
	 * {@inheritDoc}
	 * Add a client network controller.
	 */
	@Override
	public void addViewUpdaterObserver(NetworkViewUpdaterObserver observer) throws RemoteException, NetworkException {
		this.controller = observer;
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
	 * Forward the view updater to the controller.
	 */
	public void handle(ViewUpdaterInterface updater) throws RemoteException, NetworkException {
		this.notify(updater);
	}
}
