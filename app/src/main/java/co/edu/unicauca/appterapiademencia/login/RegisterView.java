package co.edu.unicauca.appterapiademencia.login;

/**
 * Created by ENF on 21/10/2016.
 */

public interface RegisterView {

    void newUserError(int error);
    void handleSingUp();
    void navigateToLogin();
    void newUserSuccess();




}
