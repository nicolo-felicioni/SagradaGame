package it.polimi.se2018.network.server;

import it.polimi.se2018.exceptions.SessionException;
import it.polimi.se2018.network.utils.NetworkCommandForwarder;
import it.polimi.se2018.network.utils.NetworkForwarder;

/**
 * @author davide yi xian hu
 */
public interface SessionInterface extends NetworkForwarder {

	void logout(String uid) throws SessionException;

	String getUID();
}
