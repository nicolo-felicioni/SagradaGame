package it.polimi.se2018.network.rmi;

import it.polimi.se2018.event.game.*;
import it.polimi.se2018.event.network.ReconnectGameEvent;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Davide Yi Xian Hu
 */
public interface RMIServerSessionInterface extends Remote, Serializable {

    /**
     * Disconnect a client.
     */
    void disconnect(RMIClientInterface client) throws RemoteException;

    /**
     * Handle a ChooseDraftDieValueEvent.
     *
     * @param event the ChooseDraftDieValueEvent.
     */
    void handle(ChooseDraftDieValueGameEvent event) throws RemoteException;

    /**
     * Handle a DecreaseDieValueEvent.
     *
     * @param event the DecreaseDieValueEvent.
     * @throws RemoteException if RMI errors occur during the connection.
     */
    void handle(DecreaseDieValueGameEvent event) throws RemoteException;

    /**
     * Handle a DraftAndPlaceAgainEvent.
     *
     * @param event the DraftAndPlaceAgainEvent.
     * @throws RemoteException if RMI errors occur during the connection.
     */
    void handle(DraftAndPlaceAgainGameEvent event) throws RemoteException;

    /**
     * Handle a DraftAndPlaceNoAdjacentEvent.
     *
     * @param event the DraftAndPlaceNoAdjacentEvent.
     * @throws RemoteException if RMI errors occur during the connection.
     */
    void handle(DraftAndPlaceNoAdjacentGameEvent event) throws RemoteException;

    /**
     * Handle a DraftAndPlaceEvent.
     *
     * @param event the DraftAndPlaceEvent.
     * @throws RemoteException if RMI errors occur during the connection.
     */
    void handle(DraftAndPlaceGameEvent event) throws RemoteException;

    /**
     * Handle a EndTurnEvent.
     *
     * @param event the EndTurnEvent.
     * @throws RemoteException if RMI errors occur during the connection.
     */
    void handle(EndTurnGameEvent event) throws RemoteException;

    /**
     * Handle a FlipDraftDieEvent.
     *
     * @param event the FlipDraftDieEvent.
     * @throws RemoteException if RMI errors occur during the connection.
     */
    void handle(FlipDraftDieGameEvent event) throws RemoteException;

    /**
     * Handle a IncreaseDieValueEvent.
     *
     * @param event the IncreaseDieValueEvent.
     * @throws RemoteException if RMI errors occur during the connection.
     */
    void handle(IncreaseDieValueGameEvent event) throws RemoteException;

    /**
     * Handle a MoveDieIgnoreColorRestrictionEvent.
     *
     * @param event the MoveDieIgnoreColorRestrictionEvent.
     * @throws RemoteException if RMI errors occur during the connection.
     */
    void handle(MoveDieIgnoreColorRestrictionGameEvent event) throws RemoteException;

    /**
     * Handle a MoveDieIgnoreValueRestrictionEvent.
     *
     * @param event the MoveDieIgnoreValueRestrictionEvent.
     * @throws RemoteException if RMI errors occur during the connection.
     */
    void handle(MoveDieIgnoreValueRestrictionGameEvent event) throws RemoteException;

    /**
     * Handle a MoveDieMatchColorRoundTrackEvent.
     *
     * @param event the MoveDieMatchColorRoundTrackEvent.
     * @throws RemoteException if RMI errors occur during the connection.
     */
    void handle(MoveDieMatchColorRoundTrackGameEvent event) throws RemoteException;

    /**
     * Handle a MoveDieRespectAllRestrictionsEvent.
     *
     * @param event the MoveDieRespectAllRestrictionsEvent.
     * @throws RemoteException if RMI errors occur during the connection.
     */
    void handle(MoveDieRespectAllRestrictionsGameEvent event) throws RemoteException;

    /**
     * Handle a RerollAllDraftDiceEvent.
     *
     * @param event the RerollAllDraftDiceEvent.
     * @throws RemoteException if RMI errors occur during the connection.
     */
    void handle(RerollAllDraftDiceGameEvent event) throws RemoteException;

    /**
     * Handle a RerollDraftDieEvent.
     *
     * @param event the RerollDraftDieEvent.
     * @throws RemoteException if RMI errors occur during the connection.
     */
    void handle(RerollDraftDieGameEvent event) throws RemoteException;
    /**
     * Handle a StartGameEvent.
     *
     * @param event the StartGameEvent.
     * @throws RemoteException if RMI errors occur during the connection.
     */
    void handle(StartGameEvent event) throws RemoteException;

    /**
     * Handle a SwapDraftDieWithDiceBagDieEvent.
     *
     * @param event the SwapDraftDieWithDiceBagDieEvent.
     * @throws RemoteException if RMI errors occur during the connection.
     */
    void handle(SwapDraftDieWithDiceBagDieGameEvent event) throws RemoteException;

    /**
     * Handle a SwapDraftDieWithRoundTrackDieEvent.
     *
     * @param event the SwapDraftDieWithRoundTrackDieEvent.
     * @throws RemoteException if RMI errors occur during the connection.
     */
    void handle(SwapDraftDieWithRoundTrackDieGameEvent event) throws RemoteException;

    /**
     * Handle a UseToolCardEvent.
     *
     * @param event the UseToolCardEvent.
     * @throws RemoteException if RMI errors occur during the connection.
     */
    void handle(UseToolCardGameEvent event) throws RemoteException;

    /**
     * Handle a WindowPatternChosenEvent.
     *
     * @param event the WindowPatternChosenEvent.
     * @throws RemoteException if RMI errors occur during the connection.
     */
    void handle(WindowPatternChosenGameEvent event) throws RemoteException;
}
