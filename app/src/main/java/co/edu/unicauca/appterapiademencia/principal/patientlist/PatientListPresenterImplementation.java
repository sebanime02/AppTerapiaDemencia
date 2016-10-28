package co.edu.unicauca.appterapiademencia.principal.patientlist;

import java.util.List;

import co.edu.unicauca.appterapiademencia.domain.Patient;
import co.edu.unicauca.appterapiademencia.events.ListEvent;
import co.edu.unicauca.appterapiademencia.lib.GreenRobotEventBus;
import co.edu.unicauca.appterapiademencia.login.LoginInteractorImplementation;
import co.edu.unicauca.appterapiademencia.principal.PrincipalListInteractor;
import co.edu.unicauca.appterapiademencia.principal.PrincipalListInteractorImplementation;

/**
 * Created by ENF on 27/10/2016.
 */

public class PatientListPresenterImplementation implements PatientListPresenter {

    private PatientListView patientListView;
    private PrincipalListInteractor patienListInteractor;
    private GreenRobotEventBus eventBus;

    public PatientListPresenterImplementation(PatientListView patientListView){
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
    public void showPatients(List<Patient> patient) {

    }

    @Override
    public void onEventMainThread(ListEvent event) {

    }

    @Override
    public void getPatient() {
        patienListInteractor.getPatients();
    }
}
