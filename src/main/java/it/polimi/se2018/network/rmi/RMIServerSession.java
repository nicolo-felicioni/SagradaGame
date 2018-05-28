package it.polimi.se2018.network.rmi;

import it.polimi.se2018.controller.CommandInterface;
import it.polimi.se2018.controller.ViewUpdaterInterface;
import it.polimi.se2018.exceptions.NetworkException;
import it.polimi.se2018.network.server.SessionInterface;
import it.polimi.se2018.network.client.ClientInterface;
import it.polimi.se2018.network.utils.NetworkCommandObserver;
import it.polimi.se2018.network.utils.NetworkViewUpdaterObserver;

import java.rmi.RemoteException;

/**
 * @author davide yi xian hu
 */
public class RMIServerSession implements SessionInterface{

	/**
	 * RMI Client
	 */
	private NetworkViewUpdaterObserver client;

	/**
	 * RMI Server session.
	 */
	private NetworkCommandObserver controller;

	/**
	 * Unique identifier of the client.
	 */
	private String uid;

	/**
	 * Default constructor. Create a RMI session between the client and the server.
	 *
	 * @param client the client, a network view updater observer.
	 */
	public RMIServerSession(NetworkViewUpdaterObserver client) {
		this.client = client;
		this.controller = null;
	}

	/**
	 * {@inheritDoc}
	 * Add a server session controller.
	 */
	@Override
	public void addCommandObserverver(NetworkCommandObserver observer) throws RemoteException, NetworkException {
		this.controller = observer;
	}

	/**
	 * {@inheritDoc}
	 * Add a client observer.
	 */
	@Override
	public void addViewUpdaterObserverver(NetworkViewUpdaterObserver observer) throws RemoteException, NetworkException {
		this.client = observer;
	}

	/**
	 * {@inheritDoc}
	 * Notify the server session controller.
	 */
	@Override
	public void notify(CommandInterface command) throws RemoteException, NetworkException{
		this.controller.handle(command);
	}

	/**
	 * {@inheritDoc}
	 * Notify a view updater to the client;
	 */
	@Override
	public void notify(ViewUpdaterInterface updater) throws RemoteException, NetworkException {
		this.client.handle(updater);
	}

	/**
	 * {@inheritDoc}
	 * Forward the request to the server controller.
	 */
	@Override
	public void handle(CommandInterface command) throws RemoteException, NetworkException {
		this.notify(command);
	}

	/**
	 * {@inheritDoc}
	 * Forward the view updater to the client.
	 */
	public void handle(ViewUpdaterInterface updater) throws RemoteException, NetworkException {
		this.notify(updater);
	}

	@Override
	public void logout(String uid) {

	}

	@Override
	public String getUID() {
		return this.uid;
	}


}
