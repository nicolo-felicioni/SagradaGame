package it.polimi.se2018.event;

import it.polimi.se2018.controller.Controller;
import it.polimi.se2018.model.Model;
import it.polimi.se2018.observer.GameEventObserver;

/**
 * @author davide yi xian hu
 */
public interface GameEvent { // = ModelUpdater

	/**
	 * Accept an observer. Visitor pattern.
	 * @param observer the observer to be called.
	 */
	void accept(GameEventObserver observer);

}
