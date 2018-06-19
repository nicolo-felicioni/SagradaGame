package it.polimi.se2018.controller.updater;

import it.polimi.se2018.controller.ViewUpdaterInterface;
import it.polimi.se2018.model.Player;
import it.polimi.se2018.model.PlayerState;
import it.polimi.se2018.view.View;

public class PlayerStateUpdater implements ViewUpdaterInterface {

    PlayerState newState;
    String playerId;

    public PlayerStateUpdater(String playerId, PlayerState newState){
        this.playerId=playerId;
        this.newState=newState;
    }

    public PlayerStateUpdater(Player player, PlayerState newState){
        this.playerId=player.getId();
        this.newState=newState;
    }



    @Override
    public void update(View view) {
        view.updateStatePlayer(playerId, newState);
    }
}
