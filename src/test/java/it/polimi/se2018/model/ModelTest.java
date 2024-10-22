package it.polimi.se2018.model;

import it.polimi.se2018.controller.utils.MyLog;
import it.polimi.se2018.exceptions.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.logging.Level;

import static org.junit.Assert.*;

public class ModelTest {

    Model model;

    WindowPattern createBlankWindow(){
        int randomDiff;
        Space[][] blankSpaces;

        blankSpaces = new Space[WindowPattern.SPACES_HEIGHT][WindowPattern.SPACES_LENGTH];


        randomDiff = WindowPattern.MIN_DIFFICULTY + (int) (Math.random() * (WindowPattern.MAX_DIFFICULTY - WindowPattern.MIN_DIFFICULTY));

        for(int k = 0; k<WindowPattern.SPACES_HEIGHT; k++)
            for(int j=0; j<WindowPattern.SPACES_LENGTH; j++)
                blankSpaces[k][j] = new BlankSpace();

        try {
            return new WindowPattern(blankSpaces, randomDiff, "pattern");
        } catch (WindowPatternDimensionException e) {
            MyLog.getMyLog().log(Level.WARNING, e.getMessage());
        } catch (UnboundDifficultyValueException e) {
            MyLog.getMyLog().log(Level.WARNING, e.getMessage());
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
        WindowPattern window0 = createBlankWindow();
        WindowPattern window1 = createBlankWindow();
        WindowPattern window2 = createBlankWindow();
        WindowPattern window3 = createBlankWindow();
        WindowPattern[] patterns = new WindowPattern[4];
        patterns[0] = window0;
        patterns[1] = window1;
        patterns[2] = window2;
        patterns[3] = window3;

        Player nico = new Player("Nico");
        try {
            nico.setPatterns(patterns);
        } catch (NotValidPatternVectorException e) {
            fail();
        }


        try {
            model.addPlayer(nico);
        } catch (TooManyPlayersException e) {
            fail();
        } catch (NotValidIdException e) {
            fail();
        }

        try {
            assertTrue(model.getPlayer("Nico").getId().equals("Nico"));
            assertTrue(model.getPlayer(nico.getId()).getId().equals("Nico"));
        } catch (NotValidIdException e) {
            fail();
        }

        assertTrue(model.getPlayers().stream().findAny()
                .filter(player -> player.equalsPlayer(nico)).isPresent());


    }

    @Test
    public void addPlayerException() {
        Player nico = new Player("Nico");

        try {
            model.addPlayer(nico);
        } catch (TooManyPlayersException e) {
            fail();
        } catch (NotValidIdException e) {
            fail();
        }

        try {
            model.addPlayer(nico);
            fail();
        } catch (TooManyPlayersException e) {
            fail();
        } catch (NotValidIdException e) {
            //ok
        }


    }

    @Test
    public void addPlayerException1() {
        Player one = new Player("one");
        Player two = new Player("two");
        Player three = new Player("three");
        Player four = new Player("four");
        Player five = new Player("five");


        try {
            model.addPlayer(one);
            model.addPlayer(two);
            model.addPlayer(three);
            model.addPlayer(four);
        } catch (TooManyPlayersException e) {
            fail();
        } catch (NotValidIdException e) {
            fail();
        }

        try {
            model.addPlayer(five);
            fail();
        } catch (TooManyPlayersException e) {
            //ok
        } catch (NotValidIdException e) {
            fail();
        }


    }

    @Test
    public void addTooManyPlayers(){
        try {
            model.addPlayer(new Player("1"));
            model.addPlayer(new Player("2"));
            model.addPlayer(new Player("3"));
            model.addPlayer(new Player("4"));
        } catch (TooManyPlayersException e) {
            fail();
        } catch (NotValidIdException e) {
            fail();
        }

        try {
            model.addPlayer(new Player("Player in excess"));
            fail();
        } catch (TooManyPlayersException e) {
            //ok
        } catch (NotValidIdException e) {
            fail();
        }
    }

    @Test
    public void addTwoPlayersWithSameId(){
        try {
            model.addPlayer(new Player("id"));
        } catch (TooManyPlayersException e) {
            fail();
        } catch (NotValidIdException e) {
            fail();
        }
        try {
            model.addPlayer(new Player("id"));
        } catch (TooManyPlayersException e) {
            fail();
        } catch (NotValidIdException e) {
            //ok
        }

    }

    @Test
    public void removePlayer() throws NotValidIdException, TooManyPlayersException {
        Player player = new Player("id");
        model.addPlayer(player);
        assertFalse(model.getPlayers().isEmpty());

        try {
            model.removePlayer(player);
        } catch (NotPresentPlayerException e) {
            fail();
        }

        assertTrue(model.getPlayers().isEmpty());


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
        DiceBag diceBag = model.getDiceBag();
        assertTrue(diceBag.size() == 90);
    }

    @Test
    public void getRoundTrack() {


    }


    @Test
    public void getPlayers() {

        assertTrue(model.getPlayers().isEmpty());

        try {
            model.addPlayer(new Player("Nico"));
            model.addPlayer(new Player("Davide"));
        } catch (TooManyPlayersException e) {
            MyLog.getMyLog().log(Level.WARNING, e.getMessage());
        } catch (NotValidIdException e) {
            MyLog.getMyLog().log(Level.WARNING, e.getMessage());
        }
        List<Player> players = model.getPlayers();

        assertTrue(players.stream().anyMatch(player -> player.getId().equals("Nico")));
        assertTrue(players.stream().anyMatch(player -> player.getId().equals("Davide")));
    }

    @Test
    public void getPublicObjectiveCards() {
    }

    @Test
    public void getToolCards() {
    }




    @Test
    public void getPlayersId() throws NotValidIdException, TooManyPlayersException {
        String nico = "Nico";
        String davide = "Davide";

        model.addPlayer(new Player(nico));
        model.addPlayer(new Player(davide));

        assertTrue(model.getPlayersId().stream().anyMatch(s -> s.equals(nico)));

        assertTrue(model.getPlayersId().stream().anyMatch(s -> s.equals(davide)));
    }

    @Test
    public void removeDieFromDraftPool() {

    }


}