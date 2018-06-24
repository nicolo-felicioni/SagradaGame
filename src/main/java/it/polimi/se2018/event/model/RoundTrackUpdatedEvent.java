package it.polimi.se2018.event.model;

import it.polimi.se2018.model.RoundTrack;

/**
 * @author Davide Yi Xian Hu
 */
public class RoundTrackUpdatedEvent {

    /**
     * The round track.
     */
    RoundTrack updatedRoundTrack;

    /**
     * Constructor.
     * @param roundTrack the round track.
     */
    public RoundTrackUpdatedEvent(RoundTrack roundTrack){
        this.updatedRoundTrack=roundTrack.cloneRoundTrack();
    }

    /**
     * Round track getter.
     * @return the round track.
     */
    public RoundTrack getUpdatedRoundTrack() {
        return updatedRoundTrack.cloneRoundTrack();
    }
}
