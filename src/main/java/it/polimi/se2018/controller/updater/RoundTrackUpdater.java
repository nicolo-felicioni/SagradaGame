package it.polimi.se2018.controller.updater;

import it.polimi.se2018.controller.ViewUpdaterInterface;
import it.polimi.se2018.model.RoundTrack;
import it.polimi.se2018.view.View;

public class RoundTrackUpdater implements ViewUpdaterInterface {

    /**
     * The round track.
     */
    RoundTrack updatedRoundTrack;

    /**
     * Constructor.
     * @param roundTrack the round track.
     */
    public RoundTrackUpdater(RoundTrack roundTrack){
        this.updatedRoundTrack=roundTrack.cloneRoundTrack();
    }

    /**
     * Update a view.
     *
     * @param view the object that have to be modified by this command.
     */
    @Override
    public void update(View view) {
        view.updateRoundTrack(updatedRoundTrack);
    }
}
