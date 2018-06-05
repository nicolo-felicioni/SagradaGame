package it.polimi.se2018.event;

/**
 * @author davide yi xian hu
 */
public class UseToolCardEvent extends AbstractPlayerEvent {

	/**
	 * The position of the tool card.
	 */
	private int positionOfToolCard;

	/**
	 * Constructor.
	 * @param position the position of the tool card.
	 * @param playerId the player identifier.
	 */
	public UseToolCardEvent(int position, String playerId) {
		super(playerId);
		this.positionOfToolCard = position;
	}


	/**
	 * Getter of the position of the tool card.
	 * @return the position of the tool card.
	 */
	public int getPositionOfToolCard() {
		return positionOfToolCard;
	}
}
