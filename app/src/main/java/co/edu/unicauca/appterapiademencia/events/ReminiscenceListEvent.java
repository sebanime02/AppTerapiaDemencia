package co.edu.unicauca.appterapiademencia.events;

import java.util.List;

import co.edu.unicauca.appterapiademencia.domain.Reminiscence;

/**
 * Created by SEBAS on 10/01/2017.
 */

public class ReminiscenceListEvent {
    private List<Reminiscence> reminiscenceList;

    public List<Reminiscence> getReminiscenceList() {
        return reminiscenceList;
    }

    public void setReminiscenceList(List<Reminiscence> reminiscenceList) {
        this.reminiscenceList = reminiscenceList;
    }
}
