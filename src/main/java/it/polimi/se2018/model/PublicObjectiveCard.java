package it.polimi.se2018.model;

/**
 * @author Davide Yi Xian Hu
 */
public abstract class PublicObjectiveCard implements ObjectiveCard {

	/**
	 * Card name.
	 */
	private final String name;

	/**
	 * Card information.
	 */
	private final String info;

	/**
	 * Constructor.
	 *
	 * @param name the name of the card.
	 * @param info the info of the card.
	 */
	public PublicObjectiveCard(String name, String info) {
		this.name = name;
		this.info = info;
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
	 * Gettere of the info attribute.
	 *
	 * @return a string that contains the info of the card.
	 */
	@Override
	public String getInfo() {
		return info;
	}

}