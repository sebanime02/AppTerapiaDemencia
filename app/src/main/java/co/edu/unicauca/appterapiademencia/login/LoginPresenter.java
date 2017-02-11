package co.edu.unicauca.appterapiademencia.login;

import co.edu.unicauca.appterapiademencia.events.LoginEvent;

/**
 * Created by ENF on 13/10/2016.
 */

public interface LoginPresenter {


    void OnDestroy();//El presentador abstrae algunos metodos del ciclo de vida asociada a la vista.
    void OnCreate();

    void validateLogin(String username,String password); //Orquesta logica de negocio
    void onEventMainThread(LoginEvent event);  //Recibe eventos que vienen de la interfaz EventBus
    void manageInputs(); //Manejo de funciones de la vista sin conocer su implementacion.

}


