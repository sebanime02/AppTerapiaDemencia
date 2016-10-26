package co.edu.unicauca.appterapiademencia.principal;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.List;

import co.edu.unicauca.appterapiademencia.domain.Patient;
import co.edu.unicauca.appterapiademencia.domain.Tip;

/**
 * Created by ENF on 26/10/2016.
 */

public interface PrincipalListPresenter {

    void signOff();


    void addPatient();
    List<Patient> showPatients();
    void getPatients();
    void delete(Patient patient);

    void insertTip();
    List<Tip> getTips();

    void getNotifications();
    void aprobeNotifications(List<Annotation> annotationsList);

    void changeUserData(HashMap<String,Object> hashMap);

    void onEventMainThread();






}
