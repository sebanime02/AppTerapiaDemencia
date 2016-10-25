package co.edu.unicauca.appterapiademencia.login;

import android.util.Log;

import co.edu.unicauca.appterapiademencia.events.LoginEvent;
import co.edu.unicauca.appterapiademencia.lib.EventBus;
import co.edu.unicauca.appterapiademencia.lib.GreenRobotEventBus;

/**
 * Created by ENF on 14/10/2016.
 */

public class LoginPresenterImplementation implements LoginPresenter {

    private EventBus eventBus;
    private LoginView loginView;
    private RegisterView registerView;
    private LoginInteractor loginInteractor;
    private Boolean inputState = false;




    public LoginPresenterImplementation(LoginView loginView){
        this.loginView = loginView;
        this.loginInteractor = new LoginInteractorImplementation();
        this.eventBus = GreenRobotEventBus.getInstance();
    }


    @Override
    public void OnCreate(){

        eventBus.register(this); //Se registra al presentador para que escuche por eventbus

    }
    @Override
    public void OnDestroy() {
        loginView = null;

        eventBus.unregister(this);

    }
    //Este checkforauthenticated user es bueno implementarlo con shared preference

    @Override
    public void checkForAuthenticatedUser() {
     if(loginView != null){
            loginView.navigateToMainScreen();
        }
        loginInteractor.checkSession();
    }

    @Override
    public void validateLogin(String username, String password) {
        Log.e("Login","Estoy en el presentador");

        loginInteractor.doSignIn(username,password);

    }




    @Override
    public void onEventMainThread(LoginEvent event) {
        switch (event.getEventType()){
            case LoginEvent.onSingInSuccess:
                Log.e("Login","En el presentador de vuelta, login success");
                onSignInSucces();
                break;
            case LoginEvent.onSingInError:
                Log.e("Login","En el presentador de vuelta, login error");
                onSignInError();
                break;

            case LoginEvent.onFailedToRecoverSession:
                onFailedToRecoverSession();
                break;
        }
    }

    @Override
    public void manageInputs() {
        Log.e("Login","Entro al manageInputs del presentador");


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
          //PENDIENTE  loginView.saveLoginPreference();
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
