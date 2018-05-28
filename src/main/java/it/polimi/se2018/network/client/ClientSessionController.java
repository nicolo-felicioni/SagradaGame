package it.polimi.se2018.network.client;

import it.polimi.se2018.controller.CommandInterface;
import it.polimi.se2018.controller.ViewUpdaterInterface;
import it.polimi.se2018.controller.ViewUpdaterObserver;
import it.polimi.se2018.exceptions.NetworkException;
import it.polimi.se2018.network.utils.NetworkCommandObserver;

import java.rmi.RemoteException;

/**
 * @author davide yi xian hu
 */
public class ClientSessionController implements ClientSessionControllerInterface {

	/**
	 * The network client. It observe command created from the controller.
	 */
	private NetworkCommandObserver client;

	/**
	 * The ui controller. It observe view updater commands.
	 */
	private ViewUpdaterObserver controller;

	@Override
	public void addCommandObserver(NetworkCommandObserver observer) throws RemoteException, NetworkException {
		this.client = observer;
	}

	@Override
	public void notify(CommandInterface command) throws RemoteException, NetworkException {
		this.client.handle(command);
	}

	@Override
	public void handle(CommandInterface command){
		try {
			this.notify(command);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NetworkException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Add a view updater observer.
	 *
	 * @param observer the view updater observer.
	 */
	@Override
	public void addObserver(ViewUpdaterObserver observer) {
		this.controller = observer;
	}

	/**
	 * Notify a view updater.
	 *
	 * @param updater the view updater to be executed.
	 */
	@Override
	public void notify(ViewUpdaterInterface updater) {
		this.controller.handle(updater);
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
		this.notify(updater);
	}
}
