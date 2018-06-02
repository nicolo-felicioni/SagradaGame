package it.polimi.se2018.model;

/**
 * @author Davide Yi Xian Hu
 */
public class ToolCardNine extends ToolCard {

	/**
	 * Name of the ToolCardNine.
	 */
	public static final String NAME = "Cork-backed Straightedge";

	/**
	 * Info of the ToolCardNine.
	 */
	public static final String INFO = "After drafting, place the die in a spot that is not adjacent to another die. You must obey all other placement restrictions";

	/**
	 * Constructor of the class. No parameters.
	 */
	public ToolCardNine() {
		super(NAME, INFO);
	}

	/**
	 * Copy constructor.
	 */
	public ToolCardNine(boolean used, boolean active, int favorTokensSpent) {
		super(NAME, INFO, used, active, favorTokensSpent);
	}

	/**
	 * @inheritDoc
	 * If the card is active, it returns true.
	 */
	@Override
	public boolean placeDraftedDieNoAdjacent() {
		return isActive();
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public ToolCard cloneToolCard() {
		return new ToolCardNine(this.isUsed(), this.isActive(), this.getFavorTokensSpent());
	}
}