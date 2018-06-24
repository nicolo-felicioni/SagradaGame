package it.polimi.se2018.event.game;

import it.polimi.se2018.model.CardPosition;
import it.polimi.se2018.observer.game.GameEventObserver;

/**
 * @author Davide Yi Xian Hu
 */
public class UseToolCardGameEvent extends AbstractPlayerGameEvent {

	/**
	 * The position of the tool card.
	 */
	private CardPosition positionOfToolCard;

	/**
	 * Constructor.
	 * @param position the position of the tool card.
	 * @param playerId the player identifier.
	 */
	public UseToolCardGameEvent(CardPosition position, String playerId) {
		super(playerId);
		this.positionOfToolCard = position;
	}


	/**
	 * Getter of the position of the tool card.
	 * @return the position of the tool card.
	 */
	public CardPosition getPositionOfToolCard() {
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
