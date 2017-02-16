package co.edu.unicauca.appterapiademencia.principal.patientprofile;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.squareup.picasso.Picasso;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import co.edu.unicauca.appterapiademencia.R;
import co.edu.unicauca.appterapiademencia.adapters.PatientListAdapter;
import co.edu.unicauca.appterapiademencia.domain.Patient;
import co.edu.unicauca.appterapiademencia.domain.dao.GreenDaoHelper;
import co.edu.unicauca.appterapiademencia.items.BlessedScoreAverage;
import co.edu.unicauca.appterapiademencia.principal.patientlist.AddPatient2Activity;
import co.edu.unicauca.appterapiademencia.util.CircleTransform;

/**
 * Created by SEBAS on 07/11/2016.
 */

public class PatientProfileFragment extends Fragment implements PatientProfileView{

    private Long idpatient;
    private PatientProfilePresenter patientProfilePresenter;

    private ImageView imageProfile;
    private TextView txtName;
    private TextView txtAge;
    private TextView txtIdentity;

    private String name;
    private String birthday;
    private String photopath;
    private String sexo,escolaridad,institucionalizado,eps,sindromes,antecedentes,observaciones,vision,escritura,dibujo;
    private int visionState,escrituraState,dibujoState;
    private TextView txtSexo,txtEscolaridad,txtInstitucionalizado,txtEps,txtSindromes,txtAntecedentes,txtObservaciones,txtVision,txtEscritura,txtDibujo;
    private TextView txtPuntajeBlessed,txtPuntajeMMSE,txtDateMMSE,txtComentarioBlessed,txtPuntajeFast,txtComentarioFast,txtPuntajeDownton,txtComentarioDownton,txtPuntajeLawton,txtComentarioLawton;
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
    private String mmseCount;
    private String comentarioLawton;
    private String comentarioDownton;
    private String puntajeDownton;
    private String puntajeMMSE;
    private String fechaMMSE;
    private Long idsistema;
    private String etapa,caracteristicas,edadMental,gds,mec;
    private BarChart blessedChart;
    private Calendar calendar;
    private int year,month,day;
    private Spinner spiBlessed;
    private Button btngoMoreStatistics,btnMoreBlessed,btnMoreLawton,btnMoreDownton,btnMoreFast,btnMoreMinimenta;
    private static final String[]  estadisticablessed = {"Últimos Meses","Últimos Años"};
    private Button btnMoreStatistics;
    private LinearLayout linearMinimental;


    public PatientProfileFragment(){

        daoHelper = GreenDaoHelper.getInstance();



    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_profile_information,container,false);
        Log.d("Time count","Activity to activity termino de contar");

        //setRootView(rootView);
        return rootView;

    }



    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageProfile = (ImageView) view.findViewById(R.id.img_patient_perfil);
        txtName = (TextView) view.findViewById(R.id.name_patient_perfil);
        txtAge = (TextView) view.findViewById(R.id.age_patient_perfil);
        txtIdentity = (TextView) view.findViewById(R.id.identity_patient_perfil);
        txtEps = (TextView) view.findViewById(R.id.txt_eps);
        txtSexo = (TextView) view.findViewById(R.id.sexo_patient_perfil);
        txtEscolaridad = (TextView) view.findViewById(R.id.txt_escolaridad);
        txtInstitucionalizado = (TextView) view.findViewById(R.id.txt_institucionalizado);
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
        txtPuntajeMMSE = (TextView) view.findViewById(R.id.mec_value);
        txtDateMMSE = (TextView) view.findViewById(R.id.mec_date);
         blessedChart = (BarChart) view.findViewById(R.id.blessedChart);
        spiBlessed = (Spinner) view.findViewById(R.id.spi_blessed);
        btngoMoreStatistics = (Button) view.findViewById(R.id.btnMoreStatistics);
        btnMoreBlessed = (Button) view.findViewById(R.id.btn_more_blessed);
        btnMoreDownton = (Button) view.findViewById(R.id.btn_more_downton);
        btnMoreFast = (Button) view.findViewById(R.id.btn_more_fast);
        btnMoreLawton = (Button) view.findViewById(R.id.btn_more_lawton);
        btnMoreMinimenta = (Button) view.findViewById(R.id.btn_more_minimental);
        linearMinimental = (LinearLayout) view.findViewById(R.id.minimentalcontainer);





        Bundle args = getArguments();
        idpatient=args.getLong("cedula");
        try {
            Patient patient = daoHelper.getPatientInformationUsingCedula(idpatient);
            idsistema = patient.getId();
        }
        catch (Exception e){}
        Log.d("Vista profile","Cedula: "+idpatient);


        btngoMoreStatistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToStatistics(idsistema);
            }
        });



        try
        {
            if(args.getBoolean("carerIndicator"))
            {
                Log.e("carerindicator",""+args.getBoolean("carerIndicator"));
                new MaterialDialog.Builder(getActivity().getApplicationContext()).title(R.string.title_note_added).content(R.string.content_note_added).positiveText(R.string.dialog_succes_agree).show();

            }
        }catch (NullPointerException e){

        }

        try {

        }catch (Exception e)
        {
            getPatientData(idpatient);
            getBlessedScore(idsistema);
            getFastScore(idsistema);
            getLawtonScore(idsistema);
            getMMSEScore(idsistema);
        }



        new Thread(new Runnable() {

            @Override
            public void run() {
                try
                {
                    patientProfilePresenter.getBlessedData(idsistema,1);

                }catch (Exception e)
                {
                    Log.e("Error blessed","Atrapo la excepcion en blessed data");

                }
            }
        }).start();



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
        txtPuntajeDownton.setText(this.puntajeDownton);
        txtComentarioDownton.setText(this.comentarioDownton);


        int imageSize = view.getResources().getDimensionPixelSize(R.dimen.img_patient_profile_size);

        try {

            Picasso.with(this.getActivity()).load(getActivity().getDatabasePath(this.photopath)).resize(imageSize, imageSize).transform(new CircleTransform()).into(imageProfile);
        } catch (Exception e) {

            Picasso.with(this.getActivity()).load(Uri.parse(PatientListAdapter.fotodefault)).resize(imageSize, imageSize).transform(new CircleTransform()).into(imageProfile);
        }




        btnMoreLawton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                showDetailsTest(view2,"lawton");
            }
        });
        btnMoreMinimenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                showDetailsTest(view2,"minimental");
            }
        });

        btnMoreDownton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                showDetailsTest(view2,"downton");
            }
        });
        btnMoreFast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                Log.d("Time count","Empieza a contar vista superpuesta");

                showDetailsTest(view2,"fast");
            }
        });
        btnMoreBlessed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                showDetailsTest(view2,"blessed");
            }
        });





    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        Log.e("Pasa la app","Arranca el fragment");
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

        try
        {
            getBlessedScore(idsistema);
            getFastScore(idsistema);
            getLawtonScore(idsistema);
            getDowntonScore(idsistema);
            getMMSEScore(idsistema);
        }catch (Exception e)
        {

        }


        //patientProfilePresenter.getBlessedData(idsistema);


        /*
        new Thread(new Runnable() {

            @Override
            public void run() {
                try
                {
                    patientProfilePresenter.getBlessedData(idsistema,0);

                }catch (Exception e)
                {
                    Log.e("Error blessed","Atrapo la excepcion en blessed data");

                }
            }
        }).start();
        */


        txtName.setText(this.name);
        txtAge.setText("Nació en: "+this.birthday);
        txtIdentity.setText("  Cédula: "+this.identity);
        txtSexo.setText(" Sexo: "+this.sexo);
        txtEscolaridad.setText(this.escolaridad);
        txtInstitucionalizado.setText(this.institucionalizado);
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
        txtPuntajeLawton.setText(this.lawtonCount);
        txtComentarioLawton.setText(this.comentarioLawton);
        txtPuntajeDownton.setText(this.puntajeDownton);
        txtComentarioDownton.setText(this.comentarioDownton);



        if(this.puntajeMMSE=="")
        {
            txtPuntajeMMSE.setText("Sin Valores MMSE");
            txtPuntajeMMSE.setTextColor(getResources().getColor(R.color.gray));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
            //linearMinimental.setLayoutParams(params);
            txtPuntajeMMSE.setLayoutParams(params);
            txtDateMMSE.setText("");

        }else
        {
            txtPuntajeMMSE.setText(this.puntajeMMSE);
            txtDateMMSE.setText("Fecha :"+this.fechaMMSE);
        }


        txtComentarioFast.setText("Etapa: "+etapa+"\nCaracteristica: "+caracteristicas+" \nEdad Mental: "+edadMental+" \nGDS: "+gds+" \nMEC: "+mec);


        Bundle bundle;
        try {
            bundle = getActivity().getIntent().getExtras();
            if(bundle.getBoolean("guardar"))
            {
                java.util.Date date = new java.util.Date();

                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);

                Log.e("fecha guardada","Año "+year+" mes"+month+" dia"+day);


                daoHelper.insertHistoricScale(idsistema,"Blessed",this.blessedCount,year,month,day);
                daoHelper.insertHistoricScale(idsistema,"Lawton",Double.parseDouble(this.lawtonCount),year,month,day);

                daoHelper.insertHistoricScale(idsistema,"FAST",Double.parseDouble(this.gds),year,month,day);
                daoHelper.insertHistoricScale(idsistema,"Downton",Double.parseDouble(this.puntajeDownton),year,month,day);

            }

        }catch (Exception e){}

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


        if(patient.getInstitutional())
        {
            institucionalizado = "Institucionalizado";
        }
        else
        {
            institucionalizado = "No es institucionalizado";
        }

       if(patient.getScolarity())
       {
           escolaridad = "Baja Escolaridad";
       }
        else
       {
           escolaridad ="Escolarizado";
       }



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
        this.puntajeDownton = score+"";
        this.comentarioDownton = comentario;

    }

    @Override
    public void getLawtonScore(Long id) {
        patientProfilePresenter.getLawtonScore(id);
    }

    @Override
    public void getMMSEScore(Long id) {
        patientProfilePresenter.getMMSEScore(id);
    }

    @Override
    public void showMMSEScore(String value,String date) {
        this.puntajeMMSE = value;
        this.fechaMMSE = date;
    }

    @Override
    public void showLawtonScore(int score, String comentario) {

        this.lawtonCount = score+"";
        this.comentarioLawton = comentario;

    }

    @Override
    public void graphBlessedScore(List<BlessedScoreAverage>  blessedScoreAverageList) {
        final String[] labels;
        float month;
        float score;
        List<BlessedScoreAverage> blessedScoreAverages = new ArrayList<BlessedScoreAverage>();

        /*
        for(int j=0;j<blessedScoreAverageList.size();j++)
        {
            blessedScoreAverages.add(blessedScoreAverageList.get(j));
        }
            blessedScoreAverageList.clear();
            */


            blessedScoreAverages.clear();
            blessedScoreAverages = daoHelper.getScoreData(idsistema);





        try {


         blessedChart.setNoDataText("Gráfico de Monitoreo de la demencia segun Blessed, Vacía");
        List<BarEntry> entries = new ArrayList<BarEntry>();
            entries.clear();
        //List<String> labels = new ArrayList<String>();
        labels = new String[blessedScoreAverages.size()];
        //final ArrayList<String> labels= new ArrayList<String>();
            for(int m=0;m<blessedScoreAverages.size();m++)
            {

                score = Float.parseFloat(blessedScoreAverages.get(m).getScore()+"");
                Log.e("score graph"," Score"+score);
                entries.add(new BarEntry(Float.parseFloat(m+""),score));
                labels[m] = blessedScoreAverages.get(m).getMonth();

            }
        entries.size();


        for(int q=0;q<entries.size();q++)
        {
            Log.e("score graph"," Entry x"+entries.get(q).getX());
            Log.e("score graph"," Entry Y"+entries.get(q).getY());
            Log.e("score graph"," Año"+labels[q]);

        }
            if(entries.size()>0)
            {

                Log.e("score graph","Tamaño Entries "+entries);

                BarDataSet dataSet = new BarDataSet(entries, "Escala Demencia Blessed, ");
                //dataSet.setDrawFilled(true);
                //dataSet.setDrawCircles(true);
                //dataSet.setColors();

                //dataSet.setColor(getResources().getColor(R.color.white));
                //dataSet.setCircleColor(getResources().getColor(R.color.colorPrimaryDark));
                //dataSet.setColors(getResources().getColor(R.color.white));
                //dataSet.setCircleColors(getResources().getColor(R.color.white));
                dataSet.setColor(getResources().getColor(R.color.colorPrimaryDark));
                dataSet.setValueTextSize(12f);

                //dataSet.setValueTextSize(15f);

                //List<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
                BarData data = new BarData(dataSet);
                //dataSet.addEntry(dataSet);
                //LineData data = new LineData(dataSets);
                data.setBarWidth(0.6f);
                data.setValueTextSize(15f);

                //blessedChart.setFitBars(true);

                XAxis xAxis = blessedChart.getXAxis();
                YAxis yAxis = blessedChart.getAxisLeft();
                YAxis yAxisright = blessedChart.getAxisRight();


                try {
                    xAxis.setValueFormatter(new IAxisValueFormatter() {

                        public int getDecimalDigits() {
                            return 0;
                        }

                        @Override
                        public String getFormattedValue(float value, AxisBase axis) {
                            return labels[((int)value)];
                        }
                    });
                }catch (Exception e){}


                xAxis.setGranularity(1f);

                xAxis.setTextSize(15f);
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                blessedChart.getAxisLeft().setDrawGridLines(false);
                blessedChart.getXAxis().setDrawGridLines(false);
                blessedChart.getAxisLeft().setDrawAxisLine(false);



                //xAxis.setGridColor(getResources().getColor(R.color.white));
                //xAxis.setAxisMinimum(Float.parseFloat(0+""));
                //xAxis.setAxisMaximum(Float.parseFloat(32+""));
                yAxis.setTextSize(15f);
                yAxisright.setTextSize(15f);
                yAxisright.setEnabled(false);
                //yAxis.setTextColor(getResources().getColor(R.color.white));
                blessedChart.setData(data);

                Description desc = new Description();
                desc.setText("Monitoreo del la Demencia Segun Escala Blessed");
                //desc.setTextAlign(Paint.Align.CENTER);
                //desc.setTextColor(getResources().getColor(R.color.white));
                desc.setTextSize(12f);
                //blessedChart.setGridBackgroundColor(getResources().getColor(R.color.white));

                blessedChart.setDescription(desc);


                blessedChart.invalidate();
                blessedChart.notifyDataSetChanged();

            }




        //blessedChart.invalidate();

        }catch (Exception e)
        {

        }
        blessedScoreAverageList.clear();


    }

    @Override
    public void graphLawtonScore() {

    }

    @Override
    public void graphDowntonScore() {

    }

    @Override
    public void graphGDSScore() {

    }

    @Override
    public void goToStatistics(Long idsistema) {
        Intent intent = new Intent(getContext(), StatisticsActivity.class);
        intent.putExtra("idsistema",idsistema);
        intent.putExtra("cedula",idpatient);
        startActivity(intent);
    }

    public String[] showDetailsTest(View view,String test)
    {
        String[] array = new String[2];
        String title="",description="";
        Resources resources;
        resources = view.getResources();
        switch (test)
        {
            case "minimental":
                title = resources.getString(R.string.minimental_range_information_title);
                description = resources.getString(R.string.minimental_range_information_description);
                break;
            case "fast":
                title = resources.getString(R.string.fast_range_information_title);
                description = resources.getString(R.string.fast_range_information_description);
                break;
            case "blessed":
                title = resources.getString(R.string.blessed_range_information_title);
                description = resources.getString(R.string.blessed_range_information_description);
                break;
            case "lawton":
                title = resources.getString(R.string.lawton_range_information_title);
                description = resources.getString(R.string.lawton_range_information_description);
                break;
            case "downton":
                title = resources.getString(R.string.downton_range_information_title);
                description = resources.getString(R.string.downton_range_information_description);
                break;
        }
        array[0] = title;
        array[1] = description;
        new MaterialDialog.Builder(view.getContext()).title(title).content(description).positiveText(R.string.dialog_succes_agree).show();
        Log.d("Time count","Termina de contar vista superpuesta");


        return array;

    }


    public void showBlessedError()
    {

    }

}
