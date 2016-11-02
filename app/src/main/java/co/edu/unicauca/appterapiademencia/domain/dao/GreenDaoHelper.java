package co.edu.unicauca.appterapiademencia.domain.dao;

import android.content.Context;

import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.HashMap;
import java.util.List;

import co.edu.unicauca.appterapiademencia.SetupActivity;
import co.edu.unicauca.appterapiademencia.domain.User;


/**
 * Created by ENF on 21/10/2016.
 */

public class GreenDaoHelper {

    private static co.edu.unicauca.appterapiademencia.domain.dao.DaoSession daoSession;
    private static final String DB_NAME="terapiaprueba-db";
    private static Context context;
    private HashMap<String,String> userinformation;
    private QueryBuilder queryBuilder;


    private static class SingletonHolder{

        private static final GreenDaoHelper INSTANCE = new GreenDaoHelper();

    }
    public static GreenDaoHelper getInstance(){
            return SingletonHolder.INSTANCE;
    }

    private GreenDaoHelper()
    {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(SetupActivity.getContext(), DB_NAME);
        Database db = helper.getWritableDb();
        this.daoSession = new co.edu.unicauca.appterapiademencia.domain.dao.DaoMaster(db).newSession();
    }

    public static DaoSession getDaoSession(){
        return daoSession;
    }
    public static UserDao getUserDao(){
        return daoSession.getUserDao();
    }
    public static PatientDao getPatientDao(){
        return daoSession.getPatientDao();
    }
    public static ExerciseDao getExerciseDao(){
        return daoSession.getExerciseDao();
    }
    public static NoteDao getNoteDao(){
        return daoSession.getNoteDao();
    }
    public static TipDao getTipDao(){
        return daoSession.getTipDao();
    }
    public static RecommendationDao getRecommendationDao(){
        return daoSession.getRecommendationDao();
    }
    public static HistoricDao getHistoricDao(){
        return daoSession.getHistoricDao();
    }

    public HashMap<String,String> getUserInformation(String username)
    {
        queryBuilder=getUserDao().queryBuilder();
        List<User> listuser = queryBuilder.where(UserDao.Properties.Username.eq(username)).limit(1).list();
        userinformation.put("completename",listuser.get(0).getCompleteName());
        //userinformation.put("",listuser.get(0).get());
        return userinformation;
    }







}
