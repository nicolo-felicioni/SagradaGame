package it.polimi.se2018.model;

/**
 * @author Davide Yi Xian Hu
 */
public class ToolCardEleven extends ToolCard {

	/**
	 * Name of the ToolCardEleven.
	 */
	public static final String NAME = "Flux Remover";

	/**
	 * Info of the ToolCardEleven.
	 */
	public static final String INFO = "After drafting, return the die to the DiceBag and pull 1 die from the bag. Choose a value and place the new die, obeying all placement restrictions, or return it to the draft pool";

	/**
	 * Constructor of the class. No parameters.
	 */
	public ToolCardEleven() {
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
	 * If the card is active, it returns true.
	 */
	@Override
	public boolean returnDieAndGetNewFromDiceBag() {
		return isActive();
	}

	/**
	 * @inheritDoc
	 * If the card is active, it returns true.
	 */
	@Override
	public boolean chooseNewDieValue() {
		return isActive();
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