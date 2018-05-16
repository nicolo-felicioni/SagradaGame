package it.polimi.se2018.model;

/**
 * @author Davide Yi Xian Hu
 */
public class PublicObjectiveCardFive extends PublicObjectiveCard {

	/**
	 * Name of the PublicObjectiveCardFive.
	 */
	public static final String NAME = "Light Shades";

	/**
	 * Info of the PublicObjectiveCardFive.
	 */
	public static final String INFO = "Sets of 1 & 2 values anywhere";

	/**
	 * Points per pair of values everywhere.
	 */
	public static final int POINTS_PER_PAIR = 2;

	/**
	 * First value of the pair of values.
	 */
	public static final DieValue FIRST_VALUE = DieValue.ONE;

	/**
	 * Second value of the pair of values.
	 */
	public static final DieValue SECOND_VALUE = DieValue.TWO;

	/**
	 * Constructor of the class. No parameters.
	 */
	public PublicObjectiveCardFive() {
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
		int numberOfOnes = this.getAmountOfDiceWith(windowPattern, FIRST_VALUE);
		int numberOfTwos = this.getAmountOfDiceWith(windowPattern, SECOND_VALUE);
		int numberOfPairs = Math.min(numberOfOnes, numberOfTwos);
		return numberOfPairs * POINTS_PER_PAIR;
	}

}