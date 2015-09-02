package be.stesch.person.common.observer;

import java.util.List;

/**
 * @author Steve Schols
 * @since 1/09/2015
 */
public interface Observable {

    void registerObserver(Observer observer);

    void unregisterObserver(Observer observer);

    List<Observer> getObservers();

    void notifyObservers();

}
