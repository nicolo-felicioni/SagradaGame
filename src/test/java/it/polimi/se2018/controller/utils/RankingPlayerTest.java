package it.polimi.se2018.controller.utils;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class RankingPlayerTest {

    public List<RankingPlayer> players;

    @Before
    public void setUp(){
        players = new ArrayList<>();

        RankingPlayer nico = new RankingPlayer("nico");
        nico.setPoints(31);
        nico.setPointsFromPrivateObjective(10);
        nico.setFavorTokensRemaining(4);
        RankingPlayer davide = new RankingPlayer("davide");
        davide.setPoints(30);
        davide.setPointsFromPrivateObjective(10);
        davide.setFavorTokensRemaining(2);
        RankingPlayer gao = new RankingPlayer("gao");
        gao.setPoints(30);
        gao.setPointsFromPrivateObjective(10);
        gao.setFavorTokensRemaining(2);

        players.add(gao);
        players.add(nico);
        players.add(davide);

    }

    @Test
    public void comparator(){
        RankingPlayer.RankingPlayerComparator comparator = new RankingPlayer.RankingPlayerComparator();

        Collections.sort(players, comparator.reversed());

        players.forEach(player -> {
            System.out.println(player.getPlayerId());
            System.out.println(player.getPoints());
        });

        assertTrue(players.get(0).getPlayerId().equals("nico"));
    }

    @Test
    public void getPlayerId() {
    }

    @Test
    public void getPoints() {
    }

    @Test
    public void getPointsFromPrivateObjective() {
    }

    @Test
    public void getFavorTokensRemaining() {
    }

    @Test
    public void getReverseOrderFinalRound() {
    }
}