package it.polimi.se2018.controller.utils;

import it.polimi.se2018.model.Model;

import java.util.ArrayList;
import java.util.List;

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
        return this.queue.remove(0);
    }

    /**
     * Increase the turn by one. If it is the last turn in a round, increase the round by one.
     * If there's no turn to be played. Do nothing.
     */
    private void nextTurn() {
        if(this.hasNext()) {
            if (this.currentTurn == (this.playerIds.size() * 2) - 1) {
                this.currentTurn = 0;
                this.currentRound++;
            } else {
                this.currentTurn++;
            }
        }
    }

    /**
     * Return true if it is the first turn of a player in a round.
     * @return true if it is the first turn of a player in a round.
     */
    public boolean isFirstTurnOfRound() {
        return currentTurn < this.playerIds.size();
    }

    /**
     * Return the player identifier of the playing player.
     * @return the player identifier of the playing player.
     */
    public String getCurrentPlayerId() {
        return this.currentPlayerID;
    }

}
