package co.edu.unicauca.appterapiademencia.principal;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.List;

import co.edu.unicauca.appterapiademencia.domain.Patient;

/**
 * Created by ENF on 26/10/2016.
 */

public interface PrincipalListRepository {

    Patient getPatientData(Long id);
    void addPatient();
    void showPatients();
    List<Patient> getPatients();
    void delete(Patient patient);

    void insertTip();
    void getTips();

    void getNotifications();
    void aprobeNotifications(List<Annotation> annotationsList);

    void changeUserData(HashMap<String,Object> hashMap);

}
