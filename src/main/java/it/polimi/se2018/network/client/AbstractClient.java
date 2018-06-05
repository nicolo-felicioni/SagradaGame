package it.polimi.se2018.network.client;

import it.polimi.se2018.controller.ViewUpdaterInterface;
import it.polimi.se2018.exceptions.NetworkException;
import it.polimi.se2018.network.utils.NetworkViewUpdaterObserver;

import java.rmi.RemoteException;
import java.util.List;

/**
 * @author davide yi xian hu
 */
public abstract class AbstractClient implements ClientInterface{

	/**
	 * Session controller. It handle the requests from the server.
	 */
	private List<NetworkViewUpdaterObserver> observers;

	/**
	 * Unique identifier of the client.
	 */
	private String uid;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addViewUpdaterObserver(NetworkViewUpdaterObserver observer) throws RemoteException, NetworkException {
		this.observers.add(observer);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeViewUpdaterObserver(NetworkViewUpdaterObserver observer) throws RemoteException, NetworkException {
		this.observers.remove(observer);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void notify(ViewUpdaterInterface updater) throws RemoteException, NetworkException {
		for(NetworkViewUpdaterObserver obs : observers) {
			obs.handle(updater);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void handle(ViewUpdaterInterface updater) throws RemoteException, NetworkException {
		this.notify(updater);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getUid() {
		return uid;
	}
}
