package co.edu.unicauca.appterapiademencia.events;

import java.util.List;

import co.edu.unicauca.appterapiademencia.domain.Patient;

/**
 * Created by ENF on 26/10/2016.
 */
public class PatientListEvent {
    public final static int onPatienListSuccess =  0;
    public final static int onPatientListError = 1;
    public final static int onPatientGetDataSuccess = 2;
    public final static int onPatientGetDataError=3;
    private int eventType;

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }
}
