package co.edu.unicauca.appterapiademencia.principal.patientlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager LManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_listpatients, container, false);
        floatingActionButton= (FloatingActionButton) rootView.findViewById(R.id.add_patient);
        recycler = (RecyclerView) rootView.findViewById(R.id.reciclador);
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
            recycler.setHasFixedSize(true);
            LManager = new LinearLayoutManager(getContext());
            recycler.setLayoutManager(LManager);

            adapter = new PatientListAdapter(patientList, getActivity());
            recycler.setAdapter(adapter);
        }catch (Exception e){

        }
          adapter.notifyDataSetChanged();







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
