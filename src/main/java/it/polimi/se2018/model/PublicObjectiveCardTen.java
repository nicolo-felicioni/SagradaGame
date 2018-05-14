package it.polimi.se2018.model;

/**
 * @author Davide Yi Xian Hu
 */
public class PublicObjectiveCardTen extends PublicObjectiveCard {

	/**
	 * Name of the PublicObjectiveCardTen.
	 */
	public static final String NAME = "Color Variety";

	/**
	 * Info of the PublicObjectiveCardTen.
	 */
	public static final String INFO = "Sets of one of each color anywhere";

	/**
	 * Points per set of values everywhere.
	 */
	public static final int POINTS_PER_SET = 4;

	/**
	 * Constructor of the class. No parameters.
	 */
	public PublicObjectiveCardTen() {
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
		int blues = this.getAmountOfDiceWith(windowPattern, DieColor.BLUE);
		int greens = this.getAmountOfDiceWith(windowPattern, DieColor.GREEN);
		int purples = this.getAmountOfDiceWith(windowPattern, DieColor.PURPLE);
		int reds = this.getAmountOfDiceWith(windowPattern, DieColor.RED);
		int yellows = this.getAmountOfDiceWith(windowPattern, DieColor.YELLOW);
		int numberOfSets = Math.min(Math.min(Math.min(blues, greens), Math.min(purples, reds)), yellows);
		return numberOfSets * POINTS_PER_SET;
	}

	/**
	 * Get the amount of dice that has a certain color in a window pattern.
	 *
	 * @param windowPattern the WindowPattern of the player.
	 * @param color the color of the die.
	 * @return the amount of dice that has a color equals to the input color.
	 */
	private int getAmountOfDiceWith(WindowPattern windowPattern, DieColor color) {
		Space[][] spaces = windowPattern.getAllSpaces();
		int numberOfDice = 0;
		for(int i = 0; i < WindowPattern.SPACES_HEIGTH; i++) {
			for(int j = 0; j < WindowPattern.SPACES_LENGTH; j++) {
				if(spaces[i][j].hasDie()) {
					if (color.equals(spaces[i][j].getDie().getColor())) {
						numberOfDice++;
					}
				}
			}
		}
		return numberOfDice;
	}
}