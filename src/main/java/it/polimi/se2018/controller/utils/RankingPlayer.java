package it.polimi.se2018.controller.utils;

import java.io.Serializable;
import java.util.Comparator;

/**
 * @author Nicolò Felicioni
 */

public class RankingPlayer implements Serializable{

    /**
     * the player id
     */
    private String playerId;

    /**
     * the total points of the player
     */
    private int points;

    /**
     * the points that the player earned from his private objective card.
     */
    private int pointsFromPrivateObjective;

    /**
     * the favor tokens remaining at the end of the game
     */
    private int favorTokensRemaining;


    /**
     * setter of the total points
     * @param points the points of the player
     */
    public void setPoints(int points) {
        this.points = points;
    }


    /**
     * setter of the points earned from private objective card
     * @param pointsFromPrivateObjective the points earned from private objective card
     */
    public void setPointsFromPrivateObjective(int pointsFromPrivateObjective) {
        this.pointsFromPrivateObjective = pointsFromPrivateObjective;
    }

    /**
     * setter of the favor tokens remaining at the end of the game
     * @param favorTokensRemaining the favor tokens remaining at the end of the game
     */
    public void setFavorTokensRemaining(int favorTokensRemaining) {
        this.favorTokensRemaining = favorTokensRemaining;
    }


    /**
     * constructor.
     * @param playerId the id of the player
     */
    public RankingPlayer(String playerId){
        this.playerId = playerId;
    }

    /**
     * consturctor.
     * @param playerId the id of the player
     * @param points the total points
     * @param pointsFromPrivateObjective the points earned from private objective card
     * @param favorTokensRemaining the favor tokens remaining
     */
    public RankingPlayer(String playerId, int points, int pointsFromPrivateObjective, int favorTokensRemaining){
        this.playerId = playerId;
        this.points = points;
        this.pointsFromPrivateObjective = pointsFromPrivateObjective;
        this.favorTokensRemaining = favorTokensRemaining;
    }

    /**
     * getter of the player id
     * @return the player id
     */
    public String getPlayerId(){
        return playerId;
    }

    /**
     * getter of the total points
     * @return the total points
     */
    public int getPoints() {
        return points;
    }

    /**
     * getter of the points earned from private objective card
     * @return the points earned from private objective card
     */
    public int getPointsFromPrivateObjective() {
        return pointsFromPrivateObjective;
    }


    /**
     * getter of the favor tokens remaining at the end of the game
     * @return the favor tokens remaining at the end of the game
     */
    public int getFavorTokensRemaining() {
        return favorTokensRemaining;
    }


    /**
     * the comparator of the RankingPlayer class.
     */
    public static class RankingPlayerComparator implements Comparator<RankingPlayer>{

        /**
         * compares two players according to the Sagrada's rules.
         * @param one the first player
         * @param two the second player
         */
        @Override
        public int compare(RankingPlayer one, RankingPlayer two) {

            if(one.points!=two.points)
                return Integer.compare(one.points , two.points);
            else if(one.pointsFromPrivateObjective!=two.pointsFromPrivateObjective)
                return Integer.compare(one.pointsFromPrivateObjective, two.pointsFromPrivateObjective);
            else if(one.favorTokensRemaining != two.favorTokensRemaining)
                return Integer.compare(one.favorTokensRemaining, two.favorTokensRemaining);
            else
                return 0;
        }
    }
}
