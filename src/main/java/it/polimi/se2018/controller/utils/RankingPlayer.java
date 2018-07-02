package it.polimi.se2018.controller.utils;

import java.io.Serializable;
import java.util.Comparator;

public class RankingPlayer implements Serializable{
    private String playerId;
    private int points;
    private int pointsFromPrivateObjective;
    private int favorTokensRemaining;

    public void setPoints(int points) {
        this.points = points;
    }

    public void setPointsFromPrivateObjective(int pointsFromPrivateObjective) {
        this.pointsFromPrivateObjective = pointsFromPrivateObjective;
    }

    public void setFavorTokensRemaining(int favorTokensRemaining) {
        this.favorTokensRemaining = favorTokensRemaining;
    }

    public void setReverseOrderFinalRound(int reverseOrderFinalRound) {
        this.reverseOrderFinalRound = reverseOrderFinalRound;
    }

    private int reverseOrderFinalRound;


    public RankingPlayer(String playerId){
        this.playerId = playerId;
    }

    public RankingPlayer(String playerId, int points, int pointsFromPrivateObjective, int favorTokensRemaining, int reverseOrderFinalRound){
        this.playerId = playerId;
        this.points = points;
        this.pointsFromPrivateObjective = pointsFromPrivateObjective;
        this.favorTokensRemaining = favorTokensRemaining;
        this.reverseOrderFinalRound = reverseOrderFinalRound;
    }

    public String getPlayerId(){
        return playerId;
    }

    public int getPoints() {
        return points;
    }

    public int getPointsFromPrivateObjective() {
        return pointsFromPrivateObjective;
    }

    public int getFavorTokensRemaining() {
        return favorTokensRemaining;
    }

    public int getReverseOrderFinalRound() {
        return reverseOrderFinalRound;
    }

    public static class RankingPlayerComparator implements Comparator<RankingPlayer>{

        @Override
        public int compare(RankingPlayer one, RankingPlayer two) {

            if(one.points!=two.points)
                return Integer.compare(one.points , two.points);
            else if(one.pointsFromPrivateObjective!=two.pointsFromPrivateObjective)
                return Integer.compare(one.pointsFromPrivateObjective, two.pointsFromPrivateObjective);
            else if(one.favorTokensRemaining != two.favorTokensRemaining)
                return Integer.compare(one.favorTokensRemaining, two.favorTokensRemaining);
            else
                return Integer.compare(one.reverseOrderFinalRound, two.reverseOrderFinalRound);

        }
    }
}
