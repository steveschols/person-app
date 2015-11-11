package be.stesch.person.service;

import be.stesch.person.common.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;

/**
 * @author Steve Schols
 * @since 1/09/2015
 */
@Stateless
public class NotificationServiceImpl implements NotificationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationServiceImpl.class);

    public void publishNotification(Notification notification) {
        LOGGER.info("External system notified: {}", notification.getNotification());
        notification.setSent(true);
    }

}
