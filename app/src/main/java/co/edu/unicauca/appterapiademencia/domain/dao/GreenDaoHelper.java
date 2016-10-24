package co.edu.unicauca.appterapiademencia.domain.dao;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

import co.edu.unicauca.appterapiademencia.SetupActivity;


/**
 * Created by ENF on 21/10/2016.
 */

public class GreenDaoHelper {

    private static DaoSession daoSession;
    private static final String DB_NAME="terapia-db";
    private static Context context;


    private static class SingletonHolder{

        private static final GreenDaoHelper INSTANCE = new GreenDaoHelper();

    }
    public static GreenDaoHelper getInstance(){
            return SingletonHolder.INSTANCE;
    }

    private GreenDaoHelper(){


        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(SetupActivity.getContext(), DB_NAME);
        Database db = helper.getWritableDb();
        this.daoSession = new DaoMaster(db).newSession();
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







}
