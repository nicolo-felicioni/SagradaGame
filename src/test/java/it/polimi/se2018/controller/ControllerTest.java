package it.polimi.se2018.controller;

import it.polimi.se2018.event.game.StartGameEvent;
import it.polimi.se2018.exceptions.NotValidIdException;
import it.polimi.se2018.exceptions.TooManyPlayersException;
import it.polimi.se2018.model.Model;
import it.polimi.se2018.model.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ControllerTest {

    private Controller controller;
    private Model model;
    private Player player1, player2, player3;

    @Before
    public void setUp(){
        player1 = new Player("player1");
        player2 = new Player("player2");
        player3 = new Player("player3");
        model = new Model();
        controller = new Controller(model);

    }

    @Test
    public void handleStartGameEvent() {
        List<String> ids = new ArrayList<>();
        ids.add(player1.getId());
        ids.add(player2.getId());
        ids.add(player3.getId());
        new StartGameEvent(ids);
    }

    @Test
    public void handle1() {
    }

    @Test
    public void handle2() {
    }

    @Test
    public void handle3() {
    }

    @Test
    public void handle4() {
    }

    @Test
    public void handle5() {
    }

    @Test
    public void handle6() {
    }

    @Test
    public void handle7() {
    }

    @Test
    public void handle8() {
    }

    @Test
    public void handle9() {
    }

    @Test
    public void handle10() {
    }

    @Test
    public void handle11() {
    }

    @Test
    public void handle12() {
    }

    @Test
    public void handle13() {
    }

    @Test
    public void handle14() {
    }

    @Test
    public void handle15() {
    }

    @Test
    public void handle16() {
    }

    @Test
    public void handle17() {
    }

    @Test
    public void handle18() {
    }

    @Test
    public void handle19() {
    }

    @Test
    public void addObserver() {
    }

    @Test
    public void removeObserver() {
    }

    @Test
    public void notifyObservers() {
    }

}