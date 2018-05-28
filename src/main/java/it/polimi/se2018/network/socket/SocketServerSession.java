package it.polimi.se2018.network.socket;

import it.polimi.se2018.controller.CommandInterface;
import it.polimi.se2018.controller.ViewUpdaterInterface;
import it.polimi.se2018.exceptions.NetworkException;
import it.polimi.se2018.exceptions.SessionException;
import it.polimi.se2018.network.server.SessionInterface;
import it.polimi.se2018.network.utils.NetworkCommandObserver;
import it.polimi.se2018.network.utils.NetworkViewUpdaterObserver;

import java.rmi.RemoteException;

/**
 * @author davide yi xian hu
 */
public class SocketServerSession implements SessionInterface {

	@Override
	public void logout(String uid) throws SessionException {

	}

	@Override
	public String getUID() {
		return null;
	}

	/**
	 * Add a network command observer.
	 *
	 * @param observer the network command observer.
	 * @throws RemoteException  if RMI errors occur during the connection.
	 * @throws NetworkException if any connection error occurs during the connection.
	 */
	@Override
	public void addCommandObserverver(NetworkCommandObserver observer) throws RemoteException, NetworkException {

	}

	/**
	 * Notify a network command.
	 *
	 * @param command the network command to be executed.
	 * @throws RemoteException  if RMI errors occur during the connection.
	 * @throws NetworkException if any connection error occurs during the connection.
	 */
	@Override
	public void notify(CommandInterface command) throws RemoteException, NetworkException {

	}

	/**
	 * Handle a command from the network.
	 *
	 * @param command the command to be executed.
	 * @throws RemoteException  if RMI errors occur during the connection.
	 * @throws NetworkException if any connection error occurs during the connection.
	 */
	@Override
	public void handle(CommandInterface command) throws RemoteException, NetworkException {

	}

	/**
	 * Add a network view updater observer.
	 *
	 * @param observer the network view updater observer.
	 * @throws RemoteException  if RMI errors occur during the connection.
	 * @throws NetworkException if any connection error occurs during the connection.
	 */
	@Override
	public void addViewUpdaterObserverver(NetworkViewUpdaterObserver observer) throws RemoteException, NetworkException {

	}

	/**
	 * Notify a view updater.
	 *
	 * @param updater the view updater.
	 * @throws RemoteException  if RMI errors occur during the connection.
	 * @throws NetworkException if any connection error occurs during the connection.
	 */
	@Override
	public void notify(ViewUpdaterInterface updater) throws RemoteException, NetworkException {

	}

	/**
	 * Handle a view update from the network.
	 *
	 * @param updater the view updater.
	 * @throws RemoteException  if RMI errors occur during the connection.
	 * @throws NetworkException if any connection error occurs during the connection.
	 */
	@Override
	public void handle(ViewUpdaterInterface updater) throws RemoteException, NetworkException {

	}
}
