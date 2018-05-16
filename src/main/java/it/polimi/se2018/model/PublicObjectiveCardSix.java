package it.polimi.se2018.model;

/**
 * @author Davide Yi Xian Hu
 */
public class PublicObjectiveCardSix extends PublicObjectiveCard {

	/**
	 * Name of the PublicObjectiveCardSix.
	 */
	public static final String NAME = "Medium Shades";

	/**
	 * Info of the PublicObjectiveCardSix.
	 */
	public static final String INFO = "Sets of 3 & 4 values anywhere";

	/**
	 * Points per pair of values everywhere.
	 */
	public static final int POINTS_PER_PAIR = 2;

	/**
	 * First value of the pair of values.
	 */
	public static final DieValue FIRST_VALUE = DieValue.THREE;

	/**
	 * Second value of the pair of values.
	 */
	public static final DieValue SECOND_VALUE = DieValue.FOUR;

	/**
	 * Constructor of the class. No parameters.
	 */
	public PublicObjectiveCardSix() {
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
		int numberOfThrees = this.getAmountOfDiceWith(windowPattern, FIRST_VALUE);
		int numberOfFours = this.getAmountOfDiceWith(windowPattern, SECOND_VALUE);
		int numberOfPairs = Math.min(numberOfThrees, numberOfFours);
		return numberOfPairs * POINTS_PER_PAIR;
	}

}