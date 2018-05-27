package it.polimi.se2018.network.rmi;

import it.polimi.se2018.controller.CommandInterface;
import it.polimi.se2018.exceptions.NetworkException;
import it.polimi.se2018.network.server.SessionInterface;
import it.polimi.se2018.network.client.ClientInterface;
import it.polimi.se2018.network.utils.NetworkCommandObserver;

import java.rmi.RemoteException;

/**
 * @author davide yi xian hu
 */
public class RMIServerSession implements SessionInterface{

	/**
	 * RMI Client
	 */
	private ClientInterface client;

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
	 * @param client the client.
	 * @param server the server.
	 */
	public RMIServerSession(ClientInterface client) {
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
	 * Notify the server session controller.
	 */
	@Override
	public void notify(CommandInterface command) throws RemoteException, NetworkException{
		this.controller.handle(command);
	}

	@Override
	public void logout(String uid) {

	}

	@Override
	public String getUID() {
		return this.uid;
	}

	/**
	 * {@inheritDoc}
	 * Forward the request to the server controller.
	 */
	@Override
	public void handle(CommandInterface command) throws RemoteException, NetworkException {

	}
}
