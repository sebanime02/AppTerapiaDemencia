package co.edu.unicauca.appterapiademencia.principal.patientlist;

import android.os.Bundle;
import android.support.annotation.Nullable;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_listpatients, container, false);


        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
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
