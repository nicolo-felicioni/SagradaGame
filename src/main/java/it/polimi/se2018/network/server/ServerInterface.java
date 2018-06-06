package it.polimi.se2018.network.server;

import it.polimi.se2018.exceptions.LoginException;
import it.polimi.se2018.exceptions.NetworkException;
import it.polimi.se2018.network.client.ClientInterface;

import java.rmi.RemoteException;

/**
 * @author davide yi xian hu
 */
public interface ServerInterface{

	/**
	 * Login a client to the server.
	 *
	 * @param uid the unique identifier of the client.
	 * @param client the client.
	 */
	SessionInterface login (String uid, ClientInterface client) throws RemoteException, NetworkException;

}
