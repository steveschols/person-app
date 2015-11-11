package be.stesch.person.service.observer;

import be.stesch.person.common.Notification;
import be.stesch.person.common.observer.Observer;
import be.stesch.person.model.Person;
import be.stesch.person.service.NotificationService;
import be.stesch.person.service.NotificationServiceImpl;

import static java.lang.String.format;

/**
 * @author Steve Schols
 * @since 1/09/2015
 */
public class MaritalStatusChangeObserver implements Observer<Person> {

    static final String NOTIFICATION_STRING = "Person %s %s changed marital status from %s to %s";

    private NotificationService notificationService = new NotificationServiceImpl();

    public void notifyObserver(Person person) {
        String notificationString = format(NOTIFICATION_STRING, person.getFirstName(), person.getLastName(),
                person.getOriginalMaritalStatus(), person.getMaritalStatus());

        Notification notification = new Notification(notificationString);
        notificationService.publishNotification(notification);
    }

}
