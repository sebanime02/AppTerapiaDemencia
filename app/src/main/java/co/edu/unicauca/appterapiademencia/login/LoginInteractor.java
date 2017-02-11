package co.edu.unicauca.appterapiademencia.login;

/**
 * Created by ENF on 13/10/2016.
 */

public interface LoginInteractor {

    //El interactor de Login,
    //Se encarga de agregar una nueva subcapa de seperacion
    // entre capa presentacion y dominio.

    void checkSession();

    //Este metodo comunica al repositorio que debe ejecutar la
    // logica de negocio para autenticar un usuario
    //El desacople facilita las pruebas unitarias
    void doSignIn(String username, String password);
}


