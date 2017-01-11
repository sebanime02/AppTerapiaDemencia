package co.edu.unicauca.appterapiademencia.principal.reminiscence;

/**
 * Created by SEBAS on 10/01/2017.
 */

public interface ReminiscenceListPresenter
{

    void onCreate();
    void onResume();
    void onDestroy();
    void getReminiscence();
    void onEventMainThread();

}
