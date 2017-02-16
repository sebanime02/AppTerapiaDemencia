package co.edu.unicauca.appterapiademencia.principal;

import android.util.Log;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.List;

import co.edu.unicauca.appterapiademencia.domain.HistoricScore;
import co.edu.unicauca.appterapiademencia.domain.Note;
import co.edu.unicauca.appterapiademencia.domain.Patient;
import co.edu.unicauca.appterapiademencia.domain.Reminiscence;
import co.edu.unicauca.appterapiademencia.domain.Tip;

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
    public List<Tip> getTips() {
        return principalListRepository.getTips();
    }

    @Override
    public List<Note> getNotes(Long id) {
        Log.e("addnote","llege a getnotes del interactor");
        return principalListRepository.getNotes(id);
    }

    @Override
    public void getNotifications() {
        principalListRepository.getNotifications();
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

    @Override
    public Double getBlessedScore(Long id) {
        return principalListRepository.getBlessedScore(id);
    }

    @Override
    public String getFastScore(Long id) {
        return principalListRepository.getFastScore(id);
    }

    @Override
    public Note getNote(Long idnote) {
        return principalListRepository.getNote(idnote);
    }

    @Override
    public void deleteNote(Long idnote) {
            principalListRepository.deleteNote(idnote);
    }

    @Override
    public void aprobeNote(Long idnote) {
        principalListRepository.aprobeNote(idnote);

    }

    @Override
    public int getDowntonScore(Long id) {
        return principalListRepository.getDowntonScore(id);
    }

    @Override
    public int getLawtonScore(Long id) {
        return  principalListRepository.getLawtonScore(id);
    }

    @Override
    public HistoricScore getMMSEScore(Long id) {
        return principalListRepository.getMMSEScore(id);
    }

    @Override
    public void getBlessedData(Long id,int mode) {

        principalListRepository.getBlessedData(id,mode);
    }

    @Override
    public List<Reminiscence> getReminiscenceList()
    {
        return principalListRepository.getReminiscenceList();
    }

    @Override
    public boolean getNotificationState()
    {
        return principalListRepository.getNotificationState();
    }

    @Override
    public void setNotificationsState(boolean mode)
    {
        principalListRepository.changeNotificationsState(mode);
    }


}
