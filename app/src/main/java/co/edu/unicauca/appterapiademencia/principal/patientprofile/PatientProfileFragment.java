package co.edu.unicauca.appterapiademencia.principal.patientprofile;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import co.edu.unicauca.appterapiademencia.R;
import co.edu.unicauca.appterapiademencia.adapters.PatientListAdapter;
import co.edu.unicauca.appterapiademencia.domain.Patient;
import co.edu.unicauca.appterapiademencia.principal.patientlist.PatientListPresenterImplementation;
import co.edu.unicauca.appterapiademencia.principal.patientprofile.PatientProfileView;
import co.edu.unicauca.appterapiademencia.util.CircleTransform;

/**
 * Created by SEBAS on 07/11/2016.
 */

public class PatientProfileFragment extends Fragment implements PatientProfileView {

    private Long idpatient;
    private PatientProfilePresenter patientProfilePresenter;
    private ImageView imageProfile;
    private TextView txtName;
    private TextView txtAge;
    private String name;
    private String birthday;
    private String photopath;
    private int imageSize;


    public PatientProfileFragment(){
        this.idpatient = idpatient;

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_profile_information,container,false);
        imageProfile = (ImageView) rootView.findViewById(R.id.img_patient_profile);
        txtName = (TextView) rootView.findViewById(R.id.name_patient_profile);
        txtAge = (TextView) rootView.findViewById(R.id.age_patient_profile);


        return rootView;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        patientProfilePresenter = new PatientProfilePresenterImplementation(this);
        patientProfilePresenter.onCreate();
        Bundle args = getArguments();
        idpatient=args.getLong("cedula");
        Log.d("Vista profile","Cedula: "+idpatient);
        getPatientData(idpatient);





    }

    @Override
    public void onResume() {
        super.onResume();
        Bundle args = getArguments();
        idpatient=args.getLong("cedula");
        getPatientData(idpatient);
    }

    @Override
    public void showPatientData(Patient patient) {
       imageSize = getActivity().getResources().getDimensionPixelSize(R.dimen.img_patient_profile_size);
        name = patient.getName();
        birthday = patient.getBirthday();
        //photopath = patient.getPhotopath();

        Log.d("En la vista show","nombre "+name+"Path "+photopath);

        txtName.setText(name);
        txtAge.setText(birthday);

          /*
        try {

            Picasso.with(this.getActivity()).load(getActivity().getDatabasePath(photopath)).resize(imageSize, imageSize).transform(new CircleTransform()).into(imageProfile);
        } catch (Exception e) {

            Picasso.with(this.getActivity()).load(Uri.parse(PatientListAdapter.fotodefault)).resize(imageSize, imageSize).transform(new CircleTransform()).into(imageProfile);
        }
          */

    }

    @Override
    public void changePhoto() {

    }

    @Override
    public void editPatientData(Long id) {

    }

    @Override
    public void getPatientData(Long id) {
        Log.d("Vista metodo getData","id: "+id);
        patientProfilePresenter.getPatientData(id);
    }
}
