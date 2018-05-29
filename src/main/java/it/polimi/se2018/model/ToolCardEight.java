package it.polimi.se2018.model;

/**
 * @author Davide Yi Xian Hu
 */
public class ToolCardEight extends ToolCard {

	/**
	 * Name of the ToolCardEight.
	 */
	public static final String NAME = "Running Pliers";

	/**
	 * Info of the ToolCardEight.
	 */
	public static final String INFO = "After your first turn immediately draft a die. Skip your next turn this round";

	/**
	 * Constructor of the class. No parameters.
	 */
	public ToolCardEight() {
		super(NAME, INFO);
	}

	/**
	 * @inheritDoc
	 * If the card is active, it returns true.
	 */
	@Override
	public boolean placeDieAfterFirstTurn() {
		return isActive();
	}

	@Override
	public ToolCard cloneToolCard() {
		//TODO - implementare clone
		return null;
	}

}