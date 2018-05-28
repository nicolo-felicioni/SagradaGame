package it.polimi.se2018.network.utils;

import it.polimi.se2018.controller.CommandInterface;
import it.polimi.se2018.exceptions.NetworkException;

import java.rmi.RemoteException;

/**
 * @author davide yi xian hu
 */
public interface NetworkCommandObservable {

	/**
	 * Add a network command observer.
	 * @param observer the network command observer.
	 *
	 * @throws RemoteException if RMI errors occur during the connection.
	 * @throws NetworkException if any connection error occurs during the connection.
	 */
	void addCommandObserver(NetworkCommandObserver observer) throws RemoteException, NetworkException;

	/**
	 * Notify a network command.
	 * @param command the network command to be executed.
	 *
	 * @throws RemoteException if RMI errors occur during the connection.
	 * @throws NetworkException if any connection error occurs during the connection.
	 */
	void notify(CommandInterface command) throws RemoteException, NetworkException;

}
