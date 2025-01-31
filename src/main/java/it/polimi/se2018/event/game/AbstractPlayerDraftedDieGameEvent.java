package it.polimi.se2018.event.game;

import it.polimi.se2018.model.Die;

public abstract class AbstractPlayerDraftedDieGameEvent extends AbstractPlayerGameEvent{

    /**
     * The die of the draft pool.
     */
    private Die draftedDie;

    /**
     * Constructor.
     * @param draftedDie the die of the draft pool.
     * @param playerId the player identifier.
     */
    public AbstractPlayerDraftedDieGameEvent(Die draftedDie, String playerId) {
        super(playerId);
        this.draftedDie = draftedDie.cloneDie();
    }

    /**
     * Getter of the drafted die from the draft pool.
     * @return the drafted die from the draft pool.
     */
    public Die getDraftedDie() {
        return draftedDie.cloneDie();
    }

}
