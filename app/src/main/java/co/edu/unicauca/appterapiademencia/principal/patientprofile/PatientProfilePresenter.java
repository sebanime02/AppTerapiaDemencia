package co.edu.unicauca.appterapiademencia.principal.patientprofile;

import co.edu.unicauca.appterapiademencia.domain.Patient;
import co.edu.unicauca.appterapiademencia.events.BlessedEvent;
import co.edu.unicauca.appterapiademencia.events.BlessedGraphEvent;

/**
 * Created by SEBAS on 07/11/2016.
 */

public interface PatientProfilePresenter {
    void showPatientData(Patient patient);
    void changePhoto();
    void getPatientData(Long id);
    void editPatientData(Long id);
    void onCreate();
    void onDestroy();
    void onResume();
    void getBlessedScore(Long id);
    void onEventMainThread(BlessedEvent event);
    void onEventMainThread(BlessedGraphEvent event);
    void getFastScore(Long id);
    void getDowntonScore(Long id);
    void getLawtonScore(Long id);
    void getBlessedData(Long id,int mode);
    void handleBlessedGraph(int selector);


}
