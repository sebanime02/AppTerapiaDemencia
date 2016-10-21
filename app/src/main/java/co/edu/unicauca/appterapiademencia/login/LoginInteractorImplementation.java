package co.edu.unicauca.appterapiademencia.login;

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
    public void doSingUp(String username, String password) {
        loginRepository.signUp(username,password);
    }

    @Override
    public void doSignIn(String username, String password) {
        loginRepository.signIn(username,password);
    }
}
