package co.edu.unicauca.appterapiademencia.login;

import co.edu.unicauca.appterapiademencia.events.LoginEvent;
import co.edu.unicauca.appterapiademencia.events.RegisterEvent;

/**
 * Created by SEBAS on 22/10/2016.
 */

public interface RegisterPresenter {
    void OnDestroy();
    void OnCreate();
    void registerUser(String username,String password,String completeName,int accessType);
    void onEventMainThread(RegisterEvent event);



}
