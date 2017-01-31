package co.edu.unicauca.appterapiademencia.login;

import android.util.Log;

/**
 * Created by SEBAS on 22/10/2016.
 */

public class RegisterInteractorImplementation implements RegisterInteractor{

    private LoginRepository loginRepository;

    public RegisterInteractorImplementation(){
        loginRepository = new LoginRepositoryImplementation();
    }

    @Override
    public void doSingUp(String username, String password, String completeName,int accessType) {
        Log.e("Registro","llego al interactor");
        loginRepository.signUp(username,password,completeName,accessType);
    }
}
