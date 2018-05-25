package it.polimi.se2018.network.rmi;

import it.polimi.se2018.exceptions.NetworkException;
import it.polimi.se2018.exceptions.SessionException;
import it.polimi.se2018.model.*;
import it.polimi.se2018.network.GameRoom;
import it.polimi.se2018.network.SessionInterface;
import it.polimi.se2018.network.server.Server;
import it.polimi.se2018.network.server.ServerSessionController;

import java.rmi.RemoteException;

/**
 * @author davide yi xian hu
 */
public class RMISession implements SessionInterface {

	private RMIClient client;
	private RMIServer server;
	private ServerSessionController controller;
	private String uid;

	public RMISession (RMIClient client, RMIServer server) {
		this.client = client;
		this.server = server;
	}


	@Override
	public void updateDiceBag(DiceBag diceBag) throws SessionException {
		try{
			client.updateDiceBag(diceBag);
		}catch(RemoteException e){
			throw new SessionException("RMI Session failed to invoke remote method. Can not update the dice bag.");
		}
	}

	@Override
	public void login(String uid) throws SessionException {
		try{
			GameRoom gameRoom = Server.login(uid);
			this.uid = uid;
			this.controller = new ServerSessionController(this, gameRoom);
		}catch(NetworkException e){
			throw new SessionException("Login to server failed.");
		}
	}

	@Override
	public void logout(String uid) throws SessionException {

	}

	@Override
	public String getUID() {
		return null;
	}
}
