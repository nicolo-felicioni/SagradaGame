package it.polimi.se2018.model;

/**
 * @author Davide Yi Xian Hu
 */
public interface ToolCardEffect {

	/**
	 * This effect permit the player to increase a drafted die by one.
	 *
	 * @return true if a player can increase a drafted die by one.
	 */
	boolean increaseDieValue();

	/**
	 * This effect permit the player to decrease a drafted die by one.
	 *
	 * @return true if a player can decrease a drafted die by one.
	 */
	boolean decreaseDieValue();

	/**
	 * This effect permit the player to move a placed die.
	 * The moved die can ignore color restrictions.
	 *
	 * @return true if a player can move a die to another space of the window pattern.
	 */
	boolean moveDieIgnoreColor();

	/**
	 * This effect permit the player to move a placed die.
	 * The moved die can ignore value restrictions.
	 *
	 * @return true if a player can move a die to another space of the window pattern.
	 */
	boolean moveDieIgnoreValue();

	/**
	 * This effect permit the player to move two times a placed die to another space of the window pattern.
	 *
	 * @return true if a player can move two die.
	 */
	boolean moveTwoDice();

	/**
	 * This effect permit the player to swap a die from the draft pool with a die from the round track.
	 *
	 * @return true if a player can swap the dice.
	 */
	boolean swapDraftDieWithRoundTrackDie();

	/**
	 * This effect permit the player to reroll the drafted die.
	 *
	 * @return true if a player can reroll a drafted die.
	 */
	boolean rerollDraftedDie();

	/**
	 * This effect permit the player to reroll all dice in the draft pool.
	 *
	 * @return true if a player can reroll all dice in the draft pool.
	 */
	boolean rerollAllDraftPoolDice();

	/**
	 * This effect permit the player to draft and place another die after the end of his own turn.
	 *
	 * @return true if a player can draft and place another die after the end of his own turn.
	 */
	boolean placeDieAfterFirstTurn();

	/**
	 * This effect permit the player to place a drafted die in a spot that is not adjacent to another die.
	 *
	 * @return true if a player can place a drafted die in a spot that is not adjacent to another die.
	 */
	boolean placeDraftedDieNoAdjacent();

	/**
	 * This effect permit the player to flip a drafted die.
	 *
	 * @return true if a player can flip a drafted die.
	 */
	boolean flipDraftedDie();

	/**
	 * This effect permit the player to swap a drafted die with a die in the dice bag.
	 *
	 * @return true if a player can swap the dice.
	 */
	boolean returnDieAndGetNewFromDiceBag();

	/**
	 * This effect permit the player to choose the value of the die get from the dice bag.
	 *
	 * @return true if a player can choose the value of the die.
	 */
	boolean chooseNewDieValue();

	/**
	 * This effect permit the player to move two dice that match the color of a die of the round track.
	 *
	 * @return true if a player can move two dice.
	 */
	boolean moveTwoDiceMatchColorOnRoundTrack();

}
