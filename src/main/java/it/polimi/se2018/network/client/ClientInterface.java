package it.polimi.se2018.network.client;

import it.polimi.se2018.controller.ViewUpdaterObservable;
import it.polimi.se2018.exceptions.LoginException;
import it.polimi.se2018.exceptions.NetworkException;
import it.polimi.se2018.network.utils.NetworkViewUpdaterObservable;
import it.polimi.se2018.network.utils.NetworkViewUpdaterObserver;
import it.polimi.se2018.observer.GameEventObserver;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * @author davide yi xian hu
 */
public interface ClientInterface extends GameEventObserver, NetworkViewUpdaterObserver {


	/**
	 * Connect the client to the server.
	 * @param address the server address.
 	 * @param port the server port.
	 * @throws NetworkException if the client can not connect to the server.
	 */
	void connect (String address, int port) throws RemoteException, NetworkException, NotBoundException;

	/**
	 * Login a client to the server.
	 *
	 * @param uid the unique identifier of the client.
	 */
	void login (String uid) throws RemoteException, LoginException;

	/**
	 * Getter of the unique identifier.
	 *
	 * @return the unique identifier.
	 */
	String getUid ();

}
