package co.edu.unicauca.appterapiademencia.loguin;

/**
 * Created by ENF on 13/10/2016.
 */

public interface LoginRepository {
    void signUp(String username, String password);
    void signIn(String username, String password);
    void checkSession();
}
