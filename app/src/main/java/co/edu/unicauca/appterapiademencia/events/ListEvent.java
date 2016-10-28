package co.edu.unicauca.appterapiademencia.events;

import java.util.List;

/**
 * Created by ENF on 26/10/2016.
 */

public class ListEvent {
    public List<Object> getObjectlist() {
        return objectlist;
    }

    public void setObjectlist(List<Object> objectlist) {
        this.objectlist = objectlist;
    }

    private List<Object> objectlist;
}
