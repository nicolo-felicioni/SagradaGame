package it.polimi.se2018.model;

/**
 * @author Davide Yi Xian Hu
 */
public class ToolCardFour extends ToolCard {

	/**
	 * Name of the ToolCardFour.
	 */
	public static final String NAME = "Lathekin";

	/**
	 * Info of the ToolCardFour.
	 */
	public static final String INFO = "Move exactly two dice, obeying all placement restrictions";

	/**
	 * Constructor of the class. No parameters.
	 */
	public ToolCardFour() {
		super(NAME, INFO);
	}

	/**
	 * @inheritDoc
	 * If the card is active, it returns true.
	 */
	@Override
	public boolean moveTwoDice() {
		return isActive();
	}

}