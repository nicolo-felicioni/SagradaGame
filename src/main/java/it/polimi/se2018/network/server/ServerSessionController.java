package it.polimi.se2018.network.server;

import it.polimi.se2018.model.DiceBag;
import it.polimi.se2018.network.GameRoom;
import it.polimi.se2018.network.SessionControllerInterface;
import it.polimi.se2018.network.SessionInterface;

/**
 * @author davide yi xian hu
 */
public class ServerSessionController implements SessionControllerInterface {

	private SessionInterface session;
	private GameRoom gameRoom;

	public ServerSessionController(SessionInterface session, GameRoom gameRoom) {
		this.session = session;
		this.gameRoom = gameRoom;
	}

	@Override
	public void updateDiceBag(DiceBag diceBag) {

	}

}
