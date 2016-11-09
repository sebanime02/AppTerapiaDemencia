package co.edu.unicauca.appterapiademencia.principal.patientprofile;

import android.util.Log;

import co.edu.unicauca.appterapiademencia.domain.Patient;
import co.edu.unicauca.appterapiademencia.lib.GreenRobotEventBus;
import co.edu.unicauca.appterapiademencia.principal.PrincipalListInteractor;
import co.edu.unicauca.appterapiademencia.principal.PrincipalListInteractorImplementation;

/**
 * Created by SEBAS on 07/11/2016.
 */

public class PatientProfilePresenterImplementation implements PatientProfilePresenter {

    private PrincipalListInteractor principalListInteractor;
    private PatientProfileView patientProfileView;
    private Patient patientObject;
    private GreenRobotEventBus eventBus;

    public PatientProfilePresenterImplementation(PatientProfileView patientProfileView){
        this.patientProfileView = patientProfileView;
        this.principalListInteractor = new PrincipalListInteractorImplementation();
        this.eventBus = GreenRobotEventBus.getInstance();


    }



    @Override
    public void showPatientData(Patient patient) {
        Log.d("Devuelta presentador","nombre"+patient.getName().toString());
            patientProfileView.showPatientData(patient);


    }

    @Override
    public void changePhoto() {

    }

    @Override
    public void getPatientData(Long id) {

       showPatientData(principalListInteractor.getPatientData(id));


    }



    @Override
    public void editPatientData(Long id) {

    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {
        patientProfileView = null;
    }

    @Override
    public void onResume() {

    }
}
