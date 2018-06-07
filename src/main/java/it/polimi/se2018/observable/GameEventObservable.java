package it.polimi.se2018.observable;

import it.polimi.se2018.observer.GameEventObserver;

/**
 * @author davide yi xian hu
 */
public interface GameEventObservable extends ChooseDraftDieValueObservable, DecreaseDieValueObservable, DraftAndPlaceNoAdjacentObservable, DraftAndPlaceAgainObservable, DraftAndPlaceObservable, EndTurnObservable, FlipDraftDieObservable, IncreaseDieValueObservable, MoveDieIgnoreColorRestrictionObservable, MoveDieIgnoreValueRestrictionObservable, MoveDieMatchColorRoundTrackObservable, MoveDieRespectAllRestrictionsObservable, RerollDraftDieObservable, RerollAllDraftDiceObservable, SwapDraftDieWithDiceBagDieObservable, SwapDraftDieWithRoundTrackDieObservable, UseToolCardObservable, WindowPatternChosenObservable{

	/**
	 * Add a GameEventObserver.
	 *
	 * @param observer the GameEventObserver.
	 */
	public void addGameObserver(GameEventObserver observer);

	/**
	 * Remove a GameEventObserver.
	 *
	 * @param observer the GameEventObserver.
	 */
	public void removeGameObserver(GameEventObserver observer);

}
