package it.polimi.se2018.model;

/**
 * @author Davide Yi Xian Hu
 */
public class ToolCardSeven extends ToolCard {

	/**
	 * Name of the ToolCardSeven.
	 */
	public static final String NAME = "Glazing Hammer";

	/**
	 * Info of the ToolCardSeven.
	 */
	public static final String INFO = "Reroll all the dice in the draft pool. This may be used on your second turn before drafting";

	/**
	 * Constructor of the class. No parameters.
	 */
	public ToolCardSeven() {
		super(NAME, INFO);
	}

	/**
	 * Copy constructor.
	 */
	public ToolCardSeven(boolean used, boolean active, int favorTokensSpent) {
		super(NAME, INFO, used, active, favorTokensSpent);
	}

	/**
	 * @inheritDoc
	 * If the card is active, it returns true.
	 */
	@Override
	public boolean rerollAllDraftPoolDice() {
		return isActive();
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public ToolCard cloneToolCard() {
		return new ToolCardSeven(this.isUsed(), this.isActive(), this.getFavorTokensSpent());
	}

}