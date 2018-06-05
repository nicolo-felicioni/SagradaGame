package it.polimi.se2018.model;

/**
 * @author Davide Yi Xian Hu
 */
public class ToolCardTwo extends ToolCard {

	/**
	 * Name of the ToolCardTwo.
	 */
	public static final String NAME = "Eglomise Brush";

	/**
	 * Info of the ToolCardTwo.
	 */
	public static final String INFO = "Move any one die in your window ignoring the color restrictions. You must obey all other placement restrictions";

	/**
	 * Constructor of the class. No parameters.
	 */
	public ToolCardTwo() {
		super(NAME, INFO);
	}

	/**
	 * Copy constructor.
	 */
	public ToolCardTwo(boolean used, boolean active, int favorTokensSpent) {
		super(NAME, INFO, used, active, favorTokensSpent);
	}

	/**
	 * @inheritDoc
	 * If the card is active, it returns true.
	 */
	@Override
	public boolean moveDieIgnoreColor() {
		return isActive();
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public ToolCard cloneToolCard() {
		return new ToolCardTwo(this.isUsed(), this.isActive(), this.getFavorTokensSpent());
	}

}