package it.polimi.se2018.model;

/**
 * @author Davide Yi Xian Hu
 */
public class ToolCardNine extends ToolCard {

	/**
	 * Name of the ToolCardNine.
	 */
	public static final String NAME = "Cork-backed Straightedge";

	/**
	 * Info of the ToolCardNine.
	 */
	public static final String INFO = "After drafting, place the die in a spot that is not adjacent to another die. You must obey all other placement restrictions";

	/**
	 * Constructor of the class. No parameters.
	 */
	public ToolCardNine() {
		super(NAME, INFO);
	}

	/**
	 * @inheritDoc
	 * If the card is active, it returns true.
	 */
	@Override
	public boolean placeDraftedDieNoAdjacent() {
		return isActive();
	}

	@Override
	public ToolCard cloneToolCard() {
		//TODO - implementare clone
		return null;
	}
}