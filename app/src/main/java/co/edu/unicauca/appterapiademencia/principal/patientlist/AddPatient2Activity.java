package co.edu.unicauca.appterapiademencia.principal.patientlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import co.edu.unicauca.appterapiademencia.R;
import co.edu.unicauca.appterapiademencia.domain.BlessedIncapacity;
import co.edu.unicauca.appterapiademencia.domain.Patient;
import co.edu.unicauca.appterapiademencia.domain.dao.BlessedIncapacityDao;
import co.edu.unicauca.appterapiademencia.domain.dao.GreenDaoHelper;
import co.edu.unicauca.appterapiademencia.domain.dao.PatientDao;
import co.edu.unicauca.appterapiademencia.principal.MainActivity;

/**
 * Created by ENF on 30/10/2016.
 */

public class AddPatient2Activity extends AppCompatActivity implements View.OnClickListener{
    private Button btn_guardar, btn_atras;
    private Spinner s_vision,s_escritura,s_dibujo;
    public static final String[]  vision = {"Visión Normal","Baja visión","Ceguera"};
    public static final String[] escritura = {"Puede Escribir","No puede escribir"};
    public static final String[] dibujo = {"Puede Dibujar","No puede dibujar"};
    private QueryBuilder queryBuilder;
    String[] paciente,datosa;
    private String actualizar="";
    private PatientDao patientDao;
    private GreenDaoHelper helper;
    private Patient patientObj;
    private Toolbar toolbar;
    private ActionBar actionBar;
    private String[] datosb;
    private RadioGroup rdgTareasDomesticas;
    private RadioGroup rdgPequenasDinero;
    private RadioGroup rdgListasCortas;
    private RadioGroup rdgOrientarseCasa;
    private RadioGroup rdgOrientarseCalle;
    private RadioGroup rdgValorarEntorno;
    private RadioGroup rdgRecordarPacientes;
    private RadioGroup rdgRememorarPasado;
    private RadioButton rdgTotalTareasDomesticas,rdgParcialTareasDomesticas,rdgNingunoTareasDomesticas;
    private RadioButton rdgTotalPequenasDinero,rdgParcialPequenasDinero,rdgNingunoPequenasDinero;
    private RadioButton rdgTotalListasCortas,rdgParcialListasCortas,rdgNingunoListasCortas;
    private RadioButton rdgTotalOrientarseCasa,rdgParcialOrientarseCasa,rdgNingunoOrientarseCasa;
    private RadioButton rdgTotalOrientarseCalle,rdgParcialOrientarseCalle,rdgNingunoOrientarseCalle;
    private RadioButton rdgTotalValorarEntorno,rdgParcialValorarEntorno,rdgNingunoValorarEntorno;
    private RadioButton rdgTotalRecordarPacientes,rdgParcialRecordarPacientes,rdgNingunoRecordarPacientes;
    private RadioButton rdgTotalRememorarPasado,rdgParcialRememorarPasado,rdgNingunoRememorarPasado;
    private double calificationTareasDomesticos;
    private double calificationPequenasDinero;
    private double calificationListasCortas;
    private double calificationOrientarseCasa;
    private double calificationOrientarseCalle;
    private double calificationValorarEntorno;
    private double calificationRecodarPacientes;
    private double calificationRememorarPasado;
    private BlessedIncapacityDao blessedDao;





    public AddPatient2Activity()
    {
        this.helper= GreenDaoHelper.getInstance();
        this.patientDao = helper.getPatientDao();
        this.blessedDao = helper.getBlessedIncapacityDao();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpatient_physicalinformation);
        Bundle bundl = getIntent().getExtras();

        btn_guardar = (Button) findViewById(R.id.btn_guardar_paciente);
        btn_atras = (Button) findViewById(R.id.btn_atras_paciente);
        rdgTareasDomesticas = (RadioGroup) findViewById(R.id.rdgTareasDomesticas);
        rdgListasCortas = (RadioGroup) findViewById(R.id.rdgListasCortas);
        rdgPequenasDinero = (RadioGroup) findViewById(R.id.rdgPequenasDineor);
        rdgOrientarseCalle = (RadioGroup) findViewById(R.id.rdgOrientarseCalle);
        rdgOrientarseCasa = (RadioGroup) findViewById(R.id.rdgOrientarseCasa);
        rdgValorarEntorno = (RadioGroup) findViewById(R.id.rdgValorarEntorno);
        rdgRecordarPacientes = (RadioGroup) findViewById(R.id.rdgRecordarRecientes);
        rdgRememorarPasado = (RadioGroup) findViewById(R.id.rdgRememorarPasado);


        btn_guardar.setOnClickListener(this);
        btn_atras.setOnClickListener(this);




        s_vision = (Spinner) findViewById(R.id.spi_vision);
        s_escritura = (Spinner) findViewById(R.id.spi_escritura);
        s_dibujo = (Spinner) findViewById(R.id.spi_dibujo);

        rdgNingunoTareasDomesticas = (RadioButton) findViewById(R.id.ninguno_tareas_domesticas);
        rdgParcialTareasDomesticas = (RadioButton) findViewById(R.id.parcial_tareas_domesticas);
        rdgTotalTareasDomesticas = (RadioButton) findViewById(R.id.total_tareas_domesticas);

        rdgNingunoListasCortas = (RadioButton) findViewById(R.id.ninguno_listas_cortas);
        rdgParcialListasCortas= (RadioButton) findViewById(R.id.parcial_listas_cortas);
        rdgTotalListasCortas = (RadioButton) findViewById(R.id.total_listas_cortas);


        rdgNingunoPequenasDinero = (RadioButton) findViewById(R.id.ninguno_pequenas_dinero);
        rdgParcialPequenasDinero = (RadioButton) findViewById(R.id.parcial_pequenas_dinero);
        rdgTotalPequenasDinero = (RadioButton) findViewById(R.id.total_pequenas_dinero);


        rdgNingunoOrientarseCalle = (RadioButton) findViewById(R.id.ninguno_orientarse_calle);
        rdgParcialOrientarseCalle = (RadioButton) findViewById(R.id.parcial_orientarse_calle);
        rdgTotalOrientarseCalle = (RadioButton) findViewById(R.id.total_orientarse_calle);

        rdgNingunoOrientarseCasa = (RadioButton) findViewById(R.id.ninguno_orientarse_casa);
        rdgParcialOrientarseCasa = (RadioButton) findViewById(R.id.parcial_orientarse_casa);
        rdgTotalOrientarseCasa = (RadioButton) findViewById(R.id.total_orientarse_casa);

        rdgNingunoValorarEntorno = (RadioButton) findViewById(R.id.ninguno_valorar_entorno);
        rdgParcialValorarEntorno = (RadioButton) findViewById(R.id.parcial_valorar_entorno);
        rdgTotalValorarEntorno = (RadioButton) findViewById(R.id.total_valorar_entorno);

        rdgNingunoRecordarPacientes = (RadioButton) findViewById(R.id.ninguno_recordar_recientes);
        rdgParcialRecordarPacientes = (RadioButton) findViewById(R.id.parcial_recordar_recientes);
        rdgTotalRecordarPacientes = (RadioButton) findViewById(R.id.total_recordar_recientes);

        rdgNingunoRememorarPasado = (RadioButton) findViewById(R.id.ninguno_rememorar_pasado);
        rdgParcialRememorarPasado = (RadioButton) findViewById(R.id.parcial_rememorar_pasado);
        rdgTotalRememorarPasado = (RadioButton) findViewById(R.id.total_rememorar_pasado);






        ArrayAdapter<String> adaptervision = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, vision);
        ArrayAdapter<String> adapterescritura = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, escritura);
        ArrayAdapter<String> adapterdibujo = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dibujo);

        s_vision.setAdapter(adaptervision);
        s_escritura.setAdapter(adapterescritura);
        s_dibujo.setAdapter(adapterdibujo);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Agregar Paciente paso 2");





        if (bundl != null) {
            paciente = bundl.getStringArray("paciente");
            actualizar = bundl.getString("actualizar");
            if (actualizar == null) {
                actualizar = "";
            } else {
                actionBar.setTitle("Actualizando Paciente paso 2");
                datosa = new String[4];
                datosa = bundl.getStringArray("datosa");
                datosb = bundl.getStringArray("datosb");
                if (datosa[1].toString().equals("0")) {

                    s_vision.setSelection(0);
                }
                if (datosa[1].toString().equals("1")) {
                    s_vision.setSelection(1);
                }
                if (datosa[1].toString().equals("2")) {

                    s_vision.setSelection(2);
                }

                 if (datosa[2].toString().equals("0")) {
                        s_escritura.setSelection(0);
                    }
                 if (datosa[2].toString().equals("1")) {
                        s_escritura.setSelection(1);
                    }

                  if (datosa[3].toString().equals("0")) {
                        s_dibujo.setSelection(0);
                    }
                  if (datosa[3].toString().equals("1")) {
                        s_dibujo.setSelection(1);
                    }
                Log.e("datosb"," "+datosb[0]+" "+datosb[1]+" "+datosb[2]+ " "+datosb[3]+" "+datosb[4]+" "+datosb[5]+" "+datosb[6]);



                /*
                if(datosb[0].toString().equals("0.0"))
                {
                    rdgNingunoTareasDomesticas.setChecked(true);
                    calificationTareasDomesticos=0.0;

                }
                if(datosb[0].toString().equals("0.5")){
                    rdgParcialTareasDomesticas.setChecked(true);
                    calificationTareasDomesticos=0.5;

                }
                if(datosb[0].toString().equals("1.0")){
                    rdgTotalTareasDomesticas.setChecked(true);
                    calificationTareasDomesticos=1.0;
                }


                if(datosb[1].toString().equals("0.0"))
                {
                    rdgNingunoPequenasDinero.setChecked(true);
                }
                if(datosb[1].toString().equals("0.5")){
                    rdgParcialPequenasDinero.setChecked(true);

                }
                if(datosb[1].toString().equals("1.0")){

                    rdgTotalPequenasDinero.setChecked(true);
                }
                */





                switch (datosb[0].toString()){

                    case "0.0":
                        rdgNingunoTareasDomesticas.setChecked(true);
                        calificationTareasDomesticos=0.0;
                        break;
                    case "0.5":
                        rdgParcialTareasDomesticas.setChecked(true);
                        calificationTareasDomesticos=0.5;
                        break;
                    case "1.0":
                        rdgTotalTareasDomesticas.setChecked(true);
                        calificationTareasDomesticos=1.0;
                        break;
                }
                switch (datosb[1].toString()){
                    case "0.0":
                        rdgNingunoPequenasDinero.setChecked(true);
                        calificationPequenasDinero=0.0;
                        break;
                    case "0.5":
                        rdgParcialPequenasDinero.setChecked(true);
                        calificationPequenasDinero=0.5;
                        break;
                    case "1.0":

                        rdgTotalPequenasDinero.setChecked(true);
                        calificationPequenasDinero=1.0;
                        break;
                }

                switch (datosb[2].toString()){
                    case "0.0":
                        rdgNingunoListasCortas.setChecked(true);
                        calificationListasCortas=0.0;
                        break;
                    case "0.5":
                        rdgParcialListasCortas.setChecked(true);
                        calificationListasCortas=0.5;
                        break;
                    case "1.0":
                        rdgTotalListasCortas.setChecked(true);
                        calificationListasCortas=1.0;
                        break;
                }
                switch (datosb[3].toString()){
                    case "0.0":
                        rdgNingunoOrientarseCasa.setChecked(true);
                        calificationOrientarseCasa=0.0;
                        break;
                    case "0.5":
                        rdgParcialOrientarseCasa.setChecked(true);
                        calificationOrientarseCasa=0.5;
                        break;
                    case "1.0":
                        rdgTotalOrientarseCasa.setChecked(true);
                        calificationOrientarseCasa=1.0;

                        break;
                }
                switch (datosb[4].toString()){
                    case "0.0":

                        rdgNingunoOrientarseCalle.setChecked(true);
                        calificationOrientarseCalle=0.0;
                        break;
                    case "0.5":
                        rdgParcialOrientarseCalle.setChecked(true);
                        calificationOrientarseCalle=0.5;

                        break;
                    case "1.0":
                        rdgTotalOrientarseCalle.setChecked(true);
                        calificationOrientarseCalle=1.0;


                        break;
                }
                switch (datosb[5].toString()){
                    case "0.0":
                        rdgNingunoValorarEntorno.setChecked(true);
                        calificationValorarEntorno=0.0;

                        break;
                    case "0.5":
                        rdgParcialValorarEntorno.setChecked(true);
                        calificationValorarEntorno=0.5;

                        break;
                    case "1.0":
                        rdgTotalValorarEntorno.setChecked(true);
                        calificationValorarEntorno=1.0;

                        break;
                }
                switch (datosb[6].toString()){
                    case "0.0":
                        rdgNingunoRecordarPacientes.setChecked(true);
                        calificationRecodarPacientes=0.0;

                        break;
                    case "0.5":
                        rdgParcialRecordarPacientes.setChecked(true);
                        calificationRecodarPacientes=0.5;
                        break;
                    case "1.0":
                        rdgTotalRecordarPacientes.setChecked(true);
                        calificationRecodarPacientes=1.0;
                        break;
                }
                switch (datosb[7]){
                    case "0.0":
                        rdgNingunoRememorarPasado.setChecked(true);
                        calificationRememorarPasado=0.0;

                        break;
                    case "0.5":
                        rdgParcialRememorarPasado.setChecked(true);
                        calificationRememorarPasado=0.5;
                        break;
                    case "1.0":
                        rdgTotalRememorarPasado.setChecked(true);
                        calificationRememorarPasado=1.0;
                        break;
                }



                }

            }


        rdgTareasDomesticas.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if(checkedId==R.id.ninguno_tareas_domesticas){
                    calificationTareasDomesticos=0.0;
                }
                if(checkedId==R.id.parcial_tareas_domesticas){
                    calificationTareasDomesticos=0.5;
                }
                if(checkedId==R.id.total_tareas_domesticas){
                    calificationTareasDomesticos=1.0;
                }
            }
        });

        rdgListasCortas.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if(checkedId==R.id.ninguno_listas_cortas){
                    calificationListasCortas=0.0;
                    Log.e("listas cortas"," "+calificationListasCortas);
                }
                if(checkedId==R.id.parcial_listas_cortas){
                    calificationListasCortas=0.5;
                    Log.e("listas cortas"," "+calificationListasCortas);
                }
                if(checkedId==R.id.total_listas_cortas){
                    calificationListasCortas=1.0;
                    Log.e("listas cortas"," "+calificationListasCortas);
                }
            }
        });

        rdgPequenasDinero.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if(checkedId==R.id.ninguno_pequenas_dinero){
                    calificationPequenasDinero=0.0;
                }
                if(checkedId==R.id.parcial_pequenas_dinero){
                    calificationPequenasDinero=0.5;
                }
                if(checkedId==R.id.total_pequenas_dinero){
                    calificationPequenasDinero=1.0;
                }
            }
        });

        rdgOrientarseCasa.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if(checkedId==R.id.ninguno_orientarse_casa){
                    calificationOrientarseCasa=0.0;
                }
                if(checkedId==R.id.parcial_orientarse_casa){
                    calificationOrientarseCasa=0.5;
                }
                if(checkedId==R.id.total_orientarse_casa){
                    calificationOrientarseCasa=1.0;
                }
            }
        });

        rdgOrientarseCalle.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if(checkedId==R.id.ninguno_orientarse_calle){
                    calificationOrientarseCalle=0.0;
                }
                if(checkedId==R.id.parcial_orientarse_calle){
                    calificationOrientarseCalle=0.5;
                }
                if(checkedId==R.id.total_orientarse_calle){
                    calificationOrientarseCalle=1.0;
                }
            }
        });

        rdgValorarEntorno.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if(checkedId==R.id.ninguno_valorar_entorno){
                    calificationValorarEntorno=0.0;
                }
                if(checkedId==R.id.parcial_valorar_entorno){
                    calificationValorarEntorno=0.5;
                }
                if(checkedId==R.id.total_valorar_entorno){
                    calificationValorarEntorno=1.0;
                }
            }
        });

        rdgRecordarPacientes.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if(checkedId==R.id.ninguno_recordar_recientes){
                    calificationRecodarPacientes=0.0;
                }
                if(checkedId==R.id.parcial_recordar_recientes){
                    calificationRecodarPacientes=0.5;
                }
                if(checkedId==R.id.total_recordar_recientes){
                    calificationRecodarPacientes=1.0;
                }
            }
        });

        rdgRememorarPasado.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if(checkedId==R.id.ninguno_rememorar_pasado){
                    calificationRememorarPasado=0.0;
                }
                if(checkedId==R.id.parcial_rememorar_pasado){
                    calificationRememorarPasado=0.5;
                }
                if(checkedId==R.id.total_rememorar_pasado){
                    calificationRememorarPasado=1.0;
                }
            }
        });

        }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                super.onBackPressed();
                overridePendingTransition(R.anim.right_in, R.anim.right_out);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_guardar_paciente:
                /*if (validar(e_peso.getText().toString(), e_color.getText().toString(), e_raza.getText().toString(), e_id_padre.getText().toString(), e_id_madre.getText().toString()) == false) {
                    Toast.makeText(this, "Debe Llenar Todos Los Campos", Toast.LENGTH_LONG).show();
                } else {*/



                    /*
                       String var_foto;
                    if(actualizar.equals(""))
                    {
                        var_foto = paciente[1];
                    }else {
                        if (paciente[1].equals("")) {

                            var_foto = datosa[2];
                        } else {

                            var_foto = paciente[1];
                            //}
                        }
                    }
                              */

                    String var_id = paciente[0];
                    Long parse_id = Long.parseLong(var_id);
                    String var_foto = paciente[1];
                    String var_nombre = paciente[2];
                    String var_fecha = paciente[3];
                    String var_eps= paciente[4];
                    String var_antecedentes = paciente[5];
                    String var_sindromes = paciente[6];
                    String var_observaciones = paciente[7];


                    /*
                    int var_tareas_domesticas= rdgRecordarPacientes.getCheckedRadioButtonId();
                    int var_pequeno_dinero =rdgPequenasDinero.getCheckedRadioButtonId();
                    int var_listas_cortas = rdgListasCortas.getCheckedRadioButtonId();
                    int var_orientarse_casa = rdgOrientarseCasa.getCheckedRadioButtonId();
                    int var_orientarse_calle = rdgOrientarseCalle.getCheckedRadioButtonId();
                    int var_valorar_entorno = rdgValorarEntorno.getCheckedRadioButtonId();
                    int var_recordar_pacientes = rdgRecordarPacientes.getCheckedRadioButtonId();
                    int var_rememorar_pasado = rdgRememorarPasado.getCheckedRadioButtonId();
                    */


                    int var_vision = s_vision.getSelectedItemPosition();
                    int var_escritura = s_escritura.getSelectedItemPosition();
                    int var_dibujo = s_dibujo.getSelectedItemPosition();


                    if (actualizar.equals("actualizar")) {


                        String[] fechas = new String[7];
                        queryBuilder = helper.getPatientDao().queryBuilder();

                        List<Patient> patientList = queryBuilder.where(PatientDao.Properties.Identity.eq(parse_id)).limit(1).list();
                        Patient patient = patientList.get(0);

                        //List<BlessedIncapacity> blessedIncapacities = queryBuilder.where(BlessedIncapacityDao.Properties.PatientId.eq(patientList.get(0).getId())).list();
                        Long patientid = patient.getId();
                        BlessedIncapacity blessedIncapacity = helper.getBlessedbyid(patientid);

                        //BlessedIncapacity blessedIncapacity = blessedIncapacities.get(0);
                        Log.e("Add patient 2","Name "+patient.getName().toString());
                        patient.setName(var_nombre);
                        patient.setBirthday(var_fecha);
                        patient.setEps(var_eps);
                        patient.setAntecedents(var_antecedentes);
                        patient.setSyndromes(var_sindromes);
                        patient.setObservations(var_observaciones);

                        patient.setPhotopath(var_foto);
                        patient.setVisionlimitation(var_vision);
                        patient.setWritinglimitation(var_escritura);
                        patient.setDrawinglimitation(var_dibujo);
                        blessedIncapacity.setTareasdomesticas(calificationTareasDomesticos);
                        blessedIncapacity.setPequenasdinero(calificationPequenasDinero);
                        blessedIncapacity.setListascortas(calificationListasCortas);
                        blessedIncapacity.setOrientarsecasa(calificationOrientarseCasa);
                        blessedIncapacity.setOrientarsecalle(calificationOrientarseCalle);
                        blessedIncapacity.setValorarentorno(calificationValorarEntorno);
                        blessedIncapacity.setRecordarrecientes(calificationRecodarPacientes);
                        blessedIncapacity.setRememorarpasado(calificationRememorarPasado);

                        Log.e("calificationupdate"," domesticos"+calificationTareasDomesticos);
                        Log.e("calificationupdate"," pequenasdinero"+calificationPequenasDinero);
                        Log.e("calificationupdate"," listas cortas"+calificationListasCortas);
                        Log.e("calificationupdate"," casa"+calificationOrientarseCasa);
                        Log.e("calificationupdate"," calle"+calificationOrientarseCalle);
                        Log.e("calificationupdate"," entorno"+calificationValorarEntorno);





                        Log.e("Add patient 2","Dibujo "+var_dibujo);


                            if(var_foto.equals("android.resource://co.edu.unicauca.appterapiademencia/"+R.drawable.emptyuser))
                            {
                                var_foto="";
                            }

                    } else {
                        int var_mec = 0;
                        int var_gds = 0;
                        Patient patient2 = new Patient(null,var_nombre,var_fecha,var_foto,var_eps,parse_id,var_antecedentes,var_sindromes,var_observaciones,null,null,var_vision,var_escritura,var_dibujo);
                        this.patientDao.insert(patient2);

                        try{
                            if(patient2.getId().toString()!=null){

                                int rdgalimentacion=0,rdgvestimenta=0,rdgesfinteres=0;
                                int [] personalidad = new int[11];
                                for(int m=0;m<personalidad.length;m++)
                                {
                                    personalidad[m]=0;
                                }

                                BlessedIncapacity blessedIncapacity = new BlessedIncapacity(null,patient2.getId(),calificationTareasDomesticos,calificationPequenasDinero,calificationListasCortas,calificationOrientarseCasa,calificationOrientarseCalle,calificationValorarEntorno,calificationRecodarPacientes,calificationRememorarPasado,
                                        rdgalimentacion,rdgvestimenta,rdgesfinteres,personalidad[0],personalidad[1],personalidad[2],personalidad[3],personalidad[4],personalidad[5],personalidad[6],personalidad[7],personalidad[8],personalidad[9],personalidad[10]);
                                this.blessedDao.insert(blessedIncapacity);
                                Log.e("addpatient blessed",blessedIncapacity.getId()+" con patientid"+blessedIncapacity.getPatientId());
                            }
                        }catch (NullPointerException e){

                        }



                        }




                    //-------------------------------
                    Intent ir_main = new Intent(this, MainActivity.class);
                    startActivity(ir_main);
                    overridePendingTransition(R.anim.left_in, R.anim.left_out);
                    finish();
                    break;
                case R.id.btn_atras_paciente:
                    super.onBackPressed();
                    overridePendingTransition(R.anim.right_in, R.anim.right_out);
                    break;
            }
    }


}
