package co.edu.unicauca.appterapiademencia.principal.notes;

import java.util.List;

import co.edu.unicauca.appterapiademencia.domain.Note;

/**
 * Created by SEBAS on 14/11/2016.
 */

public interface NotesView {

    void getNotes(Long id);
    void showNotes(List<Note> noteList);
    void addNote(Long idpatient);
    void getnotesCount(Long idpatient);
    void showNotesCount(int[] notescount);
    void showNote(Note note);
    void onItemClicked(Long position);
    Boolean checkDialogOpen();

}
