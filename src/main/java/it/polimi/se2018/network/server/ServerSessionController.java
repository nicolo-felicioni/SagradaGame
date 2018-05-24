package it.polimi.se2018.network.server;

import it.polimi.se2018.controller.CommandInterface;
import it.polimi.se2018.model.DiceBag;
import it.polimi.se2018.network.GameRoom;
import it.polimi.se2018.network.SessionControllerInterface;

/**
 * @author davide yi xian hu
 */
public class ServerSessionController implements SessionControllerInterface {

	private SessionInterface session;
	private GameRoom gameRoom;
	private String uid;

	public ServerSessionController(SessionInterface session) {
		this.session = session;
		this.uid = session.getUID();
		this.gameRoom = null;
	}

	@Override
	public void updateDiceBag(DiceBag diceBag) {

	}

	@Override
	public void notify(CommandInterface command) {
		gameRoom.notify(command);
	}

	@Override
	public String getUID() {
		return uid;
	}

	@Override
	public void addGameRoom(GameRoom gameRoom) {
		if(gameRoom != null) {
			gameRoom = gameRoom;
		}
	}

}
