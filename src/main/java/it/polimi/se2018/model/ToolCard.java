package it.polimi.se2018.model;

import it.polimi.se2018.exceptions.ToolCardStateException;

/**
 * @author Davide Yi Xian Hu
 */
public abstract class ToolCard implements Card, Cloneable, ToolCardEffect{

	/**
	 * Card name.
	 */
	private final String name;

	/**
	 * Card information.
	 */
	private final String info;

	/**
	 * Flag that indicates if the card has already been used.
	 */
	private boolean used;

	/**
	 * Flag that indicates if the card is active.
	 */
	private boolean active;

	/**
	 * Amount of favor tokens spent on this card.
	 */
	private int favorTokensSpent;

	/**
	 * Cost of the card if it has never been used.
	 */
	public static final int NEVER_USED_TOKEN_COST = 1;

	/**
	 * Cost of the card if it has been used.
	 */
	public static final int USED_TOKEN_COST = 2;

	/**
	 * Constructor with name and info.
	 *
	 * @param name the name of the card.
	 * @param info the info of the card.
	 */
	public ToolCard(String name, String info) {
		this.name = name;
		this.info = info;
		this.used = false;
		this.active = false;
		this.favorTokensSpent = 0;
	}

	/**
	 * Constructor with name, info, used, active and tokens spent.
	 *
	 * @param name the name of the card.
	 * @param info the info of the card.
	 * @param used if the card has been used.
	 * @param active if the card is active.
	 * @param favorTokensSpent the amount of favor tokens spent on this card.
	 */
	public ToolCard(String name, String info, boolean used, boolean active, int favorTokensSpent) {
		this.name = name;
		this.info = info;
		this.used = used;
		this.active = active;
		this.favorTokensSpent = favorTokensSpent;
	}

	/**
	 * Getter of the name attribute.
	 *
	 * @return a string that contains the name of the card.
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * Getter of the info attribute.
	 *
	 * @return a string that contains the info of the card.
	 */
	@Override
	public String getInfo() {
		return info;
	}

	/**
	 * Getter of used attribute.
	 *
	 * @return true if the card has been used, false otherwise.
	 */
	public boolean isUsed() {
		return used;
	}

	/**
	 * Getter of active attribute.
	 *
	 * @return true if the card is active, false otherwise.
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * Getter of favorTokensSpent attribute.
	 *
	 * @return the amount of favor tokens spent on this card.
	 */
	public int getFavorTokensSpent() {
		return favorTokensSpent;
	}

	/**
	 * Activate this card.
	 */
	public void activate() throws ToolCardStateException {
		if(isActive()) {
			throw new ToolCardStateException(this.getName() + " is already active.");
		}else{
			this.favorTokensSpent += cost();
			this.used = true;
			this.active = true;
		}
	}

	/**
	 * End activation of this card.
	 */
	public void endActivation() throws ToolCardStateException {
		if(!isActive()) {
			throw new ToolCardStateException(this.getName() + " is not active or cannot end the activation of the card.");
		}else {
			this.active = false;
		}
	}

	/**
	 * Get the cost of the card.
	 */
	public int cost() {
		if(isUsed()){
			return USED_TOKEN_COST;
		}else {
			return NEVER_USED_TOKEN_COST;
		}
	}

	/**
	 * @inheritDoc
	 * The default behaviour is to end the activation of the card.
	 */
	@Override
	public void consumeEffect() throws ToolCardStateException{
		this.endActivation();
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
	 */
	@Override
	public boolean moveDieIgnoreValue() { return false; }

	/**
	 * @inheritDoc
	 */
	@Override
	public boolean moveADie() {
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
	 */
	@Override
	public boolean returnDieAndGetNewFromDiceBag() {
		return false;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public boolean chooseNewDieValue() {
		return false;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public boolean moveTwoDiceMatchColorOnRoundTrack() {
		return false;
	}

	/**
	 * Clone this tool card.
	 * @return a clone of this tool card.
	 */
	public abstract ToolCard cloneToolCard();

}