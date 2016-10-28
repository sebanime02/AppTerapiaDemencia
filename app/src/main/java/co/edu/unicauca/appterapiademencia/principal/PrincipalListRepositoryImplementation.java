package co.edu.unicauca.appterapiademencia.principal;

import android.util.Log;

import org.greenrobot.greendao.query.QueryBuilder;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.List;

import co.edu.unicauca.appterapiademencia.domain.Patient;
import co.edu.unicauca.appterapiademencia.domain.dao.GreenDaoHelper;
import co.edu.unicauca.appterapiademencia.domain.dao.PatientDao;
import co.edu.unicauca.appterapiademencia.domain.dao.UserDao;
import co.edu.unicauca.appterapiademencia.events.ListEvent;
import co.edu.unicauca.appterapiademencia.events.LoginEvent;
import co.edu.unicauca.appterapiademencia.events.RegisterEvent;
import co.edu.unicauca.appterapiademencia.lib.EventBus;
import co.edu.unicauca.appterapiademencia.lib.GreenRobotEventBus;

/**
 * Created by ENF on 26/10/2016.
 */

public class PrincipalListRepositoryImplementation implements PrincipalListRepository {
    private GreenDaoHelper helper;
    private UserDao userDao;
    private PatientDao patientDao;
    private boolean accessType;

    public PrincipalListRepositoryImplementation(){
        this.helper = GreenDaoHelper.getInstance();
        this.userDao = GreenDaoHelper.getUserDao();
        this.patientDao = GreenDaoHelper.getPatientDao();
    }
    @Override
    public void addPatient() {

    }

    @Override
    public void showPatients() {

    }

    @Override
    public void getPatients() {

     QueryBuilder qbpatients = GreenDaoHelper.getPatientDao().queryBuilder();
        List patients = qbpatients.list();
        List<Patient> patientList = patients;

        postEvent(patients,1);


    }

    @Override
    public void delete(Patient patient) {

    }

    @Override
    public void insertTip() {

    }

    @Override
    public void getTips() {

    }

    @Override
    public void getNotifications() {

    }

    @Override
    public void aprobeNotifications(List<Annotation> annotationsList) {

    }

    @Override
    public void changeUserData(HashMap<String, Object> hashMap) {

    }
    private void postEvent(List<Object> objectList,int typemethod){
        //typemethod==1 para eventos de pacientes,
        if(typemethod==1)
        {
            ListEvent listEvent = new ListEvent();
            listEvent.setObjectlist(objectList);

            EventBus eventBus = GreenRobotEventBus.getInstance();
            Log.e("Lista","Va a registrar el evento");
            eventBus.post(listEvent);
        }
        else
        {

        }

    }
}
