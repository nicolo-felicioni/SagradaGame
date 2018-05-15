package it.polimi.se2018.model;

/**
 * @author Davide Yi Xian Hu
 */
public class ToolCardOne extends ToolCard {

	/**
	 * Name of the ToolCardOne.
	 */
	public static final String NAME = "Grozing Pliers";

	/**
	 * Info of the ToolCardOne.
	 */
	public static final String INFO = "After drafting, increase or decrease the value of the drafted die by 1. 1 may not change to 6, or 6 to 1";

	/**
	 * Constructor of the class. No parameters.
	 */
	public ToolCardOne() {
		super(NAME, INFO);
	}

	/**
	 * @inheritDoc
	 * If the card is active, it returns true.
	 */
	@Override
	public boolean increaseDieValue() {
		return isActive();
	}

	/**
	 * @inheritDoc
	 * If the card is active, it returns true.
	 */
	@Override
	public boolean decreaseDieValue() {
		return isActive();
	}

	/**
	 * @inheritDoc
	 * When the effect is used the card is deactivated.
	 */
	@Override
	void effectUsed() {
		this.endActivion();
	}
}