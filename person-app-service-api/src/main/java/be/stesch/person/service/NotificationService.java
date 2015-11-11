package be.stesch.person.service;

import be.stesch.person.common.Notification;

import javax.ejb.Local;

/**
 * @author Steve Schols
 * @since 1/09/2015
 */
@Local
public interface NotificationService {

    void publishNotification(Notification notification);

}
