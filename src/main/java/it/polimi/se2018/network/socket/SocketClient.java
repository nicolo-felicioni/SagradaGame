package it.polimi.se2018.network.socket;

import it.polimi.se2018.network.SessionControllerInterface;
import it.polimi.se2018.network.client.AbstractClient;

/**
 * @author davide yi xian hu
 */
public class SocketClient extends AbstractClient {

	/**
	 * Constructor of the class.
	 *
	 * @param controller the client session controller
	 */
	protected SocketClient(SessionControllerInterface controller) {
		super(controller);
	}
}
