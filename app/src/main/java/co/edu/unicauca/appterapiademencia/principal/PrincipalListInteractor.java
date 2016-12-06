package co.edu.unicauca.appterapiademencia.principal;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.List;

import co.edu.unicauca.appterapiademencia.domain.Note;
import co.edu.unicauca.appterapiademencia.domain.Patient;
import co.edu.unicauca.appterapiademencia.domain.Tip;

/**
 * Created by ENF on 26/10/2016.
 */

public interface PrincipalListInteractor {



    void addPatient();
    void showPatients();
    Patient getPatientData(Long id);
    List<Patient> getPatients();
    void delete(Patient patient);

    void insertTip();
    List<Tip> getTips();
    List<Note> getNotes(Long id);


    void getNotifications();
    void aprobeNotifications(List<Annotation> annotationsList);

    void changeUserData(HashMap<String,Object> hashMap);
    int[] notesCount(Long id);
    Double getBlessedScore(Long id);
}
