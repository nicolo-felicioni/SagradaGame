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
	 * @inheritDoc
	 */
	@Override
	public boolean increaseDieValue() {
		return false;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public boolean decreaseDieValue() {
		return false;
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
	public boolean moveDieIgnoreValue() { return false; }

	/**
	 * @inheritDoc
	 */
	@Override
	public boolean moveTwoDice() {
		return false;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public boolean swapDraftDieWithRoundTrackDie() {
		return false;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public boolean rerollDraftedDie() {
		return false;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public boolean rerollAllDraftPoolDice() {
		return false;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public boolean placeDieAfterFirstTurn() {
		return false;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public boolean placeDraftedDieNoAdjacent() {
		return false;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public boolean flipDraftedDie() {
		return false;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public boolean returnDieAndGetNewFromDiceBag() {
		return false;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public boolean chooseNewDieValue() {
		return false;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public boolean moveTwoDiceMatchColorOnRoundTrack() {
		return false;
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