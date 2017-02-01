package co.edu.unicauca.appterapiademencia.principal.notification;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import co.edu.unicauca.appterapiademencia.domain.Note;
import co.edu.unicauca.appterapiademencia.events.ItemNotificationEvent;
import co.edu.unicauca.appterapiademencia.events.NotificationEvent;
import co.edu.unicauca.appterapiademencia.lib.EventBus;
import co.edu.unicauca.appterapiademencia.lib.GreenRobotEventBus;
import co.edu.unicauca.appterapiademencia.principal.PrincipalListInteractor;
import co.edu.unicauca.appterapiademencia.principal.PrincipalListInteractorImplementation;

/**
 * Created by SEBAS on 12/12/2016.
 */

public class NotificationPresenterImplementation implements NotificationPresenter {

    private NotificationView notificationView;
    private PrincipalListInteractor principalListInteractor;
    private EventBus eventBus;
    private List<Note> noteList;

    public NotificationPresenterImplementation(NotificationView notificationView)
    {
        this.notificationView = notificationView;
        this.principalListInteractor = new PrincipalListInteractorImplementation();
        this.eventBus = GreenRobotEventBus.getInstance();
        this.noteList = new ArrayList<Note>();
    }

    @Override
    public void getNotification() {

        principalListInteractor.getNotifications();

    }

    @Override
    public void onEventMainThread(NotificationEvent event) {


        noteList.clear();


        for(int m=0;m<event.getNoteList().size();m++)
        {
            noteList.add(event.getNoteList().get(m));
        }



        NotificationEvent stickyevent = de.greenrobot.event.EventBus.getDefault().removeStickyEvent(NotificationEvent.class);
        if(stickyevent!=null) {
            eventBus.removeSticky(stickyevent);
        }


            if (notificationView != null) {
                Log.e("presenter", "va a llamar el metodo de la vista para despejar las notas");

                int countResultados = notificationView.showNotifications(noteList);
                if(countResultados==0){notificationView.showEmptyNotificationList();}


            }


    }

    @Override
    public void onEvent(ItemNotificationEvent event) {
        switch (event.getMode())
        {
            case 0:
                principalListInteractor.deleteNote(event.getIdnote());
                notificationView.refreshNotification();

                break;
            case 1:
                principalListInteractor.aprobeNote(event.getIdnote());
                notificationView.refreshNotification();

                break;
        }
    }

    @Override
    public void onCreate() {
        //eventBus.register(this);
        eventBus.registerSticky(this);

    }

    @Override
    public void onResume() {
    }

    @Override
    public void onDestroy() {


        eventBus.unregister(this);

        notificationView = null;
    }

    @Override
    public void onAttach() {

    }

    @Override
    public void onDetach() {


    }
}
