package it.polimi.se2018.network.client;

import it.polimi.se2018.controller.CommandInterface;
import it.polimi.se2018.exceptions.NetworkException;
import it.polimi.se2018.network.utils.NetworkCommandObserver;

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
	//private NetworkCommandObserver controller;

	/**
	 * {@inheritDoc}
	 * Add a server session.
	 */
	public void addCommandObserverver(NetworkCommandObserver observer) throws RemoteException, NetworkException {
		this.serverSession = observer;
	}

	/**
	 * {@inheritDoc}
	 * Notify the server session.
	 */
	public void notify(CommandInterface command) throws RemoteException, NetworkException {
		this.serverSession.handle(command);
	}

	/**
	 * {@inheritDoc}
	 * Forward the request to the server session.
	 */
	public void handle(CommandInterface command) throws RemoteException, NetworkException {
		this.serverSession.handle(command);
	}

}
