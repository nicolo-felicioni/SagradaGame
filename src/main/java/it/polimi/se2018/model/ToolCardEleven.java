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
	 * Tool card state. True if the die has been returned to the dicebag.
	 */
	private boolean isDieReturned;


	/**
	 * Constructor of the class. No parameters.
	 */
	public ToolCardEleven() {
		super(NAME, INFO);
		isDieReturned = false;
	}

	/**
	 * Copy constructor.
	 */
	public ToolCardEleven(boolean used, boolean active, int favorTokensSpent, boolean isDieReturned) {
		super(NAME, INFO, used, active, favorTokensSpent);
		this.isDieReturned = isDieReturned;
	}

	/**
	 * @inheritDoc
	 * If the card is active, it returns true.
	 */
	@Override
	public boolean returnDieAndGetNewFromDiceBag() {
		return isActive() && !isDieReturned;
	}

	/**
	 * @inheritDoc
	 * If the card is active, it returns true.
	 */
	@Override
	public boolean chooseNewDieValue() {
		return isActive() && isDieReturned;
	}

	/**
	 * @inheritDoc
	 * When the effect is used the card is deactivated.
	 */
	@Override
	public void consumeEffect() throws ToolCardStateException {
		if(isActive()) {
			if(returnDieAndGetNewFromDiceBag()) {
				isDieReturned = true;
			}else{
				isDieReturned = false;
				this.endActivion();
			}
		} else {
			throw new ToolCardStateException("Can not consume effect. Card is not active.");
		}
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public ToolCard cloneToolCard() {
		return new ToolCardEleven(this.isUsed(), this.isActive(), this.getFavorTokensSpent(), isDieReturned);
	}

	/**
	 * End activation of this card. Reset the state of this card.
	 */
	@Override
	public void endActivion() throws ToolCardStateException {
		super.endActivion();
		isDieReturned = false;
	}

}