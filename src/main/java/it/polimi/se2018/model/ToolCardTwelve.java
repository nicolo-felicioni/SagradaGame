package it.polimi.se2018.model;

/**
 * @author Davide Yi Xian Hu
 */
public class ToolCardTwelve extends ToolCard {

	/**
	 * Name of the ToolCardTwelve.
	 */
	public static final String NAME = "Tap Wheel";

	/**
	 * Info of the ToolCardTwelve.
	 */
	public static final String INFO = "Move up to two dice of the same color that match the color of a die on the round track. You must obey all placement restrictions";

	/**
	 * Constructor of the class. No parameters.
	 */
	public ToolCardTwelve() {
		super(NAME, INFO);
	}

	/**
	 * Copy constructor.
	 */
	public ToolCardTwelve(boolean used, boolean active, int favorTokensSpent) {
		super(NAME, INFO, used, active, favorTokensSpent);
	}

	/**
	 * @inheritDoc
	 * If the card is active, it returns true.
	 */
	@Override
	public boolean moveTwoDiceMatchColorOnRoundTrack() {
		return isActive();
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public ToolCard cloneToolCard() {
		return new ToolCardTwelve(this.isUsed(), this.isActive(), this.getFavorTokensSpent());
	}
}