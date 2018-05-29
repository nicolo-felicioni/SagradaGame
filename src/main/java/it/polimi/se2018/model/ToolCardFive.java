package it.polimi.se2018.model;

/**
 * @author Davide Yi Xian Hu
 */
public class ToolCardFive extends ToolCard {

	/**
	 * Name of the ToolCardFive.
	 */
	public static final String NAME = "Lens Cutter";

	/**
	 * Info of the ToolCardFive.
	 */
	public static final String INFO = "After drafting, swap the drafted die with a die from the round track";

	/**
	 * Constructor of the class. No parameters.
	 */
	public ToolCardFive() {
		super(NAME, INFO);
	}

	/**
	 * @inheritDoc
	 * If the card is active, it returns true.
	 */
	@Override
	public boolean swapDraftDieWithRoundTrackDie() {
		return isActive();
	}

	@Override
	public ToolCard cloneToolCard() {
		//TODO - implementare clone
		return null;
	}
}