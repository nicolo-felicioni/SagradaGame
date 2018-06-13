package it.polimi.se2018.event;

import it.polimi.se2018.model.WindowPattern;
import it.polimi.se2018.observer.GameEventObserver;

/**
 * @author davide yi xian hu
 */
public class WindowPatternChosenGameEvent extends AbstractPlayerGameEvent {

	/**
	 * The window pattern chosen by the player.
	 */
	private WindowPattern window;

	/**
	 * Constructor.
	 * @param window the window pattern chosen by the player.
	 * @param playerId the player identifier.
	 */
	public WindowPatternChosenGameEvent(WindowPattern window, String playerId) {
		super(playerId);
		this.window = window.cloneWindowPattern();
	}

	/**
	 * Getter of window pattern chosen by the player.
	 * @return window pattern chosen by the player.
	 */
	public WindowPattern getWindow() {
		return window.cloneWindowPattern();
	}

	/**
	 * Accept an observer. Visitor pattern.
	 *
	 * @param observer the observer to be called.
	 */
	@Override
	public void accept(GameEventObserver observer) {
		observer.handle(this);
	}

}
