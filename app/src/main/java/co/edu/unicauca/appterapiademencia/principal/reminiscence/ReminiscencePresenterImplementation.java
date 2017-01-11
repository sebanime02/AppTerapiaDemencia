package co.edu.unicauca.appterapiademencia.principal.reminiscence;

import co.edu.unicauca.appterapiademencia.lib.EventBus;
import co.edu.unicauca.appterapiademencia.lib.GreenRobotEventBus;
import co.edu.unicauca.appterapiademencia.principal.PrincipalListInteractor;
import co.edu.unicauca.appterapiademencia.principal.PrincipalListInteractorImplementation;

/**
 * Created by SEBAS on 10/01/2017.
 */

public class ReminiscencePresenterImplementation implements ReminiscenceListPresenter {

    private ReminiscenceListView reminiscenceListView;
    private PrincipalListInteractor principalListInteractor;
    private EventBus eventBus;

    public ReminiscencePresenterImplementation(ReminiscenceListView reminiscenceListView)
    {
        this.reminiscenceListView = reminiscenceListView;
        this.principalListInteractor = new PrincipalListInteractorImplementation();
        this.eventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void onCreate() {
            eventBus.register(this);
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
    }

    @Override
    public void getReminiscence() {
            reminiscenceListView.showReminiscenceList(principalListInteractor.getReminiscenceList());
    }

    @Override
    public void onEventMainThread() {

    }
}
