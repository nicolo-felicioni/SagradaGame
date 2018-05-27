package it.polimi.se2018.network.utils;

import it.polimi.se2018.controller.CommandInterface;
import it.polimi.se2018.controller.ViewUpdaterInterface;
import it.polimi.se2018.exceptions.NetworkException;

import java.rmi.RemoteException;

/**
 * @author davide yi xian hu
 */
public interface NetworkViewUpdaterObserver {

	/**
	 * Handle a view update from the network.
	 * @param updater the view updater.
	 *
	 * @throws RemoteException if RMI errors occur during the connection.
	 * @throws NetworkException if any connection error occurs during the connection.
	 */
	void handle(ViewUpdaterInterface updater) throws RemoteException, NetworkException;

}
