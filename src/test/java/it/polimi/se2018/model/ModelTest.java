package it.polimi.se2018.model;

import it.polimi.se2018.exceptions.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ModelTest {

    Model model;

    public WindowPattern createBlankWindow(){
        int randomDiff;
        Space[][] blankSpaces;

        blankSpaces = new Space[WindowPattern.SPACES_HEIGTH][WindowPattern.SPACES_LENGTH];


        randomDiff = WindowPattern.MIN_DIFFICULTY + (int) (Math.random() * (WindowPattern.MAX_DIFFICULTY - WindowPattern.MIN_DIFFICULTY));

        for(int k=0; k<WindowPattern.SPACES_HEIGTH; k++)
            for(int j=0; j<WindowPattern.SPACES_LENGTH; j++)
                blankSpaces[k][j] = new BlankSpace();

        try {
            return new WindowPattern(blankSpaces, randomDiff);
        } catch (WindowPatternDimensionException e) {
            e.printStackTrace();
        } catch (UnboundDifficultyValueException e) {
            e.printStackTrace();
        }

        //impossible
        return null;
    }

    @Before
    public void setUp(){
        model = new Model();
    }

    @Test
    public void addPlayer() {
    }

    @Test
    public void removePlayer() {
    }

    @Test
    public void getPlayer() throws NotValidPatternVectorException, NotValidPatterException, NotValidIdException {
        WindowPattern window0 = createBlankWindow();
        WindowPattern window1 = createBlankWindow();
        WindowPattern window2 = createBlankWindow();
        WindowPattern window3 = createBlankWindow();
        Player player = new Player("Nico");
        Die die = new Die(DieColor.RED, DieValue.ONE);



        WindowPattern[] patterns = new WindowPattern[4];

        patterns[0] = window0;
        patterns[1] = window1;
        patterns[2] = window2;
        patterns[3] = window3;

        player.setPatterns(patterns);

        player.choosePattern(window0);


        try {
            model.addPlayer(player);
        } catch (TooManyPlayersException e) {
        }

        assertTrue(model.getPlayer("Nico").getId().equals("Nico"));



    }

    @Test
    public void getDiceBag() {
    }

    @Test
    public void getRoundTrack() {
    }

    @Test
    public void getDraftPool() {
    }

    @Test
    public void getToolCard() {
    }

    @Test
    public void getPublicObjectiveCard() {
    }

    @Test
    public void getPlayers() {
    }

    @Test
    public void getPublicObjectiveCards() {
    }

    @Test
    public void getToolCards() {
    }

    @Test
    public void placeDie() throws NotValidPointException, NotValidPatternVectorException, NotValidPatterException, NotValidIdException {
        WindowPattern window0 = createBlankWindow();
        WindowPattern window1 = createBlankWindow();
        WindowPattern window2 = createBlankWindow();
        WindowPattern window3 = createBlankWindow();
        Player player = new Player("Nico");
        Die die = new Die(DieColor.RED, DieValue.ONE);



        WindowPattern[] patterns = new WindowPattern[4];

        patterns[0] = window0;
        patterns[1] = window1;
        patterns[2] = window2;
        patterns[3] = window3;

        player.setPatterns(patterns);

        player.choosePattern(window0);


        try {
            model.addPlayer(player);
        } catch (TooManyPlayersException e) {

        }

        try {
            model.placeDie(new Point (0, 0), die, "Nico");
        } catch (GameMoveException e) {
            System.out.println(e);
        } catch (NotValidIdException e) {
            e.printStackTrace();
        }

        assertTrue(model.getPlayer("Nico").getPattern().getSpace(0, 0).getDie().equalsDie(die));

    }

    @Test
    public void placeDie1() {
    }

    @Test
    public void getPlayerTokens() {
    }

    @Test
    public void removeDieFromDraftPool() {
    }

    @Test
    public void drawDiceFromDiceBag() {
    }
}