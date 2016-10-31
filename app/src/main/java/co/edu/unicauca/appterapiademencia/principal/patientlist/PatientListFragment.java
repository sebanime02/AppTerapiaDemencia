package co.edu.unicauca.appterapiademencia.principal.patientlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import co.edu.unicauca.appterapiademencia.R;
import co.edu.unicauca.appterapiademencia.domain.Patient;
import co.edu.unicauca.appterapiademencia.principal.PrincipalListPresenter;

/**
 * Created by ENF on 25/10/2016.
 */

public class PatientListFragment extends Fragment implements PatientListView {

    private PrincipalListPresenter principalPresenter;
    private FloatingActionButton floatingActionButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_listpatients, container, false);
        floatingActionButton= (FloatingActionButton) rootView.findViewById(R.id.add_patient);
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
       /* principalPresenter = new PrincipalListPresenterImplementation(this);
        principalPresenter.OnCreate(); */


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
    public void addPatient() {
        startActivity(new Intent(getActivity(),AddPatientActivity.class));
    }

    @Override
    public void showPatients(List<Patient> patientList) {

    }

    @Override
    public void navigateToDetail(Patient patient) {

    }


    @Override
    public void navigateToExercise(Patient patient) {

    }

    @Override
    public void getPatients() {

    }
    public void navigateToAddPatient(){

    }




}
