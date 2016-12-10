package co.edu.unicauca.appterapiademencia.principal.notes;

import android.util.Log;

import co.edu.unicauca.appterapiademencia.events.ItemNoteEvent;
import co.edu.unicauca.appterapiademencia.events.NoteEvent;
import co.edu.unicauca.appterapiademencia.lib.GreenRobotEventBus;
import co.edu.unicauca.appterapiademencia.principal.PrincipalListInteractor;
import co.edu.unicauca.appterapiademencia.principal.PrincipalListInteractorImplementation;

/**
 * Created by SEBAS on 14/11/2016.
 */

public class NotesPresenterImplementation implements NotesPresenter {

    private PrincipalListInteractor principalListInteractor;
    private NotesView notesView;
    private GreenRobotEventBus eventBus;

    public NotesPresenterImplementation(NotesView notesView){
        this.notesView= notesView;
        this.principalListInteractor = new PrincipalListInteractorImplementation();
        this.eventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        notesView = null;

        eventBus.unregister(this);
    }

    @Override
    public void getNotes(Long id)
    {
            Log.e("addnote","llege a getnotes del presenter");
            notesView.showNotes(principalListInteractor.getNotes(id));

    }

    @Override
    public void getnotesCount(Long idpatient) {

       //principalListInteractor.notesCount(idpatient);

    }

    @Override
    public void getNote(Long idnote) {
        principalListInteractor.getNote(idnote);

    }

    @Override
    public void onEventMainThread(NoteEvent noteEvent) {
        if(notesView!=null)
        {
            notesView.showNote(noteEvent.getNote());
        }
    }

    @Override
    public void onEventMainThread(ItemNoteEvent itemNoteEvent) {
        principalListInteractor.getNote(itemNoteEvent.idnote);
    }


}
