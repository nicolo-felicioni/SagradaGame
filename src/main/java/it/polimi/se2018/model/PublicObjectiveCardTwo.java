package it.polimi.se2018.model;

/**
 * @author Davide Yi Xian Hu
 */
public class PublicObjectiveCardTwo extends PublicObjectiveCard {

	/**
	 * Name of the PublicObjectiveCardTwo.
	 */
	public static final String NAME = "Columnn Color Variety";

	/**
	 * Info of the PublicObjectiveCardTwo.
	 */
	public static final String INFO = "Columns with no repeated colors";

	/**
	 * Points per column with all spaces with no repeated colors.
	 */
	public static final int POINTS_PER_COLUMN = 5;

	/**
	 * Constructor of the class. No parameters.
	 */
	public PublicObjectiveCardTwo() {
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
		int numberOfColumn = 0;
		for (int i = 0; i < WindowPattern.SPACES_LENGTH; i++) {
			if (columnColorVariety(windowPattern.getSpacesColumn(i))) {
				numberOfColumn++;
			}
		}
		return numberOfColumn * POINTS_PER_COLUMN;
	}

	/**
	 * Check if an array of spaces has no repeated colors.
	 *
	 * @param spaces the column of a window pattern. It's an array of spaces.
	 * @return true if the array has no repeated colors, false otherwise.
	 */
	private boolean columnColorVariety(Space[] spaces) {
		for(int i = 0; i < spaces.length; i++) {
			for(int j = i+1; j < spaces.length; j++) {
				if(spaces[i].hasDie() && spaces[j].hasDie()) {
					if(spaces[i].getDie().getColor().equals(spaces[j].getDie().getColor())) {
						return false;
					}
				}
			}
		}
		return true;
	}
}