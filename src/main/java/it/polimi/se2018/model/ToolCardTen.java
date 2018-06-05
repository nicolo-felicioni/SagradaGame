package it.polimi.se2018.model;

/**
 * @author Davide Yi Xian Hu
 */
public class ToolCardTen extends ToolCard {

	/**
	 * Name of the ToolCardTen.
	 */
	public static final String NAME = "Grinding Stone";

	/**
	 * Info of the ToolCardTen.
	 */
	public static final String INFO = "After drafting, flip the die to its opposite side. 6 flips to 1, 5 to 2, 4 to 3, etc";

	/**
	 * Constructor of the class. No parameters.
	 */
	public ToolCardTen() {
		super(NAME, INFO);
	}

	/**
	 * Copy constructor.
	 */
	public ToolCardTen(boolean used, boolean active, int favorTokensSpent) {
		super(NAME, INFO, used, active, favorTokensSpent);
	}

	/**
	 * @inheritDoc
	 * If the card is active, it returns true.
	 */
	@Override
	public boolean flipDraftedDie() {
		return isActive();
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public ToolCard cloneToolCard() {
		return new ToolCardTen(this.isUsed(), this.isActive(), this.getFavorTokensSpent());
	}

}