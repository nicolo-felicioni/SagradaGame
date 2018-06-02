package it.polimi.se2018.model;

/**
 * @author Davide Yi Xian Hu
 */
public class ToolCardEight extends ToolCard {

	/**
	 * Name of the ToolCardEight.
	 */
	public static final String NAME = "Running Pliers";

	/**
	 * Info of the ToolCardEight.
	 */
	public static final String INFO = "After your first turn immediately draft a die. Skip your next turn this round";

	/**
	 * Constructor of the class. No parameters.
	 */
	public ToolCardEight() {
		super(NAME, INFO);
	}

	/**
	 * Copy constructor.
	 */
	public ToolCardEight(boolean used, boolean active, int favorTokensSpent) {
		super(NAME, INFO, used, active, favorTokensSpent);
	}

	/**
	 * @inheritDoc
	 * If the card is active, it returns true.
	 */
	@Override
	public boolean placeDieAfterFirstTurn() {
		return isActive();
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public ToolCard cloneToolCard() {
		return new ToolCardEight(this.isUsed(), this.isActive(), this.getFavorTokensSpent());
	}

}