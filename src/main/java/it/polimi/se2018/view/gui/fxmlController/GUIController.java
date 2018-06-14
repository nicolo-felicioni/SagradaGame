package it.polimi.se2018.view.gui.fxmlController;

import it.polimi.se2018.controller.ViewUpdaterInterface;
import it.polimi.se2018.model.WindowPatternPosition;
import it.polimi.se2018.model.*;
import it.polimi.se2018.network.client.ClientInterface;
import it.polimi.se2018.view.AbstractView;
import it.polimi.se2018.view.gui.MainGUI;
import javafx.application.Application;
import javafx.fxml.FXML;

import java.util.List;

/**
 * Author Gao PeiQing
 */
public class GUIController extends AbstractView {

    private ClientInterface client;
    private List<Player> players;
    private Player player;
    private WindowPattern[] patterns;
    private RoundTrack roundTrack;
    private DraftPool draftPool;
    private PrivateObjectiveCard privateObjectiveCard;
    private ToolCard[] toolCards;
    private PublicObjectiveCard[] publicObjectiveCards;

    @FXML
    MainGUI mainGUI;

    public void start(String[] args){
        Application.launch(MainGUI.class,args);
    }
    @Override
    public void updateMoveDieFromDraftToWindow(Point p, Die draftedDie, String playerId) {

    }

    @Override
    public void updateToolCard(ToolCard toolCard, int number) {

    }

    @Override
    public void updateRoundTrack(RoundTrack roundTrack) {

    }

    @Override
    public void updateDraftPool(DraftPool draftPool) {

    }

    @Override
    public void updateStatePlayer(String playerId, PlayerState state) {

    }

    @Override
    public void updatePlayer(String playerId) {

    }

    @Override
    public void updatePrivateObjectiveCard(String playerId, PrivateObjectiveCard card) {

    }

    @Override
    public void updateWindowPattern(String playerId, WindowPattern windowPattern, WindowPatternPosition position) {

    }

    /**
     * Handle the view updater.
     *
     * @param updater the updater that have to be notified to the observer.
     */
    @Override
    public void handle(ViewUpdaterInterface updater) {

    }
}
