package co.edu.unicauca.appterapiademencia.login;

import android.widget.EditText;

import co.edu.unicauca.appterapiademencia.events.RegisterEvent;
import co.edu.unicauca.appterapiademencia.lib.EventBus;
import co.edu.unicauca.appterapiademencia.lib.GreenRobotEventBus;

/**
 * Created by SEBAS on 22/10/2016.
 */

public class RegisterPresenterImplementation implements RegisterPresenter{

    private RegisterView registerView;
    private RegisterInteractor registerInteractor;
    private EventBus eventBus;

    public RegisterPresenterImplementation(RegisterView registerView){
        this.registerView = registerView;
        this.registerInteractor = new RegisterInteractorImplementation();
        this.eventBus = GreenRobotEventBus.getInstance();
    }
    @Override
    public void OnDestroy() {
        registerView = null;

        eventBus.unregister(this);
    }

    @Override
    public void OnCreate() {
        eventBus.register(this);
    }

    @Override
    public void registerUser(String username, String password, String completeName, String passwordaprobal) {
        if(validateInputs(username,password,completeName,passwordaprobal)==true) {
            registerInteractor.doSingUp(username, password, completeName, passwordaprobal);
        }
        else{
            registerView.newUserError(RegisterEvent.onSingUpErrorEmptyInputs);
        }

    }
    private boolean validateInputs(String username, String password, String completeName, String passwordaprobal) {
        if(username.equals("") || password.equals("") || completeName.equals("") || passwordaprobal.equals("")){
            return true;
        }
        else{
            return  false;
        }
    }

    @Override
    public void onEventMainThread(RegisterEvent event) {
        switch (event.getEventType()){
            case RegisterEvent.onSingUpSuccess:
                onSignUpSucces();
                break;
            case RegisterEvent.onSingUpError:
                onSignUpError();
                break;


        }
    }
    private void onSignUpSucces() {
        if(registerView != null){
            registerView.newUserSucess();
            registerView.navigateToLogin();
        }

    }

    private void onSignUpError() {
        if (registerView != null) {
            registerView.newUserError(RegisterEvent.onSingUpError);
        }
    }
}
