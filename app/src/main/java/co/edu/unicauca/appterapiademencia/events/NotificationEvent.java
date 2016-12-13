package co.edu.unicauca.appterapiademencia.events;

import android.app.Notification;

import java.util.List;

import co.edu.unicauca.appterapiademencia.domain.Note;

/**
 * Created by SEBAS on 12/12/2016.
 */

public class NotificationEvent {

    //0 traernotas;
    //1 eliminarnotas;
    //2 aprobarnotas;
    public List<Note> noteList;



    public List<Note> getNoteList() {
        return noteList;
    }

    public void setNoteList(List<Note> noteList) {
        this.noteList = noteList;
    }




}
