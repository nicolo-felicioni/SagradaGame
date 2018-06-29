package it.polimi.se2018.model;

import it.polimi.se2018.exceptions.ToolCardStateException;

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
	 * Indicates how many dice have been moved since activation.
	 * Cannot be more than two.
	 */
	private int numberOfDiceMoved;

	/**
	 * Constructor of the class. No parameters.
	 */
	public ToolCardFour() {
		super(NAME, INFO);
		numberOfDiceMoved = 0;
	}

	/**
	 * Copy constructor.
	 */
	public ToolCardFour(boolean used, boolean active, int favorTokensSpent, int  numberOfDiceMoved) {
		super(NAME, INFO, used, active, favorTokensSpent);
		this.numberOfDiceMoved = numberOfDiceMoved;
	}

	/**
	 * @inheritDoc
	 * If the card is active, it returns true.
	 */
	@Override
	public boolean moveADie() {
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
		return new ToolCardFour(this.isUsed(), this.isActive(), this.getFavorTokensSpent(), numberOfDiceMoved);
	}

	/**
	 * End activation of this card. Reset the state of this card.
	 */
	@Override
	public void endActivion() throws ToolCardStateException {
		super.endActivion();
		numberOfDiceMoved = 0;
	}

}