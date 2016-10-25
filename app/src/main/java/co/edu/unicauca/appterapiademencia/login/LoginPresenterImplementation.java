package co.edu.unicauca.appterapiademencia.login;

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
    private Boolean inputState;




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
        if(loginView != null){
            loginView.navigateToMainScreen();
        }
        loginInteractor.doSignIn(username,password);

    }




    @Override
    public void onEventMainThread(LoginEvent event) {
        switch (event.getEventType()){
            case LoginEvent.onSingInSuccess:
                onSignInSucces();
                break;
            case LoginEvent.onSingInError:
                onSignInError(event.getErrorMessage());
                break;
            case LoginEvent.onSingUpSuccess:
                newUserSuccess();

            case LoginEvent.onFailedToRecoverSession:
                onFailedToRecoverSession();
                break;
        }
    }

    @Override
    public void manageInputs() {
        if(loginView != null) {
            loginView.disableInputs();
        }

        /*
        if(loginView != null) {
            if ((getInputState() == false) || (getInputState() == null)) {
                setInputState(true);
                loginView.enableInputs();
            } else {
                setInputState(false);
                loginView.disableInputs();
            }
        }
         */

    }

    @Override
    public void newUserSuccess() {
        if(loginView != null) {
            loginView.newUserSucces();
        }

    }

    private void onFailedToRecoverSession(){

    }
    private void onSignInSucces() {
        if(loginView != null){
            loginView.navigateToMainScreen();
        }

    }

    private void onSignInError(String error) {
        if(loginView != null){
            loginView.loginError(error);
        }


    }


    public Boolean getInputState() {
        return inputState;
    }

    public void setInputState(Boolean inputState) {
        this.inputState = inputState;
    }
}
