package co.edu.unicauca.appterapiademencia.principal.patientlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import co.edu.unicauca.appterapiademencia.R;
import co.edu.unicauca.appterapiademencia.adapters.PatientListAdapter;
import co.edu.unicauca.appterapiademencia.domain.Patient;

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
    private List<Patient> filteredList;
    private EditText searchBox;
    private List<Patient> updatelist;
    private List<Patient> patientList;

    public PatientListFragment(){
        this.updatelist = updatelist;
        this.patientList = patientList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_listpatients, container, false);
        floatingActionButton= (FloatingActionButton) rootView.findViewById(R.id.add_patient);
        recycler = (RecyclerView) rootView.findViewById(R.id.reciclador);
        searchBox = (EditText)rootView.findViewById(R.id.search_box);
        getPatients();
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
    public void onResume() {
        super.onResume();

        getPatients();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void addPatient() {
        startActivity(new Intent(getActivity(),AddPatientActivity.class));
    }

    @Override
    public void showPatients(List<Patient> patientList) {

        try {
            this.patientList = patientList;
            filteredList = new ArrayList<Patient>();
            filteredList.addAll(patientList);
            recycler.setHasFixedSize(true);



            LManager = new LinearLayoutManager(getContext());
            recycler.setLayoutManager(LManager);

            adapter = new PatientListAdapter(patientList,filteredList, getActivity());
            recycler.setAdapter(adapter);

            searchBox.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    adapter.getFilter().filter(s.toString());

                    getResults();
                    //new PatientListAdapter(patientList,filteredList, getActivity()).getFilter().filter(s.toString());


                }
                @Override
                public void afterTextChanged(Editable s) {
                }
            });

        }catch (Exception e){
            adapter.notifyDataSetChanged();
        }








    }
    public void getResults(){
        adapter = new PatientListAdapter(patientList,this.updatelist, getActivity());
        recycler.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void setResults(List<Patient> updatelist){
        this.updatelist = updatelist;
    }
    @Override
    public void navigateToDetail(Patient patient) {

    }


    @Override
    public void navigateToExercise(Patient patient) {

    }

    @Override
    public void getPatients() {
        patientListPresenter.getPatient();
    }
    public void navigateToAddPatient(){

    }




}
