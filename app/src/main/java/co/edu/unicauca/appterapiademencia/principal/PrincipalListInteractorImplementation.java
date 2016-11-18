package co.edu.unicauca.appterapiademencia.principal;

import android.util.Log;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.List;

import co.edu.unicauca.appterapiademencia.domain.Note;
import co.edu.unicauca.appterapiademencia.domain.Patient;

/**
 * Created by ENF on 26/10/2016.
 */

public class PrincipalListInteractorImplementation implements PrincipalListInteractor {

    private PrincipalListRepository principalListRepository;

    public PrincipalListInteractorImplementation(){
        principalListRepository = new PrincipalListRepositoryImplementation();
    }

    @Override
    public void addPatient() {

    }

    @Override
    public void showPatients() {

    }

    @Override
    public Patient getPatientData(Long id) {
        Log.d("Llegue al interactor","El id siguie"+id);
        return principalListRepository.getPatientData(id);
    }


    @Override
    public List<Patient> getPatients() {

        Log.e("List Patients","En el interactor principal, llama al repositorio");
        return principalListRepository.getPatients();


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
    public List<Note> getNotes(Long id) {
        Log.e("addnote","llege a getnotes del interactor");
        return principalListRepository.getNotes(id);
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

    @Override
    public int[] notesCount(Long id) {
        return new int[0];
    }
}
