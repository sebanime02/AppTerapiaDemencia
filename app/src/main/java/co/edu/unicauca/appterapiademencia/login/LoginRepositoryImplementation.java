package co.edu.unicauca.appterapiademencia.login;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import co.edu.unicauca.appterapiademencia.domain.dao.GreenDaoHelper;
import co.edu.unicauca.appterapiademencia.domain.dao.UserDao;
import co.edu.unicauca.appterapiademencia.events.LoginEvent;
import co.edu.unicauca.appterapiademencia.lib.EventBus;
import co.edu.unicauca.appterapiademencia.lib.GreenRobotEventBus;

/**
 * Created by ENF on 14/10/2016.
 */

public class LoginRepositoryImplementation implements LoginRepository {
    private GreenDaoHelper helper;


    public LoginRepositoryImplementation(){
        this.helper = GreenDaoHelper.getInstance();
    }
    @Override
    public void signUp(String username, String password) {

        postEvent(LoginEvent.onSingUpSuccess);
    }

    @Override
    public void signIn(String username, String password) {


        QueryBuilder qbsignin = GreenDaoHelper.getUserDao().queryBuilder();
        qbsignin.where(UserDao.Properties.Username.eq(username), UserDao.Properties.Password.eq(password));

        List users = qbsignin.list();

        if(users.size()==1){
            postEvent(LoginEvent.onSingInSuccess);
        }
        else{
            postEvent(LoginEvent.onSingInError);
        }


    }

    @Override
    public void checkSession() {
        postEvent(LoginEvent.onFailedToRecoverSession);
    }

    private void postEvent(int type, String errorMessage){
        LoginEvent loginEvent = new LoginEvent();
        loginEvent.setEventType(type);
        if(errorMessage != null){
            loginEvent.setErrorMessage(errorMessage);
        }
        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(loginEvent);

    }
    private void postEvent(int type){
        postEvent(type, null);
    }
}
