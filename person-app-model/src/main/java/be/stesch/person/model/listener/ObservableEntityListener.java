package be.stesch.person.model.listener;

import be.stesch.person.common.PersonAppProperties;
import be.stesch.person.common.observer.Observable;
import be.stesch.person.common.observer.Observer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.PostLoad;
import java.util.ArrayList;
import java.util.List;

import static be.stesch.person.common.PersonAppProperties.MARITAL_STATUS_CHANGE_OBSERVER_CLASS;

/**
 * @author Steve Schols
 * @since 1/09/2015
 */
public class ObservableEntityListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ObservableEntityListener.class);

    protected static Class getMaritalStatusChangeObserverClass() throws ClassNotFoundException {
        String maritalStatusChangeObserverClassName =
                PersonAppProperties.getInstance().getProperty(MARITAL_STATUS_CHANGE_OBSERVER_CLASS);

        return Class.forName(maritalStatusChangeObserverClassName);
    }

    @PostLoad
    public void addObserver(Object object) {
        if (object instanceof Observable) {
            Observable observable = (Observable) object;
            for (Observer<Observable> observer : getObservers()) {
                observable.registerObserver(observer);
            }
        }
    }

    private List<Observer<Observable>> getObservers() {
        List<Observer<Observable>> observers = new ArrayList<>();
        observers.add(getMaritalStatusChangeObserver());

        return observers;
    }

    private Observer<Observable> getMaritalStatusChangeObserver() {
        try {
            return (Observer<Observable>) getMaritalStatusChangeObserverClass().newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
