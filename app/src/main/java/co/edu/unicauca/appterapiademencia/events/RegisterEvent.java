package co.edu.unicauca.appterapiademencia.events;

import android.app.Application;
import android.content.res.Resources;

/**
 * Created by SEBAS on 22/10/2016.
 */

public class RegisterEvent {
    public final static int onSingUpError = 0;
    public final static int onSingUpSuccess = 1;
    public final static int onSingUpErrorEmptyInputs = 2;


    private int eventType;
    private String errorMessage;

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
