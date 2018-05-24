package it.polimi.se2018.network.client;

import it.polimi.se2018.controller.CommandInterface;
import it.polimi.se2018.exceptions.SessionException;
import it.polimi.se2018.model.*;
import it.polimi.se2018.network.SessionControllerInterface;
import it.polimi.se2018.network.server.SessionInterface;

import java.rmi.RemoteException;

/**
 * @author davide yi xian hu
 */
public abstract class AbstractClient implements ClientInterface{

	/**
	 * Server session. It handle the requests to the server.
	 */
	private SessionInterface serverSession;

	/**
	 * Session controller. It handle the requests from the server.
	 */
	private SessionControllerInterface controller;

	/**
	 * Constructor of the abstract class.
	 */
	protected AbstractClient(SessionControllerInterface controller) {
		this.controller = controller;
	}

	/**
	 * Update the dice bag. Forward the request to the controller.
	 * @param diceBag the updated dice bag.
	 */
	@Override
	public void updateDiceBag(DiceBag diceBag) throws RemoteException {
		this.controller.updateDiceBag(diceBag);
	}

	/**
	 * Notify a command to the server session.
	 * @param command the command to be executed.
	 */
	public void notify(CommandInterface command) throws RemoteException, SessionException {
		this.serverSession.notify(command);
	}

	/**
	 * Session setter.
	 * @param session the session between client and server.
	 */
	public void setSession(SessionInterface session) {
		this.serverSession = session;
	}

}
