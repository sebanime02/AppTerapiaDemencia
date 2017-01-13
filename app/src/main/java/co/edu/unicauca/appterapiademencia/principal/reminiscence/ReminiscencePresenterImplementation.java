package co.edu.unicauca.appterapiademencia.principal.reminiscence;

import co.edu.unicauca.appterapiademencia.principal.PrincipalListInteractor;
import co.edu.unicauca.appterapiademencia.principal.PrincipalListInteractorImplementation;

/**
 * Created by SEBAS on 10/01/2017.
 */

public class ReminiscencePresenterImplementation implements ReminiscenceListPresenter {

    private ReminiscenceListView reminiscenceListView;
    private PrincipalListInteractor principalListInteractor;

    public ReminiscencePresenterImplementation(ReminiscenceListView reminiscenceListView)
    {
        this.reminiscenceListView = reminiscenceListView;
        this.principalListInteractor = new PrincipalListInteractorImplementation();

    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void getReminiscence() {
            reminiscenceListView.showReminiscenceList(principalListInteractor.getReminiscenceList());
    }

    @Override
    public void onEventMainThread() {

    }
}
