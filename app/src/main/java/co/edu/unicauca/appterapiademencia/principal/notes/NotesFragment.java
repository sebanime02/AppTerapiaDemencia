package co.edu.unicauca.appterapiademencia.principal.notes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import co.edu.unicauca.appterapiademencia.R;
import co.edu.unicauca.appterapiademencia.adapters.NoteAdapter;
import co.edu.unicauca.appterapiademencia.domain.Note;

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
    private TextView txt_empty,txt_caida,txt_movilidad,txt_alimentacion,txt_humor,txt_salud,txt_medication;
    private Long idpatient;
    private List<Note> noteList;
    private Intent ir_reg;
    private List<Note> list = new ArrayList<Note>();
    int movCount=0,caidasCount=0,eatingCount=0,fallCount=0,medicationCount=0,healthCount=0,changeCount=0;
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
        Log.e("cedulanotas",": "+idpatient);
        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.add_note);
        txt_caida= (TextView) view.findViewById(R.id.count_caida);
        txt_movilidad= (TextView) view.findViewById(R.id.count_movilidad);
        txt_alimentacion= (TextView) view.findViewById(R.id.count_alimentacion);
        txt_humor= (TextView) view.findViewById(R.id.count_humor);
        txt_salud= (TextView) view.findViewById(R.id.count_salud);
        txt_medication= (TextView) view.findViewById(R.id.count_medicacion);
        txt_empty = (TextView) view.findViewById(R.id.txt_vacio_note);




        recycler = (RecyclerView) view.findViewById(R.id.reciclador);
        recycler.setHasFixedSize(true);
        LManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recycler.setLayoutManager(LManager);
        getNotes(idpatient);
        if(noteList.size()>=1){
            txt_empty.setVisibility(view.GONE);
            recycler.setVisibility(view.VISIBLE);
        }

        adapter = new NoteAdapter(this.noteList, getActivity());
        recycler.setAdapter(adapter);
        txt_caida.setText(""+this.fallCount);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("Notes","entro al onclick");
                addNote(idpatient);
            }
        });




    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        list.clear();
        Bundle args = getArguments();
        idpatient=args.getLong("cedula");
        getNotes(idpatient);

        try {
            recycler.setHasFixedSize(true);
            LManager = new LinearLayoutManager(getActivity().getApplicationContext());
            recycler.setLayoutManager(LManager);
            adapter = new NoteAdapter(list, getActivity());
            recycler.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            txt_caida.setText(""+this.fallCount);
            //callListenerText();
        } catch (Exception e) {
            adapter.notifyDataSetChanged();
        }
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
        list.clear();
        Bundle args = getArguments();
        idpatient=args.getLong("cedula");
        getNotes(idpatient);

        try {
            recycler.setHasFixedSize(true);
            LManager = new LinearLayoutManager(getActivity().getApplicationContext());
            recycler.setLayoutManager(LManager);
            adapter = new NoteAdapter(list, getActivity());
            recycler.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            //callListenerText();
        } catch (Exception e) {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void getNotes(Long id) {
        Log.e("addnote","getnotes del fragment");
        notesPresenterImplementation.getNotes(id);
    }

    @Override
    public void showNotes(List<Note> noteList) {



        Log.e("addnote","de nuevo al fragment, show la lista, tamaño: "+list.size());
        for (int j = 0; j < noteList.size(); j++)
        {
            String noteType = noteList.get(j).getNoteType().toString();
            switch (noteType){
                case "movility":

                    this.movCount = movCount+1 ;
                    break;
                case "eating":
                    this.eatingCount = eatingCount+1 ;
                    break;
                case "fall":

                    this.fallCount = fallCount+1 ;
                    break;
                case "medication":
                    this.medicationCount = medicationCount+1;
                    break;
                case "otro":

                    break;
                case "health":
                    this.healthCount = healthCount +1;
                    break;
                case "changebehaviour":
                    this.changeCount = changeCount +1;
                    break;

            }
            list.add(noteList.get(j));



        }




        /*
        if(list.size()>=1)
        {
            for(int j=0;j<=list.size();j++)
            {

                Log.e("notelist ","id: "+list.get(j).getId().toString());
                Log.e("notelist ","description: "+list.get(j).getDescription());


            }

        }
        */

        this.noteList=list;

    }

    @Override
    public void addNote(Long idpatient) {
        Intent ir_reg = new Intent(getContext(), AddNoteActivity.class);
        Log.e("idpatient recibido :",""+idpatient);
        ir_reg.putExtra("idpatient", idpatient);
        startActivity(ir_reg);
    }

    @Override
    public void getnotesCount(Long idpatient) {
        notesPresenterImplementation.getnotesCount(idpatient);
    }

    @Override
    public void showNotesCount(int[] notescount) {

    }


}
