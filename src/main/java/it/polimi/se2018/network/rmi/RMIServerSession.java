package it.polimi.se2018.network.rmi;

import it.polimi.se2018.event.Event;
import it.polimi.se2018.controller.ViewUpdaterInterface;
import it.polimi.se2018.exceptions.NetworkException;
import it.polimi.se2018.network.server.SessionInterface;
import it.polimi.se2018.network.utils.NetworkCommandObserver;
import it.polimi.se2018.network.utils.NetworkViewUpdaterObservable;
import it.polimi.se2018.network.utils.NetworkViewUpdaterObserver;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author davide yi xian hu
 */
public class RMIServerSession implements Remote, SessionInterface, NetworkViewUpdaterObservable {

	/**
	 * RMI Client
	 */
	private List<NetworkViewUpdaterObserver> viewUpdaterObservers;

	/**
	 * Server session controller.
	 */
	private List<NetworkCommandObserver> commandObservers;

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
		this.viewUpdaterObservers = new ArrayList<>();
		this.commandObservers = new ArrayList<>();
	}

	/**
	 * {@inheritDoc}
	 * Add a server session controller.
	 */
	@Override
	public void addCommandObserver(NetworkCommandObserver observer) throws RemoteException, NetworkException {
		this.commandObservers.add(observer);
	}

	/**
	 * {@inheritDoc}
	 * Notify the server session controller.
	 */
	@Override
	public void notify(Event command) throws RemoteException, NetworkException{
		for(NetworkCommandObserver obs : commandObservers) {
			obs.handle(command);
		}
	}

	/**
	 * Remove a network command observer.
	 *
	 * @param observer the network command observer.
	 * @throws RemoteException  if RMI errors occur during the connection.
	 * @throws NetworkException if any connection error occurs during the connection.
	 */
	@Override
	public void removeCommandObserver(NetworkCommandObserver observer) throws RemoteException, NetworkException {
		this.commandObservers.remove(observer);
	}


	/**
	 * {@inheritDoc}
	 * Add a client observer.
	 */
	@Override
	public void addViewUpdaterObserver(NetworkViewUpdaterObserver observer) throws RemoteException, NetworkException {
		this.viewUpdaterObservers.add(observer);
	}

	/**
	 * {@inheritDoc}
	 * Notify a view updater to the client;
	 */
	@Override
	public void notify(ViewUpdaterInterface updater) throws RemoteException, NetworkException {
		for(NetworkViewUpdaterObserver obs : viewUpdaterObservers) {
			obs.handle(updater);
		}
	}

	/**
	 * Remove a network view updater observer.
	 *
	 * @param observer the network view updater observer.
	 * @throws RemoteException  if RMI errors occur during the connection.
	 * @throws NetworkException if any connection error occurs during the connection.
	 */
	@Override
	public void removeViewUpdaterObserver(NetworkViewUpdaterObserver observer) throws RemoteException, NetworkException {
		this.viewUpdaterObservers.add(observer);
	}

	/**
	 * {@inheritDoc}
	 * Forward the request to the server controller.
	 */
	@Override
	public void handle(Event command) throws RemoteException, NetworkException {
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
