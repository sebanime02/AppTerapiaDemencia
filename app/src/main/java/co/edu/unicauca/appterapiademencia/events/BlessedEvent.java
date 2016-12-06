package co.edu.unicauca.appterapiademencia.events;

/**
 * Created by SEBAS on 05/12/2016.
 */

public class BlessedEvent {
    public final static int onBlessedScoreSuccess=1;
    public final static int onBlessedScoreError=0;
    private int eventType;

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }
}
