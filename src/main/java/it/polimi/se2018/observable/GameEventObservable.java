package it.polimi.se2018.observable;

import it.polimi.se2018.event.*;
import it.polimi.se2018.observer.*;

import java.util.List;

/**
 * @author davide yi xian hu
 */
public class GameEventObservable implements ChooseDraftDieValueObservable, DecreaseDieValueObservable, DraftAndPlaceNoAdjacentObservable, DraftAndPlaceAgainObservable, DraftAndPlaceObservable, EndTurnObservable, FlipDraftDieObservable, IncreaseDieValueObservable, MoveDieIgnoreColorRestrictionObservable, MoveDieIgnoreValueRestrictionObservable, MoveDieMatchColorRoundTrackObservable, MoveDieRespectAllRestrictionsObservable, RerollDraftDieObservable, RerollAllDraftDiceObservable, SwapDraftDieWithDiceBagDieObservable, SwapDraftDieWithRoundTrackDieObservable, UseToolCardObservable, WindowPatternChosenObservable{

	private List<ChooseDraftDieValueObserver> chooseDraftDieValueObservers;
	private List<DecreaseDieValueObserver> decreaseDieValueObservers;
	private List<DraftAndPlaceAgainObserver> draftAndPlaceAgainObservers;
	private List<DraftAndPlaceNoAdjacentObserver> draftAndPlaceNoAdjacentObservers;
	private List<DraftAndPlaceObserver> draftAndPlaceObservers;
	private List<EndTurnObserver> endTurnObservers;
	private List<FlipDraftDieObserver> flipDraftDieObservers;
	private List<IncreaseDieValueObserver> increaseDieValueObservers;
	private List<MoveDieIgnoreColorRestrictionObserver> moveDieIgnoreColorRestrictionObservers;
	private List<MoveDieIgnoreValueRestrictionObserver> moveDieIgnoreValueRestrictionObservers;
	private List<MoveDieMatchColorRoundTrackObserver> moveDieMatchColorRoundTrackObservers;
	private List<MoveDieRespectAllRestrictionsObserver> moveDieRespectAllRestrictionsObservers;
	private List<RerollAllDraftDiceObserver> rerollAllDraftDiceObservers;
	private List<RerollDraftDieObserver> rerollDraftDieObservers;
	private List<SwapDraftDieWithDiceBagDieObserver> swapDraftDieWithDiceBagDieObservers;
	private List<SwapDraftDieWithRoundTrackDieObserver> swapDraftDieWithRoundTrackDieObservers;
	private List<UseToolCardObserver> useToolCardObservers;
	private List<WindowPatternChosenObserver> windowPatternChosenObservers;

	/**
	 * Add a ChooseDraftDieValueObserver.
	 *
	 * @param observer the ChooseDraftDieValueObserver.
	 */
	@Override
	public void addObserver(ChooseDraftDieValueObserver observer) {
		this.chooseDraftDieValueObservers.add(observer);
	}

	/**
	 * Remove a ChooseDraftDieValueObserver.
	 *
	 * @param observer the ChooseDraftDieValueObserver.
	 */
	@Override
	public void removeObserver(ChooseDraftDieValueObserver observer) {
		this.chooseDraftDieValueObservers.remove(observer);
	}

	/**
	 * Notify the ChooseDraftDieValueObservers an ChooseDraftDieValueEvent.
	 *
	 * @param event the ChooseDraftDieValueEvent.
	 */
	@Override
	public void notifyObservers(ChooseDraftDieValueEvent event) {
		this.chooseDraftDieValueObservers.forEach(chooseDraftDieValueObserver -> chooseDraftDieValueObserver.handle(event));
	}

	/**
	 * Add a DecreaseDieValueObserver.
	 *
	 * @param observer the DecreaseDieValueObserver.
	 */
	@Override
	public void addObserver(DecreaseDieValueObserver observer) {
		this.decreaseDieValueObservers.add(observer);
	}

	/**
	 * Remove a DecreaseDieValueObserver.
	 *
	 * @param observer the DecreaseDieValueObserver.
	 */
	@Override
	public void removeObserver(DecreaseDieValueObserver observer) {
		this.decreaseDieValueObservers.remove(observer);
	}

	/**
	 * Notify the DecreaseDieValueObservers an DecreaseDieValueEvent.
	 *
	 * @param event the DecreaseDieValueEvent.
	 */
	@Override
	public void notifyObservers(DecreaseDieValueEvent event) {
		this.decreaseDieValueObservers.forEach(decreaseDieValueObserver -> decreaseDieValueObserver.handle(event));
	}

	/**
	 * Add a DraftAndPlaceAgainObserver.
	 *
	 * @param observer the DraftAndPlaceAgainObserver.
	 */
	@Override
	public void addObserver(DraftAndPlaceAgainObserver observer) {
		this.draftAndPlaceAgainObservers.add(observer);
	}

	/**
	 * Remove a DraftAndPlaceAgainObserver.
	 *
	 * @param observer the DraftAndPlaceAgainObserver.
	 */
	@Override
	public void removeObserver(DraftAndPlaceAgainObserver observer) {
		this.draftAndPlaceAgainObservers.remove(observer);
	}

	/**
	 * Notify the DraftAndPlaceAgainObservers an DraftAndPlaceAgainEvent.
	 *
	 * @param event the DraftAndPlaceAgainEvent.
	 */
	@Override
	public void notifyObservers(DraftAndPlaceAgainEvent event) {
		this.draftAndPlaceAgainObservers.forEach(draftAndPlaceAgainObserver -> draftAndPlaceAgainObserver.handle(event));
	}

	/**
	 * Add a DraftAndPlaceNoAdjacentObserver.
	 *
	 * @param observer the DraftAndPlaceNoAdjacentObserver.
	 */
	@Override
	public void addObserver(DraftAndPlaceNoAdjacentObserver observer) {
		this.draftAndPlaceNoAdjacentObservers.add(observer);
	}

	/**
	 * Remove a DraftAndPlaceNoAdjacentObserver.
	 *
	 * @param observer the DraftAndPlaceNoAdjacentObserver.
	 */
	@Override
	public void removeObserver(DraftAndPlaceNoAdjacentObserver observer) {
		this.draftAndPlaceNoAdjacentObservers.add(observer);
	}

	/**
	 * Notify the DraftAndPlaceNoAdjacentObservers an DraftAndPlaceNoAdjacentEvent.
	 *
	 * @param event the DraftAndPlaceNoAdjacentEvent.
	 */
	@Override
	public void notifyObservers(DraftAndPlaceNoAdjacentEvent event) {
		this.draftAndPlaceNoAdjacentObservers.forEach(draftAndPlaceNoAdjacentObserver -> draftAndPlaceNoAdjacentObserver.handle(event));
	}

	/**
	 * Add a DraftAndPlaceObserver.
	 *
	 * @param observer the DraftAndPlaceObserver.
	 */
	@Override
	public void addObserver(DraftAndPlaceObserver observer) {
		this.draftAndPlaceObservers.add(observer);
	}

	/**
	 * Remove a DraftAndPlaceObserver.
	 *
	 * @param observer the DraftAndPlaceObserver.
	 */
	@Override
	public void removeObserver(DraftAndPlaceObserver observer) {
		this.draftAndPlaceObservers.add(observer);
	}

	/**
	 * Notify the DraftAndPlaceObservers an DraftAndPlaceEvent.
	 *
	 * @param event the DraftAndPlaceEvent.
	 */
	@Override
	public void notifyObservers(DraftAndPlaceEvent event) {
		this.draftAndPlaceObservers.forEach(draftAndPlaceObserver -> draftAndPlaceObserver.handle(event));
	}

	/**
	 * Add a EndTurnObserver.
	 *
	 * @param observer the EndTurnObserver.
	 */
	@Override
	public void addObserver(EndTurnObserver observer) {
		this.endTurnObservers.add(observer);
	}

	/**
	 * Remove a EndTurnObserver.
	 *
	 * @param observer the EndTurnObserver.
	 */
	@Override
	public void removeObserver(EndTurnObserver observer) {
		this.endTurnObservers.add(observer);
	}

	/**
	 * Notify the EndTurnObservers an EndTurnEvent.
	 *
	 * @param event the EndTurnEvent.
	 */
	@Override
	public void notifyObservers(EndTurnEvent event) {
		this.endTurnObservers.forEach(endTurnObserver -> endTurnObserver.handle(event));
	}

	/**
	 * Add a FlipDraftDieObserver.
	 *
	 * @param observer the FlipDraftDieObserver.
	 */
	@Override
	public void addObserver(FlipDraftDieObserver observer) {
		this.flipDraftDieObservers.add(observer);
	}

	/**
	 * Remove a FlipDraftDieObserver.
	 *
	 * @param observer the FlipDraftDieObserver.
	 */
	@Override
	public void removeObserver(FlipDraftDieObserver observer) {
		this.flipDraftDieObservers.add(observer);
	}

	/**
	 * Notify the FlipDraftDieObservers an FlipDraftDieEvent.
	 *
	 * @param event the FlipDraftDieEvent.
	 */
	@Override
	public void notifyObservers(FlipDraftDieEvent event) {
		this.flipDraftDieObservers.forEach(flipDraftDieObserver -> flipDraftDieObserver.handle(event));
	}

	/**
	 * Add a IncreaseDieValueObserver.
	 *
	 * @param observer the IncreaseDieValueObserver.
	 */
	@Override
	public void addObserver(IncreaseDieValueObserver observer) {
		this.increaseDieValueObservers.add(observer);
	}

	/**
	 * Remove a IncreaseDieValueObserver.
	 *
	 * @param observer the IncreaseDieValueObserver.
	 */
	@Override
	public void removeObserver(IncreaseDieValueObserver observer) {
		this.increaseDieValueObservers.add(observer);
	}

	/**
	 * Notify the IncreaseDieValueObservers an IncreaseDieValueEvent.
	 *
	 * @param event the IncreaseDieValueEvent.
	 */
	@Override
	public void notifyObservers(IncreaseDieValueEvent event) {
		this.increaseDieValueObservers.forEach(increaseDieValueObserver -> increaseDieValueObserver.handle(event));
	}

	/**
	 * Add a MoveDieIgnoreColorRestrictionObserver.
	 *
	 * @param observer the MoveDieIgnoreColorRestrictionObserver.
	 */
	@Override
	public void addObserver(MoveDieIgnoreColorRestrictionObserver observer) {
		this.moveDieIgnoreColorRestrictionObservers.add(observer);
	}

	/**
	 * Remove a MoveDieIgnoreColorRestrictionObserver.
	 *
	 * @param observer the MoveDieIgnoreColorRestrictionObserver.
	 */
	@Override
	public void removeObserver(MoveDieIgnoreColorRestrictionObserver observer) {
		this.moveDieIgnoreColorRestrictionObservers.add(observer);
	}

	/**
	 * Notify the MoveDieIgnoreColorRestrictionObservers an MoveDieIgnoreColorRestrictionEvent.
	 *
	 * @param event the MoveDieIgnoreColorRestrictionEvent.
	 */
	@Override
	public void notifyObservers(MoveDieIgnoreColorRestrictionEvent event) {
		this.moveDieIgnoreColorRestrictionObservers.forEach(moveDieIgnoreColorRestrictionObserver -> moveDieIgnoreColorRestrictionObserver.handle(event));
	}

	/**
	 * Add a MoveDieIgnoreValueRestrictionObserver.
	 *
	 * @param observer the MoveDieIgnoreValueRestrictionObserver.
	 */
	@Override
	public void addObserver(MoveDieIgnoreValueRestrictionObserver observer) {
		this.moveDieIgnoreValueRestrictionObservers.add(observer);
	}

	/**
	 * Remove a MoveDieIgnoreValueRestrictionObserver.
	 *
	 * @param observer the MoveDieIgnoreValueRestrictionObserver.
	 */
	@Override
	public void removeObserver(MoveDieIgnoreValueRestrictionObserver observer) {
		this.moveDieIgnoreValueRestrictionObservers.add(observer);
	}

	/**
	 * Notify the MoveDieIgnoreValueRestrictionObservers an MoveDieIgnoreValueRestrictionEvent.
	 *
	 * @param event the MoveDieIgnoreValueRestrictionEvent.
	 */
	@Override
	public void notifyObservers(MoveDieIgnoreValueRestrictionEvent event) {
		this.moveDieIgnoreValueRestrictionObservers.forEach(moveDieIgnoreValueRestrictionObserver -> moveDieIgnoreValueRestrictionObserver.handle(event));
	}

	/**
	 * Add a MoveDieMatchColorRoundTrackObserver.
	 *
	 * @param observer the MoveDieMatchColorRoundTrackObserver.
	 */
	@Override
	public void addObserver(MoveDieMatchColorRoundTrackObserver observer) {
		this.moveDieMatchColorRoundTrackObservers.add(observer);
	}

	/**
	 * Remove a MoveDieMatchColorRoundTrackObserver.
	 *
	 * @param observer the MoveDieMatchColorRoundTrackObserver.
	 */
	@Override
	public void removeObserver(MoveDieMatchColorRoundTrackObserver observer) {
		this.moveDieMatchColorRoundTrackObservers.add(observer);
	}

	/**
	 * Notify the MoveDieMatchColorRoundTrackObservers an MoveDieMatchColorRoundTrackEvent.
	 *
	 * @param event the MoveDieMatchColorRoundTrackEvent.
	 */
	@Override
	public void notifyObservers(MoveDieMatchColorRoundTrackEvent event) {
		this.moveDieMatchColorRoundTrackObservers.forEach(moveDieMatchColorRoundTrackObserver -> moveDieMatchColorRoundTrackObserver.handle(event));
	}

	/**
	 * Add a MoveDieRespectAllRestrictionObserver.
	 *
	 * @param observer the MoveDieRespectAllRestrictionObserver.
	 */
	@Override
	public void addObserver(MoveDieRespectAllRestrictionsObserver observer) {
		this.moveDieRespectAllRestrictionsObservers.add(observer);
	}

	/**
	 * Remove a MoveDieRespectAllRestrictionObserver.
	 *
	 * @param observer the MoveDieRespectAllRestrictionObserver.
	 */
	@Override
	public void removeObserver(MoveDieRespectAllRestrictionsObserver observer) {
		this.moveDieRespectAllRestrictionsObservers.add(observer);
	}

	/**
	 * Notify the MoveDieRespectAllRestrictionObservers an MoveDieRespectAllRestrictionEvent.
	 *
	 * @param event the MoveDieRespectAllRestrictionEvent.
	 */
	@Override
	public void notifyObservers(MoveDieRespectAllRestrictionsEvent event) {
		this.moveDieRespectAllRestrictionsObservers.forEach(moveDieRespectAllRestrictionsObserver -> moveDieRespectAllRestrictionsObserver.handle(event));
	}

	/**
	 * Add a RerollAllDraftDiceObserver.
	 *
	 * @param observer the RerollAllDraftDiceObserver.
	 */
	@Override
	public void addObserver(RerollAllDraftDiceObserver observer) {
		this.rerollAllDraftDiceObservers.add(observer);
	}

	/**
	 * Remove a RerollAllDraftDiceObserver.
	 *
	 * @param observer the RerollAllDraftDiceObserver.
	 */
	@Override
	public void removeObserver(RerollAllDraftDiceObserver observer) {
		this.rerollAllDraftDiceObservers.add(observer);
	}

	/**
	 * Notify the RerollAllDraftDiceObservers an RerollAllDraftDiceEvent.
	 *
	 * @param event the RerollAllDraftDiceEvent.
	 */
	@Override
	public void notifyObservers(RerollAllDraftDiceEvent event) {
		this.rerollAllDraftDiceObservers.forEach(rerollAllDraftDiceObserver -> rerollAllDraftDiceObserver.handle(event));
	}

	/**
	 * Add a RerollDraftDieObserver.
	 *
	 * @param observer the RerollDraftDieObserver.
	 */
	@Override
	public void addObserver(RerollDraftDieObserver observer) {
		this.rerollDraftDieObservers.add(observer);
	}

	/**
	 * Remove a RerollDraftDieObserver.
	 *
	 * @param observer the RerollDraftDieObserver.
	 */
	@Override
	public void removeObserver(RerollDraftDieObserver observer) {
		this.rerollDraftDieObservers.add(observer);
	}

	/**
	 * Notify the RerollDraftDieObservers an RerollDraftDieEvent.
	 *
	 * @param event the RerollDraftDieEvent.
	 */
	@Override
	public void notifyObservers(RerollDraftDieEvent event) {
		this.rerollDraftDieObservers.forEach(rerollDraftDieObserver -> rerollDraftDieObserver.handle(event));
	}

	/**
	 * Add a SwapDraftDieWithDiceBagDieObserver.
	 *
	 * @param observer the SwapDraftDieWithDiceBagDieObserver.
	 */
	@Override
	public void addObserver(SwapDraftDieWithDiceBagDieObserver observer) {
		this.swapDraftDieWithDiceBagDieObservers.add(observer);
	}

	/**
	 * Remove a SwapDraftDieWithDiceBagDieObserver.
	 *
	 * @param observer the SwapDraftDieWithDiceBagDieObserver.
	 */
	@Override
	public void removeObserver(SwapDraftDieWithDiceBagDieObserver observer) {
		this.swapDraftDieWithDiceBagDieObservers.add(observer);
	}

	/**
	 * Notify the SwapDraftDieWithDiceBagDieObservers an SwapDraftDieWithDiceBagDieEvent.
	 *
	 * @param event the SwapDraftDieWithDiceBagDieEvent.
	 */
	@Override
	public void notifyObservers(SwapDraftDieWithDiceBagDieEvent event) {
		this.swapDraftDieWithDiceBagDieObservers.forEach(swapDraftDieWithDiceBagDieObserver -> swapDraftDieWithDiceBagDieObserver.handle(event));
	}

	/**
	 * Add a SwapDrafDieWithRoundTrackDieObserver.
	 *
	 * @param observer the SwapDrafDieWithRoundTrackDieObserver.
	 */
	@Override
	public void addObserver(SwapDraftDieWithRoundTrackDieObserver observer) {
		this.swapDraftDieWithRoundTrackDieObservers.add(observer);
	}

	/**
	 * Remove a SwapDrafDieWithRoundTrackDieObserver.
	 *
	 * @param observer the SwapDrafDieWithRoundTrackDieObserver.
	 */
	@Override
	public void removeObserver(SwapDraftDieWithRoundTrackDieObserver observer) {
		this.swapDraftDieWithRoundTrackDieObservers.add(observer);
	}

	/**
	 * Notify the SwapDrafDieWithRoundTrackDieObservers an SwapDrafDieWithRoundTrackDieEvent.
	 *
	 * @param event the SwapDrafDieWithRoundTrackDieEvent.
	 */
	@Override
	public void notifyObservers(SwapDraftDieWithRoundTrackDieEvent event) {
		this.swapDraftDieWithRoundTrackDieObservers.forEach(swapDraftDieWithRoundTrackDieObserver -> swapDraftDieWithRoundTrackDieObserver.handle(event));
	}

	/**
	 * Add a UseToolCardObserver.
	 *
	 * @param observer the UseToolCardObserver.
	 */
	@Override
	public void addObserver(UseToolCardObserver observer) {
		this.useToolCardObservers.add(observer);
	}

	/**
	 * Remove a UseToolCardObserver.
	 *
	 * @param observer the UseToolCardObserver.
	 */
	@Override
	public void removeObserver(UseToolCardObserver observer) {
		this.useToolCardObservers.add(observer);
	}

	/**
	 * Notify the UseToolCardObservers an UseToolCardEvent.
	 *
	 * @param event the UseToolCardEvent.
	 */
	@Override
	public void notifyObservers(UseToolCardEvent event) {
		this.useToolCardObservers.forEach(useToolCardObserver -> useToolCardObserver.handle(event));
	}

	/**
	 * Add a WindowPatternChosenObserver.
	 *
	 * @param observer the WindowPatternChosenObserver.
	 */
	@Override
	public void addObserver(WindowPatternChosenObserver observer) {
		this.windowPatternChosenObservers.add(observer);
	}

	/**
	 * Remove a WindowPatternChosenObserver.
	 *
	 * @param observer the WindowPatternChosenObserver.
	 */
	@Override
	public void removeObserver(WindowPatternChosenObserver observer) {
		this.windowPatternChosenObservers.add(observer);
	}

	/**
	 * Notify the WindowPatternChosenObservers an WindowPatternChosenEvent.
	 *
	 * @param event the WindowPatternChosenEvent.
	 */
	@Override
	public void notifyObservers(WindowPatternChosenEvent event) {
		this.windowPatternChosenObservers.forEach(windowPatternChosenObserver -> windowPatternChosenObserver.handle(event));
	}
}
