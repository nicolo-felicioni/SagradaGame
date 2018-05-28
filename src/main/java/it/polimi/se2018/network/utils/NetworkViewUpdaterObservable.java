package it.polimi.se2018.network.utils;

import it.polimi.se2018.controller.ViewUpdaterInterface;
import it.polimi.se2018.exceptions.NetworkException;

import java.rmi.RemoteException;

/**
 * @author davide yi xian hu
 */
public interface NetworkViewUpdaterObservable {

	/**
	 * Add a network view updater observer.
	 * @param observer the network view updater observer.
	 *
	 * @throws RemoteException if RMI errors occur during the connection.
	 * @throws NetworkException if any connection error occurs during the connection.
	 */
	void addViewUpdaterObserver(NetworkViewUpdaterObserver observer) throws RemoteException, NetworkException;

	/**
	 * Notify a view updater.
	 * @param updater the view updater.
	 *
	 * @throws RemoteException if RMI errors occur during the connection.
	 * @throws NetworkException if any connection error occurs during the connection.
	 */
	void notify(ViewUpdaterInterface updater) throws RemoteException, NetworkException;
}
