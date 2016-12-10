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


    public LoginRepositoryImplementation()
    {
        this.helper = GreenDaoHelper.getInstance();
        this.userDao = GreenDaoHelper.getInstance().getUserDao();

    }


    @Override
    public void signUp(String username, String password, String completeName) {


        Log.e("Registro","llego al patron repositorio");
        /*
        QueryBuilder qbsignup = GreenDaoHelper.getUserDao().queryBuilder();
        qbsignup.where(UserDao.Properties.Password.eq(passwordaprobal));

        List users = qbsignup.list();
*/

        QueryBuilder qbsignup = helper.getUserDao().queryBuilder();
        qbsignup.where(UserDao.Properties.Username.eq(username));

        List users = qbsignup.list();


        Log.e("Registro", "Numero de contraseñas repetidas: "+users.size()+"");

        if(users.size()==0)
        {
            Boolean resultInser;
            accessType = true;
            resultInser = helper.insertUser(username,password,completeName,accessType);

            if(!resultInser.booleanValue())
            {
                Log.e("Registro","Error al registrar el nuevo usuario");
                postEvent(RegisterEvent.onSingUpError,2);

            }
            else{
                postEvent(RegisterEvent.onSingUpSuccess,2);
            }

        }
        else{
            Log.e("Registro","Nombre de Usuario ya registrado");
            postEvent(RegisterEvent.onSingUpErrorAprobal,2);
        }

        /*
        accessType = true; //verdadero es supervisor
        User user = new User(null,username,password,completeName,accessType,"");
        this.userDao.insert(user);

        Log.d("RegistroUsuario","Nueva id insertada: "+user.getId());

        if(user.getId()!=null)
        {

            postEvent(RegisterEvent.onSingUpSuccess,2);
        }
        else
        {
            Log.e("Registro","Error al registrar el nuevo usuario");
            postEvent(RegisterEvent.onSingUpError,2);
        }
      */

    }

    @Override
    public void signIn(String username, String password) {
        Log.e("Login","Estoy en el repositorio");

        QueryBuilder qbsignin = helper.getUserDao().queryBuilder();
        qbsignin.where(UserDao.Properties.Username.eq(username), UserDao.Properties.Password.eq(password));

        List<User> users = qbsignin.listLazyUncached();
        //Log.e("loginrepo","username aceptada "+users.get(0).getUsername());


        if(users.size()==1)
        {

            Log.e("Login","Hay un usuario que coincide");
            postEvent(LoginEvent.onSingInSuccess,1);
        }
        else{
            Log.e("Login","No coincide");
            postEvent(LoginEvent.onSingInError,1);
        }


    }



    @Override
    public void checkSession() {
        postEvent(LoginEvent.onFailedToRecoverSession,1);
    }

    private void postEvent(int type,int typemethod){
       //typemethod==1 para eventos de logueo, typemethod==2 para eventos de registro
        if(typemethod==1)
        {
        LoginEvent loginEvent = new LoginEvent();
        loginEvent.setEventType(type);

        EventBus eventBusLogin = GreenRobotEventBus.getInstance();
        Log.e("Login","Va a registrar el evento");
        eventBusLogin.post(loginEvent);
        }
        else
        {
         RegisterEvent registerEvent = new RegisterEvent();
         registerEvent.setEventType(type);

         EventBus eventBusRegister = GreenRobotEventBus.getInstance();
         Log.e("Registro","Va a registrar el evento");
         eventBusRegister.post(registerEvent);
        }

    }

}
