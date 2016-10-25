package co.edu.unicauca.appterapiademencia.login;

import android.util.Log;

/**
 * Created by ENF on 14/10/2016.
 */

public class LoginInteractorImplementation implements  LoginInteractor {
    private LoginRepository loginRepository;

    public LoginInteractorImplementation(){
        loginRepository = new LoginRepositoryImplementation();
    }


    @Override
    public void checkSession() {
        loginRepository.checkSession();
    }



    @Override
    public void doSignIn(String username, String password) {
        Log.e("Login","Estoy en el interactor");
        loginRepository.signIn(username,password);
    }
}
