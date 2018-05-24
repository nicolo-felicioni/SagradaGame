package it.polimi.se2018.network.server;

import it.polimi.se2018.controller.CommandInterface;
import it.polimi.se2018.exceptions.SessionException;
import it.polimi.se2018.model.*;
import it.polimi.se2018.network.SessionControllerInterface;

import java.rmi.RemoteException;

/**
 * @author davide yi xian hu
 */
public interface SessionInterface {

	 /**
	 * Update the dice bag. Forward the request to the client.
	 *
	 * @param diceBag the updated dice bag.
	 * @throws SessionException if any connection error occurs between client and server.
	 */
	void updateDiceBag(DiceBag diceBag) throws SessionException;

	/**
	 * Notify a command.
	 * @param command the command to be executed.
	 *
	 * @throws SessionException if any connection error occurs between client and server.
	 */
	void notify(CommandInterface command) throws RemoteException, SessionException;

	/**
	 * Session controller setter.
	 *
	 * @param controller the session controller.
	 */
	void addSessionController(SessionControllerInterface controller);

	void logout(String uid) throws SessionException;
	String getUID();
}
