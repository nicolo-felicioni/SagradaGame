package it.polimi.se2018.observable;

import it.polimi.se2018.event.*;
import it.polimi.se2018.observer.GameEventObserver;

/**
 * @author davide yi xian hu
 */
public interface GameEventObservable{

	/**
	 * Add a GameEventObserver.
	 * @param observer the GameEventObserver.
	 */
	void addObserver(GameEventObserver observer);

	/**
	 * Remove a GameEventObserver.
	 * @param observer the GameEventObserver.
	 */
	void removeObserver(GameEventObserver observer);

	/**
	 * Notify the ChooseDraftDieValueObservers an ChooseDraftDieValueEvent.
	 *
	 * @param event the ChooseDraftDieValueEvent.
	 */
	void notifyObservers(ChooseDraftDieValueEvent event);

	/**
	 * Notify the DecreaseDieValueObservers an DecreaseDieValueEvent.
	 *
	 * @param event the DecreaseDieValueEvent.
	 */
	void notifyObservers(DecreaseDieValueEvent event);

	/**
	 * Notify the DraftAndPlaceAgainObservers an DraftAndPlaceAgainEvent.
	 *
	 * @param event the DraftAndPlaceAgainEvent.
	 */
	void notifyObservers(DraftAndPlaceAgainEvent event);

	/**
	 * Notify the DraftAndPlaceNoAdjacentObservers an DraftAndPlaceNoAdjacentEvent.
	 *
	 * @param event the DraftAndPlaceNoAdjacentEvent.
	 */
	void notifyObservers(DraftAndPlaceNoAdjacentEvent event);

	/**
	 * Notify the DraftAndPlaceObservers an DraftAndPlaceEvent.
	 *
	 * @param event the DraftAndPlaceEvent.
	 */
	void notifyObservers(DraftAndPlaceEvent event);

	/**
	 * Notify the EndTurnObservers an EndTurnEvent.
	 *
	 * @param event the EndTurnEvent.
	 */
	void notifyObservers(EndTurnEvent event);

	/**
	 * Notify the FlipDraftDieObservers an FlipDraftDieEvent.
	 *
	 * @param event the FlipDraftDieEvent.
	 */
	void notifyObservers(FlipDraftDieEvent event);

	/**
	 * Notify the IncreaseDieValueObservers an IncreaseDieValueEvent.
	 *
	 * @param event the IncreaseDieValueEvent.
	 */
	void notifyObservers(IncreaseDieValueEvent event);

	/**
	 * Notify the MoveDieIgnoreColorRestrictionObservers an MoveDieIgnoreColorRestrictionEvent.
	 *
	 * @param event the MoveDieIgnoreColorRestrictionEvent.
	 */
	void notifyObservers(MoveDieIgnoreColorRestrictionEvent event);

	/**
	 * Notify the MoveDieIgnoreValueRestrictionObservers an MoveDieIgnoreValueRestrictionEvent.
	 *
	 * @param event the MoveDieIgnoreValueRestrictionEvent.
	 */
	void notifyObservers(MoveDieIgnoreValueRestrictionEvent event);

	/**
	 * Notify the MoveDieMatchColorRoundTrackObservers an MoveDieMatchColorRoundTrackEvent.
	 *
	 * @param event the MoveDieMatchColorRoundTrackEvent.
	 */
	void notifyObservers(MoveDieMatchColorRoundTrackEvent event);

	/**
	 * Notify the MoveDieRespectAllRestrictionObservers an MoveDieRespectAllRestrictionEvent.
	 *
	 * @param event the MoveDieRespectAllRestrictionEvent.
	 */
	void notifyObservers(MoveDieRespectAllRestrictionsEvent event);

	/**
	 * Notify the RerollAllDraftDiceObservers an RerollAllDraftDiceEvent.
	 *
	 * @param event the RerollAllDraftDiceEvent.
	 */
	void notifyObservers(RerollAllDraftDiceEvent event);

	/**
	 * Notify the RerollDraftDieObservers an RerollDraftDieEvent.
	 *
	 * @param event the RerollDraftDieEvent.
	 */
	void notifyObservers(RerollDraftDieEvent event);

	/**
	 * Notify the SwapDraftDieWithDiceBagDieObservers an SwapDraftDieWithDiceBagDieEvent.
	 *
	 * @param event the SwapDraftDieWithDiceBagDieEvent.
	 */
	void notifyObservers(SwapDraftDieWithDiceBagDieEvent event);

	/**
	 * Notify the SwapDrafDieWithRoundTrackDieObservers an SwapDrafDieWithRoundTrackDieEvent.
	 *
	 * @param event the SwapDrafDieWithRoundTrackDieEvent.
	 */
	void notifyObservers(SwapDraftDieWithRoundTrackDieEvent event);

	/**
	 * Notify the UseToolCardObservers an UseToolCardEvent.
	 *
	 * @param event the UseToolCardEvent.
	 */
	void notifyObservers(UseToolCardEvent event);

	/**
	 * Notify the WindowPatternChosenObservers an WindowPatternChosenEvent.
	 *
	 * @param event the WindowPatternChosenEvent.
	 */
	void notifyObservers(WindowPatternChosenEvent event);
}
