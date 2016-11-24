package co.edu.unicauca.appterapiademencia.domain.dao;

import android.content.Context;
import android.util.Log;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.HashMap;
import java.util.List;

import co.edu.unicauca.appterapiademencia.domain.BlessedIncapacity;
import co.edu.unicauca.appterapiademencia.domain.Patient;
import co.edu.unicauca.appterapiademencia.domain.User;


/**
 * Created by ENF on 21/10/2016.
 */

public class GreenDaoHelper {

    private static co.edu.unicauca.appterapiademencia.domain.dao.DaoSession daoSession;
    public static final String DB_NAME="terapiaprueba-db";
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
        /*
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(SetupActivity.getContext(), DB_NAME);
        Database db = helper.getWritableDb();
        this.daoSession = new co.edu.unicauca.appterapiademencia.domain.dao.DaoMaster(db).newSession();
        */
    }

    public  DaoSession getDaoSession(){
        return daoSession;
    }
    public void setDaoSession(DaoSession daoSession){
        this.daoSession = daoSession;
    }
    public UserDao getUserDao(){
        return daoSession.getUserDao();
    }
    public  PatientDao getPatientDao(){
        return daoSession.getPatientDao();
    }
    public  ExerciseDao getExerciseDao(){
        return daoSession.getExerciseDao();
    }
    public  NoteDao getNoteDao(){
        return daoSession.getNoteDao();
    }
    public  TipDao getTipDao(){
        return daoSession.getTipDao();
    }
    public  RecommendationDao getRecommendationDao(){
        return daoSession.getRecommendationDao();
    }
    public  HistoricDao getHistoricDao(){
        return daoSession.getHistoricDao();
    }
    public BlessedIncapacityDao getBlessedIncapacityDao(){
        return daoSession.getBlessedIncapacityDao();
    }
    public  SintomaDao getSintomaDao(){
        return daoSession.getSintomaDao();
    }



    public User getUserInformation(String username)
    {
        queryBuilder=getUserDao().queryBuilder();
        List<User> listuser = queryBuilder.where(UserDao.Properties.Username.eq(username)).limit(1).list();
        return listuser.get(0);

    }
    public Patient getPatientInformationUsingCedula(Long id){
        queryBuilder=getPatientDao().queryBuilder();
        List<Patient> listpatient = queryBuilder.where(PatientDao.Properties.Identity.eq(id)).limit(1).list();
        return listpatient.get(0);
    }
    public User getUserInformationUsingId(Long id){
        queryBuilder=getUserDao().queryBuilder();
        List<User> listuser = queryBuilder.where(UserDao.Properties.Id.eq(id)).limit(1).list();
        return listuser.get(0);
    }

    public Patient getPatientInformation(Long id){
        queryBuilder=getPatientDao().queryBuilder();
        List<Patient> listpatient = queryBuilder.where(PatientDao.Properties.Identity.eq(id)).limit(1).list();
        return listpatient.get(0);
    }
    public Boolean carerIdDetector(){
        queryBuilder=getUserDao().queryBuilder();
        try
        {
            List<User> listuser = queryBuilder.where(UserDao.Properties.AccessType.eq(false)).limit(1).list();
            Log.e("helper name carer",listuser.get(0).getCompleteName());
            return true;
        }catch (IndexOutOfBoundsException e)
        {
            return false;
        }
    }
    public User getCarerInformation(){
        queryBuilder=getUserDao().queryBuilder();
        List<User> listuser = queryBuilder.where(UserDao.Properties.AccessType.eq(false)).limit(1).list();
        return listuser.get(0);
    }

    public void getUsers(){
        queryBuilder=getUserDao().queryBuilder();
        List<User> listuser = queryBuilder.list();
        try {
            for (int m = 0; m <= listuser.size(); m++) {
                Log.e("helper getusers", " " + listuser.get(m).getId());
                Log.e("helper getusers", " " + listuser.get(m).getCompleteName());
                Log.e("helper getusers", " " + listuser.get(m).getAccessType());
                Log.e("helper getusers", "                     ");
            }
        }
        catch (IndexOutOfBoundsException e){
            Log.e("helper getusers","Sin usuarios");

        }
    }
    public void getIncapacities(){
        queryBuilder = getBlessedIncapacityDao().queryBuilder();
        List<BlessedIncapacity> listblessed = queryBuilder.list();

        try {
            Log.e("size getincapacities",listblessed.size()+"");
            for (int m = 0; m <= listblessed.size(); m++) {
                Log.e("helper getincapacities", " " + listblessed.get(m).getId());
                Log.e("helper getincapacities", " " + listblessed.get(m).getPatientId());
                Log.e("helper getincapacities", "                     ");
            }
        }
        catch (IndexOutOfBoundsException e){
            Log.e("helper getincapacities","Sin discapacidades AVD");

        }

    }
    public BlessedIncapacity getBlessedbyid(Long id){
        BlessedIncapacity blesedone;
        QueryBuilder<BlessedIncapacity> blessedquery = getBlessedIncapacityDao().queryBuilder();
        blessedquery.where(BlessedIncapacityDao.Properties.PatientId.eq(id)).limit(1).list();
        //blessedquery.join(BlessedIncapacityDao.Properties.PatientId,Patient.class,PatientDao.Properties.Id).where(BlessedIncapacityDao.Properties.PatientId.eq(id));
        List<BlessedIncapacity> blessedList = blessedquery.limit(1).list();

        //List<BlessedIncapacity> blessedList = queryBuilder.where(BlessedIncapacityDao.Properties.PatientId.eq(patientone.getId())).limit(1).list();
        //queryBuilder.join(Address.class, AddressDao.Properties.userId)
        blesedone = blessedList.get(0);
        return blesedone;
    }










}
