package co.edu.unicauca.appterapiademencia.loguin;

/**
 * Created by ENF on 14/10/2016.
 */

public class LoginPresenterImplementation implements LoginPresenter {

    private LoginView loginView;
    private LoginInteractor loginInteractor;

    public LoginPresenterImplementation(LoginView loginView){
        this.loginView = loginView;

    }

    @Override
    public void OnDestroy() {
        loginView = null;

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
    private void onSignUpSucces(){
        if(loginView != null){
            loginView.newUserSucess();
        }


    }
    private void onSignUpError(String error){
        if(loginView != null){
            loginView.loginError(error);
        }

    }
}
