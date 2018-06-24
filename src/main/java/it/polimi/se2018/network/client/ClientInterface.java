package it.polimi.se2018.network.client;

import it.polimi.se2018.controller.ViewUpdaterObserver;
import it.polimi.se2018.exceptions.LoginException;
import it.polimi.se2018.exceptions.NetworkException;
import it.polimi.se2018.observer.game.GameEventObserver;

import java.rmi.NotBoundException;

/**
 * @author Davide Yi Xian Hu
 */
public interface ClientInterface extends GameEventObserver, ViewUpdaterObserver {

	/**
	 * Connect the client to the server.
	 * @param address the server address.
 	 * @param port the server port.
	 * @throws NetworkException if the client can not connect to the server.
	 */
	void connect (String address, int port) throws NetworkException, NotBoundException;

	/**
	 * Login a client to the server.
	 *
	 * @param uid the unique identifier of the client.
	 */
	void login (String uid) throws LoginException;

	/**
	 * Getter of the unique identifier.
	 *
	 * @return the unique identifier.
	 */
	String getUid ();

}
