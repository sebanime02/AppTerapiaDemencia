package co.edu.unicauca.appterapiademencia.principal.patientprofile;

import co.edu.unicauca.appterapiademencia.domain.Patient;

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

}
