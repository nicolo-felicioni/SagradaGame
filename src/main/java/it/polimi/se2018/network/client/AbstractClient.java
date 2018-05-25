package it.polimi.se2018.network.client;

import it.polimi.se2018.model.*;
import it.polimi.se2018.network.SessionControllerInterface;

import java.rmi.RemoteException;

/**
 * @author davide yi xian hu
 */
public abstract class AbstractClient {

	private final SessionControllerInterface controller;

	/**
	 * Constructor of the abstract class.
	 */
	protected AbstractClient(SessionControllerInterface controller) {
		this.controller = controller;
	}

	/**
	 * Update the dice bag. Forward the request to the controller.
	 * @param diceBag the updated dice bag.
	 */
	public void updateDiceBag(DiceBag diceBag) throws RemoteException {
		this.controller.updateDiceBag(diceBag);
	}
}
