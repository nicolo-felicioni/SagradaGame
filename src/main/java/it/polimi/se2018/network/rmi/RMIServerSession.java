package it.polimi.se2018.network.rmi;

import it.polimi.se2018.controller.CommandInterface;
import it.polimi.se2018.exceptions.SessionException;
import it.polimi.se2018.model.*;
import it.polimi.se2018.network.SessionControllerInterface;
import it.polimi.se2018.network.server.SessionInterface;
import it.polimi.se2018.network.client.ClientInterface;
import it.polimi.se2018.network.server.ServerInterface;
import it.polimi.se2018.network.server.ServerSessionController;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author davide yi xian hu
 */
public class RMIServerSession implements SessionInterface, Remote {

	/**
	 * RMI Client
	 */
	private ClientInterface client;

	/**
	 * RMI Server session.
	 */
	private ServerInterface server;

	/**
	 * Unique identifier of the client.
	 */
	private String uid;

	/**
	 * Controller of the session.
	 */
	private SessionControllerInterface controller;

	/**
	 * Default constructor. Create a RMI session between the client and the server.
	 *
	 * @param client the client.
	 * @param server the server.
	 */
	public RMIServerSession(ClientInterface client, ServerInterface server) {
		this.client = client;
		this.server = server;
		this.controller = null;
	}

	/**
	 * Update the dice bag. Forward the request to the client.
	 *
	 * @param diceBag the updated dice bag.
	 * @throws SessionException if any connection error occurs between client and server.
	 */
	@Override
	public void updateDiceBag(DiceBag diceBag) throws SessionException {
		try{
			client.updateDiceBag(diceBag);
		}catch(RemoteException e){
			throw new SessionException("RMI Session failed to invoke remote method. Can not update the dice bag.");
		}
	}

	/**
	 * Notify a command.
	 * @param command the command to be executed.
	 *
	 * @throws SessionException if any connection error occurs between client and server.
	 */
	@Override
	public void notify(CommandInterface command) throws RemoteException, SessionException{
		this.controller.notify(command);
	}

	/**
	 * Session controller adder. If the session has already got a controller, the new controller will not be added.
	 *
	 * @param controller the session controller.
	 */
	@Override
	public void addSessionController(SessionControllerInterface controller) {
		if(controller == null) {
			this.controller = controller;
		}
	}


	@Override
	public void logout(String uid) {

	}

	@Override
	public String getUID() {
		return this.uid;
	}
}
