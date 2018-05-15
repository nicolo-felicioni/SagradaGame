package it.polimi.se2018.model;

/**
 * @author Davide Yi Xian Hu
 */
public class PublicObjectiveCardThree extends PublicObjectiveCard {

	/**
	 * Name of the PublicObjectiveCardThree.
	 */
	public static final String NAME = "Row Shade Variety";

	/**
	 * Info of the PublicObjectiveCardThree.
	 */
	public static final String INFO = "Rows with no repeated values";

	/**
	 * Points per row with all spaces with no repeated values.
	 */
	public static final int POINTS_PER_ROW = 5;

	/**
	 * Constructor of the class. No parameters.
	 */
	public PublicObjectiveCardThree() {
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
		int numberOfRows = 0;
		for(int i = 0; i < WindowPattern.SPACES_HEIGTH; i++) {
			if (rowShadeVariety(windowPattern.getSpacesRow(i))) {
				numberOfRows++;
			}
		}
		return numberOfRows * POINTS_PER_ROW;
	}

	/**
	 * Check if an array of spaces has no repeated values.
	 *
	 * @param spaces the row of a window pattern. It's an array of spaces.
	 * @return true if the array has no repeated values, false otherwise.
	 */
	private boolean rowShadeVariety(Space[] spaces) {
		for(int i = 0; i < spaces.length; i++) {
			for(int j = i+1; j < spaces.length; j++) {
				if(spaces[i].hasDie() && spaces[j].hasDie()) {
					if (spaces[i].getDie().getValue().equals(spaces[j].getDie().getValue())) {
						return false;
					}
				}else{
					return false;
				}
			}
		}
		return true;
	}
}