package it.polimi.se2018.model;

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
	 * Constructor with name, info and used attributes.
	 *
	 * @param name the name of the card.
	 * @param info the info of the card.
	 */
	public ToolCard(String name, String info) {
		this.name = name;
		this.info = info;
		this.used = false;
		this.favorTokensSpent = 0;
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
		return used;
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
	public void activate() {
		this.used = true;
		this.favorTokensSpent =+ cost();
		this.active = true;
	}

	/**
	 * End activation of this card.
	 */
	public void endActivion() {
		this.active = false;
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
	 * Consume the effect of the card. It can enable or disable some effect or set the card from active to inactive.
	 *
	 */
	abstract void effectUsed();


}