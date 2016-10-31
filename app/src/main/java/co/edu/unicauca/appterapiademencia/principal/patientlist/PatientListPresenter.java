package co.edu.unicauca.appterapiademencia.principal.patientlist;

import java.util.List;

import co.edu.unicauca.appterapiademencia.domain.Patient;
import co.edu.unicauca.appterapiademencia.events.PatientListEvent;

/**
 * Created by ENF on 26/10/2016.
 */

public interface PatientListPresenter {
    void onCreate();
    void onResume();
    void onDestroy();
    void addPatient();
    void showPatients(List<Patient> patient);
    void onEventMainThread(PatientListEvent event);
    void getPatient();
}
