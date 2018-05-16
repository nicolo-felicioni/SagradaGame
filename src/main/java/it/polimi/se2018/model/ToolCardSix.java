package it.polimi.se2018.model;

/**
 * @author Davide Yi Xian Hu
 */
public class ToolCardSix extends ToolCard {

	/**
	 * Name of the ToolCardSix.
	 */
	public static final String NAME = "Flux Brush";

	/**
	 * Info of the ToolCardSix.
	 */
	public static final String INFO = "After drafting, reroll the drated die. If it cannot be placed, return it to the draft pool";

	/**
	 * Constructor of the class. No parameters.
	 */
	public ToolCardSix() {
		super(NAME, INFO);
	}

	/**
	 * @inheritDoc
	 * If the card is active, it returns true.
	 */
	@Override
	public boolean rerollDraftedDie() {
		return isActive();
	}

}