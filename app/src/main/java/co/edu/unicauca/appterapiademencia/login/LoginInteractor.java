package co.edu.unicauca.appterapiademencia.login;

/**
 * Created by ENF on 13/10/2016.
 */

public interface LoginInteractor {
    void checkSession();
    void doSingUp(String username, String password);
    void doSignIn(String username, String password);
}
