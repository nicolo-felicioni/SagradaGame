package it.polimi.se2018.event.game;

import it.polimi.se2018.observer.game.GameEventObserver;

import java.io.Serializable;

/**
 * @author Davide Yi Xian Hu
 */
public interface GameEvent extends Serializable { // = ModelUpdater

	/**
	 * Accept an observer. Visitor pattern.
	 * @param observer the observer to be called.
	 */
	void accept(GameEventObserver observer);

}
