package co.edu.unicauca.appterapiademencia.loguin;

/**
 * Created by ENF on 13/10/2016.
 */

public interface LoginPresenter {
    void OnDestroy();
    void checkForAuthenticatedUser();
    void validateLogin(String username,String password);
    void registerNewUser(String username,String password);
}
