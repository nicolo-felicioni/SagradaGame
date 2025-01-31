package it.polimi.se2018.controller.utils;

import it.polimi.se2018.model.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Davide Yi Xian Hu
 */
public class Scheduler {

    /**
     * Current turn.
     */
    private int currentTurn;

    /**
     * Current round.
     */
    private int currentRound;

    /**
     * List of player identifiers.
     */
    private List<String> playerIds;

    /**
     * List of player identifiers.
     */
    private List<String> queue;

    /**
     * Current player identifier.
     */
    private String currentPlayerID;
    /**
     * The round off set.
     */
    private int roundOffSet;

    /**
     * Constructor.
     *
     * @param playerIds the player identifiers.
     */
    public Scheduler(List<String> playerIds) {
        this.playerIds = new ArrayList<>();
        this.playerIds.addAll(playerIds);
        this.queue = new ArrayList<>();
        this.initQueue();
    }

    /**
     * Initialize the player queue.
     */
    private void initQueue() {
        List<String> temp = new ArrayList<>();
        temp.addAll(playerIds);
        for(int i = 0; i < Model.MAX_ROUND; i++) {
            this.queue.addAll(temp);
            this.queue.addAll(reverseOrder(temp));
            temp.add(temp.remove(0));
        }
        currentRound = 0;
        currentTurn = 0;
    }

    /**
     * Return a reverse ordered list.
     * @param list the input list.
     * @return the reverse ordered list.
     */
    private List reverseOrder(List list) {
        List returnedList = new ArrayList();
        for(int i = 0; i < list.size(); i++) {
            returnedList.add(i, list.get(list.size() - i - 1));
        }
        return returnedList;
    }

    /**
     * If there's another player turn after the current one.
     * @return true if there's another player turn after the current one, false otherwise.
     */
    public boolean hasNext() {
        return !this.queue.isEmpty();
    }

    /**
     * Return the next player identifier that have to play.
     * Remove the player from the queue.
     *
     * @return the next player identifier that have to play.
     */
    public String next() {
        this.nextTurn();
        this.currentPlayerID = this.queue.remove(0);
        return currentPlayerID;
    }

    /**
     * Increase the turn by one. If it is the last turn in a round, increase the round by one.
     * If there's no turn to be played. Do nothing.
     */
    private void nextTurn() {
        if(this.hasNext()) {
            if (this.currentTurn == (this.playerIds.size() * 2) -roundOffSet) {
                this.currentTurn = 1;
                this.currentRound++;
                this.roundOffSet=0;
            } else {
                this.currentTurn++;
            }
        }
    }

    /**
     * Return the unique identifier of the turn and the round.
     * @return the unique identifier of the turn and the round.
     */
    public String getTurnId() {
        return Integer.toString(currentRound) + Integer.toString(currentTurn);
    }

    /**
     * Remove the first occurence of a player id in the queue.
     * @param id the player id.
     * @return true if a player id has been removed.
     */
    public boolean removeFirstOccurenceOf(String id) {
        this.roundOffSet++;
        return this.queue.remove(id);
    }

    /**
     * Return true if it is the first turn of the round.
     * @return true if it is the first turn of the round.
     */
    public boolean isFirstTurnOfRound() {
        return currentTurn == 1;
    }

    /**
     * Return true if it is the first half turns of the round.
     * @return true if it is the first half turns of the round.
     */
    public boolean isFirstHalfOfRound() {
        return currentTurn < playerIds.size() + 1;
    }

    /**
     * Return true if it is the first half turns of the round.
     * @return true if it is the first half turns of the round.
     */
    public boolean isFirstTurnOfPlayer() {
        return currentTurn <= playerIds.size() && currentRound == 0;
    }

    /**
     * Return the player identifier of the playing player.
     * @return the player identifier of the playing player.
     */
    public String getCurrentPlayerId() {
        return this.currentPlayerID;
    }

    /**
     * Return true if it is the first turn of the player of the first round.
     * @return true if it is the first turn of the player of the first round.
     */
    public boolean isFirstTurnOfGame() {
        return currentRound == 0 && isFirstTurnOfRound();
    }

}
