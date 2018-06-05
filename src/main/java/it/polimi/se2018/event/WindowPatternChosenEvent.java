package it.polimi.se2018.event;

import it.polimi.se2018.model.WindowPattern;

/**
 * @author davide yi xian hu
 */
public class WindowPatternChosenEvent extends AbstractPlayerEvent{

	/**
	 * The window pattern chosen by the player.
	 */
	private WindowPattern window;

	/**
	 * Constructor.
	 * @param window the window pattern chosen by the player.
	 * @param playerId the player identifier.
	 */
	public WindowPatternChosenEvent(WindowPattern window, String playerId) {
		super(playerId);
		this.window = window;
	}

	/**
	 * Getter of window pattern chosen by the player.
	 * @return window pattern chosen by the player.
	 */
	public WindowPattern getWindow() {
		return window;
	}


}
