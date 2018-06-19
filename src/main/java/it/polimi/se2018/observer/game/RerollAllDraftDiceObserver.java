package it.polimi.se2018.observer.game;

import it.polimi.se2018.event.game.RerollAllDraftDiceGameEvent;

/**
 * @author davide yi xian hu
 */
public interface RerollAllDraftDiceObserver {

	/**
	 * Handle a RerollAllDraftDiceEvent.
	 * @param event the RerollAllDraftDiceEvent.
	 */
	void handle(RerollAllDraftDiceGameEvent event);

}
