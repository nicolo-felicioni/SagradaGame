package it.polimi.se2018.model;

/**
 * @author Davide Yi Xian Hu
 */
public abstract class PublicObjectiveCard implements ObjectiveCard {

	/**
	 * Card name.
	 */
	private final String name;

	/**
	 * Card information.
	 */
	private final String info;

	/**
	 * Constructor.
	 *
	 * @param name the name of the card.
	 * @param info the info of the card.
	 */
	public PublicObjectiveCard(String name, String info) {
		this.name = name;
		this.info = info;
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
	 * Get the amount of dice that has a certain value in a window pattern.
	 *
	 * @param windowPattern the WindowPattern of the player.
	 * @param value the value of the die.
	 * @return the amount of dice that has a value equals to the input value.
	 */
	protected int getAmountOfDiceWith(WindowPattern windowPattern, DieValue value) {
		Space[][] spaces = windowPattern.getAllSpaces();
		int numberOfDice = 0;
		for(int i = 0; i < WindowPattern.SPACES_HEIGHT; i++) {
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


	/**
	 * Get the amount of dice that has a certain color in a window pattern.
	 *
	 * @param windowPattern the WindowPattern of the player.
	 * @param color the color of the die.
	 * @return the amount of dice that has a color equals to the input color.
	 */
	protected int getAmountOfDiceWith(WindowPattern windowPattern, DieColor color) {
		Space[][] spaces = windowPattern.getAllSpaces();
		int numberOfDice = 0;
		for(int i = 0; i < WindowPattern.SPACES_HEIGHT; i++) {
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