package co.edu.unicauca.appterapiademencia.login;

import android.util.Log;

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
    public void registerUser(String username, String password, String completeName) {

        Log.e("Registro","llego al presentador");
            registerInteractor.doSingUp(username, password, completeName);

            //registerView.newUserError(RegisterEvent.onSingUpErrorEmptyInputs);


    }


    @Override
    public void onEventMainThread(RegisterEvent event) {
        switch (event.getEventType()){
            case RegisterEvent.onSingUpSuccess:
                Log.e("Registro","el presentador devolvio exito en el registro");
                onSignUpSuccess();
                break;
            case RegisterEvent.onSingUpError:
                Log.e("Registro","en el presentador devolvio error de registro");
                onSignUpError(RegisterEvent.onSingUpError);
                break;
            case RegisterEvent.onSingUpErrorAprobal:
                Log.e("Registro","en el presentador devolvio error de username ocupada");
                onSignUpError(RegisterEvent.onSingUpErrorAprobal);



        }
    }
    private void onSignUpSuccess() {
        if(registerView != null){

            registerView.newUserSuccess();
        }

    }

    private void onSignUpError(int evento) {
        if (registerView != null) {
            registerView.newUserError(evento);
        }
    }
}
