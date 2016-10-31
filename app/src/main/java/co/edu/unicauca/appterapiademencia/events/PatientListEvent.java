package co.edu.unicauca.appterapiademencia.events;

import java.util.List;

import co.edu.unicauca.appterapiademencia.domain.Patient;

/**
 * Created by ENF on 26/10/2016.
 */

public class PatientListEvent {
    private List<Patient> patientList;


    public List<Patient> getPatientList() {
        return patientList;
    }

    public void setPatientList(List<Patient> patientList) {
        this.patientList = patientList;
    }
}
