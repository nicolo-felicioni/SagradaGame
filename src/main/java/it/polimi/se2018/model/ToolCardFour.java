package it.polimi.se2018.model;

/**
 * @author Davide Yi Xian Hu
 */
public class ToolCardFour extends ToolCard {

	/**
	 * Name of the ToolCardFour.
	 */
	public static final String NAME = "Lathekin";

	/**
	 * Info of the ToolCardFour.
	 */
	public static final String INFO = "Move exactly two dice, obeying all placement restrictions";

	/**
	 * Constructor of the class. No parameters.
	 */
	public ToolCardFour() {
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
	 */
	@Override
	public boolean moveDieIgnoreColor() {
		return false;
	}

	/**
	 * @inheritDoc
	 *
	 */
	@Override
	public boolean moveDieIgnoreValue() {
		return false;
	}

	/**
	 * @inheritDoc
	 * If the card is active, it returns true.
	 */
	@Override
	public boolean moveTwoDice() {
		return isActive();
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