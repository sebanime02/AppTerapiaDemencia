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
import co.edu.unicauca.appterapiademencia.events.PatientListEvent;
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
    private QueryBuilder queryBuildergeneral;

    public PrincipalListRepositoryImplementation(){
        this.helper = GreenDaoHelper.getInstance();
        this.userDao = GreenDaoHelper.getUserDao();
        this.patientDao = GreenDaoHelper.getPatientDao();
        this.queryBuildergeneral = GreenDaoHelper.getPatientDao().queryBuilder();
    }

    @Override
    public Patient getPatientData(Long id) {
       List<Patient> patientList = queryBuildergeneral.where(PatientDao.Properties.Identity.eq(id)).limit(1).list();
        Log.d("Repositorio","devolvio el nombre: "+patientList.get(0).getName());
        return patientList.get(0);

    }

    @Override
    public void addPatient() {

    }

    @Override
    public void showPatients() {

    }

    @Override
    public List<Patient> getPatients() {
       /*Patient prueba = new Patient(null,"Orlando Ñ","18/04/1995","","cosmitet",11111,"ninguno",null,null,null,null,0,0,0);
        this.patientDao.insert(prueba);
           */
     QueryBuilder qbpatients = GreenDaoHelper.getPatientDao().queryBuilder();
        List patients = qbpatients.orderDesc(PatientDao.Properties.Id).list();
        List<Patient> patientList = patients;
        int i;
        for(i=0; i<patientList.size();i++){
            Log.e("List Patients","Paciente con nombre "+patientList.get(i).getName());
        }
        Log.e("List Patients","Envia al postlistevent la lista");
        return patientList;


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
    private void postPatientListEvent(List<Patient> patientList){


            PatientListEvent listEvent = new PatientListEvent();
            listEvent.setPatientList(patientList);

            EventBus eventBus = GreenRobotEventBus.getInstance();
            Log.e("Lista de Pacientes","Va a registrar el evento con lista de pacientes");





    }
}
