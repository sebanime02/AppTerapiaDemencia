package co.edu.unicauca.appterapiademencia.principal.patientlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;
import java.util.List;

import co.edu.unicauca.appterapiademencia.R;
import co.edu.unicauca.appterapiademencia.adapters.PatientListAdapter;
import co.edu.unicauca.appterapiademencia.domain.Patient;
import co.edu.unicauca.appterapiademencia.principal.tips.AddTipActivity;

/**
 * Created by ENF on 25/10/2016.
 */

public class PatientListFragment extends Fragment implements PatientListView {

    private PatientListPresenter patientListPresenter;
    private FloatingActionButton floatingActionButton;
    private RecyclerView recycler;
    private PatientListAdapter adapter;
    private RecyclerView.Adapter newadapter;
    private RecyclerView.LayoutManager LManager;
    private List<Patient> list = new ArrayList<Patient>();
    private EditText searchBox;
    private ImageButton imgBtnHelpListPatients;
    private List<Patient> patientList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View rootView = inflater.inflate(R.layout.fragment_listpatients, container, false);
        floatingActionButton = (FloatingActionButton) rootView.findViewById(R.id.add_patient);
        recycler = (RecyclerView) rootView.findViewById(R.id.reciclador);
        searchBox = (EditText) rootView.findViewById(R.id.search_box);
        imgBtnHelpListPatients = (ImageButton) rootView.findViewById(R.id.btn_instructions_listpatients);


        recycler.setHasFixedSize(true);
        LManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recycler.setLayoutManager(LManager);
        getPatients();
        adapter = new PatientListAdapter(list, getActivity());
        recycler.setAdapter(adapter);

        registerForContextMenu(recycler);
        callListenerText();



        imgBtnHelpListPatients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInstructions(view);
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPatient();
            }
        });
        return rootView;



    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        patientListPresenter = new PatientListPresenterImplementation(this);
        patientListPresenter.onCreate();

    }

    @Override
    public void onResume()
    {
        super.onResume();
        list.clear();
        getPatients();

        try {
            recycler.setHasFixedSize(true);
            LManager = new LinearLayoutManager(getActivity().getApplicationContext());
            recycler.setLayoutManager(LManager);
            adapter = new PatientListAdapter(list, getActivity());
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
    public void addPatient() {
        startActivity(new Intent(getActivity(), AddPatientActivity.class));
    }

    @Override
    public void showPatients(List<Patient> patientList) {

        Log.e("patient", "" + patientList.size());
        for (int j = 0; j < patientList.size(); j++) {
            list.add(patientList.get(j));
        }


    }

    @Override
    public void navigateToDetail(Long identity) {
        Log.e("Volvio a la vista","Le carga la cedula:"+identity);
        Intent intent=new Intent(getActivity(),PatientProfileActivity.class);
        intent.putExtra("cedula",identity);
        startActivity(intent);

    }

    @Override
    public void openMecTest(Long identity) {

    }

    @Override
    public void navigateToExercise(Long identity) {

    }


    private void callListenerText() {

        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                s = s.toString().toLowerCase();
                final List<Patient> filteredList = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    final String text = list.get(i).getName().toLowerCase();

                    if (text.contains(s)) {
                        filteredList.add(list.get(i));
                    }
                }


                recycler.setLayoutManager(new LinearLayoutManager(getContext()));

                adapter = new PatientListAdapter(filteredList, getActivity());
                recycler.setAdapter(adapter);
                adapter.notifyDataSetChanged();


            }

            @Override
            public void afterTextChanged(Editable s) {


            }

        });

    }


    @Override
    public void getPatients() {
        patientListPresenter.getPatient();
    }

    @Override
    public void showInstructions(View view)
    {
        new MaterialDialog.Builder(view.getContext()).title("Manejo de Pacientes").content(R.string.txt_info_listpatients).positiveText("Bueno").icon(getResources().getDrawable(R.drawable.ic_action_action_help)).show();

    }

    public void navigateToAddPatient() {
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater menuinflater = getActivity().getMenuInflater();
        menuinflater.inflate(R.menu.menu_longclick_patient, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {

            case R.id.context_menu_long_ver:

                break;
            case R.id.context_menu_long_mec:

                break;
            case R.id.context_menu_long_ec:

                break;
        }
        return false;
    }
}
