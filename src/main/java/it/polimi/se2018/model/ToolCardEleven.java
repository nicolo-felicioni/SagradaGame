package it.polimi.se2018.model;

import it.polimi.se2018.exceptions.ToolCardStateException;

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
	 * Copy constructor.
	 */
	public ToolCardEleven(boolean used, boolean active, int favorTokensSpent) {
		super(NAME, INFO, used, active, favorTokensSpent);
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
	 * When the effect is used the card is deactivated.
	 */
	@Override
	public void consumeEffect() throws ToolCardStateException {
		this.endActivion();
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public ToolCard cloneToolCard() {
		return new ToolCardEleven(this.isUsed(), this.isActive(), this.getFavorTokensSpent());
	}

}