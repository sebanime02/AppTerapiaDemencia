package co.edu.unicauca.appterapiademencia.login;

import co.edu.unicauca.appterapiademencia.SetupActivity;
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
    private SetupActivity setup;


    public LoginPresenterImplementation(LoginView loginView){
        this.loginView = loginView;
        this.loginInteractor = new LoginInteractorImplementation();
        this.eventBus = GreenRobotEventBus.getInstance();
    }
    public LoginPresenterImplementation(RegisterView registerView){
        this.registerView = registerView;
        this.loginInteractor = new LoginInteractorImplementation();
        this.eventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void OnCreate(){

        eventBus.register(this); //Se registra al presentador para que escuche por eventbus

    }
    @Override
    public void OnDestroy() {
        if(setup.autenticationMode()==false){
        loginView = null;

        }
        else{ //true
            registerView=null;
        }

        eventBus.unregister(this);

    }

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
    public void registerNewUser(String username, String password) {
        if(loginView != null){
            loginView.navigateToMainScreen();
        }
        loginInteractor.doSingUp(username,password);
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
                onSignUpSucces();
                break;
            case LoginEvent.onSingUpError:
                onSignUpError(event.getErrorMessage());
                break;
            case LoginEvent.onFailedToRecoverSession:
                onFailedToRecoverSession();
                break;
        }
    }

    private void onFailedToRecoverSession(){

    }
    private void onSignInSucces() {
        if(loginView != null){
            loginView.navigateToMainScreen();
        }

    }
    private void onSignUpSucces() {
        if(registerView != null){
            registerView.newUserSucess();
            registerView.navigateToLogin();
        }

    }
    private void onSignInError(String error) {
        if(loginView != null){
            loginView.loginError(error);
        }


    }

    private void onSignUpError(String error){
        if(loginView != null){
            loginView.loginError(error);
        }

    }
}
