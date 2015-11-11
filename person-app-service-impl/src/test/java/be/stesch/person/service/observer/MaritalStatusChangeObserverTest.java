package be.stesch.person.service.observer;

import be.stesch.person.common.Notification;
import be.stesch.person.model.Person;
import be.stesch.person.service.NotificationService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static be.stesch.person.model.MaritalStatus.SINGLE;
import static be.stesch.person.service.observer.MaritalStatusChangeObserver.NOTIFICATION_STRING;
import static java.lang.String.format;
import static org.mockito.Mockito.verify;

/**
 * @author Steve Schols
 * @since 3/09/2015
 */
@RunWith(MockitoJUnitRunner.class)
public class MaritalStatusChangeObserverTest {

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private MaritalStatusChangeObserver observer;

    @Captor
    private ArgumentCaptor<Notification> notificationArgumentCaptor;

    @Test
    public void testNotifyObserver() throws Exception {
        Person person = new Person("Test", "Person", SINGLE);
        observer.notifyObserver(person);

        String expectedNotificationString = format(NOTIFICATION_STRING, person.getFirstName(), person.getLastName(),
                person.getOriginalMaritalStatus(), person.getMaritalStatus());
        verify(notificationService).publishNotification(notificationArgumentCaptor.capture());
        Assert.assertEquals(expectedNotificationString, notificationArgumentCaptor.getValue().getNotification());
    }

}