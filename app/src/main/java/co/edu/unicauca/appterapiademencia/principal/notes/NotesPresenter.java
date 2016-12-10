package co.edu.unicauca.appterapiademencia.principal.notes;

import co.edu.unicauca.appterapiademencia.events.ItemNoteEvent;
import co.edu.unicauca.appterapiademencia.events.NoteEvent;

/**
 * Created by SEBAS on 14/11/2016.
 */

public interface NotesPresenter {
    void onCreate();
    void onDestroy();
    void getNotes(Long id);
    void getnotesCount(Long idpatient);
    void getNote(Long idnote);
    void onEventMainThread(NoteEvent noteEvent);
    void onEventMainThread(ItemNoteEvent itemNoteEvent);


}
