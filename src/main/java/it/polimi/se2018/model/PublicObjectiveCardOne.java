package it.polimi.se2018.model;

/**
 * @author Davide Yi Xian Hu
 */
public class PublicObjectiveCardOne extends PublicObjectiveCard {

	/**
	 * Name of the PublicObjectiveCardOne.
	 */
	public static final String NAME = "Row Color Variety";

	/**
	 * Info of the PublicObjectiveCardOne.
	 */
	public static final String INFO = "Rows with no repeated colors";

	/**
	 * Points per row with all spaces with no repeated colors.
	 */
	public static final int POINTS_PER_ROW = 6;

	/**
	 * Constructor of the class. No parameters.
	 */
	public PublicObjectiveCardOne() {
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
		for(int i = 0; i < WindowPattern.SPACES_HEIGHT; i++) {
			if (rowColorVariety(windowPattern.getSpacesRow(i))) {
				numberOfRows++;
			}
		}
		return numberOfRows * POINTS_PER_ROW;
	}

	/**
	 * Check if an array of spaces has no repeated colors.
	 *
	 * @param spaces the row of a window pattern. It's an array of spaces.
	 * @return true if the array has no repeated colors, false otherwise.
	 */
	private boolean rowColorVariety(Space[] spaces) {
		for(int i = 0; i < spaces.length; i++) {
			for(int j = i+1; j < spaces.length; j++) {
				if(spaces[i].hasDie() && spaces[j].hasDie()) {
					if (spaces[i].getDie().getColor().equals(spaces[j].getDie().getColor())) {
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