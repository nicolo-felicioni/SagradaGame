package it.polimi.se2018.model;

import it.polimi.se2018.exceptions.ToolCardStateException;

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
	 * Indicates how many dice have been moved since activation.
	 * Cannot be more than two.
	 */
	private int numberOfDiceMoved;

	/**
	 * Constructor of the class. No parameters.
	 */
	public ToolCardTwelve() {
		super(NAME, INFO);
	}

	/**
	 * Copy constructor.
	 */
	public ToolCardTwelve(boolean used, boolean active, int favorTokensSpent, int numberOfDiceMoved) {
		super(NAME, INFO, used, active, favorTokensSpent);
		this.numberOfDiceMoved = numberOfDiceMoved;
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
	 * Consume the effect of the card.
	 * A player can move a die twice.
	 * If the player have used the card to move 2 dice, this method end the activation of the card.
	 */
	@Override
	public void consumeEffect() throws ToolCardStateException {
		if(this.isActive()) {
			if (numberOfDiceMoved == 1) {
				this.endActivion();
			} else {
				numberOfDiceMoved++;
			}
		}else{
			throw new ToolCardStateException(this.getName() + " is not active or cannot end the activation of the card.");
		}
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public ToolCard cloneToolCard() {
		return new ToolCardTwelve(this.isUsed(), this.isActive(), this.getFavorTokensSpent(), numberOfDiceMoved);
	}
}