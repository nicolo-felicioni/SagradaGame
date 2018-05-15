package it.polimi.se2018.model;

/**
 * @author Davide Yi Xian Hu
 */
public class ToolCardTen extends ToolCard {

	/**
	 * Name of the ToolCardTen.
	 */
	public static final String NAME = "Grinding Stone";

	/**
	 * Info of the ToolCardTen.
	 */
	public static final String INFO = "After drafting, flip the die to its opposite side. 6 flips to 1, 5 to 2, 4 to 3, etc";

	/**
	 * Constructor of the class. No parameters.
	 */
	public ToolCardTen() {
		super(NAME, INFO);
	}

	/**
	 * @inheritDoc
	 * If the card is active, it returns true.
	 */
	@Override
	public boolean flipDraftedDie() {
		return isActive();
	}

}