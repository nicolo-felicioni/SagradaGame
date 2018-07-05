package it.polimi.se2018.controller.utils;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SchedulerTest {

    private Scheduler scheduler2;
    private Scheduler scheduler3;

    @Before
    public void setUp() throws Exception {
        List<String> players2 = new ArrayList<>();
        players2.add("first");
        players2.add("second");
        scheduler2 = new Scheduler(players2);
        List<String> players3 = new ArrayList<>();
        players3.add("first");
        players3.add("second");
        players3.add("third");
        scheduler3 = new Scheduler(players3);
    }

    @Test
    public void hasNext() {
        for(int i = 0; i < 40; i++) {
            assertTrue(scheduler2.hasNext());
            scheduler2.next();
        }
        assertFalse(scheduler2.hasNext());


        for(int i = 0; i < 60; i++) {
            assertTrue(scheduler3.hasNext());
            scheduler3.next();
        }
        assertFalse(scheduler3.hasNext());
    }

    @Test
    public void next() {
        String first = scheduler2.next();
        String second = scheduler2.next();
        assertTrue(second.equals(scheduler2.next()));
        assertTrue(first.equals(scheduler2.next()));
        assertTrue(second.equals(scheduler2.next()));
        assertTrue(first.equals(scheduler2.next()));
        assertTrue(first.equals(scheduler2.next()));
        assertTrue(second.equals(scheduler2.next()));


        //Test scheduler 3 player ids.
        //First round
        first = scheduler3.next();
        second = scheduler3.next();
        String third = scheduler3.next();
        assertTrue(third.equals(scheduler3.next()));
        assertTrue(second.equals(scheduler3.next()));
        assertTrue(first.equals(scheduler3.next()));

        //Second round
        assertTrue(second.equals(scheduler3.next()));
        assertTrue(third.equals(scheduler3.next()));
        assertTrue(first.equals(scheduler3.next()));
        assertTrue(first.equals(scheduler3.next()));
        assertTrue(third.equals(scheduler3.next()));
        assertTrue(second.equals(scheduler3.next()));
    }

    @Test
    public void getTurnId() {
        List<String> turnIds = new ArrayList<>();
        for(int i = 0; i < 40; i++) {
            String id = scheduler2.getTurnId();
            assertFalse(turnIds.contains(id));
            turnIds.add(id);
            scheduler2.next();
        }



        turnIds = new ArrayList<>();
        for(int i = 0; i < 60; i++) {
            String id = scheduler3.getTurnId();
            assertFalse(turnIds.contains(id));
            turnIds.add(id);
            scheduler3.next();
        }
    }

    @Test
    public void removeFirstOccurenceOf() {
        //First round
        String first = scheduler2.next();
        String second = scheduler2.next();
        scheduler2.removeFirstOccurenceOf(first);
        assertTrue(second.equals(scheduler2.next()));
        //Second round
        assertTrue(second.equals(scheduler2.next()));
        assertTrue(first.equals(scheduler2.next()));



        //First round
        first = scheduler3.next();
        second = scheduler3.next();
        String third = scheduler3.next();
        scheduler3.removeFirstOccurenceOf(first);
        assertTrue(third.equals(scheduler3.next()));
        assertTrue(second.equals(scheduler3.next()));
        //Second round
        assertTrue(second.equals(scheduler3.next()));
        assertTrue(third.equals(scheduler3.next()));
    }

    @Test
    public void isFirstTurnOfRound() {
        scheduler2.next();
        assertTrue(scheduler2.isFirstTurnOfRound());
        scheduler2.next();
        assertFalse(scheduler2.isFirstTurnOfRound());


        scheduler3.next();
        assertTrue(scheduler3.isFirstTurnOfRound());
        scheduler3.next();
        assertFalse(scheduler3.isFirstTurnOfRound());
    }

    @Test
    public void isFirstHalfOfRound() {
        while(scheduler2.hasNext()) {
            scheduler2.next();
            assertTrue(scheduler2.isFirstHalfOfRound());
            scheduler2.next();
            assertTrue(scheduler2.isFirstHalfOfRound());

            scheduler2.next();
            assertFalse(scheduler2.isFirstHalfOfRound());
            scheduler2.next();
            assertFalse(scheduler2.isFirstHalfOfRound());
        }


        while(scheduler3.hasNext()) {
            scheduler3.next();
            assertTrue(scheduler3.isFirstHalfOfRound());
            scheduler3.next();
            assertTrue(scheduler3.isFirstHalfOfRound());
            scheduler3.next();
            assertTrue(scheduler3.isFirstHalfOfRound());

            scheduler3.next();
            assertFalse(scheduler3.isFirstHalfOfRound());
            scheduler3.next();
            assertFalse(scheduler3.isFirstHalfOfRound());
            scheduler3.next();
            assertFalse(scheduler3.isFirstHalfOfRound());
        }
    }

    @Test
    public void isFirstTurnOfPlayer() {
        scheduler2.next();
        assertTrue(scheduler2.isFirstTurnOfPlayer());
        scheduler2.next();
        assertTrue(scheduler2.isFirstTurnOfPlayer());
        while(scheduler2.hasNext()) {
            scheduler2.next();
            assertFalse(scheduler2.isFirstTurnOfPlayer());
        }

        scheduler3.next();
        assertTrue(scheduler3.isFirstTurnOfPlayer());
        scheduler3.next();
        assertTrue(scheduler3.isFirstTurnOfPlayer());
        scheduler3.next();
        assertTrue(scheduler3.isFirstTurnOfPlayer());
        while(scheduler3.hasNext()) {
            scheduler3.next();
            assertFalse(scheduler3.isFirstTurnOfPlayer());
        }
    }

    @Test
    public void getCurrentPlayerId() {
        while(scheduler2.hasNext()) {
            assertTrue(scheduler2.next().equals(scheduler2.getCurrentPlayerId()));
        }

        while(scheduler3.hasNext()) {
            assertTrue(scheduler3.next().equals(scheduler3.getCurrentPlayerId()));
        }
    }

    @Test
    public void isFirstTurnOfGame() {
        scheduler2.next();
        assertTrue(scheduler2.isFirstTurnOfGame());
        while(scheduler2.hasNext()) {
            scheduler2.next();
            assertFalse(scheduler2.isFirstTurnOfGame());
        }


        scheduler3.next();
        assertTrue(scheduler3.isFirstTurnOfGame());
        while(scheduler3.hasNext()) {
            scheduler3.next();
            assertFalse(scheduler3.isFirstTurnOfGame());
        }
    }
}