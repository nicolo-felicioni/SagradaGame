package it.polimi.se2018.model;

import it.polimi.se2018.controller.factory.WindowPatternFactory;
import it.polimi.se2018.exceptions.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    Player player;

    @Before
    public void setUp() throws Exception {
        player = new Player("player");
    }

    @Test
    public void setPatterns1() {
        try {
            player.setPatterns(new WindowPatternFactory().getWindowPattern(4));
        } catch (NotValidPatternVectorException e) {
            fail();
        }
    }

    @Test
    public void setPatterns2() {
        try {
            player.setPatterns(new WindowPatternFactory().getWindowPattern(3));
            fail();
        } catch (NotValidPatternVectorException e) {

        }
    }

    @Test
    public void choosePattern1() {
        WindowPattern [] patterns = new WindowPatternFactory().getWindowPattern(4);
        try {
            player.setPatterns(patterns);
        } catch (NotValidPatternVectorException e) {
            fail();
        }
        try {
            player.choosePattern(patterns[0]);
        } catch (NotValidPatterException e) {
            fail();
        }
    }

    @Test
    public void choosePattern2() {
        WindowPatternFactory factory = new WindowPatternFactory();
        WindowPattern [] patterns = factory.getWindowPattern(4);
        try {
            player.setPatterns(patterns);
        } catch (NotValidPatternVectorException e) {
            fail();
        }
        try {
            player.choosePattern(factory.getWindowPattern());
            fail();
        } catch (NotValidPatterException e) {

        }
    }

    @Test
    public void placeDie() {
        player.changePlayerStateTo(new NotYourTurnState());
        try {
            player.placeDie(0, 0, new Die(DieColor.BLUE, DieValue.ONE));
            fail();
        } catch (PlacementException e) {
            fail();
        } catch (IllegalMoveTurnException e) {

        } catch (GameMoveException e) {
            fail();
        } catch (NotValidPointException e) {
            fail();
        }
    }

    @Test
    public void placeDie1() {
        player.changePlayerStateTo(new NotYourTurnState());
        try {
            player.placeDie(new Point(0, 0), new Die(DieColor.BLUE, DieValue.ONE));
            fail();
        } catch (PlacementException e) {
            fail();
        } catch (IllegalMoveTurnException e) {

        } catch (NotValidPointException e) {
            fail();
        }
    }

    @Test
    public void spendToken2() {
        try {
            player.spendToken(1);
            fail();
        } catch (NotEnoughTokenException e) {
        }
    }

    @Test
    public void spendToken1() {
        WindowPattern [] patterns = new WindowPatternFactory().getWindowPattern(4);
        try {
            player.setPatterns(patterns);
        } catch (NotValidPatternVectorException e) {
            fail();
        }
        try {
            player.choosePattern(patterns[0]);
        } catch (NotValidPatterException e) {
            fail();
        }
        try {
            player.spendToken(1);
        } catch (NotEnoughTokenException e) {
            fail();
        }
    }

    @Test
    public void useTool1() {
        player.changePlayerStateTo(new YourTurnState());
        try {
            player.useTool();
            player.useTool();
            fail();
        } catch (GameMoveException e) {
        }
    }

    @Test
    public void useTool2() {
        player.changePlayerStateTo(new NotYourTurnState());
        try {
            player.useTool();
            fail();
        } catch (GameMoveException e) {
        }
    }

    @Test
    public void equalsPlayer() {
        Player player2 = new Player("pippo");
        Player player3 = new Player("player");
        assertFalse(player2.equalsPlayer(player));
        assertTrue(player.equalsPlayer(player));
        assertTrue(player3.equalsPlayer(player));
    }

    @Test
    public void hasChosenWindowPattern() {
        assertFalse(player.hasChosenWindowPattern());
        WindowPattern [] patterns = new WindowPatternFactory().getWindowPattern(4);
        try {
            player.setPatterns(patterns);
        } catch (NotValidPatternVectorException e) {
            fail();
        }
        try {
            player.choosePattern(patterns[0]);
        } catch (NotValidPatterException e) {
            fail();
        }
        assertTrue(player.hasChosenWindowPattern());
    }

    @Test
    public void clonePlayer() {
        player.clonePlayer().equalsPlayer(player);
    }

}