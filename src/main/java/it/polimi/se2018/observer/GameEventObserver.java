package it.polimi.se2018.observer;

import it.polimi.se2018.event.StartGameEvent;

/**
 * @author davide yi xian hu
 */
public interface GameEventObserver extends ChooseDraftDieValueObserver, DecreaseDieValueObserver, DraftAndPlaceNoAdjacentObserver, DraftAndPlaceAgainObserver, DraftAndPlaceObserver, EndTurnObserver, FlipDraftDieObserver, IncreaseDieValueObserver, MoveDieIgnoreColorRestrictionObserver, MoveDieIgnoreValueRestrictionObserver, MoveDieMatchColorRoundTrackObserver, MoveDieRespectAllRestrictionsObserver, RerollDraftDieObserver, RerollAllDraftDiceObserver, SwapDraftDieWithDiceBagDieObserver, SwapDraftDieWithRoundTrackDieObserver, UseToolCardObserver, WindowPatternChosenObserver, StartGameObserver {
}
