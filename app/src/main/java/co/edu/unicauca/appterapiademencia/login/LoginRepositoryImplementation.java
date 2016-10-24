package co.edu.unicauca.appterapiademencia.login;

import android.util.Log;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import co.edu.unicauca.appterapiademencia.domain.User;
import co.edu.unicauca.appterapiademencia.domain.dao.GreenDaoHelper;
import co.edu.unicauca.appterapiademencia.domain.dao.UserDao;
import co.edu.unicauca.appterapiademencia.events.LoginEvent;
import co.edu.unicauca.appterapiademencia.events.RegisterEvent;
import co.edu.unicauca.appterapiademencia.lib.EventBus;
import co.edu.unicauca.appterapiademencia.lib.GreenRobotEventBus;

/**
 * Created by ENF on 14/10/2016.
 */

public class LoginRepositoryImplementation implements LoginRepository {
    private GreenDaoHelper helper;
    private UserDao userDao;
    private boolean accessType;


    public LoginRepositoryImplementation(){

        this.helper = GreenDaoHelper.getInstance();
        this.userDao = GreenDaoHelper.getUserDao();

    }


    @Override
    public void signUp(String username, String password, String completeName, String passwordaprobal) {
       accessType = true; //verdadero es supervisor
       User user = new User(null,username,password,completeName,accessType);
       this.userDao.insert(user);
       Log.d("RegistroUsuario","Nueva id insertada: "+user.getId());
       if(user.getId()!=null){
           postEvent(RegisterEvent.onSingUpSuccess,2);
       }
        else{
           postEvent(RegisterEvent.onSingUpError,2);
       }


    }

    @Override
    public void signIn(String username, String password) {


        QueryBuilder qbsignin = GreenDaoHelper.getUserDao().queryBuilder();
        qbsignin.where(UserDao.Properties.Username.eq(username), UserDao.Properties.Password.eq(password));

        List users = qbsignin.list();
        for(int i=0; i<=qbsignin.list().size(); i++) {
            Log.d("IngresoUsuario", "Usuario" + qbsignin.list().get(i).toString());
        }
        if(users.size()==1){
            postEvent(LoginEvent.onSingInSuccess,1);
        }
        else{
            postEvent(LoginEvent.onSingInError,1);
        }


    }



    @Override
    public void checkSession() {
        postEvent(LoginEvent.onFailedToRecoverSession,1);
    }

    private void postEvent(int type, String errorMessage,int typemethod){
       //typemethod==1 para eventos de logueo, typemethod==2 para eventos de registro
        if(typemethod==1){
        LoginEvent loginEvent = new LoginEvent();
        loginEvent.setEventType(type);
        if(errorMessage != null){
            loginEvent.setErrorMessage(errorMessage);
        }
        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(loginEvent);
        }
        else{
         RegisterEvent registerEvent = new RegisterEvent();
         registerEvent.setEventType(type);
            if(errorMessage != null){
                registerEvent.setErrorMessage(errorMessage);
            }
            EventBus eventBus = GreenRobotEventBus.getInstance();
            eventBus.post(registerEvent);
        }

    }
    private void postEvent(int type,int typemethod){
        postEvent(type,null,typemethod);
    }
}
