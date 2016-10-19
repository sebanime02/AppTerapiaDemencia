package co.edu.unicauca.appterapiademencia.loguin;

import co.edu.unicauca.appterapiademencia.events.LoginEvent;
import co.edu.unicauca.appterapiademencia.lib.EventBus;
import co.edu.unicauca.appterapiademencia.lib.GreenRobotEventBus;

/**
 * Created by ENF on 14/10/2016.
 */

public class LoginRepositoryImplementation implements LoginRepository {
    @Override
    public void signUp(String username, String password) {
        postEvent(LoginEvent.onSingUpSuccess);
    }

    @Override
    public void signIn(String username, String password) {
        postEvent(LoginEvent.onSingInSuccess);

    }

    @Override
    public void checkSession() {
        postEvent(LoginEvent.onFailedToRecoverSession);
    }

    private void postEvent(int type, String errorMessage){
        LoginEvent loginEvent = new LoginEvent();
        loginEvent.setEventType(type);
        if(errorMessage != null){
            loginEvent.setErrorMessage(errorMessage);
        }
        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(loginEvent);

    }
    private void postEvent(int type){
        postEvent(type, null);
    }
}
