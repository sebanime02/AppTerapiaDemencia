package co.edu.unicauca.appterapiademencia.login;

/**
 * Created by ENF on 13/10/2016.
 */

public interface LoginView {
    //LoginView es una interfaz que abstrae las funciones de la vista
    void enableInputs();
    void disableInputs();
    void setInputs();


    void handleSingIn();

    void navigateToMainScreen();
    void exitLogin();
    void loginError();

    void navigateToRegister();
    void saveLoginPreference();
    void setSupervisorPreference();
    void shotTooManyTries();



}


