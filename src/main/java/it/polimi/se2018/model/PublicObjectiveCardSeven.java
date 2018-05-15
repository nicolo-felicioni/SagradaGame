package it.polimi.se2018.model;

/**
 * @author Davide Yi Xian Hu
 */
public class PublicObjectiveCardSeven extends PublicObjectiveCard {

	/**
	 * Name of the PublicObjectiveCardSeven.
	 */
	public static final String NAME = "Deep Shades";

	/**
	 * Info of the PublicObjectiveCardSeven.
	 */
	public static final String INFO = "Sets of 5 & 6 values anywhere";

	/**
	 * Points per pair of values everywhere.
	 */
	public static final int POINTS_PER_PAIR = 2;

	/**
	 * First value of the pair of values.
	 */
	public static final DieValue FIRST_VALUE = DieValue.FIVE;

	/**
	 * Second value of the pair of values.
	 */
	public static final DieValue SECOND_VALUE = DieValue.SIX;

	/**
	 * Constructor of the class. No parameters.
	 */
	public PublicObjectiveCardSeven() {
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
		int numberOfFives = this.getAmountOfDiceWith(windowPattern, FIRST_VALUE);
		int numberOfSixs = this.getAmountOfDiceWith(windowPattern, SECOND_VALUE);
		int numberOfPairs = Math.min(numberOfFives, numberOfSixs);
		return numberOfPairs * POINTS_PER_PAIR;
	}

}