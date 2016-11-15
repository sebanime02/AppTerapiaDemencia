package co.edu.unicauca.appterapiademencia.principal.notes;

import java.util.List;

import co.edu.unicauca.appterapiademencia.domain.Note;

/**
 * Created by SEBAS on 14/11/2016.
 */

public interface NotesPresenter {
    void onCreate();
    void onDestroy();
    void getNotes(Long id);


}
