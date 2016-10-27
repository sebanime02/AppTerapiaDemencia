package co.edu.unicauca.appterapiademencia.principal.patientlist;

import java.util.List;

import co.edu.unicauca.appterapiademencia.domain.Patient;

/**
 * Created by ENF on 26/10/2016.
 */

public interface PatientListView {
    void addPatient();
    void showPatients(List<Patient> patient);
    void navigateToDetail(Patient patient);

    void navigateToExercise(Patient patient);
    void getPatients();

}
