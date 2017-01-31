package co.edu.unicauca.appterapiademencia.principal.cognitiveexercises;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import co.edu.unicauca.appterapiademencia.R;
import co.edu.unicauca.appterapiademencia.domain.Exercise;
import co.edu.unicauca.appterapiademencia.domain.Patient;
import co.edu.unicauca.appterapiademencia.domain.Reminiscence;
import co.edu.unicauca.appterapiademencia.domain.dao.ExerciseDao;
import co.edu.unicauca.appterapiademencia.domain.dao.GreenDaoHelper;
import co.edu.unicauca.appterapiademencia.principal.patientlist.PatientProfileActivity;

/**
 * Created by SEBAS on 26/12/2016.
 */

public class AddCognitiveExercise extends AppCompatActivity {
    private Spinner spiTaller;
    private Spinner spiNivel;
    private Spinner spiTerapia;
    private Spinner spiReminiscencia;
    private Button btnGuardar;
    private Bundle bundle;
    private Long idpatient;
    private Long idrutina;
    private int modeTherapy;
    private Intent intentBundle;
    private GreenDaoHelper daoHelper;
    private ExerciseDao exerciseDao;
    private  ArrayAdapter<String> adaptertalleres;
    private ArrayAdapter<String> adapterniveles;
    private ArrayAdapter<String> adapterterapias;
    private ArrayAdapter<String> adapterreminiscencias;
    private Toolbar toolbar;
    private ActionBar actionBar;
    private Button btnPsicoestimulacion,btnReminiscencia;
    private LinearLayout containerTaller,containerNivel,containerReminiscencia,containerDemo;
    private TextView txtDemoTaller,txtDemoNivel;
    private CardView cardTaller;
    private ImageView imgDemo;
    private TextView msgEmptyExercises;
    private String[] arrayTalleres;
    public static final String[]  niveles = {"Atencion","Funciones Ejecutivas","Lenguaje","Memoria","Percepción","Lectoescritura y Visoconstrucción"};
    public static final String[]  talleres = {"Visión Normal","Baja visión","Ceguera"};



    public AddCognitiveExercise()
    {
            daoHelper = GreenDaoHelper.getInstance();
            exerciseDao = daoHelper.getExerciseDao();


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_cognitive_exercise);

        spiTaller = (Spinner) findViewById(R.id.spi_taller);
        spiNivel = (Spinner) findViewById(R.id.spi_nivel);
        spiReminiscencia = (Spinner) findViewById(R.id.spi_taller_reminiscencia);
        btnGuardar = (Button) findViewById(R.id.btn_guardar);
        btnPsicoestimulacion = (Button) findViewById(R.id.btn_terapia_psicoestimulacion);
        btnReminiscencia = (Button) findViewById(R.id.btn_terapia_reminiscencia);
        containerDemo = (LinearLayout) findViewById(R.id.container_demo);
        containerNivel = (LinearLayout) findViewById(R.id.container_nivel);
        containerTaller = (LinearLayout) findViewById(R.id.container_taller);
        containerReminiscencia = (LinearLayout) findViewById(R.id.container_taller_reminiscencia);
        txtDemoNivel = (TextView) findViewById(R.id.taller_nivel);
        txtDemoTaller = (TextView) findViewById(R.id.taller_description);
        cardTaller = (CardView) findViewById(R.id.cardTalleres);
        imgDemo = (ImageView) findViewById(R.id.taller_imagen);
        msgEmptyExercises = (TextView) findViewById(R.id.msg_empty);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Volver A La Ficha de Paciente");



        adapterreminiscencias = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, daoHelper.getReminiscenceTitles());
        adaptertalleres = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.talleres));
        adapterniveles = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.niveles));


        spiTaller.setAdapter(adaptertalleres);
        spiNivel.setAdapter(adapterniveles);
        spiReminiscencia.setAdapter(adapterreminiscencias);

        containerDemo.setVisibility(View.GONE);

        bundle = getIntent().getExtras();

        if(bundle!=null)
        {
            try
            {
                idpatient =bundle.getLong("idpatient");
                idrutina = bundle.getLong("idrutina");
                Log.e("add exercise","idpatient "+idpatient);
                Log.e("add exercise","idrutina "+idrutina);
            }catch (Exception e)
            {
                Log.e("add exercise","Error al traer bundle");
            }
        }
        /*
        btnPsicoestimulacion.setPressed(true);

       btnReminiscencia.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {






           }
       });

        btnPsicoestimulacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

 modeTherapy=2;
        paintSelectedButton();
        btnPsicoestimulacion.setBackgroundColor(getResources().getColor(R.color.accent_color));
        containerDemo.setVisibility(View.VISIBLE);
        cardTaller.setVisibility(View.VISIBLE);

        containerReminiscencia.setVisibility(View.GONE);
        containerNivel.setVisibility(View.VISIBLE);
        containerTaller.setVisibility(View.VISIBLE);

            }
        });



*/      representReminiscence();

        if(spiTaller.getChildCount()==0)
        {
            btnGuardar.setVisibility(View.GONE);
            msgEmptyExercises.setVisibility(View.VISIBLE);
            spiTaller.setVisibility(View.GONE);
            containerDemo.setVisibility(View.GONE);

        }

        spiTaller.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                txtDemoTaller.setText(getResources().getStringArray(R.array.talleres)[position]);
                switch (position)
                {

                    case 0:
                        cardTaller.setCardBackgroundColor(getResources().getColor(R.color.negro));

                        break;
                    case 1:
                        cardTaller.setCardBackgroundColor(getResources().getColor(R.color.material_green));

                        break;
                    case 2:
                        cardTaller.setCardBackgroundColor(getResources().getColor(R.color.material_red));

                        break;
                    case 3:
                        cardTaller.setCardBackgroundColor(getResources().getColor(R.color.material_pink));

                        break;
                    case 4:
                        cardTaller.setCardBackgroundColor(getResources().getColor(R.color.material_blue));

                        break;
                    case 5:
                        cardTaller.setCardBackgroundColor(getResources().getColor(R.color.negro));

                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spiNivel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                txtDemoNivel.setText(getResources().getStringArray(R.array.niveles)[position]);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spiReminiscencia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    //int value = spiReminiscencia.getSelectedItemPosition()+1;
                    Log.e("Onitemselected"," texto recogido"+adapterreminiscencias.getItem(position).toString());
                    String titlereminiscencia = adapterreminiscencias.getItem(position).toString();
                    Reminiscence reminiscence = daoHelper.getReminiscence(titlereminiscencia);

                if(position==0)
                {
                    Log.e("spi reminicensia","position 0");
                    imgDemo.setVisibility(View.VISIBLE);
                    imgDemo.setBackgroundResource(R.drawable.popayanimagen);

                }
                else
                {

                    try
                    {
                        imgDemo.setBackground(Drawable.createFromPath(reminiscence.getPhotopath()));


                    } catch (Exception e) {
                        e.printStackTrace();
                        try {
                            imgDemo.setImageURI(Uri.parse(reminiscence.getPhotopath()));

                        } catch (Exception e2) {
                            e.printStackTrace();

                            imgDemo.setBackgroundResource(Integer.parseInt(reminiscence.getPhotopath()));

                        }

                    }
                }




            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int var_nivel=0;
                int var_taller =0;
                String txtTaller ="";



                if(modeTherapy==2)
                {
                    var_nivel = spiTaller.getSelectedItemPosition() +1;
                    var_taller = spiTaller.getSelectedItemPosition();
                    txtTaller = adaptertalleres.getItem(var_taller).toString();

                }

                if(modeTherapy==1){
                    var_taller = spiReminiscencia.getSelectedItemPosition();
                    var_nivel = 1;
                   txtTaller = adapterreminiscencias.getItem(var_taller).toString();
                }




                Log.e("Agregar Exer","Tipo terapia "+modeTherapy);

                Log.e("Agregar Exer","idrutina "+idrutina);
                Log.e("Agregar Exer","Taller "+txtTaller);
                Log.e("Agregar Exer","Nivel "+var_nivel);




                Exercise exercise = new Exercise(null,idrutina,modeTherapy,txtTaller,var_nivel,0,60,false,"");
                exerciseDao.insert(exercise);
                exerciseDao.update(exercise);
                goToExerciseList();

            }
        });

    }


   public void paintSelectedButton()
   {
       btnPsicoestimulacion.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
       btnReminiscencia.setBackgroundColor(getResources().getColor(R.color.colorPrimary));


   }


    public void goToExerciseList()
    {
        Intent intent = new Intent(getApplicationContext(),StimulationTwoActivity.class);
        intent.putExtra("idrutina",idrutina);
        intent.putExtra("idpatient",idpatient);
        startActivity(intent);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            Long cedula;
            Patient patient;
            Intent i2 = new Intent(getApplicationContext(), PatientProfileActivity.class);
            patient = daoHelper.getPatientInformationUsingId(idpatient);
            cedula = patient.getIdentity();
            i2.putExtra("cedula",cedula);
            startActivity(i2);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    public void representReminiscence()
    {
        modeTherapy=1;
        paintSelectedButton();
        btnReminiscencia.setBackgroundColor(getResources().getColor(R.color.accent_color));
        containerDemo.setVisibility(View.VISIBLE);
        imgDemo.setVisibility(View.VISIBLE);
        cardTaller.setVisibility(View.GONE);
        containerReminiscencia.setVisibility(View.VISIBLE);
        containerNivel.setVisibility(View.GONE);
        containerTaller.setVisibility(View.GONE);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            Long cedula;
            Patient patient;
            Intent i2 = new Intent(getApplicationContext(), PatientProfileActivity.class);
            patient = daoHelper.getPatientInformationUsingId(idpatient);
            cedula = patient.getIdentity();
            i2.putExtra("cedula",cedula);
            startActivity(i2);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}
