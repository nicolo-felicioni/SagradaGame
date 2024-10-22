package it.polimi.se2018.model;

/**
 * @author Davide Yi Xian Hu
 */
public class PrivateObjectiveCard implements ObjectiveCard {

	private static final String SHADES_OF = "Shades of ";

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
		this.name = SHADES_OF + color.name();
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
		int points = 0;
		Space[][] spaces = windowPattern.getAllSpaces();
		for(int i = 0; i < WindowPattern.SPACES_HEIGHT; i++) {
			for(int j = 0; j < WindowPattern.SPACES_LENGTH; j++) {
				if((spaces[i][j].hasDie())&&(color.equals(spaces[i][j].getDie().getColor()))) {
						points+=spaces[i][j].getDie().getValue().toInt();
				}
			}
		}
		return points;
	}

}