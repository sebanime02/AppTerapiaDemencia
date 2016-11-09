package co.edu.unicauca.appterapiademencia.principal.patientprofile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.greenrobot.greendao.query.QueryBuilder;

import co.edu.unicauca.appterapiademencia.R;
import co.edu.unicauca.appterapiademencia.domain.Patient;

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
    private View rootView;
    private  QueryBuilder queryBuildergeneral;


    public PatientProfileFragment(){
        /*
        this.idpatient = idpatient;
        this.rootView = rootView;
        this.queryBuildergeneral  = GreenDaoHelper.getPatientDao().queryBuilder();
        this.imageProfile = imageProfile;
        */
        this.txtAge = txtAge;
        this.txtName = txtName;



    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_profile_information,container,false);

        setRootView(rootView);
        return rootView;

    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageProfile = (ImageView) view.findViewById(R.id.img_patient_perfil);
        txtName = (TextView) view.findViewById(R.id.name_patient_perfil);
        txtAge = (TextView) view.findViewById(R.id.age_patient_perfil);
        Bundle args = getArguments();
        idpatient=args.getLong("cedula");
        Log.d("Vista profile","Cedula: "+idpatient);

        getPatientData(idpatient);
        txtName.setText(this.name);
        txtAge.setText(this.birthday);
        int imageSize2 = view.getResources().getDimensionPixelSize(R.dimen.img_patient_profile_size);
         /*
        try {

            Picasso.with(this.getActivity()).load(getActivity().getDatabasePath(this.photopath)).resize(imageSize, imageSize).transform(new CircleTransform()).into(imageProfile);
        } catch (Exception e) {

            Picasso.with(this.getActivity()).load(Uri.parse(PatientListAdapter.fotodefault)).resize(imageSize, imageSize).transform(new CircleTransform()).into(imageProfile);
        }
*/

    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        patientProfilePresenter = new PatientProfilePresenterImplementation(this);
        patientProfilePresenter.onCreate();


        Bundle args = getArguments();
        idpatient=args.getLong("cedula");
        Log.d("Vista profile","Cedula: "+idpatient);

        getPatientData(idpatient);
    }
    public View getRootView(){
        return this.rootView;
    }




    public void setRootView(View rootView){
        this.rootView = rootView;
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

        /*
        ImageView imageProfile = (ImageView) getRootView().findViewById(R.id.img_patient_perfil);
        TextView txtName = (TextView) getRootView().findViewById(R.id.name_patient_perfil);
        TextView txtAge = (TextView) getRootView().findViewById(R.id.age_patient_perfil);

         */


        this.name = patient.getName().toString();
        this.birthday = patient.getBirthday().toString();
        this.photopath = patient.getPhotopath();


        Log.d("En la vista show:","nombre "+name+" birthday"+birthday+" Path "+photopath);



           /*
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

        /*List<Patient> patientList = queryBuildergeneral.where(PatientDao.Properties.Identity.eq(id)).limit(1).list();
        Log.d("Repositorio","devolvio el nombre: "+patientList.get(0).getName());
         showPatientData(patientList.get(0)); */
    }
}
