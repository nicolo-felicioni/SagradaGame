package it.polimi.se2018.model;

/**
 * @author Davide Yi Xian Hu
 */
public interface ObjectiveCard extends Card {

	/**
	 * Calculate the points of a window pattern with this objective card.
	 *
	 * @param windowPattern the WindowPattern of the player.
	 * @return the amount of points.
	 */
	public int calculatePoints(WindowPattern windowPattern);

}