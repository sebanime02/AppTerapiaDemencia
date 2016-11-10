package co.edu.unicauca.appterapiademencia.principal.patientprofile;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.greenrobot.greendao.query.QueryBuilder;

import co.edu.unicauca.appterapiademencia.R;
import co.edu.unicauca.appterapiademencia.adapters.PatientListAdapter;
import co.edu.unicauca.appterapiademencia.domain.Patient;
import co.edu.unicauca.appterapiademencia.principal.patientlist.AddPatient2Activity;
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
    private TextView txtIdentity;

    private String name;
    private String birthday;
    private String photopath;
    private String eps,sindromes,antecedentes,observaciones,vision,escritura,dibujo;
    private int visionState,escrituraState,dibujoState;
    private TextView txtEps,txtSindromes,txtAntecedentes,txtObservaciones,txtVision,txtEscritura,txtDibujo;
    private Long identity;
    private int imageSize;
    private View rootView;
    private RecyclerView recycler;
    private RecyclerView.LayoutManager LManager;
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

        //setRootView(rootView);
        return rootView;

    }



    @Override
    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageProfile = (ImageView) view.findViewById(R.id.img_patient_perfil);
        txtName = (TextView) view.findViewById(R.id.name_patient_perfil);
        txtAge = (TextView) view.findViewById(R.id.age_patient_perfil);

        txtIdentity = (TextView) view.findViewById(R.id.identity_patient_perfil);
        txtEps = (TextView) view.findViewById(R.id.txt_eps);
        txtAntecedentes = (TextView) view.findViewById(R.id.txt_antecedentes);
        txtObservaciones = (TextView) view.findViewById(R.id.txt_observaciones);
        txtSindromes = (TextView) view.findViewById(R.id.txt_sindromes);
        txtVision = (TextView) view.findViewById(R.id.txt_vision);
        txtDibujo = (TextView) view.findViewById(R.id.txt_dibujo);
        txtEscritura= (TextView) view.findViewById(R.id.txt_escritura);


        Bundle args = getArguments();
        idpatient=args.getLong("cedula");
        Log.d("Vista profile","Cedula: "+idpatient);

        getPatientData(idpatient);
        txtName.setText(this.name);
        txtAge.setText("Nació en: "+this.birthday);
        txtIdentity.setText("  Cédula: "+this.identity);

        int imageSize = view.getResources().getDimensionPixelSize(R.dimen.img_patient_profile_size);

        try {

            Picasso.with(this.getActivity()).load(getActivity().getDatabasePath(this.photopath)).resize(imageSize, imageSize).transform(new CircleTransform()).into(imageProfile);
        } catch (Exception e) {

            Picasso.with(this.getActivity()).load(Uri.parse(PatientListAdapter.fotodefault)).resize(imageSize, imageSize).transform(new CircleTransform()).into(imageProfile);
        }


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
        txtName.setText(this.name);
        txtAge.setText("Nació en: "+this.birthday);
        txtIdentity.setText("  Cédula: "+this.identity);
        txtEps.setText(this.eps);
        txtAntecedentes.setText(this.antecedentes);
        txtSindromes.setText(this.sindromes);
        txtAntecedentes.setText(this.antecedentes);
        txtVision.setText(this.vision);
        txtEscritura.setText(this.escritura);
        txtDibujo.setText(this.dibujo);



        int imageSize = getResources().getDimensionPixelSize(R.dimen.img_patient_profile_size);

        try {

            Picasso.with(this.getActivity()).load(getActivity().getDatabasePath(this.photopath)).resize(imageSize, imageSize).transform(new CircleTransform()).into(imageProfile);
        } catch (Exception e) {

            Picasso.with(this.getActivity()).load(Uri.parse(PatientListAdapter.fotodefault)).resize(imageSize, imageSize).transform(new CircleTransform()).into(imageProfile);
        }
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
        this.identity= patient.getIdentity();
        this.eps = patient.getEps();
        this.antecedentes= patient.getAntecedents();
        this.sindromes = patient.getSyndromes();
        this.observaciones = patient.getObservations();
        this.visionState = patient.getVisionlimitation();
        this.escrituraState = patient.getWritinglimitation();
        this.dibujoState = patient.getDrawinglimitation();

        AddPatient2Activity spinners = new AddPatient2Activity();

        if (visionState==0) {
            vision=spinners.vision[0];

        }
        if (visionState==1) {
            vision = spinners.vision[1];
        }
        if (visionState==2) {

            vision=spinners.vision[2];
        }

        if (escrituraState==0) {
            escritura=spinners.escritura[0];
        }
        if (escrituraState==1) {
            escritura=spinners.escritura[1];
        }

        if (dibujoState==0) {
            dibujo=spinners.dibujo[0];
        }
        if (dibujoState==0) {
            dibujo=spinners.dibujo[1];
        }



        Log.d("En la vista show:","nombre "+name+" birthday"+birthday+" Path "+photopath);

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
