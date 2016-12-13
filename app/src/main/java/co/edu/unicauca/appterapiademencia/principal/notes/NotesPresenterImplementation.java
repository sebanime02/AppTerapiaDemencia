package co.edu.unicauca.appterapiademencia.principal.notes;

import android.util.Log;

import co.edu.unicauca.appterapiademencia.domain.Note;
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

        Log.e("notepresenter","ondestroy");

        eventBus.unregister(this);
        notesView = null;

    }
    public void onResume()
    {
        eventBus.removeAllSticky();
        de.greenrobot.event.EventBus.clearCaches();
    }

    public void register()
    {
        eventBus.register(this);

    }
    public void unregister()
    {
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
            /*
            if(notesView.checkDialogOpen())
            {
                Log.e("notesfragment","Ya hay un dialog abierto");

            }
            else
            {
                Log.e("notesfragment","sin dialogs");

                notesView.showNote(noteEvent.getNote());

            }
            */
            //eventBus.unregister(this);
            notesView.showNote(noteEvent.getNote());

        }
    }

    @Override
    public void onEvent(ItemNoteEvent itemNoteEvent) {

        Note note;
        if(notesView!=null)
        {
            try {
                Log.e("notespresenter","LLege al onevent");

                note = principalListInteractor.getNote(itemNoteEvent.idnote);
                notesView.showNote(note);

                de.greenrobot.event.EventBus.clearCaches();
            }catch (Exception e)
            {}



        }

    }





    public void receiveNote(Long idnote)
    {
        principalListInteractor.getNote(idnote);
    }




}
