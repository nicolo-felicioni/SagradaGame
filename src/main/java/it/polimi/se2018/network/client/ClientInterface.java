package it.polimi.se2018.network.client;

import it.polimi.se2018.controller.CommandInterface;
import it.polimi.se2018.exceptions.LoginException;
import it.polimi.se2018.exceptions.SessionException;
import it.polimi.se2018.model.*;
import it.polimi.se2018.network.server.SessionInterface;

import java.rmi.RemoteException;

/**
 * @author davide yi xian hu
 */
public interface ClientInterface {

	/**
	 * Login a client to the server.
	 *
	 * @param uid the unique identifier of the client.
	 */
	void login (String uid) throws LoginException;

	/**
	 * Update the dice bag.
	 * @param diceBag the updated dice bag.
	 */
	void updateDiceBag(DiceBag diceBag) throws RemoteException;

	/**
	 * Notify a command.
	 * @param command the command to be executed.
	 */
	void notify(CommandInterface command) throws RemoteException, SessionException;

	/**
	 * Session setter.
	 * @param session the session between client and server.
	 */
	void setSession(SessionInterface session);
}
