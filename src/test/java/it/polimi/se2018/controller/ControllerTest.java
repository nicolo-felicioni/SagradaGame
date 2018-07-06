package it.polimi.se2018.controller;

import it.polimi.se2018.controller.utils.RankingPlayer;
import it.polimi.se2018.event.game.DraftAndPlaceGameEvent;
import it.polimi.se2018.event.game.EndTurnGameEvent;
import it.polimi.se2018.event.game.StartGameEvent;
import it.polimi.se2018.event.game.WindowPatternChosenGameEvent;
import it.polimi.se2018.exceptions.NotPresentPlayerException;
import it.polimi.se2018.exceptions.NotValidIdException;
import it.polimi.se2018.exceptions.NotValidPatternVectorException;
import it.polimi.se2018.exceptions.TooManyPlayersException;
import it.polimi.se2018.model.*;
import it.polimi.se2018.view.View;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ControllerTest {

    private Controller controller;
    private Model model;
    private Player player1, player2, player3;
    private View view;

    private String message;
    private Map<String, WindowPattern[]> windowsToChoose;
    private Model viewModel;

    @Before
    public void setUp(){
        player1 = new Player("player1");
        player2 = new Player("player2");
        player3 = new Player("player3");
        model = new Model();
        controller = new Controller(model);
        viewModel = new Model();
        windowsToChoose = new HashMap<>();
        windowsToChoose.put(player1.getId(), new WindowPattern[4]);
        windowsToChoose.put(player2.getId(), new WindowPattern[4]);
        windowsToChoose.put(player3.getId(), new WindowPattern[4]);
        view = new View() {
            @Override
            public synchronized void updateToolCard(ToolCard toolCard, int number) {
                viewModel.setToolCard(toolCard, CardPosition.fromInt(number));
            }
            @Override
            public synchronized void updateRoundTrack(RoundTrack roundTrack) {
                viewModel.setRoundTrack(roundTrack);
            }
            @Override
            public synchronized void updateDraftPool(DraftPool draftPool) {
                viewModel.setDraftPool(draftPool);
            }

            @Override
            public synchronized void updateStatePlayer(String playerId, PlayerState state) {
                try {
                    viewModel.changePlayerStateTo(playerId, state);
                } catch (NotValidIdException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public synchronized void updatePlayer(String playerId, int favorTokens, boolean connected) {
                Player player = new Player(playerId);
                player.setConnected(connected);
                player.setTokens(favorTokens);
                if(viewModel.getPlayersId().stream().anyMatch(id -> id.equals(playerId))) {
                    try {
                        player = viewModel.getPlayer(playerId);
                        player.setConnected(connected);
                        player.setTokens(favorTokens);
                        viewModel.setPlayer(player);
                    } catch (NotPresentPlayerException e) {
                        e.printStackTrace();
                    } catch (NotValidIdException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        viewModel.addPlayer(player);
                    } catch (TooManyPlayersException e) {
                        e.printStackTrace();
                    } catch (NotValidIdException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public synchronized void updatePrivateObjectiveCard(String playerId, PrivateObjectiveCard card) {
                try {
                    viewModel.setPrivateObjectiveCard(playerId, card);
                } catch (NotValidIdException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public synchronized void updateWindowPattern(String playerId, WindowPattern windowPattern, WindowPatternPosition position) {
                System.out.println(playerId + " " + position);
                if(position.equals(WindowPatternPosition.CHOSEN)) {
                    try {
                        Player player = viewModel.getPlayer(playerId);
                        player.setChosenPattern(windowPattern);
                        viewModel.setPlayer(player);
                    } catch (NotValidIdException e) {
                        e.printStackTrace();
                    } catch (NotPresentPlayerException e) {
                        e.printStackTrace();
                    }
                }else {
                    windowsToChoose.get(playerId)[position.toInt()] = windowPattern.cloneWindowPattern();
                    if(Arrays.stream(windowsToChoose.get(playerId)).allMatch(w -> w != null)) {
                        try {
                            viewModel.setWindowPatterns(playerId, windowsToChoose.get(playerId));
                        } catch (NotValidIdException | NotValidPatternVectorException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            @Override
            public synchronized void updatePublicObjectiveCard(PublicObjectiveCard card, CardPosition position) {
                viewModel.setPublicObjectiveCard(card, position);
            }
            @Override
            public synchronized void updateErrorMessage(String playerId, String message) {
                System.out.println(message);
                message = message;
            }
            @Override
            public synchronized void updateEndGame(List<RankingPlayer> rankingPlayers, List<String> disconnectedPlayerId) {

            }
            @Override
            public synchronized void handle(ViewUpdaterInterface updater) {
                System.out.println(updater.getClass().getSimpleName());
                updater.update(this);
            }
        };
        model.addObserver(view);

    }

    @Test
    public void handleStartGameEvent() throws Exception{
        assertTrue((windowsToChoose.get(player1.getId())[0] == null));
        assertTrue((windowsToChoose.get(player1.getId())[1] == null));
        assertTrue((windowsToChoose.get(player1.getId())[2] == null));
        assertTrue((windowsToChoose.get(player1.getId())[3] == null));
        assertTrue((windowsToChoose.get(player2.getId())[0] == null));
        assertTrue((windowsToChoose.get(player2.getId())[1] == null));
        assertTrue((windowsToChoose.get(player2.getId())[2] == null));
        assertTrue((windowsToChoose.get(player2.getId())[3] == null));
        assertTrue((windowsToChoose.get(player3.getId())[0] == null));
        assertTrue((windowsToChoose.get(player3.getId())[1] == null));
        assertTrue((windowsToChoose.get(player3.getId())[2] == null));
        assertTrue((windowsToChoose.get(player3.getId())[3] == null));
        List<String> ids = new ArrayList<>();
        ids.add(player1.getId());
        ids.add(player2.getId());
        ids.add(player3.getId());
        controller.handle(new StartGameEvent(ids));
        assertTrue(viewModel.getPlayers().stream().noneMatch(player -> player.getState().hasChosenWindowPattern()));
        assertTrue(viewModel.getPlayers().stream().allMatch(player -> player.getState() instanceof ChooseWindowPatternState));
        assertTrue((windowsToChoose.get(player1.getId())[0] != null));
        assertTrue((windowsToChoose.get(player1.getId())[1] != null));
        assertTrue((windowsToChoose.get(player1.getId())[2] != null));
        assertTrue((windowsToChoose.get(player1.getId())[3] != null));
        assertTrue((windowsToChoose.get(player2.getId())[0] != null));
        assertTrue((windowsToChoose.get(player2.getId())[1] != null));
        assertTrue((windowsToChoose.get(player2.getId())[2] != null));
        assertTrue((windowsToChoose.get(player2.getId())[3] != null));
        assertTrue((windowsToChoose.get(player3.getId())[0] != null));
        assertTrue((windowsToChoose.get(player3.getId())[1] != null));
        assertTrue((windowsToChoose.get(player3.getId())[2] != null));
        assertTrue((windowsToChoose.get(player3.getId())[3] != null));
        assertTrue(viewModel.getPlayer(player1.getId()).getPatterns().length == 4);
        assertTrue(viewModel.getPlayer(player2.getId()).getPatterns().length == 4);
        assertTrue(viewModel.getPlayer(player3.getId()).getPatterns().length == 4);
    }

    @Test
    public void handleChooseWindowPattern() throws Exception{
        List<String> ids = new ArrayList<>();
        ids.add(player1.getId());
        ids.add(player2.getId());
        ids.add(player3.getId());
        controller.handle(new StartGameEvent(ids));
        controller.handle(new WindowPatternChosenGameEvent(windowsToChoose.get(player1.getId())[0], player1.getId()));
        controller.handle(new WindowPatternChosenGameEvent(windowsToChoose.get(player2.getId())[0], player2.getId()));
        controller.handle(new WindowPatternChosenGameEvent(windowsToChoose.get(player3.getId())[0], player3.getId()));
        assertTrue(viewModel.getToolCards().length == 3);
        assertTrue(Arrays.stream(viewModel.getToolCards()).allMatch(t -> t != null));
        assertTrue(viewModel.getPublicObjectiveCards().length == 3);
        assertTrue(Arrays.stream(viewModel.getPublicObjectiveCards()).allMatch(t -> t != null));
        assertTrue(viewModel.getDraftPool().getAllDice().size() == 7);

    }

    @Test
    public void handle() throws Exception{
        List<String> ids = new ArrayList<>();
        ids.add(player1.getId());
        ids.add(player2.getId());
        ids.add(player3.getId());
        controller.handle(new StartGameEvent(ids));
        controller.handle(new WindowPatternChosenGameEvent(windowsToChoose.get(player1.getId())[0], player1.getId()));
        controller.handle(new WindowPatternChosenGameEvent(windowsToChoose.get(player2.getId())[0], player2.getId()));
        controller.handle(new WindowPatternChosenGameEvent(windowsToChoose.get(player3.getId())[0], player3.getId()));
        Optional<Player> pl = viewModel.getPlayers().stream().filter(p -> p.getState().canPlaceDie()).findAny();
        Die die = null;
        Point p = null;
        if(pl.isPresent()) {
            WindowPattern w = pl.get().getPattern();
            while(true) {
                die = Die.getRandomDie();
                p =  new Point(new Random().nextInt(4), new Random().nextInt(5));
                if(w.isPlaceable(die, p)) {
                    System.out.println(die);
                    System.out.println(p);
                    System.out.println(w.getSpace(p).getColorRestriction() + " " + w.getSpace(p).getColorRestriction() + " " + w.getSpace(p).getClass().getSimpleName());

                    controller.handle(new DraftAndPlaceGameEvent(die, p, pl.get().getId()));
                    break;
                }

            }
        }
        for(Player player : viewModel.getPlayers()) {
            if(player.getState().canEndTurn()) {
                assertTrue(player.getPattern().getSpace(p).hasDie());
                assertTrue(player.getPattern().getSpace(p).getDie().equalsDie(die));
            }
        }
    }

    @Test
    public void handleEndTurn() throws Exception{
        List<String> ids = new ArrayList<>();
        ids.add(player1.getId());
        ids.add(player2.getId());
        ids.add(player3.getId());
        controller.handle(new StartGameEvent(ids));
        controller.handle(new WindowPatternChosenGameEvent(windowsToChoose.get(player1.getId())[0], player1.getId()));
        controller.handle(new WindowPatternChosenGameEvent(windowsToChoose.get(player2.getId())[0], player2.getId()));
        controller.handle(new WindowPatternChosenGameEvent(windowsToChoose.get(player3.getId())[0], player3.getId()));
        Optional<Player> pl = viewModel.getPlayers().stream().filter(p -> p.getState().canPlaceDie()).findAny();
        Die die = null;
        Point p = null;
        if(pl.isPresent()) {
            WindowPattern w = pl.get().getPattern();
            while(true) {
                die = Die.getRandomDie();
                p =  new Point(new Random().nextInt(4), new Random().nextInt(5));
                if(w.isPlaceable(die, p)) {
                    System.out.println(die);
                    System.out.println(p);
                    System.out.println(w.getSpace(p).getColorRestriction() + " " + w.getSpace(p).getColorRestriction() + " " + w.getSpace(p).getClass().getSimpleName());
                    controller.handle(new DraftAndPlaceGameEvent(die, p, pl.get().getId()));
                    controller.handle(new EndTurnGameEvent(pl.get().getId()));
                    break;
                }

            }
        }
        for(Player player : viewModel.getPlayers()) {
            if(player.getState().canPlaceDie()) {
                assertFalse(player.getState().canEndTurn());
            }
        }
    }


}