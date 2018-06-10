package it.polimi.se2018.controller;

import it.polimi.se2018.event.*;
import it.polimi.se2018.exceptions.NotValidIdException;
import it.polimi.se2018.exceptions.NotValidPatternVectorException;
import it.polimi.se2018.exceptions.TooManyPlayersException;
import it.polimi.se2018.model.Model;
import it.polimi.se2018.model.Player;
import it.polimi.se2018.observer.GameEventObserver;

import java.util.List;

/**
 * @author davide yi xian hu
 */
public class Controller implements GameEventObserver {

	private Model model;

	/**
	 * Handle a ChooseDraftDieValueEvent.
	 *
	 * @param event the ChooseDraftDieValueEvent.
	 */
	@Override
	public void handle(ChooseDraftDieValueGameEvent event) {

	}

	/**
	 * Handle a DecreaseDieValueEvent.
	 *
	 * @param event the DecreaseDieValueEvent.
	 */
	@Override
	public void handle(DecreaseDieValueGameEvent event) {

	}

	/**
	 * Handle a DraftAndPlaceAgainEvent.
	 *
	 * @param event the DraftAndPlaceAgainEvent.
	 */
	@Override
	public void handle(DraftAndPlaceAgainGameEvent event) {

	}

	/**
	 * Handle a DraftAndPlaceNoAdjacentEvent.
	 *
	 * @param event the DraftAndPlaceNoAdjacentEvent.
	 */
	@Override
	public void handle(DraftAndPlaceNoAdjacentGameEvent event) {

	}

	/**
	 * Handle a DraftAndPlaceEvent.
	 *
	 * @param event the DraftAndPlaceEvent.
	 */
	@Override
	public void handle(DraftAndPlaceGameEvent event) {

	}

	/**
	 * Handle a EndTurnEvent.
	 *
	 * @param event the EndTurnEvent.
	 */
	@Override
	public void handle(EndTurnGameEvent event) {

	}

	/**
	 * Handle a FlipDraftDieEvent.
	 *
	 * @param event the FlipDraftDieEvent.
	 */
	@Override
	public void handle(FlipDraftDieGameEvent event) {

	}

	/**
	 * Handle a IncreaseDieValueEvent.
	 *
	 * @param event the IncreaseDieValueEvent.
	 */
	@Override
	public void handle(IncreaseDieValueGameEvent event) {

	}

	/**
	 * Handle a MoveDieIgnoreColorRestrictionEvent.
	 *
	 * @param event the MoveDieIgnoreColorRestrictionEvent.
	 */
	@Override
	public void handle(MoveDieIgnoreColorRestrictionGameEvent event) {

	}

	/**
	 * Handle a MoveDieIgnoreValueRestrictionEvent.
	 *
	 * @param event the MoveDieIgnoreValueRestrictionEvent.
	 */
	@Override
	public void handle(MoveDieIgnoreValueRestrictionGameEvent event) {

	}

	/**
	 * Handle a MoveDieMatchColorRoundTrackEvent.
	 *
	 * @param event the MoveDieMatchColorRoundTrackEvent.
	 */
	@Override
	public void handle(MoveDieMatchColorRoundTrackGameEvent event) {

	}

	/**
	 * Handle a MoveDieRespectAllRestrictionsEvent.
	 *
	 * @param event the MoveDieRespectAllRestrictionsEvent.
	 */
	@Override
	public void handle(MoveDieRespectAllRestrictionsGameEvent event) {

	}

	/**
	 * Handle a RerollAllDraftDiceEvent.
	 *
	 * @param event the RerollAllDraftDiceEvent.
	 */
	@Override
	public void handle(RerollAllDraftDiceGameEvent event) {

	}

	/**
	 * Handle a RerollDraftDieEvent.
	 *
	 * @param event the RerollDraftDieEvent.
	 */
	@Override
	public void handle(RerollDraftDieGameEvent event) {

	}

	/**
	 * Handle a SwapDraftDieWithDiceBagDieEvent.
	 *
	 * @param event the SwapDraftDieWithDiceBagDieEvent.
	 */
	@Override
	public void handle(SwapDraftDieWithDiceBagDieGameEvent event) {

	}

	/**
	 * Handle a SwapDraftDieWithRoundTrackDieEvent.
	 *
	 * @param event the SwapDraftDieWithRoundTrackDieEvent.
	 */
	@Override
	public void handle(SwapDraftDieWithRoundTrackDieGameEvent event) {

	}

	/**
	 * Handle a UseToolCardEvent.
	 *
	 * @param event the UseToolCardEvent.
	 */
	@Override
	public void handle(UseToolCardGameEvent event) {

	}

	/**
	 * Handle a WindowPatternChosenEvent.
	 *
	 * @param event the WindowPatternChosenEvent.
	 */
	@Override
	public void handle(WindowPatternChosenGameEvent event) {

	}

	/**
	 * Handle a StartGameEvent.
	 *
	 * @param event the StartGameEvent.
	 */
	@Override
	public void handle(StartGameEvent event) {
		this.startGame(event.getPlayerIds());
	}

	/**
	 * Start the game. Initialize the model with the players.
	 * @param ids a list of player identifiers.
	 */
	private void startGame(List<String> ids) {
		this.model = new Model();
		ids.stream().forEach(id -> {
			try {
				this.model.addPlayer(new Player(id));
			} catch (TooManyPlayersException e) {
				e.printStackTrace();
			} catch (NotValidIdException e) {
				e.printStackTrace();
			}
		});
		this.initGame();
	}

	/**
	 * Initialize the game
	 */
	private void initGame() {
		initPrivateObjectiveCards();
		initWindowPatterns();
	}

	/**
	 * Initialize private objective cards.
	 * Give a private objective card to each player.
	 */
	private void initPrivateObjectiveCards() {
		PrivateObjectiveCardsFactory privateCardFactory = new PrivateObjectiveCardsFactory();
		model.getPlayers().stream().forEach(player -> model.setPrivateObjectiveCardToPlayer(player.getId(), privateCardFactory.drawCard()));
	}

	/**
	 * Initialize window patterns.
	 * Give to each player a set of window patterns.
	 */
	private void initWindowPatterns() {
		WindowPatternFactory windowPatternFactory = new WindowPatternFactory();
		model.getPlayers().stream().forEach(player -> {
			try {
				model.setPatternsToPlayer(player.getId(), windowPatternFactory.getWindowPattern(Player.N_WINDOW_PATTERNS));
			} catch (NotValidPatternVectorException e) {
				e.printStackTrace();
			}
		});
	}
}
