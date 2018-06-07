package it.polimi.se2018.event;

import it.polimi.se2018.observer.GameEventObserver;

/**
 * @author davide yi xian hu
 */
public class UseToolCardGameEvent extends AbstractPlayerGameEvent {

	/**
	 * The position of the tool card.
	 */
	private int positionOfToolCard;

	/**
	 * Constructor.
	 * @param position the position of the tool card.
	 * @param playerId the player identifier.
	 */
	public UseToolCardGameEvent(int position, String playerId) {
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

	/**
	 * Accept an observer. Visitor pattern.
	 *
	 * @param observer the observer to be called.
	 */
	@Override
	public void accept(GameEventObserver observer) {
		observer.handle(this);
	}

}
