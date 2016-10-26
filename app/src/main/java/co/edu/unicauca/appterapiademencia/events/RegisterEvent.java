package co.edu.unicauca.appterapiademencia.events;

/**
 * Created by SEBAS on 22/10/2016.
 */

public class RegisterEvent {
    public final static int onSingUpError = 0; //nombre de usuario ya utilizado
    public final static int onSingUpSuccess = 1;
    public final static int onSingUpErrorAprobal = 2; //contraseña de aprobacion no existente


    private int eventType;


    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }


}
