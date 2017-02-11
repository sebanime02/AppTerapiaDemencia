package co.edu.unicauca.appterapiademencia.login;

/**
 * Created by ENF on 13/10/2016.
 */

public interface LoginRepository {
    //Se escriben los métodos abstractos(sin implementacion) en la interfaz
    void signUp(String username, String password, String completeName,int accessType);
    void signIn(String username, String password);
    void checkSession();
}



