package co.edu.unicauca.appterapiademencia.principal.patientprofile;

import co.edu.unicauca.appterapiademencia.domain.Patient;

/**
 * Created by SEBAS on 07/11/2016.
 */

public interface PatientProfileView  {
    void showPatientData(Patient patient);
    void changePhoto();
    void editPatientData(Long id);
    void getPatientData(Long id);
    void getBlessedScore(Long id);
}
