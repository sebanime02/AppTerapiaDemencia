package co.edu.unicauca.appterapiademencia.login;

import android.util.Log;

/**
 * Created by ENF on 14/10/2016.
 */

        //La implementacion del Interactor contiene la instanciación al repositorio

public class LoginInteractorImplementation implements  LoginInteractor {
    private LoginRepository loginRepository;

    public LoginInteractorImplementation(){
        loginRepository = new LoginRepositoryImplementation();
    }


    @Override
    public void checkSession() {
        loginRepository.checkSession();
    }



    //El metodo doSingIn pasa los parametros al método signIn del repositorio
    @Override
    public void doSignIn(String username, String password) {
        Log.e("Login","Estoy en el interactor");
        loginRepository.signIn(username,password);
    }
}
