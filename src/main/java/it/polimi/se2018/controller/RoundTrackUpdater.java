package it.polimi.se2018.controller;

import it.polimi.se2018.model.RoundTrack;
import it.polimi.se2018.view.View;

public class RoundTrackUpdater implements ViewUpdaterInterface {

    RoundTrack updatedRoundTrack;

    public RoundTrackUpdater(RoundTrack roundTrack){
        this.updatedRoundTrack=roundTrack.cloneRoundTrack();
    }

    @Override
    public void update(View view) {
        view.updateRoundTrack(updatedRoundTrack);
    }
}
