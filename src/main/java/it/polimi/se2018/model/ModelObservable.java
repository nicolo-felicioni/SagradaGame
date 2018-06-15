package it.polimi.se2018.model;

import it.polimi.se2018.controller.ViewUpdaterInterface;
import it.polimi.se2018.controller.ViewUpdaterObservable;
import it.polimi.se2018.controller.ViewUpdaterObserver;

import java.util.ArrayList;
import java.util.List;

public class ModelObservable extends Model implements ViewUpdaterObservable {

    List<ViewUpdaterObserver> observers;

    public ModelObservable(){
        super();
        observers = new ArrayList<>();
    }

    @Override
    public void addObserver(ViewUpdaterObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers(ViewUpdaterInterface updater) {
        //TODO
        observers.forEach(observer -> observer.handle(updater));
    }
}
