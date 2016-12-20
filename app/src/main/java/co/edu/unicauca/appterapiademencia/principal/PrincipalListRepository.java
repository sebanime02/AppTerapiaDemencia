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

public interface PrincipalListRepository {

    Patient getPatientData(Long id);
    void addPatient();
    void showPatients();
    List<Patient> getPatients();
    void delete(Patient patient);

    void insertTip();
    List<Tip> getTips();

    void getNotifications();
    void aprobeNotifications(List<Annotation> annotationsList);

    void changeUserData(HashMap<String,Object> hashMap);
    List<Note> getNotes(Long id);

    Double getBlessedScore(Long id);
    Note getNote(Long idnote);

    void deleteNote(Long idnote);
    void aprobeNote(Long idnote);
    String getFastScore(Long id);

    int getLawtonScore(Long id);
    int getDowntonScore(Long id);

}
