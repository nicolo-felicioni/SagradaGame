package it.polimi.se2018.network.rmi;

import it.polimi.se2018.controller.ViewUpdaterInterface;
import it.polimi.se2018.event.*;
import it.polimi.se2018.exceptions.LoginException;
import it.polimi.se2018.exceptions.NetworkException;
import it.polimi.se2018.network.client.AbstractClient;
import it.polimi.se2018.observer.*;
import it.polimi.se2018.view.AbstractView;
import it.polimi.se2018.view.View;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 * @author davide yi xian hu
 */
public class RMIClient extends AbstractClient implements RMIClientInterface {

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
	private List<NetworkGameEventObserver> observers;

	/**
	 * Constructor.
	 */
	public RMIClient(View view) {
		observers = new ArrayList<>();
		this.view = view;
	}

	/**
	 * Connect to the RMI server.
	 *
	 * @param address the ip address of the RMI server.
	 * @param port the port number of the RMI server.
	 *
	 * @throws RemoteException if the connection with thr RMI server fails.
	 * @throws NotBoundException if the client can not connect to the RMI server.
	 */
	@Override
	public void connect(String address, int port) throws NotBoundException {
		try {
			Registry registry = LocateRegistry.getRegistry(address, port);
			server = (RMIServerInterface) registry.lookup("RMIServer") ;
			UnicastRemoteObject.exportObject(this, 0);
		} catch (RemoteException e) {
			e.printStackTrace();
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
		    NetworkGameEventObserver o = server.login(uid, this);
			this.addGameObserver(o);
		}catch(RemoteException | NetworkException e) {
			e.printStackTrace();
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
	 * Handle a ChooseDraftDieValueEvent.
	 *
	 * @param event the ChooseDraftDieValueEvent.
	 */
	@Override
	public void handle(ChooseDraftDieValueGameEvent event) {
		this.notifyObservers(event);
	}

	/**
	 * Handle a DecreaseDieValueEvent.
	 *
	 * @param event the DecreaseDieValueEvent.
	 */
	@Override
	public void handle(DecreaseDieValueGameEvent event) {
		this.notifyObservers(event);
	}

	/**
	 * Handle a DraftAndPlaceAgainEvent.
	 *
	 * @param event the DraftAndPlaceAgainEvent.
	 */
	@Override
	public void handle(DraftAndPlaceAgainGameEvent event) {
		this.notifyObservers(event);
	}

	/**
	 * Handle a DraftAndPlaceNoAdjacentEvent.
	 *
	 * @param event the DraftAndPlaceNoAdjacentEvent.
	 */
	@Override
	public void handle(DraftAndPlaceNoAdjacentGameEvent event) {
		this.notifyObservers(event);
	}

	/**
	 * Handle a DraftAndPlaceEvent.
	 *
	 * @param event the DraftAndPlaceEvent.
	 */
	@Override
	public void handle(DraftAndPlaceGameEvent event) {
		this.notifyObservers(event);
	}

	/**
	 * Handle a EndTurnEvent.
	 *
	 * @param event the EndTurnEvent.
	 */
	@Override
	public void handle(EndTurnGameEvent event) {
		this.notifyObservers(event);
	}

	/**
	 * Handle a FlipDraftDieEvent.
	 *
	 * @param event the FlipDraftDieEvent.
	 */
	@Override
	public void handle(FlipDraftDieGameEvent event) {
		this.notifyObservers(event);
	}

	/**
	 * Handle a IncreaseDieValueEvent.
	 *
	 * @param event the IncreaseDieValueEvent.
	 */
	@Override
	public void handle(IncreaseDieValueGameEvent event) {
		this.notifyObservers(event);
	}

	/**
	 * Handle a MoveDieIgnoreColorRestrictionEvent.
	 *
	 * @param event the MoveDieIgnoreColorRestrictionEvent.
	 */
	@Override
	public void handle(MoveDieIgnoreColorRestrictionGameEvent event) {
		this.notifyObservers(event);
	}

	/**
	 * Handle a MoveDieIgnoreValueRestrictionEvent.
	 *
	 * @param event the MoveDieIgnoreValueRestrictionEvent.
	 */
	@Override
	public void handle(MoveDieIgnoreValueRestrictionGameEvent event) {
		this.notifyObservers(event);
	}

	/**
	 * Handle a MoveDieMatchColorRoundTrackEvent.
	 *
	 * @param event the MoveDieMatchColorRoundTrackEvent.
	 */
	@Override
	public void handle(MoveDieMatchColorRoundTrackGameEvent event) {
		this.notifyObservers(event);
	}

	/**
	 * Handle a MoveDieRespectAllRestrictionsEvent.
	 *
	 * @param event the MoveDieRespectAllRestrictionsEvent.
	 */
	@Override
	public void handle(MoveDieRespectAllRestrictionsGameEvent event) {
		this.notifyObservers(event);
	}

	/**
	 * Handle a RerollAllDraftDiceEvent.
	 *
	 * @param event the RerollAllDraftDiceEvent.
	 */
	@Override
	public void handle(RerollAllDraftDiceGameEvent event) {
		this.notifyObservers(event);
	}

	/**
	 * Handle a RerollDraftDieEvent.
	 *
	 * @param event the RerollDraftDieEvent.
	 */
	@Override
	public void handle(RerollDraftDieGameEvent event) {
		this.notifyObservers(event);
	}

	/**
	 * Handle a StartGameEvent.
	 *
	 * @param event the StartGameEvent.
	 */
	@Override
	public void handle(StartGameEvent event) {
		this.notifyObservers(event);
	}

	/**
	 * Handle a SwapDraftDieWithDiceBagDieEvent.
	 *
	 * @param event the SwapDraftDieWithDiceBagDieEvent.
	 */
	@Override
	public void handle(SwapDraftDieWithDiceBagDieGameEvent event) {
		this.notifyObservers(event);
	}

	/**
	 * Handle a SwapDraftDieWithRoundTrackDieEvent.
	 *
	 * @param event the SwapDraftDieWithRoundTrackDieEvent.
	 */
	@Override
	public void handle(SwapDraftDieWithRoundTrackDieGameEvent event) {
		this.notifyObservers(event);
	}

	/**
	 * Handle a UseToolCardEvent.
	 *
	 * @param event the UseToolCardEvent.
	 */
	@Override
	public void handle(UseToolCardGameEvent event) {
		this.notifyObservers(event);
	}

	/**
	 * Handle a WindowPatternChosenEvent.
	 *
	 * @param event the WindowPatternChosenEvent.
	 */
	@Override
	public void handle(WindowPatternChosenGameEvent event) {
		this.notifyObservers(event);
	}

	/**
	 * Handle a view update from the network.
	 *
	 * @param updater the view updater.
	 * @throws RemoteException  if RMI errors occur during the connection.
	 * @throws NetworkException if any connection error occurs during the connection.
	 */
	@Override
	public void handle(ViewUpdaterInterface updater){
		updater.update(this.view);
	}

	/**
	 * Add a NetworkGameEventObserver.
	 *
	 * @param observer the NetworkGameEventObserver.
	 */
	@Override
	public void addGameObserver(NetworkGameEventObserver observer) {
		this.observers.add(observer);
	}

	/**
	 * Remove a NetworkGameEventObserver.
	 *
	 * @param observer the NetworkGameEventObserver.
	 */
	@Override
	public void removeGameObserver(NetworkGameEventObserver observer) {
		this.observers.remove(observer);
	}

	/**
	 * Notify the ChooseDraftDieValueObservers an ChooseDraftDieValueEvent.
	 *
	 * @param event the ChooseDraftDieValueEvent.
	 */
	@Override
	public void notifyObservers(ChooseDraftDieValueGameEvent event) {
		this.observers.forEach(observer -> {
			try {
				observer.handle(event);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Notify the DecreaseDieValueObservers an DecreaseDieValueEvent.
	 *
	 * @param event the DecreaseDieValueEvent.
	 */
	@Override
	public void notifyObservers(DecreaseDieValueGameEvent event) {
		this.observers.forEach(observer -> {
			try {
				observer.handle(event);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Notify the DraftAndPlaceAgainObservers an DraftAndPlaceAgainEvent.
	 *
	 * @param event the DraftAndPlaceAgainEvent.
	 */
	@Override
	public void notifyObservers(DraftAndPlaceAgainGameEvent event) {
		this.observers.forEach(observer -> {
			try {
				observer.handle(event);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Notify the DraftAndPlaceNoAdjacentObservers an DraftAndPlaceNoAdjacentEvent.
	 *
	 * @param event the DraftAndPlaceNoAdjacentEvent.
	 */
	@Override
	public void notifyObservers(DraftAndPlaceNoAdjacentGameEvent event) {
		this.observers.forEach(observer -> {
			try {
				observer.handle(event);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Notify the DraftAndPlaceObservers an DraftAndPlaceEvent.
	 *
	 * @param event the DraftAndPlaceEvent.
	 */
	@Override
	public void notifyObservers(DraftAndPlaceGameEvent event) {
		this.observers.forEach(observer -> {
			try {
				observer.handle(event);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Notify the EndTurnObservers an EndTurnEvent.
	 *
	 * @param event the EndTurnEvent.
	 */
	@Override
	public void notifyObservers(EndTurnGameEvent event) {
		this.observers.forEach(observer -> {
			try {
				observer.handle(event);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Notify the FlipDraftDieObservers an FlipDraftDieEvent.
	 *
	 * @param event the FlipDraftDieEvent.
	 */
	@Override
	public void notifyObservers(FlipDraftDieGameEvent event) {
		this.observers.forEach(observer -> {
			try {
				observer.handle(event);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Notify the IncreaseDieValueObservers an IncreaseDieValueEvent.
	 *
	 * @param event the IncreaseDieValueEvent.
	 */
	@Override
	public void notifyObservers(IncreaseDieValueGameEvent event) {
		this.observers.forEach(observer -> {
			try {
				observer.handle(event);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Notify the MoveDieIgnoreColorRestrictionObservers an MoveDieIgnoreColorRestrictionEvent.
	 *
	 * @param event the MoveDieIgnoreColorRestrictionEvent.
	 */
	@Override
	public void notifyObservers(MoveDieIgnoreColorRestrictionGameEvent event) {
		this.observers.forEach(observer -> {
			try {
				observer.handle(event);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Notify the MoveDieIgnoreValueRestrictionObservers an MoveDieIgnoreValueRestrictionEvent.
	 *
	 * @param event the MoveDieIgnoreValueRestrictionEvent.
	 */
	@Override
	public void notifyObservers(MoveDieIgnoreValueRestrictionGameEvent event) {
		this.observers.forEach(observer -> {
			try {
				observer.handle(event);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Notify the MoveDieMatchColorRoundTrackObservers an MoveDieMatchColorRoundTrackEvent.
	 *
	 * @param event the MoveDieMatchColorRoundTrackEvent.
	 */
	@Override
	public void notifyObservers(MoveDieMatchColorRoundTrackGameEvent event) {
		this.observers.forEach(observer -> {
			try {
				observer.handle(event);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Notify the MoveDieRespectAllRestrictionObservers an MoveDieRespectAllRestrictionEvent.
	 *
	 * @param event the MoveDieRespectAllRestrictionEvent.
	 */
	@Override
	public void notifyObservers(MoveDieRespectAllRestrictionsGameEvent event) {
		this.observers.forEach(observer -> {
			try {
				observer.handle(event);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Notify the RerollAllDraftDiceObservers an RerollAllDraftDiceEvent.
	 *
	 * @param event the RerollAllDraftDiceEvent.
	 */
	@Override
	public void notifyObservers(RerollAllDraftDiceGameEvent event) {
		this.observers.forEach(observer -> {
			try {
				observer.handle(event);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Notify the RerollDraftDieObservers an RerollDraftDieEvent.
	 *
	 * @param event the RerollDraftDieEvent.
	 */
	@Override
	public void notifyObservers(RerollDraftDieGameEvent event) {
		this.observers.forEach(observer -> {
			try {
				observer.handle(event);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Notify the StartGameObservers an StartGameEvent.
	 *
	 * @param event the StartGameEvent.
	 */
	@Override
	public void notifyObservers(StartGameEvent event) {
		this.observers.forEach(observer -> {
			try {
				observer.handle(event);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Notify the SwapDraftDieWithDiceBagDieObservers an SwapDraftDieWithDiceBagDieEvent.
	 *
	 * @param event the SwapDraftDieWithDiceBagDieEvent.
	 */
	@Override
	public void notifyObservers(SwapDraftDieWithDiceBagDieGameEvent event) {
		this.observers.forEach(observer -> {
			try {
				observer.handle(event);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Notify the SwapDrafDieWithRoundTrackDieObservers an SwapDrafDieWithRoundTrackDieEvent.
	 *
	 * @param event the SwapDrafDieWithRoundTrackDieEvent.
	 */
	@Override
	public void notifyObservers(SwapDraftDieWithRoundTrackDieGameEvent event) {
		this.observers.forEach(observer -> {
			try {
				observer.handle(event);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Notify the UseToolCardObservers an UseToolCardEvent.
	 *
	 * @param event the UseToolCardEvent.
	 */
	@Override
	public void notifyObservers(UseToolCardGameEvent event) {
		this.observers.forEach(observer -> {
			try {
				observer.handle(event);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Notify the WindowPatternChosenObservers an WindowPatternChosenEvent.
	 *
	 * @param event the WindowPatternChosenEvent.
	 */
	@Override
	public void notifyObservers(WindowPatternChosenGameEvent event) {
		this.observers.forEach(observer -> {
			try {
				observer.handle(event);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		});
	}

}