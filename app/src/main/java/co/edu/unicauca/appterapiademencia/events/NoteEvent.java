package co.edu.unicauca.appterapiademencia.events;

import co.edu.unicauca.appterapiademencia.domain.Note;

/**
 * Created by ENF on 09/12/2016.
 */

public class NoteEvent {
    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    public Note note;

}
