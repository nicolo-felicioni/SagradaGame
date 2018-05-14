package it.polimi.se2018.model;

/**
 * @author Davide Yi Xian Hu
 */
public class PublicObjectiveCardEight extends PublicObjectiveCard {

	/**
	 * Name of the PublicObjectiveCardEight.
	 */
	public static final String NAME = "Shade Variety";

	/**
	 * Info of the PublicObjectiveCardEight.
	 */
	public static final String INFO = "Sets of one of each value anywhere";

	/**
	 * Points per set of values everywhere.
	 */
	public static final int POINTS_PER_SET = 5;

	/**
	 * Constructor of the class. No parameters.
	 */
	public PublicObjectiveCardEight() {
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
		int ones = this.getAmountOfDiceWith(windowPattern, DieValue.ONE);
		int twos = this.getAmountOfDiceWith(windowPattern, DieValue.TWO);
		int threes = this.getAmountOfDiceWith(windowPattern, DieValue.THREE);
		int fours = this.getAmountOfDiceWith(windowPattern, DieValue.FOUR);
		int fives = this.getAmountOfDiceWith(windowPattern, DieValue.FIVE);
		int sixs = this.getAmountOfDiceWith(windowPattern, DieValue.SIX);
		int numberOfSets = Math.min(Math.min(Math.min(ones, twos), Math.min(threes, fours)),Math.min(fives, sixs));
		return numberOfSets * POINTS_PER_SET;
	}

	/**
	 * Get the amount of dice that has a certain value in a window pattern.
	 *
	 * @param windowPattern the WindowPattern of the player.
	 * @param value the value of the die.
	 * @return the amount of dice that has a value equals to the input value.
	 */
	private int getAmountOfDiceWith(WindowPattern windowPattern, DieValue value) {
		Space[][] spaces = windowPattern.getAllSpaces();
		int numberOfDice = 0;
		for(int i = 0; i < WindowPattern.SPACES_HEIGTH; i++) {
			for(int j = 0; j < WindowPattern.SPACES_LENGTH; j++) {
				if(spaces[i][j].hasDie()) {
					if (value.equals(spaces[i][j].getDie().getValue())) {
						numberOfDice++;
					}
				}
			}
		}
		return numberOfDice;
	}
}