package co.edu.unicauca.appterapiademencia.principal.patientprofile;

import android.util.Log;

import co.edu.unicauca.appterapiademencia.domain.Patient;
import co.edu.unicauca.appterapiademencia.events.BlessedEvent;
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
        eventBus.register(this);

    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
        patientProfileView = null;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void getBlessedScore(Long id) {

        Double score;
        String comentario = "No presenta demencia por el momento";
        score = principalListInteractor.getBlessedScore(id);

        if(patientProfileView!=null)
        {
            if(score>=4.0 || score<=15.0)
            {
            comentario ="Hay sospecha de Demencia en el Paciente";
                patientProfileView.showBlessedScore(score,comentario,"verde");
            }
            if(score<4.0){
                comentario = "No presenta demencia por el momento";
                patientProfileView.showBlessedScore(score,comentario,"amarillo");

            }
            if(score>=15.0)
            {
                comentario = "Es altamente probable que el paciente tenga Demencia en etapa moderada o grave";
                patientProfileView.showBlessedScore(score,comentario,"rojo");

            }



        }
    }

    @Override
    public void onEventMainThread(BlessedEvent event) {
        switch (event.getEventType())
        {
            case BlessedEvent.onBlessedScoreError:

            break;
        }
    }

    @Override
    public void getFastScore(Long id) {
        String score;
        score = principalListInteractor.getFastScore(id);
        if(patientProfileView!=null)
        {
            patientProfileView.showFastScore(score);
        }
    }
}
