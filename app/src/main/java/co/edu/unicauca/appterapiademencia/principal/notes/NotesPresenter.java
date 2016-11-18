package co.edu.unicauca.appterapiademencia.principal.notes;

/**
 * Created by SEBAS on 14/11/2016.
 */

public interface NotesPresenter {
    void onCreate();
    void onDestroy();
    void getNotes(Long id);
    void getnotesCount(Long idpatient);


}
