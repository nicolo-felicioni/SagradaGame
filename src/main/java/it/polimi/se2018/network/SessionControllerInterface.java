package it.polimi.se2018.network;

import it.polimi.se2018.controller.CommandInterface;
import it.polimi.se2018.exceptions.SessionException;
import it.polimi.se2018.model.*;

import java.rmi.RemoteException;

/**
 * @author davide yi xian hu
 */
public interface SessionControllerInterface {

	void updateDiceBag(DiceBag diceBag);

	void notify(CommandInterface command) throws RemoteException, SessionException;

	String getUID();

	void addGameRoom(GameRoom gameRoom);
}
