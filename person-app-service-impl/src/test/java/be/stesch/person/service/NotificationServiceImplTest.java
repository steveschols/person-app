package be.stesch.person.service;

import be.stesch.person.common.Notification;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author Steve Schols
 * @since 3/09/2015
 */
public class NotificationServiceImplTest {

    @Test
    public void testPublishNotification() throws Exception {
        NotificationService notificationService = new NotificationServiceImpl();

        Notification notification = new Notification("Test");
        notificationService.publishNotification(notification);

        assertTrue(notification.isSent());
    }

}
