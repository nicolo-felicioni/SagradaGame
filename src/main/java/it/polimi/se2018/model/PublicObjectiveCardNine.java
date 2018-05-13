package it.polimi.se2018.model;

/**
 * @author Davide Yi Xian Hu
 */
public class PublicObjectiveCardNine extends PublicObjectiveCard {

	/**
	 * Name of the PublicObjectiveCardNine.
	 */
	public static final String NAME = "Color Diagonals";

	/**
	 * Info of the PublicObjectiveCardNine.
	 */
	public static final String INFO = "Count of diagonally adjacent same-color dice";

	/**
	 * Points per die.
	 */
	public static final int POINTS_PER_DIE = 1;

	public PublicObjectiveCardNine() {
		super(NAME, INFO);
	}

	/**
	 * Calculate the points of a window pattern with this objective card.
	 *
	 * @param windowPattern the WindowPattern of the player.
	 * @return the amount of points.
	 */
	@Override
	public int calculatePoints(WindowPattern windowPattern) {
		return 0;
	}



}