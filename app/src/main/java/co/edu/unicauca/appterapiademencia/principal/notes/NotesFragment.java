package co.edu.unicauca.appterapiademencia.principal.notes;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.List;

import co.edu.unicauca.appterapiademencia.R;
import co.edu.unicauca.appterapiademencia.adapters.NoteAdapter;
import co.edu.unicauca.appterapiademencia.adapters.PatientListAdapter;
import co.edu.unicauca.appterapiademencia.domain.Note;
import co.edu.unicauca.appterapiademencia.principal.patientlist.AddPatientActivity;

/**
 * Created by SEBAS on 07/11/2016.
 */

public class NotesFragment extends Fragment implements NotesView{
    FloatingActionButton floatingActionButton;
    RecyclerView recycler;
    private NoteAdapter adapter;
    private RecyclerView.Adapter newadapter;
    private RecyclerView.LayoutManager LManager;
    private NotesPresenterImplementation notesPresenterImplementation;
    private Long idpatient;
    private List<Note> noteList;
    private Intent ir_reg;
    public NotesFragment(){
        this.noteList = noteList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_profile_notes,container,false);

        return rootView;


    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle args = getArguments();
        idpatient=args.getLong("cedula");
        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.add_note);
        recycler = (RecyclerView) view.findViewById(R.id.reciclador);
        recycler.setHasFixedSize(true);
        LManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recycler.setLayoutManager(LManager);
        getNotes(idpatient);
        adapter = new NoteAdapter(this.noteList, getActivity());
        recycler.setAdapter(adapter);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNote();
            }
        });



    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        notesPresenterImplementation = new NotesPresenterImplementation(this);
        notesPresenterImplementation.onCreate();

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void getNotes(Long id) {
        notesPresenterImplementation.getNotes(id);
    }

    @Override
    public void showNotes(List<Note> list) {
        this.noteList=list;

    }

    @Override
    public void addNote() {
        Bundle args = getArguments();
        idpatient=args.getLong("cedula");
        ir_reg.putExtra("idpatient", idpatient);
        startActivity(new Intent(getActivity(), AddNoteActivity.class));
    }


}
