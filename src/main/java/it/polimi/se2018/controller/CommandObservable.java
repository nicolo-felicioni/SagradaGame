package it.polimi.se2018.controller;

/**
 * @author davide yi xian hu
 */
public interface CommandObservable {

	void addObserver(CommandObserver o);

	void notify(CommandInterface commandInterface);
}
