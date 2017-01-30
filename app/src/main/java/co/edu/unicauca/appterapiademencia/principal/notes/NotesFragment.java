package co.edu.unicauca.appterapiademencia.principal.notes;

import android.app.AlertDialog;
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

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

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
    private TextView txt_empty,txt_caida,txt_movilidad,txt_alimentacion,txt_humor,txt_estadodeanimo,txt_higiene,txt_orientacion,txt_memoria,txt_lenguaje,txt_medicacion;
    private Long idpatient;
    private List<Note> noteList;
    private Intent ir_reg;
    private List<Note> list = new ArrayList<Note>();

    int movCount=0;
    int eatingCount=0;
    int fallCount=0;
    int medicationCount=0;
    int estadoanimoCount=0;
    int higieneCount=0;
    int vestimentaCount=0;
    int memoryCount=0;
    int languageCount=0;
    int orientationCount=0;
    int personalidadCount=0;


    private MaterialDialog dialog;
    private AlertDialog alertdialog;


    public NotesFragment(){
        this.noteList = noteList;
        this.fallCount = fallCount;
        this.eatingCount=eatingCount;
        this.changeCount = changeCount;
        this.estadodeanimoCount = estadodeanimoCount;
        this.medicationCount = medicationCount;


        this.languageCount = languageCount;
        this.vestimentaCount = vestimentaCount;
        this.memoryCount = medicationCount;
        this.higieneCount = higieneCount;
        this.orientationCount = orientationCount;
        this.personalidadCount = personalidadCount;

    }


    public int getMemoryCount() {
        return memoryCount;
    }

    public void setMemoryCount(int memoryCount) {
        this.memoryCount = memoryCount;
    }

    public int getHigieneCount() {
        return higieneCount;
    }

    public void setHigieneCount(int higieneCount) {
        this.higieneCount = higieneCount;
    }

    public int getVestimentaCount() {
        return vestimentaCount;
    }

    public void setVestimentaCount(int vestimentaCount) {
        this.vestimentaCount = vestimentaCount;
    }

    public int getLanguageCount() {
        return languageCount;
    }

    public void setLanguageCount(int languageCount) {
        this.languageCount = languageCount;
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
        txt_higiene= (TextView) view.findViewById(R.id.count_higiene);
        txt_orientacion = (TextView) view.findViewById(R.id.count_orientacion);
        txt_medicacion = (TextView) view.findViewById(R.id.count_medicacion);
        txt_lenguaje = (TextView) view.findViewById(R.id.count_lenguaje);
        txt_memoria = (TextView) view.findViewById(R.id.count_memoria);

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

        adapter = new NoteAdapter(this.noteList, getActivity(),getContext());
        recycler.setAdapter(adapter);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("Notes","entro al onclick");
                Log.e("Calculo","Arranca a contar");
                addNote(idpatient);
            }
        });




    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        notesPresenterImplementation.onResume();
        list.clear();
        Bundle args = getArguments();
        idpatient=args.getLong("cedula");
        getNotes(idpatient);

        try {
            recycler.setHasFixedSize(true);
            LManager = new LinearLayoutManager(getActivity().getApplicationContext());
            recycler.setLayoutManager(LManager);
            adapter = new NoteAdapter(list, getActivity(),getContext());
            recycler.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            /*
            txt_caida.setText(""+getFallCount());
            txt_humor.setText(""+getEstadodeanimoCount());
            txt_higiene.setText(""+getMedicationCount());
            txt_alimentacion.setText(""+getEatingCount());
            txt_movilidad.setText(""+getMovCount());
            txt_estadodeanimo.setText(""+getEstadodeanimoCount());
            txt_lenguaje.setText(""+getLanguageCount());
            txt_medicacion.setText(""+getMedicationCount());
            txt_orientacion.setText(""+getLanguageCount());
            txt_memoria.setText(""+getMemoryCount());
            */

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
       notesPresenterImplementation.onResume();
        list.clear();
        Bundle args = getArguments();
        idpatient=args.getLong("cedula");
        getNotes(idpatient);

        try {
            recycler.setHasFixedSize(true);
            LManager = new LinearLayoutManager(getActivity().getApplicationContext());
            recycler.setLayoutManager(LManager);
            adapter = new NoteAdapter(list, getActivity(),getContext());
            recycler.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            //callListenerText();
        } catch (Exception e) {
            adapter.notifyDataSetChanged();
        }
        waitBundle();
    }

    private void waitBundle() {
        Long idnote;
        Bundle bundle = getActivity().getIntent().getExtras();
        if (bundle != null) {
            try {
                idnote = Long.parseLong(bundle.getString("idnote"));
                notesPresenterImplementation.getNote(idnote);

            } catch (Exception e) {
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        final MaterialDialog.Builder builder = new MaterialDialog.Builder(getContext());

        notesPresenterImplementation.onDestroy();
    }

    @Override
    public void onStop() {
        super.onStop();
        notesPresenterImplementation.onResume();


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
        higieneCount=0;

        languageCount=0;
        vestimentaCount=0;
        memoryCount=0;
        orientationCount=0;


        Log.e("addnote","de nuevo al fragment, show la lista, tamaño: "+list.size());
        for (int j = 0; j < noteList.size(); j++)
        {
            String noteType;

            try {
                noteType = noteList.get(j).getAmbito().toString();

            }
            catch (Exception e){
                noteType = "";
            }





            switch (noteType){
                case "movilidad":

                     setMovCount(movCount+1);
                    break;
                case "alimentacion":
                    setEatingCount(eatingCount+1);

                    break;
                case "caidas":
                    setFallCount(fallCount+1);
                    break;
                case "medicacion":
                    setMedicationCount(medicationCount+1);

                    break;
                case "higiene":
                    setMedicationCount(medicationCount+1);

                    break;
                case "lenguaje":
                    setLanguageCount(languageCount+1);
                    break;
                case "vestimenta":
                    setVestimentaCount(vestimentaCount+1);
                    break;
                case "memoria":
                    setMemoryCount(memoryCount+1);
                    break;
                case "animo":
                   setEstadodeanimoCount(estadodeanimoCount+1);
                    break;
                case "personalidad":
                    setChangeCount(changeCount+1);
                    break;
                case "orientacion":
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
    public void onPause() {
        super.onPause();

    }

    @Override
    public void addNote(Long idpatient) {
        //Intent ir_reg = new Intent(getContext(), AddNoteActivity.class);

        Intent ir_reg = new Intent(getContext(), AddNoteActivity.class);
        Log.e("idpatient recibido :",""+idpatient);
        ir_reg.putExtra("idpatient", idpatient);
        startActivity(ir_reg);
        //ir_reg.putExtra("idpatient", idpatient);
        //startActivity(ir_reg);
    }

    @Override
    public void getnotesCount(Long idpatient) {
        notesPresenterImplementation.getnotesCount(idpatient);
    }

    @Override
    public void showNotesCount(int[] notescount) {

    }
    public Boolean checkDialogOpen()
    {
        if(alertdialog!=null && alertdialog.isShowing())
        {
            return  false;
        }
        else
        {
            return true;
        }

    }

    @Override
    public void showNote(Note note) {

        Log.e("notesfragment","Va mostrar el materialdialog");
        //new MaterialDialog.Builder(getContext()).title(note.getAmbito().toUpperCase()).content("Descripción: "+note.getDescription()+"\nHora:"+note.getHour()+"\nFecha:"+note.getDate()).positiveText(R.string.dialog_succes_agree).show().dismiss();



            final MaterialDialog.Builder builder = new MaterialDialog.Builder(getContext());

            //builder.autoDismiss(true);

            builder.title(note.getAmbito().toUpperCase()).content("Descripción: "+note.getDescription()+"\nHora:"+note.getHour()+"\nFecha:"+note.getDate()).positiveText(R.string.dialog_succes_agree).show();;
            builder.onPositive(new MaterialDialog.SingleButtonCallback() {
                @Override
                public void onClick(MaterialDialog materialDialog, DialogAction dialogAction) {
                    //notesPresenterImplementation.register();
                    materialDialog.dismiss();


                }
            });
            MaterialDialog materialdialog = builder.build();
            materialdialog.show();








      /*

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setIcon(android.R.drawable.ic_dialog_alert).setTitle("Salir")
                .setMessage("Estás seguro?").setNegativeButton(android.R.string.cancel, null)//sin listener
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {//un listener que al pulsar, cierre la aplicacion
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        alertdialog.dismiss();


                    }
                });

        alertdialog = builder.create();
        alertdialog.show();
        */





    }

    @Override
    public void onItemClicked(Long position) {

    }


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



}
