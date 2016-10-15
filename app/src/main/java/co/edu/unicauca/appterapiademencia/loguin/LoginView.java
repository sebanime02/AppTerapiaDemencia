package co.edu.unicauca.appterapiademencia.loguin;

/**
 * Created by ENF on 13/10/2016.
 */

public interface LoginView {
    void enableInputs();
    void disableInputs();
    void handleSingUp();
    void handleSingIn();

    void navigateToMainScreen();
    void exitLogin();
    void loginError(String error);
    void newUserError(String Error);
    void newUserSucess();
    void navigateToLogin();
    void navigateToRegister();


}
