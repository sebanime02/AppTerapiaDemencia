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
    public void onDestroy()
    {

    }

    @Override
    public void getReminiscence() {
        int countRemniscenceList;

        if(reminiscenceListView!=null)
        {
            countRemniscenceList= reminiscenceListView.showReminiscenceList(principalListInteractor.getReminiscenceList());

            if(countRemniscenceList==0)
            {
                reminiscenceListView.emtpyReminiscenceList();
            }
            else
            {
                reminiscenceListView.refreshView();
            }
        }


    }

    @Override
    public void onEventMainThread() {

    }
}
