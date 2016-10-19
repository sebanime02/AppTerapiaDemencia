package co.edu.unicauca.appterapiademencia.loguin;

import co.edu.unicauca.appterapiademencia.events.LoginEvent;

/**
 * Created by ENF on 13/10/2016.
 */

public interface LoginPresenter {
    void OnDestroy();
    void OnCreate();
    void checkForAuthenticatedUser();
    void validateLogin(String username,String password);
    void registerNewUser(String username,String password);
    void onEventMainThread(LoginEvent event);
}
