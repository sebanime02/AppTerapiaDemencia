package co.edu.unicauca.appterapiademencia.principal.notification;

import co.edu.unicauca.appterapiademencia.events.ItemNotificationEvent;
import co.edu.unicauca.appterapiademencia.events.NotificationEvent;

/**
 * Created by SEBAS on 12/12/2016.
 */

public interface NotificationPresenter {
    void getNotification();
    void onEventMainThread(NotificationEvent event);
    void onEvent(ItemNotificationEvent event);
    void onCreate();
    void onResume();
    void onDestroy();
    void onAttach();
    void onDetach();

}
