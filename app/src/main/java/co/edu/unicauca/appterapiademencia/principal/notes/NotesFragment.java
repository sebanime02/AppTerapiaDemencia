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
    private TextView txt_empty,txt_caida,txt_movilidad,txt_alimentacion,txt_humor,txt_estadodeanimo,txt_medication;
    private Long idpatient;
    private List<Note> noteList;
    private Intent ir_reg;
    private List<Note> list = new ArrayList<Note>();
    int movCount=0;
    int eatingCount=0;
    int fallCount=0;
    int medicationCount=0;

    public int getEstadodeanimoCount() {
        return estadodeanimoCount;
    }

    public void setEstadodeanimoCount(int estadodeanimoCount) {
        this.estadodeanimoCount = estadodeanimoCount;
    }

    int estadodeanimoCount=0;
    int changeCount=0;



    public int getMovCount() {
        return movCount;
    }

    public void setMovCount(int movCount) {
        this.movCount = movCount;
    }

    public int getEatingCount() {
        return eatingCount;
    }

    public void setEatingCount(int eatingCount) {
        this.eatingCount = eatingCount;
    }

    public int getMedicationCount() {
        return medicationCount;
    }

    public void setMedicationCount(int medicationCount) {
        this.medicationCount = medicationCount;
    }



    public int getChangeCount() {
        return changeCount;
    }

    public void setChangeCount(int changeCount) {
        this.changeCount = changeCount;
    }

    public int getFallCount() {
        return fallCount;
    }

    public void setFallCount(int fallCount) {
        this.fallCount = fallCount;
    }

    public NotesFragment(){
        this.noteList = noteList;
        this.fallCount = fallCount;
        this.eatingCount=eatingCount;
        this.changeCount = changeCount;
        this.estadodeanimoCount = estadodeanimoCount;
        this.medicationCount = medicationCount;

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
        txt_estadodeanimo= (TextView) view.findViewById(R.id.count_salud);
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
            txt_caida.setText(""+getFallCount());
            txt_humor.setText(""+getEstadodeanimoCount());
            txt_medication.setText(""+getMedicationCount());
            txt_alimentacion.setText(""+getEatingCount());
            txt_movilidad.setText(""+getMovCount());
            txt_estadodeanimo.setText(""+getEstadodeanimoCount());
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


        movCount=0;
        eatingCount=0;
        fallCount=0;
        medicationCount=0;
        estadodeanimoCount=0;
        changeCount=0;

        Log.e("addnote","de nuevo al fragment, show la lista, tamaño: "+list.size());
        for (int j = 0; j < noteList.size(); j++)
        {

            String noteType = noteList.get(j).getNoteType().toString();
            switch (noteType){
                case "movility":

                     setMovCount(movCount+1);
                    break;
                case "eating":
                    setEatingCount(eatingCount+1);

                    break;
                case "fall":
                    setFallCount(fallCount+1);
                    break;
                case "medication":
                    setMedicationCount(medicationCount+1);

                    break;
                case "otro":

                    break;
                case "health":
                   setEstadodeanimoCount(estadodeanimoCount+1);
                    break;
                case "changebehaviour":
                    setChangeCount(changeCount+1);
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
