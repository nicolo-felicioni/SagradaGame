package it.polimi.se2018.observer;

import it.polimi.se2018.event.*;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author davide yi xian hu
 */
public interface NetworkGameEventObserver {

	/**
	 * Handle a ChooseDraftDieValueEvent.
	 *
	 * @param event the ChooseDraftDieValueEvent.
	 */
	void handle(ChooseDraftDieValueGameEvent event) throws RemoteException;

	/**
	 * Handle a DecreaseDieValueEvent.
	 *
	 * @param event the DecreaseDieValueEvent.
	 */
	void handle(DecreaseDieValueGameEvent event) throws RemoteException;

	/**
	 * Handle a DraftAndPlaceAgainEvent.
	 *
	 * @param event the DraftAndPlaceAgainEvent.
	 */
	void handle(DraftAndPlaceAgainGameEvent event) throws RemoteException;

	/**
	 * Handle a DraftAndPlaceNoAdjacentEvent.
	 *
	 * @param event the DraftAndPlaceNoAdjacentEvent.
	 */
	void handle(DraftAndPlaceNoAdjacentGameEvent event) throws RemoteException;

	/**
	 * Handle a DraftAndPlaceEvent.
	 *
	 * @param event the DraftAndPlaceEvent.
	 */
	void handle(DraftAndPlaceGameEvent event) throws RemoteException;

	/**
	 * Handle a EndTurnEvent.
	 *
	 * @param event the EndTurnEvent.
	 */
	void handle(EndTurnGameEvent event) throws RemoteException;

	/**
	 * Handle a FlipDraftDieEvent.
	 *
	 * @param event the FlipDraftDieEvent.
	 */
	void handle(FlipDraftDieGameEvent event) throws RemoteException;

	/**
	 * Handle a IncreaseDieValueEvent.
	 *
	 * @param event the IncreaseDieValueEvent.
	 */
	void handle(IncreaseDieValueGameEvent event) throws RemoteException;

	/**
	 * Handle a MoveDieIgnoreColorRestrictionEvent.
	 *
	 * @param event the MoveDieIgnoreColorRestrictionEvent.
	 */
	void handle(MoveDieIgnoreColorRestrictionGameEvent event) throws RemoteException;

	/**
	 * Handle a MoveDieIgnoreValueRestrictionEvent.
	 *
	 * @param event the MoveDieIgnoreValueRestrictionEvent.
	 */
	void handle(MoveDieIgnoreValueRestrictionGameEvent event) throws RemoteException;

	/**
	 * Handle a MoveDieMatchColorRoundTrackEvent.
	 *
	 * @param event the MoveDieMatchColorRoundTrackEvent.
	 */
	void handle(MoveDieMatchColorRoundTrackGameEvent event) throws RemoteException;

	/**
	 * Handle a MoveDieRespectAllRestrictionsEvent.
	 *
	 * @param event the MoveDieRespectAllRestrictionsEvent.
	 */
	void handle(MoveDieRespectAllRestrictionsGameEvent event) throws RemoteException;

	/**
	 * Handle a RerollAllDraftDiceEvent.
	 *
	 * @param event the RerollAllDraftDiceEvent.
	 */
	void handle(RerollAllDraftDiceGameEvent event) throws RemoteException;

	/**
	 * Handle a RerollDraftDieEvent.
	 *
	 * @param event the RerollDraftDieEvent.
	 */
	void handle(RerollDraftDieGameEvent event) throws RemoteException;

	/**
	 * Handle a SwapDraftDieWithDiceBagDieEvent.
	 *
	 * @param event the SwapDraftDieWithDiceBagDieEvent.
	 */
	void handle(SwapDraftDieWithDiceBagDieGameEvent event) throws RemoteException;

	/**
	 * Handle a SwapDraftDieWithRoundTrackDieEvent.
	 *
	 * @param event the SwapDraftDieWithRoundTrackDieEvent.
	 */
	void handle(SwapDraftDieWithRoundTrackDieGameEvent event) throws RemoteException;

	/**
	 * Handle a UseToolCardEvent.
	 *
	 * @param event the UseToolCardEvent.
	 */
	void handle(UseToolCardGameEvent event) throws RemoteException;

	/**
	 * Handle a WindowPatternChosenEvent.
	 *
	 * @param event the WindowPatternChosenEvent.
	 */
	void handle(WindowPatternChosenGameEvent event) throws RemoteException;

}
