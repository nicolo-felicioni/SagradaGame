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