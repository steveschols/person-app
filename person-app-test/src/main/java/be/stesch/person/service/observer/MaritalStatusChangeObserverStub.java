package be.stesch.person.service.observer;

import be.stesch.person.common.observer.Observable;
import be.stesch.person.common.observer.Observer;

/**
 * @author Steve Schols
 * @since 1/09/2015
 */
public class MaritalStatusChangeObserverStub implements Observer<Observable> {

    private boolean notified = false;

    @Override
    public void notifyObserver(Observable observable) {
        notified = true;
    }

    public boolean isNotified() {
        return notified;
    }

}
