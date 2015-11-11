package be.stesch.person.model;

import be.stesch.person.common.observer.Observer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static be.stesch.person.model.MaritalStatus.MARRIED;
import static be.stesch.person.model.MaritalStatus.SINGLE;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

/**
 * @author Steve Schols
 * @since 3/09/2015
 */
@RunWith(MockitoJUnitRunner.class)
public class PersonTest {

    @Mock
    private Observer<Person> observer;

    @Test
    public void testSetMaritalStatusWithChangeNotifiesObserver() {
        Person person = new Person("Test", "Person", SINGLE);
        // Set by EntityListener
        person.setOriginalMaritalStatus(SINGLE);
        person.getObservers().add(observer);

        person.setMaritalStatus(MARRIED);
        verify(observer).notifyObserver(person);
    }

    @Test
    public void testSetMaritalStatusWithoutChangeDoesNotNotifyObserver() throws Exception {
        Person person = new Person("Test", "Person", SINGLE);
        // Set by EntityListener
        person.setOriginalMaritalStatus(SINGLE);
        person.getObservers().add(observer);

        person.setMaritalStatus(SINGLE);
        verify(observer, never()).notifyObserver(person);
    }

}