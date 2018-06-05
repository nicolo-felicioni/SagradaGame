package it.polimi.se2018.network.client;

import it.polimi.se2018.exceptions.LoginException;
import it.polimi.se2018.network.utils.NetworkCommandObserver;
import it.polimi.se2018.network.utils.NetworkViewUpdaterForwarder;

/**
 * @author davide yi xian hu
 */
public interface ClientInterface extends NetworkCommandObserver, NetworkViewUpdaterForwarder {

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
