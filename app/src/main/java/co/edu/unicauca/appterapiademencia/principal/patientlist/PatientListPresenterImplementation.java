package co.edu.unicauca.appterapiademencia.principal.patientlist;

import android.util.Log;

import java.util.List;

import co.edu.unicauca.appterapiademencia.domain.Patient;
import co.edu.unicauca.appterapiademencia.events.PatientListEvent;
import co.edu.unicauca.appterapiademencia.lib.GreenRobotEventBus;
import co.edu.unicauca.appterapiademencia.principal.PrincipalListInteractor;
import co.edu.unicauca.appterapiademencia.principal.PrincipalListInteractorImplementation;

/**
 * Created by ENF on 27/10/2016.
 */

public class PatientListPresenterImplementation implements PatientListPresenter {

    private PatientListView patientListView;
    private PrincipalListInteractor patienListInteractor;
    private GreenRobotEventBus eventBus;

    public PatientListPresenterImplementation(PatientListView patientListView)
    {
        this.patientListView = patientListView;
        this.patienListInteractor = new PrincipalListInteractorImplementation();
        this.eventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void onCreate() {
        eventBus.register(this);

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {
        patientListView = null;

        eventBus.unregister(this);
    }

    @Override
    public void addPatient() {

    }

    @Override
    public void showPatients(List<Patient> patients) {
        Log.e("List Patientes","Estoy en el presentador, si devolvio registros");
        int j;
        for (j=0;j<patients.size();j++){

            Log.e("Lista pacientes",""+patients.get(j).getIdentity());
        }
        patientListView.showPatients(patients);

    }

    @Override
    public void onEventMainThread(PatientListEvent event) {
        Log.e("List Patients","En el presentador de vuelta, se llama al metodo showpatientes del presentador");
        //showPatients(event.getPatientList());

    }

    @Override
    public void getPatient() {

       showPatients(patienListInteractor.getPatients());
    }

    @Override
    public void adminRoutes(int option) {
        switch (option)
        {
            case 0:
                if(patientListView!=null){

                }
                break;
            case 1:
                if(patientListView!=null){

                }
                break;
            case 2:
                if(patientListView!=null){

                }
                break;
        }
    }
}
