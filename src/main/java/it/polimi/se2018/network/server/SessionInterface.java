package it.polimi.se2018.network.server;

import it.polimi.se2018.exceptions.SessionException;
import it.polimi.se2018.network.utils.NetworkCommandForwarder;

/**
 * @author davide yi xian hu
 */
public interface SessionInterface extends NetworkCommandForwarder {

	void logout(String uid) throws SessionException;

	String getUID();
}
