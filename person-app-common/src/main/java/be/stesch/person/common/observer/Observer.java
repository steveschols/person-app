package be.stesch.person.common.observer;

/**
 * @author Steve Schols
 * @since 1/09/2015
 */
public interface Observer<T extends Observable> {

    void notifyObserver(T observable);

}
