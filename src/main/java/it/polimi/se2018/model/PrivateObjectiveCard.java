package it.polimi.se2018.model;

/**
 * @author Davide Yi Xian Hu
 */
public class PrivateObjectiveCard implements ObjectiveCard {

	/**
	 * Card name.
	 */
	private final String name;

	/**
	 * Card information.
	 */
	private final String info;

	/**
	 * Card color.
	 */
	private final DieColor color;

	/**
	 * Points per die;
	 */
	public static final int POINTS_PER_DIE = 1;

	/**
	 * Constructor of the class.
	 *
	 * @param color the color of the private objective card.
	 *
	 */
	public PrivateObjectiveCard(DieColor color) {
		this.name = null;
		this.info = null;
		this.color = color;
	}

	/**
	 * Getter of the name attribute.
	 *
	 * @return a string that contains the name of the card.
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * Gettere of the info attribute.
	 *
	 * @return a string that contains the info of the card.
	 */
	@Override
	public String getInfo() {
		return info;
	}

	/**
	 * Getter of the color attribute.
	 *
	 * @return a DieColor that contains the color of the card.
	 */
	public DieColor getColor() {
		return color;
	}

	/**
	 * Calculate the points of a window pattern with this objective card.
	 *
	 * @param windowPattern the WindowPattern of the player.
	 * @return the amount of points.
	 */
	@Override
	public int calculatePoints(WindowPattern windowPattern) {
		return getAmountOfDiceWith(windowPattern, color) * POINTS_PER_DIE;
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
		for(int i = 0; i < WindowPattern.SPACES_LENGTH; i++) {
			for(int j = 0; j < WindowPattern.SPACES_HEIGTH; j++) {
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