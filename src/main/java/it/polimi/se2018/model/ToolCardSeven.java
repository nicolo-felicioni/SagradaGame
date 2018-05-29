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
	 * If the card is active, it returns true.
	 */
	@Override
	public boolean rerollAllDraftPoolDice() {
		return isActive();
	}

	@Override
	public ToolCard cloneToolCard() {
		//TODO - implementare clone
		return null;
	}

}