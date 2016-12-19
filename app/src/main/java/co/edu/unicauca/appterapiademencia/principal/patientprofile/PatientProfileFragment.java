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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.squareup.picasso.Picasso;

import org.greenrobot.greendao.query.QueryBuilder;

import co.edu.unicauca.appterapiademencia.R;
import co.edu.unicauca.appterapiademencia.adapters.PatientListAdapter;
import co.edu.unicauca.appterapiademencia.domain.Patient;
import co.edu.unicauca.appterapiademencia.domain.dao.GreenDaoHelper;
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
    private String sexo,eps,sindromes,antecedentes,observaciones,vision,escritura,dibujo;
    private int visionState,escrituraState,dibujoState;
    private TextView txtSexo,txtEps,txtSindromes,txtAntecedentes,txtObservaciones,txtVision,txtEscritura,txtDibujo;
    private TextView txtPuntajeBlessed,txtComentarioBlessed,txtPuntajeFast,txtComentarioFast,txtPuntajeDownton,txtComentarioDownton,txtPuntajeLawton,txtComentarioLawton;
    private LinearLayout containerBlessed;
    private Long identity;
    private int imageSize;
    private View rootView;
    private RecyclerView recycler;
    private RecyclerView.LayoutManager LManager;
    private QueryBuilder queryBuildergeneral;
    private double blessedScore;
    private GreenDaoHelper daoHelper;
    private Double blessedCount;
    private String blessedComentario;
    private String blessedColor;
    private String fastCount;
    private String lawtonCount;
    private String comentarioLawton;
    private Long idsistema;
    private String etapa,caracteristicas,edadMental,gds,mec;



    public PatientProfileFragment(){

        daoHelper = GreenDaoHelper.getInstance();



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
        txtSexo = (TextView) view.findViewById(R.id.sexo_patient_perfil);
        txtAntecedentes = (TextView) view.findViewById(R.id.txt_antecedentes);
        txtObservaciones = (TextView) view.findViewById(R.id.txt_observaciones);
        txtSindromes = (TextView) view.findViewById(R.id.txt_sindromes);
        txtVision = (TextView) view.findViewById(R.id.txt_vision);
        txtDibujo = (TextView) view.findViewById(R.id.txt_dibujo);
        txtEscritura= (TextView) view.findViewById(R.id.txt_escritura);
        txtPuntajeBlessed = (TextView) view.findViewById(R.id.puntajeBlessed);
        txtComentarioBlessed = (TextView) view.findViewById(R.id.demenciaBlessed);
        containerBlessed = (LinearLayout) view.findViewById(R.id.containerBlessed);
        txtPuntajeFast = (TextView) view.findViewById(R.id.puntajeFAST);
        txtComentarioFast = (TextView) view.findViewById(R.id.demenciaFAST);
        txtPuntajeLawton = (TextView) view.findViewById(R.id.puntajeLawton);
        txtComentarioLawton = (TextView) view.findViewById(R.id.comentarioLawton);
        txtPuntajeDownton = (TextView) view.findViewById(R.id.puntajeDowntown);
        txtComentarioDownton = (TextView) view.findViewById(R.id.comentarioDowntown);




        Bundle args = getArguments();
        idpatient=args.getLong("cedula");
        try {
            Patient patient = daoHelper.getPatientInformationUsingCedula(idpatient);
            idsistema = patient.getId();
        }
        catch (Exception e){}
        Log.d("Vista profile","Cedula: "+idpatient);

        try
        {
            if(args.getBoolean("carerIndicator"))
            {
                Log.e("carerindicator",""+args.getBoolean("carerIndicator"));
                new MaterialDialog.Builder(getActivity().getApplicationContext()).title(R.string.title_note_added).content(R.string.content_note_added).positiveText(R.string.dialog_succes_agree).show();

            }
        }catch (NullPointerException e){

        }

        getPatientData(idpatient);
        getBlessedScore(idsistema);
        getFastScore(idsistema);
        getLawtonScore(idsistema);
        txtName.setText(this.name);
        txtAge.setText("Nació en: "+this.birthday);
        txtIdentity.setText("  Cédula: "+this.identity);
        txtPuntajeBlessed.setText(this.blessedCount+"");
        txtSexo.setText(" Sexo: "+this.sexo);
        txtComentarioBlessed.setText(this.blessedComentario);
        txtPuntajeFast.setText(this.fastCount+"");
        txtComentarioFast.setText("Etapa: "+etapa+"\nCaracteristica: "+caracteristicas+" \nEdad Mental: "+edadMental+" \nGDS: "+gds+" \nMEC: "+mec);
        txtPuntajeLawton.setText(this.lawtonCount);
        txtComentarioLawton.setText(this.comentarioLawton);


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
        /*
        try {
            Patient patient = daoHelper.getPatientInformationUsingCedula(idpatient);
            idsistema = patient.getId();
        }
        catch (Exception e){}
        */
        //getBlessedScore(idsistema);

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
        try {
            Patient patient = daoHelper.getPatientInformationUsingCedula(idpatient);
            idsistema = patient.getId();
        }
        catch (Exception e){}
        getBlessedScore(idsistema);
        getFastScore(idsistema);
        getLawtonScore(idsistema);
        txtName.setText(this.name);
        txtAge.setText("Nació en: "+this.birthday);
        txtIdentity.setText("  Cédula: "+this.identity);
        txtSexo.setText(" Sexo: "+this.sexo);
        txtEps.setText(this.eps);
        txtAntecedentes.setText(this.antecedentes);
        txtSindromes.setText(this.sindromes);
        txtObservaciones.setText(this.observaciones);
        txtVision.setText(this.vision);
        txtEscritura.setText(this.escritura);
        txtDibujo.setText(this.dibujo);
        txtPuntajeBlessed.setText(this.blessedCount+"");
        txtComentarioBlessed.setText(this.blessedComentario);
        txtPuntajeFast.setText(this.fastCount+"");
        txtPuntajeLawton.setText("De 0 a 8, donde 0 es el máximo\nvalor de dependencia en AIVD\nPuntaje:"+this.lawtonCount);
        txtComentarioLawton.setText(this.comentarioLawton);

        txtComentarioFast.setText("Etapa: "+etapa+"\nCaracteristica: "+caracteristicas+" \nEdad Mental: "+edadMental+" \nGDS: "+gds+" \nMEC: "+mec);




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
        this.sexo = patient.getSex();
        this.eps = patient.getEps();
        this.antecedentes= patient.getAntecedents();
        this.sindromes = patient.getSyndromes();
        this.observaciones = patient.getObservations();
        this.visionState = patient.getVisionlimitation();
        this.escrituraState = patient.getWritinglimitation();
        this.dibujoState = patient.getDrawinglimitation();

        Log.e("patientprofile","Sexo :"+sexo);
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
        if (dibujoState==1) {
            dibujo=spinners.dibujo[1];
        }
        if(this.photopath.equals("") || this.photopath.equals(null)){
            this.photopath= PatientListAdapter.fotodefault;
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

    @Override
    public void getBlessedScore(Long id) {

        patientProfilePresenter.getBlessedScore(id);

    }

    @Override
    public void showBlessedScore(Double score,String comentario,String color)
    {

        this.blessedCount = score;
        this.blessedComentario = comentario;
        this.blessedColor = color;

    }

    @Override
    public void getFastScore(Long id) {
        patientProfilePresenter.getFastScore(id);
    }

    @Override
    public void showFastScore(String score,String etapa,String caracteristica,String edadMental,String mec,String gds) {
        this.fastCount = score;
        this.etapa = etapa;
        this.caracteristicas = caracteristica;
        this.edadMental = edadMental;
        this.mec = mec;
        this.gds = gds;
    }

    @Override
    public void getDowntonScore(Long id) {
            patientProfilePresenter.getDowntonScore(id);
    }

    @Override
    public void showDowntonScore(int score, String comentario) {

    }

    @Override
    public void getLawtonScore(Long id) {
        patientProfilePresenter.getLawtonScore(id);
    }

    @Override
    public void showLawtonScore(int score, String comentario) {

        this.lawtonCount = score+"";
        this.comentarioLawton = comentario;

    }


    public void showBlessedError()
    {

    }

}
