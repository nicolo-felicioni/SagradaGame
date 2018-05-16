package it.polimi.se2018.model;

/**
 * @author Davide Yi Xian Hu
 */
public class ToolCardThree extends ToolCard {

	/**
	 * Name of the ToolCardThree.
	 */
	public static final String NAME = "Copper Foil Burnisher";

	/**
	 * Info of the ToolCardThree.
	 */
	public static final String INFO = "Move any one die in your window ignoring the shade restrictions. You must obey all other placement restrictions";

	/**
	 * Constructor of the class. No parameters.
	 */
	public ToolCardThree() {
		super(NAME, INFO);
	}

	/**
	 * @inheritDoc
	 * If the card is active, it returns true.
	 */
	@Override
	public boolean moveDieIgnoreValue() {
		return isActive();
	}

}