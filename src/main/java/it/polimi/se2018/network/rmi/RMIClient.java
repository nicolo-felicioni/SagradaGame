package it.polimi.se2018.network.rmi;

import it.polimi.se2018.controller.ViewUpdaterInterface;
import it.polimi.se2018.controller.utils.MyLog;
import it.polimi.se2018.event.game.*;
import it.polimi.se2018.exceptions.LoginException;
import it.polimi.se2018.exceptions.NetworkException;
import it.polimi.se2018.network.client.ClientInterface;
import it.polimi.se2018.view.View;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 * @author Davide Yi Xian Hu
 */
public class RMIClient implements RMIClientInterface, ClientInterface {

	/**
	 * RMI server.
	 */
	private RMIServerInterface server;

	/**
	 * Unique identifier of the client.
	 */
	private String uid;

	/**
	 * The user interface.
	 */
	private View view;

	/**
	 * The network game event observers;
	 */
	private List<RMIServerSessionInterface> sessions;

	/**
	 * Constructor.
	 */
	public RMIClient(View view) {
		this.sessions = new ArrayList<>();
		this.view = view;
	}

	/**
	 * Connect to the RMI server.
	 *
	 * @param address the ip address of the RMI server.
	 * @param port the port number of the RMI server.
	 *
	 * @throws NotBoundException if the client can not connect to the RMI server.
	 */
	@Override
	public void connect(String address, int port) throws NotBoundException {
		try {
			Registry registry = LocateRegistry.getRegistry(address, port);
			server = (RMIServerInterface) registry.lookup("RMIServer") ;
			UnicastRemoteObject.exportObject(this, 0);
		} catch (RemoteException e) {
			MyLog.getMyLog().log(Level.WARNING,e.getMessage());
		}
	}

	/**
	 * Disconnect the client.
	 *
	 * @throws NetworkException if the client can not connect to the server.
	 */
	@Override
	public synchronized void disconnect() throws NetworkException {
		for(RMIServerSessionInterface session : sessions) {
			try {
				session.disconnect(this);
			} catch (RemoteException e) {
				throw new NetworkException("Disconnection failed");
			}
		}

	}

	/**
	 * Connect to the RMI server.
	 *
	 * @param uid the unique identifier of the user.
	 *
	 * @throws LoginException if the connection with thr RMI server fails.
	 */
	@Override
	public void login(String uid) throws LoginException {
		try {
			sessions.add(server.login(uid, this));
			this.uid = uid;
		}catch(RemoteException | NetworkException e) {
			MyLog.getMyLog().log(Level.WARNING, e.getMessage());
			throw new LoginException("Login to server failed.");
		}
	}

	/**
	 * Reconnect a client to the server.
	 *
	 * @param uid the unique identifier of the client.
	 */
	public void reconnect (String uid) throws LoginException {
		try {
			sessions.add(server.reconnect(uid, this));
			this.uid = uid;
		}catch(RemoteException | NetworkException e) {
			MyLog.getMyLog().log(Level.WARNING, e.getMessage());
			throw new LoginException("Login to server failed.");
		}
	}

	/**
	 * Getter of the unique identifier.
	 *
	 * @return the unique identifier.
	 */
	@Override
	public String getUid() {
		return uid;
	}

	/**
	 * Handle a view update from the network.
	 *
	 * @param updater the view updater.
	 */
	@Override
	public void handle(ViewUpdaterInterface updater){
		updater.update(this.view);
	}


	/**
	 * Handle a ChooseDraftDieValueEvent.
	 *
	 * @param event the ChooseDraftDieValueEvent.
	 */
	@Override
	public void handle(ChooseDraftDieValueGameEvent event) {
		this.sessions.forEach(session -> {
			try {
				session.handle(event);
			} catch (RemoteException e) {
				MyLog.getMyLog().log(Level.WARNING, e.getMessage());
			}
		});
	}

	/**
	 * Handle a DecreaseDieValueEvent.
	 *
	 * @param event the DecreaseDieValueEvent.
	 */
	@Override
	public void handle(DecreaseDieValueGameEvent event) {
		this.sessions.forEach(session -> {
			try {
				session.handle(event);
			} catch (RemoteException e) {
				MyLog.getMyLog().log(Level.WARNING, e.getMessage());
			}
		});
	}

	/**
	 * Handle a DraftAndPlaceAgainEvent.
	 *
	 * @param event the DraftAndPlaceAgainEvent.
	 */
	@Override
	public void handle(DraftAndPlaceAgainGameEvent event) {
		this.sessions.forEach(session -> {
			try {
				session.handle(event);
			} catch (RemoteException e) {
				MyLog.getMyLog().log(Level.WARNING, e.getMessage());
			}
		});
	}

	/**
	 * Handle a DraftAndPlaceNoAdjacentEvent.
	 *
	 * @param event the DraftAndPlaceNoAdjacentEvent.
	 */
	@Override
	public void handle(DraftAndPlaceNoAdjacentGameEvent event) {
		this.sessions.forEach(session -> {
			try {
				session.handle(event);
			} catch (RemoteException e) {
				MyLog.getMyLog().log(Level.WARNING, e.getMessage());
			}
		});
	}

	/**
	 * Handle a DraftAndPlaceEvent.
	 *
	 * @param event the DraftAndPlaceEvent.
	 */
	@Override
	public void handle(DraftAndPlaceGameEvent event) {
		this.sessions.forEach(session -> {
			try {
				session.handle(event);
			} catch (RemoteException e) {
				MyLog.getMyLog().log(Level.WARNING, e.getMessage());
			}
		});
	}

	/**
	 * Handle a EndTurnEvent.
	 *
	 * @param event the EndTurnEvent.
	 */
	@Override
	public void handle(EndTurnGameEvent event) {
		this.sessions.forEach(session -> {
			try {
				session.handle(event);
			} catch (RemoteException e) {
				MyLog.getMyLog().log(Level.WARNING, e.getMessage());
			}
		});
	}

	/**
	 * Handle a FlipDraftDieEvent.
	 *
	 * @param event the FlipDraftDieEvent.
	 */
	@Override
	public void handle(FlipDraftDieGameEvent event) {
		this.sessions.forEach(session -> {
			try {
				session.handle(event);
			} catch (RemoteException e) {
				MyLog.getMyLog().log(Level.WARNING, e.getMessage());
			}
		});
	}

	/**
	 * Handle a IncreaseDieValueEvent.
	 *
	 * @param event the IncreaseDieValueEvent.
	 */
	@Override
	public void handle(IncreaseDieValueGameEvent event) {
		this.sessions.forEach(session -> {
			try {
				session.handle(event);
			} catch (RemoteException e) {
				MyLog.getMyLog().log(Level.WARNING, e.getMessage());
			}
		});
	}

	/**
	 * Handle a MoveDieIgnoreColorRestrictionEvent.
	 *
	 * @param event the MoveDieIgnoreColorRestrictionEvent.
	 */
	@Override
	public void handle(MoveDieIgnoreColorRestrictionGameEvent event) {
		this.sessions.forEach(session -> {
			try {
				session.handle(event);
			} catch (RemoteException e) {
				MyLog.getMyLog().log(Level.WARNING, e.getMessage());
			}
		});
	}

	/**
	 * Handle a MoveDieIgnoreValueRestrictionEvent.
	 *
	 * @param event the MoveDieIgnoreValueRestrictionEvent.
	 */
	@Override
	public void handle(MoveDieIgnoreValueRestrictionGameEvent event) {
		this.sessions.forEach(session -> {
			try {
				session.handle(event);
			} catch (RemoteException e) {
				MyLog.getMyLog().log(Level.WARNING, e.getMessage());
			}
		});
	}

	/**
	 * Handle a MoveDieMatchColorRoundTrackEvent.
	 *
	 * @param event the MoveDieMatchColorRoundTrackEvent.
	 */
	@Override
	public void handle(MoveDieMatchColorRoundTrackGameEvent event) {
		this.sessions.forEach(session -> {
			try {
				session.handle(event);
			} catch (RemoteException e) {
				MyLog.getMyLog().log(Level.WARNING, e.getMessage());
			}
		});
	}

	/**
	 * Handle a MoveDieRespectAllRestrictionsEvent.
	 *
	 * @param event the MoveDieRespectAllRestrictionsEvent.
	 */
	@Override
	public void handle(MoveDieRespectAllRestrictionsGameEvent event) {
		this.sessions.forEach(session -> {
			try {
				session.handle(event);
			} catch (RemoteException e) {
				MyLog.getMyLog().log(Level.WARNING, e.getMessage());
			}
		});
	}

	/**
	 * Handle a RerollAllDraftDiceEvent.
	 *
	 * @param event the RerollAllDraftDiceEvent.
	 */
	@Override
	public void handle(RerollAllDraftDiceGameEvent event) {
		this.sessions.forEach(session -> {
			try {
				session.handle(event);
			} catch (RemoteException e) {
				MyLog.getMyLog().log(Level.WARNING, e.getMessage());
			}
		});
	}

	/**
	 * Handle a RerollDraftDieEvent.
	 *
	 * @param event the RerollDraftDieEvent.
	 */
	@Override
	public void handle(RerollDraftDieGameEvent event) {
		this.sessions.forEach(session -> {
			try {
				session.handle(event);
			} catch (RemoteException e) {
				MyLog.getMyLog().log(Level.WARNING, e.getMessage());
			}
		});
	}

	/**
	 * Handle a StartGameEvent.
	 *
	 * @param event the StartGameEvent.
	 */
	@Override
	public void handle(StartGameEvent event) {
		this.sessions.forEach(session -> {
			try {
				session.handle(event);
			} catch (RemoteException e) {
				MyLog.getMyLog().log(Level.WARNING, e.getMessage());
			}
		});
	}

	/**
	 * Handle a SwapDraftDieWithDiceBagDieEvent.
	 *
	 * @param event the SwapDraftDieWithDiceBagDieEvent.
	 */
	@Override
	public void handle(SwapDraftDieWithDiceBagDieGameEvent event) {
		this.sessions.forEach(session -> {
			try {
				session.handle(event);
			} catch (RemoteException e) {
				MyLog.getMyLog().log(Level.WARNING, e.getMessage());
			}
		});
	}

	/**
	 * Handle a SwapDraftDieWithRoundTrackDieEvent.
	 *
	 * @param event the SwapDraftDieWithRoundTrackDieEvent.
	 */
	@Override
	public void handle(SwapDraftDieWithRoundTrackDieGameEvent event) {
		this.sessions.forEach(session -> {
			try {
				session.handle(event);
			} catch (RemoteException e) {
				MyLog.getMyLog().log(Level.WARNING, e.getMessage());
			}
		});
	}

	/**
	 * Handle a UseToolCardEvent.
	 *
	 * @param event the UseToolCardEvent.
	 */
	@Override
	public void handle(UseToolCardGameEvent event) {
		this.sessions.forEach(session -> {
			try {
				session.handle(event);
			} catch (RemoteException e) {
				MyLog.getMyLog().log(Level.WARNING, e.getMessage());
			}
		});
	}

	/**
	 * Handle a WindowPatternChosenEvent.
	 *
	 * @param event the WindowPatternChosenEvent.
	 */
	@Override
	public void handle(WindowPatternChosenGameEvent event) {
		this.sessions.forEach(session -> {
			try {
				session.handle(event);
			} catch (RemoteException e) {
				MyLog.getMyLog().log(Level.WARNING, e.getMessage());
			}
		});
	}

}