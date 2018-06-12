package it.polimi.se2018.network.client;

import it.polimi.se2018.event.*;
import it.polimi.se2018.exceptions.LoginException;
import it.polimi.se2018.exceptions.NetworkException;
import it.polimi.se2018.network.utils.NetworkViewUpdaterObserver;
import it.polimi.se2018.observable.GameEventObservable;
import it.polimi.se2018.observer.GameEventObserver;
import it.polimi.se2018.observer.NetworkGameEventObserver;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * @author davide yi xian hu
 */
public interface ClientInterface extends GameEventObserver, NetworkViewUpdaterObserver {

	/**
	 * Connect the client to the server.
	 * @param address the server address.
 	 * @param port the server port.
	 * @throws NetworkException if the client can not connect to the server.
	 */
	void connect (String address, int port) throws RemoteException, NetworkException, NotBoundException;

	/**
	 * Login a client to the server.
	 *
	 * @param uid the unique identifier of the client.
	 */
	void login (String uid) throws RemoteException, LoginException;

	/**
	 * Getter of the unique identifier.
	 *
	 * @return the unique identifier.
	 */
	String getUid ();

	/**
	 * Add a NetworkGameEventObserver.
	 *
	 * @param observer the NetworkGameEventObserver.
	 */
	void addGameObserver(NetworkGameEventObserver observer);

	/**
	 * Remove a NetworkGameEventObserver.
	 *
	 * @param observer the NetworkGameEventObserver.
	 */
	void removeGameObserver(NetworkGameEventObserver observer);

	/**
	 * Notify the ChooseDraftDieValueObservers an ChooseDraftDieValueEvent.
	 *
	 * @param event the ChooseDraftDieValueEvent.
	 */
	void notifyObservers(ChooseDraftDieValueGameEvent event);

	/**
	 * Notify the DecreaseDieValueObservers an DecreaseDieValueEvent.
	 *
	 * @param event the DecreaseDieValueEvent.
	 */
	void notifyObservers(DecreaseDieValueGameEvent event);

	/**
	 * Notify the DraftAndPlaceAgainObservers an DraftAndPlaceAgainEvent.
	 *
	 * @param event the DraftAndPlaceAgainEvent.
	 */
	void notifyObservers(DraftAndPlaceAgainGameEvent event);

	/**
	 * Notify the DraftAndPlaceNoAdjacentObservers an DraftAndPlaceNoAdjacentEvent.
	 *
	 * @param event the DraftAndPlaceNoAdjacentEvent.
	 */
	void notifyObservers(DraftAndPlaceNoAdjacentGameEvent event);

	/**
	 * Notify the DraftAndPlaceObservers an DraftAndPlaceEvent.
	 *
	 * @param event the DraftAndPlaceEvent.
	 */
	void notifyObservers(DraftAndPlaceGameEvent event);

	/**
	 * Notify the EndTurnObservers an EndTurnEvent.
	 *
	 * @param event the EndTurnEvent.
	 */
	void notifyObservers(EndTurnGameEvent event);

	/**
	 * Notify the FlipDraftDieObservers an FlipDraftDieEvent.
	 *
	 * @param event the FlipDraftDieEvent.
	 */
	void notifyObservers(FlipDraftDieGameEvent event);

	/**
	 * Notify the IncreaseDieValueObservers an IncreaseDieValueEvent.
	 *
	 * @param event the IncreaseDieValueEvent.
	 */
	void notifyObservers(IncreaseDieValueGameEvent event);

	/**
	 * Notify the MoveDieIgnoreColorRestrictionObservers an MoveDieIgnoreColorRestrictionEvent.
	 *
	 * @param event the MoveDieIgnoreColorRestrictionEvent.
	 */
	void notifyObservers(MoveDieIgnoreColorRestrictionGameEvent event);

	/**
	 * Notify the MoveDieIgnoreValueRestrictionObservers an MoveDieIgnoreValueRestrictionEvent.
	 *
	 * @param event the MoveDieIgnoreValueRestrictionEvent.
	 */
	void notifyObservers(MoveDieIgnoreValueRestrictionGameEvent event);

	/**
	 * Notify the MoveDieMatchColorRoundTrackObservers an MoveDieMatchColorRoundTrackEvent.
	 *
	 * @param event the MoveDieMatchColorRoundTrackEvent.
	 */
	void notifyObservers(MoveDieMatchColorRoundTrackGameEvent event);

	/**
	 * Notify the MoveDieRespectAllRestrictionObservers an MoveDieRespectAllRestrictionEvent.
	 *
	 * @param event the MoveDieRespectAllRestrictionEvent.
	 */
	void notifyObservers(MoveDieRespectAllRestrictionsGameEvent event);

	/**
	 * Notify the RerollAllDraftDiceObservers an RerollAllDraftDiceEvent.
	 *
	 * @param event the RerollAllDraftDiceEvent.
	 */
	void notifyObservers(RerollAllDraftDiceGameEvent event);

	/**
	 * Notify the RerollDraftDieObservers an RerollDraftDieEvent.
	 *
	 * @param event the RerollDraftDieEvent.
	 */
	void notifyObservers(RerollDraftDieGameEvent event);

	/**
	 * Notify the StartGameObservers an StartGameEvent.
	 *
	 * @param event the StartGameEvent.
	 */
	void notifyObservers(StartGameEvent event);

	/**
	 * Notify the SwapDraftDieWithDiceBagDieObservers an SwapDraftDieWithDiceBagDieEvent.
	 *
	 * @param event the SwapDraftDieWithDiceBagDieEvent.
	 */
	void notifyObservers(SwapDraftDieWithDiceBagDieGameEvent event);

	/**
	 * Notify the SwapDrafDieWithRoundTrackDieObservers an SwapDrafDieWithRoundTrackDieEvent.
	 *
	 * @param event the SwapDrafDieWithRoundTrackDieEvent.
	 */
	void notifyObservers(SwapDraftDieWithRoundTrackDieGameEvent event);

	/**
	 * Notify the UseToolCardObservers an UseToolCardEvent.
	 *
	 * @param event the UseToolCardEvent.
	 */
	void notifyObservers(UseToolCardGameEvent event);

	/**
	 * Notify the WindowPatternChosenObservers an WindowPatternChosenEvent.
	 *
	 * @param event the WindowPatternChosenEvent.
	 */
	void notifyObservers(WindowPatternChosenGameEvent event);

}
