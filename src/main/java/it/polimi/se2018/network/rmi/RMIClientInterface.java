package it.polimi.se2018.network.rmi;

import it.polimi.se2018.controller.ViewUpdaterObserver;
import it.polimi.se2018.event.*;
import it.polimi.se2018.exceptions.LoginException;
import it.polimi.se2018.exceptions.NetworkException;
import it.polimi.se2018.network.client.ClientInterface;
import it.polimi.se2018.network.utils.NetworkViewUpdaterObserver;
import it.polimi.se2018.observer.NetworkGameEventObserver;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIClientInterface extends Remote, NetworkViewUpdaterObserver {

    /**
     * Login a client to the server.
     *
     * @param uid the unique identifier of the client.
     */
    void login (String uid) throws RemoteException, LoginException;

    /**
     * Getter of the unique identifier.
     *
     * @return the unique identifier.
     */
    String getUid () throws RemoteException;

    /**
     * Add a NetworkGameEventObserver.
     *
     * @param observer the NetworkGameEventObserver.
     */
    void addGameObserver(NetworkGameEventObserver observer) throws RemoteException;

    /**
     * Remove a NetworkGameEventObserver.
     *
     * @param observer the NetworkGameEventObserver.
     */
    void removeGameObserver(NetworkGameEventObserver observer) throws RemoteException;

    /**
     * Notify the ChooseDraftDieValueObservers an ChooseDraftDieValueEvent.
     *
     * @param event the ChooseDraftDieValueEvent.
     */
    void notifyObservers(ChooseDraftDieValueGameEvent event) throws RemoteException;

    /**
     * Notify the DecreaseDieValueObservers an DecreaseDieValueEvent.
     *
     * @param event the DecreaseDieValueEvent.
     */
    void notifyObservers(DecreaseDieValueGameEvent event) throws RemoteException;

    /**
     * Notify the DraftAndPlaceAgainObservers an DraftAndPlaceAgainEvent.
     *
     * @param event the DraftAndPlaceAgainEvent.
     */
    void notifyObservers(DraftAndPlaceAgainGameEvent event) throws RemoteException;

    /**
     * Notify the DraftAndPlaceNoAdjacentObservers an DraftAndPlaceNoAdjacentEvent.
     *
     * @param event the DraftAndPlaceNoAdjacentEvent.
     */
    void notifyObservers(DraftAndPlaceNoAdjacentGameEvent event) throws RemoteException;

    /**
     * Notify the DraftAndPlaceObservers an DraftAndPlaceEvent.
     *
     * @param event the DraftAndPlaceEvent.
     */
    void notifyObservers(DraftAndPlaceGameEvent event) throws RemoteException;

    /**
     * Notify the EndTurnObservers an EndTurnEvent.
     *
     * @param event the EndTurnEvent.
     */
    void notifyObservers(EndTurnGameEvent event) throws RemoteException;

    /**
     * Notify the FlipDraftDieObservers an FlipDraftDieEvent.
     *
     * @param event the FlipDraftDieEvent.
     */
    void notifyObservers(FlipDraftDieGameEvent event) throws RemoteException;

    /**
     * Notify the IncreaseDieValueObservers an IncreaseDieValueEvent.
     *
     * @param event the IncreaseDieValueEvent.
     */
    void notifyObservers(IncreaseDieValueGameEvent event) throws RemoteException;

    /**
     * Notify the MoveDieIgnoreColorRestrictionObservers an MoveDieIgnoreColorRestrictionEvent.
     *
     * @param event the MoveDieIgnoreColorRestrictionEvent.
     */
    void notifyObservers(MoveDieIgnoreColorRestrictionGameEvent event) throws RemoteException;

    /**
     * Notify the MoveDieIgnoreValueRestrictionObservers an MoveDieIgnoreValueRestrictionEvent.
     *
     * @param event the MoveDieIgnoreValueRestrictionEvent.
     */
    void notifyObservers(MoveDieIgnoreValueRestrictionGameEvent event) throws RemoteException;

    /**
     * Notify the MoveDieMatchColorRoundTrackObservers an MoveDieMatchColorRoundTrackEvent.
     *
     * @param event the MoveDieMatchColorRoundTrackEvent.
     */
    void notifyObservers(MoveDieMatchColorRoundTrackGameEvent event) throws RemoteException;

    /**
     * Notify the MoveDieRespectAllRestrictionObservers an MoveDieRespectAllRestrictionEvent.
     *
     * @param event the MoveDieRespectAllRestrictionEvent.
     */
    void notifyObservers(MoveDieRespectAllRestrictionsGameEvent event) throws RemoteException;

    /**
     * Notify the RerollAllDraftDiceObservers an RerollAllDraftDiceEvent.
     *
     * @param event the RerollAllDraftDiceEvent.
     */
    void notifyObservers(RerollAllDraftDiceGameEvent event) throws RemoteException;

    /**
     * Notify the RerollDraftDieObservers an RerollDraftDieEvent.
     *
     * @param event the RerollDraftDieEvent.
     */
    void notifyObservers(RerollDraftDieGameEvent event) throws RemoteException;

    /**
     * Notify the StartGameObservers an StartGameEvent.
     *
     * @param event the StartGameEvent.
     */
    void notifyObservers(StartGameEvent event) throws RemoteException;

    /**
     * Notify the SwapDraftDieWithDiceBagDieObservers an SwapDraftDieWithDiceBagDieEvent.
     *
     * @param event the SwapDraftDieWithDiceBagDieEvent.
     */
    void notifyObservers(SwapDraftDieWithDiceBagDieGameEvent event) throws RemoteException;

    /**
     * Notify the SwapDrafDieWithRoundTrackDieObservers an SwapDrafDieWithRoundTrackDieEvent.
     *
     * @param event the SwapDrafDieWithRoundTrackDieEvent.
     */
    void notifyObservers(SwapDraftDieWithRoundTrackDieGameEvent event) throws RemoteException;

    /**
     * Notify the UseToolCardObservers an UseToolCardEvent.
     *
     * @param event the UseToolCardEvent.
     */
    void notifyObservers(UseToolCardGameEvent event) throws RemoteException;

    /**
     * Notify the WindowPatternChosenObservers an WindowPatternChosenEvent.
     *
     * @param event the WindowPatternChosenEvent.
     */
    void notifyObservers(WindowPatternChosenGameEvent event) throws RemoteException;

}
