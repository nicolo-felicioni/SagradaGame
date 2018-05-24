package it.polimi.se2018.network.client;

import it.polimi.se2018.controller.CommandInterface;
import it.polimi.se2018.exceptions.SessionException;
import it.polimi.se2018.model.DiceBag;
import it.polimi.se2018.network.SessionControllerInterface;

import java.rmi.RemoteException;

/**
 * @author davide yi xian hu
 */
public class ClientSessionController implements SessionControllerInterface {

	ClientInterface client;

	/**
	 * Update the dice bag.
	 * @param diceBag the updated dice bag.
	 */
	@Override
	public void updateDiceBag(DiceBag diceBag){

	}

	@Override
	public void notify(CommandInterface command) throws RemoteException, SessionException {
		this.client.notify(command);
	}

}
