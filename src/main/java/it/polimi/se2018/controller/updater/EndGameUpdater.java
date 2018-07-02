package it.polimi.se2018.controller.updater;

import it.polimi.se2018.controller.ViewUpdaterInterface;
import it.polimi.se2018.controller.utils.RankingPlayer;
import it.polimi.se2018.view.View;

import java.util.List;

//todo

public class EndGameUpdater implements ViewUpdaterInterface {

    private List<RankingPlayer> players;

    public EndGameUpdater(List<RankingPlayer> players){
        this.players = players;
    }

    @Override
    public void update(View view) {
        view.updateEndGame(players);
    }
}
