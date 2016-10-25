package co.edu.unicauca.appterapiademencia.events;

/**
 * Created by SEBAS on 18/10/2016.
 */

public class LoginEvent {
    public final static int onSingInSuccess =  0;
    public final static int onSingInError = 1;
    public final static int onFailedToRecoverSession = 2;


    private int eventType;



    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }


}
