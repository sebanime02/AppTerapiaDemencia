package co.edu.unicauca.appterapiademencia.principal.notification;

import java.util.List;

import co.edu.unicauca.appterapiademencia.domain.Note;

/**
 * Created by SEBAS on 12/12/2016.
 */

public interface NotificationView {
    void getNotifications();
    int showNotifications(List<Note> notificationList);
    void refreshNotification();
    void showEmptyNotificationList();
}
