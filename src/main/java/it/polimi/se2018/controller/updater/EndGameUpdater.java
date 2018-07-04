package it.polimi.se2018.controller.updater;

import it.polimi.se2018.controller.ViewUpdaterInterface;
import it.polimi.se2018.controller.utils.RankingPlayer;
import it.polimi.se2018.view.View;

import java.util.List;

//todo

public class EndGameUpdater implements ViewUpdaterInterface {

    private List<RankingPlayer> players;
    private List<String> disconnectedPlayerIds;

    public EndGameUpdater(List<RankingPlayer> players, List<String> disconnectedPlayersId){
        this.players = players;
        this.disconnectedPlayerIds = disconnectedPlayersId;
    }

    @Override
    public void update(View view) {
        view.updateEndGame(players, disconnectedPlayerIds);
    }
}
