package co.edu.unicauca.appterapiademencia.events;

/**
 * Created by SEBAS on 12/12/2016.
 */

public class ItemNotificationEvent {
    //0 borrar
    //1 aprobar

    public Long idnote;
    public int mode;
    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }



    public Long getIdnote() {
        return idnote;
    }

    public void setIdnote(Long idnote) {
        this.idnote = idnote;
    }



}
