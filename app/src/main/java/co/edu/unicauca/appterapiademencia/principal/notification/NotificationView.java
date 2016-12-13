package co.edu.unicauca.appterapiademencia.principal.notification;

import android.app.Notification;

import java.util.List;

import co.edu.unicauca.appterapiademencia.domain.Note;

/**
 * Created by SEBAS on 12/12/2016.
 */

public interface NotificationView {
    void getNotifications();
    void showNotifications(List<Note> notificationList);
}
