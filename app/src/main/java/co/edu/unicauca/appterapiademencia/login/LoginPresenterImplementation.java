package co.edu.unicauca.appterapiademencia.login;

import android.util.Log;

import co.edu.unicauca.appterapiademencia.events.LoginEvent;
import co.edu.unicauca.appterapiademencia.lib.EventBus;
import co.edu.unicauca.appterapiademencia.lib.GreenRobotEventBus;

/**
 * Created by ENF on 14/10/2016.
 */

 //El loginPresenterImplementation implementa los metodos de la interfaz LoginPresenter

public class LoginPresenterImplementation implements LoginPresenter {

    private EventBus eventBus;
    private LoginView loginView;
    private RegisterView registerView;
    private LoginInteractor loginInteractor;
    private Boolean inputState = false;

 //Como vemos, se inyecta la dependencia de la vista por medio del constructor
 //Además es posible acceder al contexto del Framework Android

    public LoginPresenterImplementation(LoginView loginView){
        this.loginView = loginView;
        this.loginInteractor = new LoginInteractorImplementation();
        this.eventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void OnCreate(){

        eventBus.register(this); //Se registra como suscriber en EventBus,
        // cuando un publisher publiqe un evento, esta clase puede recibir

    }
    @Override
    public void OnDestroy() {
        loginView = null;

        eventBus.unregister(this);

    }
    @Override
    public void validateLogin(String username, String password) {
        loginInteractor.doSignIn(username,password);
    }
    @Override
    public void onEventMainThread(LoginEvent event) {
        switch (event.getEventType()){
            case LoginEvent.onSingInSuccess:
                onSignInSucces();
                break;
            case LoginEvent.onSingInError:
                onSignInError();
                break;
            case LoginEvent.onFailedToRecoverSession:
                onFailedToRecoverSession();
                break;
        }
    }
    @Override
    public void manageInputs() {
        if (getInputState() == false) {
        if(loginView != null) {

                setInputState(true);
                loginView.enableInputs();
            }
        }
        else {
            if(loginView != null) {
                setInputState(false);
                loginView.disableInputs();
            }
        }

    }



    private void onFailedToRecoverSession(){

    }
    private void onSignInSucces() {

        if(loginView != null){
            Log.e("Login","Hay vista no nula, navegemos a principal");

            loginView.setSupervisorPreference();
            loginView.navigateToMainScreen();
        }

    }

    private void onSignInError() {
        if(loginView != null){
            Log.e("Login","Hay vista no nula, mostremos el error");
            loginView.loginError();
        }


    }


    public Boolean getInputState() {
        return inputState;
    }

    public void setInputState(Boolean inputState) {
        this.inputState = inputState;
    }
}
