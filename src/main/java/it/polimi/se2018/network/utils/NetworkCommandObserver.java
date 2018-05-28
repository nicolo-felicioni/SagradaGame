package it.polimi.se2018.network.utils;

import it.polimi.se2018.controller.CommandInterface;
import it.polimi.se2018.exceptions.NetworkException;

import java.rmi.RemoteException;

/**
 * @author davide yi xian hu
 */
public interface NetworkCommandObserver {

	/**
	 * Handle a command from the network.
	 * @param command the command to be executed.
	 *
	 * @throws RemoteException if RMI errors occur during the connection.
	 * @throws NetworkException if any connection error occurs during the connection.
	 */
	void handle(CommandInterface command) throws RemoteException, NetworkException;

}
