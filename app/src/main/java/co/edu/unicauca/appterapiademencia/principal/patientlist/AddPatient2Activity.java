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
import co.edu.unicauca.appterapiademencia.domain.Patient;
import co.edu.unicauca.appterapiademencia.domain.Scale;
import co.edu.unicauca.appterapiademencia.domain.Sintoma;
import co.edu.unicauca.appterapiademencia.domain.dao.DetailFastDao;
import co.edu.unicauca.appterapiademencia.domain.dao.GreenDaoHelper;
import co.edu.unicauca.appterapiademencia.domain.dao.PatientDao;
import co.edu.unicauca.appterapiademencia.domain.dao.ScaleDao;
import co.edu.unicauca.appterapiademencia.domain.dao.SintomaDao;
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
    private String calificationTareasDomesticos;
    private String calificationPequenasDinero;
    private String calificationListasCortas;
    private String calificationOrientarseCasa;
    private String calificationOrientarseCalle;
    private String calificationValorarEntorno;
    private String calificationRecodarPacientes;
    private String calificationRememorarPasado;
    private Sintoma tareasdomesticas,pequenasdinero,memorialistascortas,orientacioncasa,orientacionentorno,orientacioncalle,memoriaolvidosbenignos,tendenciarememorar;


    private String election;
    private String[] sintomasList;
    private String[] puntajeList;
    private String[] electionList;
    private Boolean[] activeList;
    private String var_seleccion;
    private String  puntaje;
    private SintomaDao sintomaDao;
    private ScaleDao escalaDao;
    private DetailFastDao detailFastDao;
    private boolean[] checks;






    public AddPatient2Activity()
    {
        this.helper= GreenDaoHelper.getInstance();
        this.patientDao = helper.getPatientDao();

        this.sintomaDao = helper.getSintomaDao();
        this.escalaDao = helper.getScaleDao();
        this.detailFastDao = helper.getDetailFastDao();


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


        puntajeList = new String[8];
        sintomasList = new String[8];
        electionList = new String[8];
        activeList = new Boolean[8];
        checks = new boolean[2];






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
            checks = bundl.getBooleanArray("checks");
            actualizar = bundl.getString("actualizar");

            Log.e("Agregar paciente2","boolEscolaridadrecibido "+checks[0]);

            Log.e("Agregar paciente2","boolEscolaridadrecibido "+checks[1]);


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





                switch (datosb[0].toString())
                {


                    case "0.0":
                        rdgNingunoTareasDomesticas.setChecked(true);
                        calificationTareasDomesticos="0.0";
                        election="instrumentales";

                        //electionList.add(0,election);
                        //sintomasList.add(0,"incapacidadtareasdomesticas");

                        electionList[0] = election;
                        sintomasList[0] = "incapacidadtareasdomesticas";
                        activeList[0]=false;


                        break;
                    case "0.5":
                        rdgParcialTareasDomesticas.setChecked(true);
                        calificationTareasDomesticos="0.5";
                        election="instrumentales";
                        //electionList.add(0,election);
                        //sintomasList.add(0,"incapacidadtareasdomesticas");

                        electionList[0] = election;
                        sintomasList[0] = "incapacidadtareasdomesticas";
                        activeList[0]=true;

                        break;
                    case "1.0":
                        rdgTotalTareasDomesticas.setChecked(true);
                        calificationTareasDomesticos="1.0";
                        election="instrumentales";
                        electionList[0] = election;
                        sintomasList[0] = "incapacidadtareasdomesticas";
                        activeList[0]=true;
                        break;
                }
                switch (datosb[1].toString())
                {
                    case "0.0":

                        rdgNingunoPequenasDinero.setChecked(true);
                        calificationPequenasDinero="0.0";
                        election="instrumentales";
                        //electionList.add(1,election);
                        //sintomasList.add(1,"incapacidadpequenasdinero");
                        electionList[1] = election;
                        sintomasList[1] = "incapacidadpequenasdinero";
                        activeList[1]=false;
                        break;
                    case "0.5":
                        rdgParcialPequenasDinero.setChecked(true);
                        calificationPequenasDinero="0.5";
                        election="instrumentales";
                        //electionList.add(1,election);
                        //sintomasList.add(1,"incapacidadpequenasdinero");

                        electionList[1] = election;
                        sintomasList[1] = "incapacidadpequenasdinero";
                        activeList[1]=true;
                        break;
                    case "1.0":

                        rdgTotalPequenasDinero.setChecked(true);
                        calificationPequenasDinero="1.0";
                        election="instrumentales";
                        //electionList.add(1,election);
                        //sintomasList.add(1,"incapacidadpequenasdinero");

                        electionList[1] = election;
                        sintomasList[1] = "incapacidadpequenasdinero";
                        activeList[1]=true;
                        break;
                }

                switch (datosb[2].toString()){
                    case "0.0":
                        rdgNingunoListasCortas.setChecked(true);
                        calificationListasCortas="0.0";
                        election="memoria";
                        //electionList.add(2,election);
                        //sintomasList.add(2,"memorialistascortas");

                        electionList[2] = election;
                        sintomasList[2] = "memorialistascortas";
                        break;
                    case "0.5":
                        rdgParcialListasCortas.setChecked(true);
                        calificationListasCortas="0.5";
                        election="memoria";

                        electionList[2] = election;
                        sintomasList[2] = "memorialistascortas";
                        activeList[2]=false;
                        //electionList.add(2,election);
                        //sintomasList.add(2,"memorialistascortas");
                        break;
                    case "1.0":
                        rdgTotalListasCortas.setChecked(true);
                        calificationListasCortas="1.0";
                        election="memoria";
                        electionList[2] = election;
                        sintomasList[2] = "memorialistascortas";
                        activeList[2]=true;

                        //electionList.add(2,election);
                        //sintomasList.add(2,"memorialistascortas");
                        break;
                }
                switch (datosb[3].toString()){
                    case "0.0":
                        rdgNingunoOrientarseCasa.setChecked(true);
                        calificationOrientarseCasa="0.0";
                        election="orientacion";
                        electionList[3] = election;
                        sintomasList[3] = "orientacioncasa";
                        activeList[3]=false;
                        //electionList.add(3,election);
                        //sintomasList.add(3,"orientacioncasa");
                        break;
                    case "0.5":
                        rdgParcialOrientarseCasa.setChecked(true);
                        calificationOrientarseCasa="0.5";
                        election="orientacion";
                        electionList[3] = election;
                        sintomasList[3] = "orientacioncasa";
                        activeList[3]=true;
                        //electionList.add(3,election);
                        //sintomasList.add(3,"orientacioncasa");
                        break;
                    case "1.0":
                        rdgTotalOrientarseCasa.setChecked(true);
                        calificationOrientarseCasa="1.0";
                        election="orientacion";
                        electionList[3] = election;
                        sintomasList[3] = "orientacioncasa";
                        activeList[3]=true;
                        /// /electionList.add(3,election);
                        //sintomasList.add(3,"orientacioncasa");

                        break;
                }
                switch (datosb[4].toString()){
                    case "0.0":

                        rdgNingunoOrientarseCalle.setChecked(true);
                        calificationOrientarseCalle="0.0";
                        election="orientacion";
                        electionList[4] = election;
                        sintomasList[4] = "orientacioncalle";
                        activeList[4]=false;
                        //electionList.add(4,election);
                        //sintomasList.add(4,"orientacioncalle");
                        break;
                    case "0.5":
                        rdgParcialOrientarseCalle.setChecked(true);
                        calificationOrientarseCalle="0.5";
                        election="orientacion";
                        electionList[4] = election;
                        sintomasList[4] = "orientacioncalle";
                        activeList[4]=true;
                        //electionList.add(4,election);
                        //sintomasList.add(4,"orientacioncalle");

                        break;
                    case "1.0":
                        rdgTotalOrientarseCalle.setChecked(true);
                        calificationOrientarseCalle="1.0";
                        election="orientacion";
                        electionList[4] = election;
                        sintomasList[4] = "orientacioncalle";
                        activeList[4]=true;
                        /// /electionList.add(4,election);
                        //sintomasList.add(4,"orientacioncalle");

                        break;
                }
                switch (datosb[5].toString()){
                    case "0.0":
                        rdgNingunoValorarEntorno.setChecked(true);
                        calificationValorarEntorno="0.0";
                        election="orientacion";
                        electionList[5] = election;
                        sintomasList[5] = "orientacioentorno";
                        activeList[5]=false;
                        //electionList.add(5,election);
                        //sintomasList.add(5,"orientacionentorno");
                        break;
                    case "0.5":
                        rdgParcialValorarEntorno.setChecked(true);
                        calificationValorarEntorno="0.5";
                        election="orientacion";
                        electionList[5] = election;
                        sintomasList[5] = "orientacioentorno";
                        activeList[5]=true;
                        break;
                    case "1.0":
                        rdgTotalValorarEntorno.setChecked(true);
                        calificationValorarEntorno="1.0";
                        election="orientacion";
                        electionList[5] = election;
                        sintomasList[5] = "orientacionentorno";
                        activeList[5]=true;
                        break;
                }
                switch (datosb[6].toString()){
                    case "0.0":
                        rdgNingunoRecordarPacientes.setChecked(true);
                        calificationRecodarPacientes="0.0";
                        election="memoria";
                        electionList[6] = election;
                        sintomasList[6] = "memoriaolvidosbenignos";
                        activeList[6]=false;
                        //electionList.add(6,election);
                        //sintomasList.add(6,"memoriaolvidosbenignos");

                        break;
                    case "0.5":
                        rdgParcialRecordarPacientes.setChecked(true);
                        calificationRecodarPacientes="0.5";
                        election="memoria";
                        electionList[6] = election;
                        sintomasList[6] = "memoriaolvidosbenignos";
                        activeList[6]=true;
                        //electionList.add(6,election);
                        //sintomasList.add(6,"memoriaolvidosbenignos");
                        break;
                    case "1.0":
                        rdgTotalRecordarPacientes.setChecked(true);
                        calificationRecodarPacientes="1.0";
                        election="memoria";
                        electionList[6] = election;
                        sintomasList[6] = "memoriaolvidosbenignos";
                        activeList[6]=true;
                        //electionList.add(6,election);
                        //sintomasList.add(6,"memoriaolvidosbenignos");
                        break;
                }
                switch (datosb[7].toString()){
                    case "0.0":
                        rdgNingunoRememorarPasado.setChecked(true);
                        calificationRememorarPasado="0.0";
                        election="memoria";
                        electionList[7] = election;
                        sintomasList[7] = "memoriatendenciarememorar";
                        activeList[7]=false;
                        //electionList.add(7,election);
                        //sintomasList.add(7,"memoriatendenciarememorar");

                        break;
                    case "0.5":
                        rdgParcialRememorarPasado.setChecked(true);
                        calificationRememorarPasado="0.5";
                        election="memoria";
                        electionList[7] = election;
                        sintomasList[7] = "memoriatendenciarememorar";
                        activeList[7]=true;
                        break;
                    case "1.0":
                        rdgTotalRememorarPasado.setChecked(true);
                        calificationRememorarPasado="1.0";
                        election="memoria";
                        electionList[7] = election;
                        sintomasList[7] = "memoriatendenciarememorar";
                        activeList[7]=true;
                        break;
                }



                }

            }


        rdgTareasDomesticas.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if(checkedId==R.id.ninguno_tareas_domesticas){
                    calificationTareasDomesticos="0.0";
                    election="vestimenta";

                    electionList[0] = election;
                    sintomasList[0] = "incapacidadtareasdomesticas";
                    activeList[0]=false;
                }
                if(checkedId==R.id.parcial_tareas_domesticas){
                    calificationTareasDomesticos="0.5";
                    election="vestimenta";

                    electionList[0] = election;
                    sintomasList[0] = "incapacidadtareasdomesticas";
                    activeList[0]=true;
                }
                if(checkedId==R.id.total_tareas_domesticas){
                    calificationTareasDomesticos="1.0";
                    election="vestimenta";

                    electionList[0] = election;
                    sintomasList[0] = "incapacidadtareasdomesticas";
                    activeList[0]=true;
                }
            }
        });

        rdgPequenasDinero.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if(checkedId==R.id.ninguno_pequenas_dinero){
                    calificationPequenasDinero="0.0";
                    election="vestimenta";
                    electionList[1] = election;
                    sintomasList[1] = "incapacidadpequenasdinero";
                    activeList[1]=false;
                }
                if(checkedId==R.id.parcial_pequenas_dinero){
                    calificationPequenasDinero="0.5";
                    election="vestimenta";
                    electionList[1] = election;
                    sintomasList[1] = "incapacidadpequenasdinero";
                    activeList[1]=true;
                }
                if(checkedId==R.id.total_pequenas_dinero){
                    calificationPequenasDinero="1.0";
                    election="vestimenta";
                    electionList[1] = election;
                    sintomasList[1] = "incapacidadpequenasdinero";
                    activeList[1]=true;
                }
            }
        });

        rdgListasCortas.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if(checkedId==R.id.ninguno_listas_cortas){
                    calificationListasCortas="0.0";
                    election="memoria";
                    electionList[2] = election;
                    sintomasList[2] = "memorialistascortas";
                    activeList[2]=false;
                    Log.e("listas cortas"," "+calificationListasCortas);
                }
                if(checkedId==R.id.parcial_listas_cortas){
                    calificationListasCortas="0.5";
                    election="memoria";
                    electionList[2] = election;
                    sintomasList[2] = "memorialistascortas";
                    activeList[2]=true;
                    Log.e("listas cortas"," "+calificationListasCortas);
                }
                if(checkedId==R.id.total_listas_cortas){
                    calificationListasCortas="1.0";
                    election="memoria";
                    electionList[2] = election;
                    sintomasList[2] = "memorialistascortas";
                    activeList[2]=true;
                    Log.e("listas cortas"," "+calificationListasCortas);
                }
            }
        });



        rdgOrientarseCasa.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if(checkedId==R.id.ninguno_orientarse_casa){
                    calificationOrientarseCasa="0.0";
                    election="orientacion";
                    electionList[3] = election;
                    sintomasList[3] = "orientacioncasa";
                    activeList[3]=false;
                }
                if(checkedId==R.id.parcial_orientarse_casa){
                    calificationOrientarseCasa="0.5";
                    election="orientacion";
                    electionList[3] = election;
                    sintomasList[3] = "orientacioncasa";
                    activeList[3]=true;
                }
                if(checkedId==R.id.total_orientarse_casa){
                    calificationOrientarseCasa="1.0";
                    election="orientacion";
                    electionList[3] = election;
                    sintomasList[3] = "orientacioncasa";
                    activeList[3]=true;
                }
            }
        });

        rdgOrientarseCalle.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if(checkedId==R.id.ninguno_orientarse_calle){
                    calificationOrientarseCalle="0.0";
                    election="orientacion";
                    electionList[4] = election;
                    sintomasList[4] = "orientacioncalle";
                    activeList[4]=false;
                }
                if(checkedId==R.id.parcial_orientarse_calle){
                    calificationOrientarseCalle="0.5";
                    election="orientacion";
                    electionList[4] = election;
                    sintomasList[4] = "orientacioncalle";
                    activeList[4]=true;
                }
                if(checkedId==R.id.total_orientarse_calle){
                    calificationOrientarseCalle="1.0";
                    election="orientacion";
                    electionList[4] = election;
                    sintomasList[4] = "orientacioncalle";
                    activeList[4]=true;
                }
            }
        });

        rdgValorarEntorno.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if(checkedId==R.id.ninguno_valorar_entorno){
                    calificationValorarEntorno="0.0";
                    election="orientacion";
                    electionList[5] = election;
                    sintomasList[5] = "orientacionentorno";
                    activeList[5]=false;
                }
                if(checkedId==R.id.parcial_valorar_entorno){
                    calificationValorarEntorno="0.5";
                    election="orientacion";
                    electionList[5] = election;
                    sintomasList[5] = "orientacionentorno";
                    activeList[5]=true;
                }
                if(checkedId==R.id.total_valorar_entorno){
                    calificationValorarEntorno="1.0";
                    election="orientacion";
                    electionList[5] = election;
                    sintomasList[5] = "orientacionentorno";
                    activeList[5]=true;
                }
            }
        });

        rdgRecordarPacientes.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if(checkedId==R.id.ninguno_recordar_recientes){
                    calificationRecodarPacientes="0.0";
                    election="memoria";
                    electionList[6] = election;
                    sintomasList[6] = "memoriaolvidosbenignos";
                    activeList[6]=false;

                }
                if(checkedId==R.id.parcial_recordar_recientes){
                    calificationRecodarPacientes="0.5";
                    election="memoria";
                    electionList[6] = election;
                    sintomasList[6] = "memoriaolvidosbenignos";
                    activeList[6]=true;

                }
                if(checkedId==R.id.total_recordar_recientes){
                    calificationRecodarPacientes="1.0";
                    election="memoria";
                    electionList[6] = election;
                    sintomasList[6] = "memoriaolvidosbenignos";
                    activeList[6]=true;

                }
            }
        });

        rdgRememorarPasado.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {

                if(checkedId==R.id.ninguno_rememorar_pasado){
                    calificationRememorarPasado="0.0";
                    election="memoria";
                    electionList[7] = election;
                    sintomasList[7] = "memoriatendenciarememorar";
                    activeList[7]=false;
                }
                if(checkedId==R.id.parcial_rememorar_pasado){
                    calificationRememorarPasado="0.5";
                    election="memoria";
                    electionList[7] = election;
                    sintomasList[7] = "memoriatendenciarememorar";
                    activeList[0]=true;
                }
                if(checkedId==R.id.total_rememorar_pasado){
                    calificationRememorarPasado="1.0";
                    election="memoria";
                    electionList[7] = election;
                    sintomasList[7] = "memoriatendenciarememorar";
                    activeList[7]=true;
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



    public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_guardar_paciente:

                    new Thread(new Runnable() {

                        @Override
                        public void run() {
                            try
                            {
                                String var_id = paciente[0];
                                Long parse_id = Long.parseLong(var_id);
                                String var_foto = paciente[1];
                                String var_nombre = paciente[2];
                                String var_fecha = paciente[3];
                                String var_eps= paciente[4];
                                String var_antecedentes = paciente[5];
                                String var_sindromes = paciente[6];
                                String var_observaciones = paciente[7];
                                String var_sexo = paciente[8];

                                boolean var_escolaridad = checks[0];
                                boolean var_institucionalizado = checks[1];


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
                                //election ="vestimenta";


                                if (actualizar.equals("actualizar")) {


                                    String[] fechas = new String[7];
                                    queryBuilder = helper.getPatientDao().queryBuilder();

                                    List<Patient> patientList = queryBuilder.where(PatientDao.Properties.Identity.eq(parse_id)).limit(1).list();
                                    Patient patient = patientList.get(0);

                                    //List<BlessedIncapacity> blessedIncapacities = queryBuilder.where(BlessedIncapacityDao.Properties.PatientId.eq(patientList.get(0).getId())).list();
                                    Long patientid = patient.getId();
                                    // BlessedIncapacity blessedIncapacity = helper.getBlessedbyid(patientid);

                                    //BlessedIncapacity blessedIncapacity = blessedIncapacities.get(0);
                                    Log.e("Add patient 2","Name "+patient.getName().toString());
                                    patient.setName(var_nombre);
                                    patient.setBirthday(var_fecha);
                                    patient.setSex(var_sexo);
                                    patient.setEps(var_eps);
                                    patient.setScolarity(var_escolaridad);
                                    patient.setInstitutional(var_institucionalizado);
                                    patient.setAntecedents(var_antecedentes);
                                    patient.setSyndromes(var_sindromes);
                                    patient.setObservations(var_observaciones);

                                    patient.setPhotopath(var_foto);
                                    patient.setVisionlimitation(var_vision);
                                    patient.setWritinglimitation(var_escritura);
                                    patient.setDrawinglimitation(var_dibujo);
                                    patientDao.update(patient);



                                    Log.e("calificationupdate"," domesticos"+calificationTareasDomesticos);
                                    Log.e("calificationupdate"," pequenasdinero"+calificationPequenasDinero);
                                    Log.e("calificationupdate"," listas cortas"+calificationListasCortas);
                                    Log.e("calificationupdate"," casa"+calificationOrientarseCasa);
                                    Log.e("calificationupdate"," calle"+calificationOrientarseCalle);
                                    Log.e("calificationupdate"," entorno"+calificationValorarEntorno);
                                    Log.e("calificationupdate"," recordarrecientes"+calificationRecodarPacientes);
                                    Log.e("calificationupdate"," rememorarpasado"+calificationRememorarPasado);



                                    try{
                                        tareasdomesticas = helper.getSintoma(patientid,"Blessed","vestimenta","incapacidadtareasdomesticas");
                                        tareasdomesticas.setActivo(activeList[0]);
                                        tareasdomesticas.getScaleList().get(0).setPuntaje(calificationTareasDomesticos);
                                        //tareasdomesticas.update();
                                        sintomaDao.update(tareasdomesticas);

                                        escalaDao.update(tareasdomesticas.getScaleList().get(0));
                                        //tareasdomesticas.refresh();
                                        Log.e("guardado y actualizado",tareasdomesticas.getScaleList().get(0).getPuntaje().toString());


                                    }catch (Exception e){}
                                    try{
                                        pequenasdinero = helper.getSintoma(patientid,"Blessed","vestimenta","incapacidadpequenasdinero");
                                        pequenasdinero.setActivo(activeList[1]);
                                        pequenasdinero.getScaleList().get(0).setPuntaje(calificationPequenasDinero);
                                        //pequenasdinero.update();
                                        sintomaDao.update(pequenasdinero);

                                        escalaDao.update(pequenasdinero.getScaleList().get(0));
                                        //pequenasdinero.refresh();
                                        Log.e("guardado y actualizado",pequenasdinero.getScaleList().get(0).getPuntaje().toString());

                                    }catch (Exception e){}

                                    try{
                                        memorialistascortas = helper.getSintoma(patientid,"Blessed","memoria","memorialistascortas");
                                        memorialistascortas.setActivo(activeList[2]);
                                        memorialistascortas.getScaleList().get(0).setPuntaje(calificationListasCortas);
                                        //memorialistascortas.update();
                                        sintomaDao.update(memorialistascortas);

                                        escalaDao.update(memorialistascortas.getScaleList().get(0));

                                        //memorialistascortas.refresh();
                                        Log.e("guardado y actualizado",memorialistascortas.getScaleList().get(0).getPuntaje().toString());


                                    }catch (Exception e){
                                    }
                                    try{
                                        orientacioncasa = helper.getSintoma(patientid,"Blessed","orientacion","orientacioncasa");
                                        orientacioncasa.setActivo(activeList[3]);
                                        orientacioncasa.getScaleList().get(0).setPuntaje(calificationOrientarseCasa);
                                        orientacioncasa.update();
                                        sintomaDao.update(orientacioncasa);

                                        escalaDao.update(orientacioncasa.getScaleList().get(0));

                                        //orientacioncasa.refresh();
                                        Log.e("guardado y actualizado",orientacioncasa.getScaleList().get(0).getPuntaje().toString());


                                    }catch (Exception e){
                                    }
                                    try{
                                        orientacioncalle = helper.getSintoma(patientid,"Blessed","orientacion","orientacioncalle");
                                        orientacioncalle.setActivo(activeList[4]);
                                        orientacioncalle.getScaleList().get(0).setPuntaje(calificationOrientarseCalle);
                                        sintomaDao.update(orientacioncalle);

                                        escalaDao.update(orientacioncalle.getScaleList().get(0));
                                        //orientacioncalle.update();
                                        //orientacioncalle.refresh();
                                        Log.e("guardado y actualizado",orientacioncalle.getScaleList().get(0).getPuntaje().toString());


                                    }catch (Exception e){
                                    }
                                    try{
                                        orientacionentorno = helper.getSintoma(patientid,"Blessed","orientacion","orientacionentorno");
                                        orientacionentorno.setActivo(activeList[5]);
                                        orientacionentorno.getScaleList().get(0).setPuntaje(calificationValorarEntorno);
                                        sintomaDao.update(orientacionentorno);

                                        escalaDao.update(orientacionentorno.getScaleList().get(0));
                                        //orientacionentorno.update();
                                        //orientacionentorno.refresh();
                                        Log.e("guardado y actualizado",orientacionentorno.getScaleList().get(0).getPuntaje().toString());

                                    }catch (Exception e){
                                    }

                                    try{
                                        memoriaolvidosbenignos = helper.getSintoma(patientid,"Blessed","memoria","memoriaolvidosbenignos");
                                        memoriaolvidosbenignos.setActivo(activeList[6]);
                                        memoriaolvidosbenignos.getScaleList().get(0).setPuntaje(calificationRecodarPacientes);
                                        sintomaDao.update(memoriaolvidosbenignos);

                                        escalaDao.update(memoriaolvidosbenignos.getScaleList().get(0));
                                        //memoriaolvidosbenignos.update();
                                        //memoriaolvidosbenignos.refresh();

                                        Log.e("guardado y actualizado", memoriaolvidosbenignos.getScaleList().get(0).getPuntaje().toString());


                                    }catch (Exception e){
                                    }

                                    try{
                                        tendenciarememorar = helper.getSintoma(patientid,"Blessed","memoria","memoriatendenciarememorar");
                                        tendenciarememorar.setActivo(activeList[7]);
                                        tendenciarememorar.getScaleList().get(0).setPuntaje(calificationRememorarPasado);
                                        sintomaDao.update(tendenciarememorar);
                                        escalaDao.update(tendenciarememorar.getScaleList().get(0));

                                        //tareasdomesticas.update();
                                        Log.e("guardado y actualizado", tendenciarememorar.getScaleList().get(0).getPuntaje().toString());

                                    }catch (Exception e){    }



                                    Log.e("Add patient 2","Dibujo "+var_dibujo);




                                } else {
                                    int var_mec = 0;
                                    int var_gds = 0;
                                    Patient patient2 = new Patient(null,var_nombre,var_sexo,var_fecha,var_foto,var_eps,parse_id,var_escolaridad,var_institucionalizado,var_antecedentes,var_sindromes,var_observaciones,null,null,var_vision,var_escritura,var_dibujo);
                                    patientDao.insert(patient2);


                                    String var_tareasdomesticas;

                                    String[] calificaciones = new String[8];
                                    calificaciones[0]= calificationTareasDomesticos;
                                    calificaciones[1]= calificationPequenasDinero;
                                    calificaciones[2]= calificationListasCortas;
                                    calificaciones[3] = calificationOrientarseCasa;
                                    calificaciones[4] = calificationOrientarseCalle;
                                    calificaciones[5]= calificationValorarEntorno;
                                    calificaciones[6]= calificationRecodarPacientes;
                                    calificaciones[7] = calificationRememorarPasado;




                                    if (calificaciones[0]==null){calificaciones[0]="0.0"; Log.e("Para guardar0",calificaciones[0]);} else{ Log.e("Para guardar0",calificaciones[0].toString());}
                                    if (calificaciones[1]==null){calificaciones[1]="0.0";Log.e("Para guardar1",calificaciones[1]);} else {Log.e("Para guardar1",calificaciones[1].toString());}
                                    if (calificaciones[2]==null){calificaciones[2]="0.0";Log.e("Para guardar2",calificaciones[2]);} else {Log.e("Para guardar2",calificaciones[2].toString());}
                                    if (calificaciones[3]==null){calificaciones[3]="0.0";Log.e("Para guardar3",calificaciones[3]);} else {Log.e("Para guardar3",calificaciones[3].toString());}
                                    if (calificaciones[4]==null){calificaciones[4]="0.0";Log.e("Para guardar4",calificaciones[4]);} else {Log.e("Para guardar4",calificaciones[4].toString());}
                                    if (calificaciones[5]==null){calificaciones[5]="0.0";Log.e("Para guardar5",calificaciones[5]);} else {Log.e("Para guardar5",calificaciones[5].toString());}
                                    if (calificaciones[6]==null){calificaciones[6]="0.0";Log.e("Para guardar6",calificaciones[6]);} else {Log.e("Para guardar6",calificaciones[6].toString());}
                                    if (calificaciones[7]==null){calificaciones[7]="0.0";Log.e("Para guardar7",calificaciones[7]);} else {Log.e("Para guardar7",calificaciones[7].toString());}




                                    //INICIAR SINTOMAS


                                    Sintoma  sintomaLenguaje,sintomaHigiene,sintomaAnimo,sintomaMovilidad,sintomaAlimentacion,sintomaOrientacion,sintomaMemoria,sintomaVestimenta;


                                    //HIGIENE
                                    Sintoma sintomahigieneayudabanarse = new Sintoma(null,patient2.getId(),"higiene","higieneayudabanarse",false);
                                    Sintoma sintomahigieneayudainodoro = new Sintoma(null,patient2.getId(),"higiene","higieneayudainodoro",false);
                                    Sintoma sintomahigieneayudasoltarbano = new Sintoma(null,patient2.getId(),"higiene","higieneayudasoltarbano",false);
                                    Sintoma sintomaincontinensiaurinaria = new Sintoma(null,patient2.getId(),"higiene","higieneayudaincontinensiaurinaria",false);
                                    Sintoma sintomahigieneayudaincontinensiafecal = new Sintoma(null,patient2.getId(),"higiene","higieneayudaincontinensiafecal",false);



                                    sintomaDao.insert(sintomahigieneayudabanarse);
                                    sintomaDao.insert(sintomahigieneayudainodoro);
                                    sintomaDao.insert(sintomahigieneayudasoltarbano);
                                    sintomaDao.insert(sintomaincontinensiaurinaria);
                                    sintomaDao.insert(sintomahigieneayudaincontinensiafecal);


                                    //MOVILIDAD
                                    Sintoma sintomamovilidadsitioslejanos = new Sintoma(null,patient2.getId(),"movilidad","movilidadsitioslejanos",false);
                                    Sintoma sintomamovilidadcaminar = new Sintoma(null,patient2.getId(),"movilidad","movilidadcaminar",false);
                                    Sintoma sintomamovilidadsentarse = new Sintoma(null,patient2.getId(),"movilidad","movilidadsentarse",false);
                                    Sintoma sintomamovilidadcabeza = new Sintoma(null,patient2.getId(),"movilidad","movilidadcabeza",false);

                                    sintomaDao.insert(sintomamovilidadsitioslejanos);
                                    sintomaDao.insert(sintomamovilidadcaminar);
                                    sintomaDao.insert(sintomamovilidadsentarse);
                                    sintomaDao.insert(sintomamovilidadcabeza);


                                    //MEDICACION
                                    Sintoma medicacionmedicamentos = new Sintoma(null,patient2.getId(),"medicacion","medicacionmedicamentos",false);
                                    sintomaDao.insert(medicacionmedicamentos);

                                    //VESTIMENTA

                                    Sintoma sintomavestimentaactividades = new Sintoma(null,patient2.getId(),"vestimenta","vestimentaactividades",false);

                                    Sintoma sintomavestimentafallosocasionales = new Sintoma(null,patient2.getId(),"vestimenta","vestimentafallosocasionales",false);
                                    Sintoma sintomavestimentaseleccionar = new Sintoma(null,patient2.getId(),"vestimenta","vestimentaseleccionar",false);
                                    Sintoma sintomavestimentasecuencia = new Sintoma(null,patient2.getId(),"vestimenta","vestimentasecuencia",false);
                                    Sintoma sintomavestimentaayudavestirse = new Sintoma(null,patient2.getId(),"vestimenta","vestimentaayudavestirse",false);
                                    Sintoma sintomavestimentaincapaz = new Sintoma(null,patient2.getId(),"vestimenta","vestimentaincapaz",false);

                                    sintomaDao.insert(sintomavestimentaactividades);

                                    sintomaDao.insert(sintomavestimentafallosocasionales);
                                    sintomaDao.insert(sintomavestimentaseleccionar);
                                    sintomaDao.insert(sintomavestimentasecuencia);
                                    sintomaDao.insert(sintomavestimentaayudavestirse);
                                    sintomaDao.insert(sintomavestimentaincapaz);

                                    //ACTIVIDADES INSTRUMENTALES DE LA VIDA DIARIA
                                    Sintoma sintomaincapacidadtareasdomesticas = new Sintoma(null,patient2.getId(),"instrumentales","incapacidadtareasdomesticas",false);
                                    Sintoma sintomaincapacidadpequenasdinero = new Sintoma(null,patient2.getId(),"instrumentales","incapacidadpequenasdinero",false);

                                    Sintoma sintomainstrumentalestelefono = new Sintoma(null,patient2.getId(),"instrumentales","instrumentalestelefono",false);
                                    Sintoma sintomainstrumentalescompras = new Sintoma(null,patient2.getId(),"instrumentales","instrumentalescompras",false);
                                    Sintoma sintomainstrumentalescuidacasa = new Sintoma(null,patient2.getId(),"instrumentales","instrumentalescuidacasa",false);
                                    Sintoma sintomainstrumentalescomida = new Sintoma(null,patient2.getId(),"instrumentales","instrumentalescomida",false);

                                    Sintoma sintomainstrumentaleslavaropa = new Sintoma(null,patient2.getId(),"instrumentales","instrumentaleslavaropa",false);
                                    Sintoma sintomainstrumentalestransporte = new Sintoma(null,patient2.getId(),"instrumentales","instrumentalestransporte",false);
                                    Sintoma sintomainstrumentalesmedicacion = new Sintoma(null,patient2.getId(),"instrumentales","instrumentalesmedicacion",false);



                                    sintomaDao.insert(sintomaincapacidadtareasdomesticas);
                                    sintomaDao.insert(sintomaincapacidadpequenasdinero);
                                    sintomaDao.insert(sintomainstrumentalestelefono);
                                    sintomaDao.insert(sintomainstrumentalescompras);
                                    sintomaDao.insert(sintomainstrumentalescomida);

                                    sintomaDao.insert(sintomainstrumentalescuidacasa);
                                    sintomaDao.insert(sintomainstrumentaleslavaropa);
                                    sintomaDao.insert(sintomainstrumentalestransporte);
                                    sintomaDao.insert(sintomainstrumentalesmedicacion);



                                    //LENGUAJE
                                    Sintoma sintomalenguajelimitado = new Sintoma(null,patient2.getId(),"lenguaje","lenguajelimitado",false);
                                    Sintoma sintomalenguajepalabra = new Sintoma(null,patient2.getId(),"lenguaje","lenguajepalabra",false);

                                    sintomaDao.insert(sintomalenguajelimitado);
                                    sintomaDao.insert(sintomalenguajepalabra);

                                    //MEMORIA

                                    Sintoma sintomamemoriatendenciarememorar = new Sintoma(null,patient2.getId(),"memoria","memoriatendenciarememorar",false);
                                    Sintoma sintomamemoriaolvidosbenignos = new Sintoma(null,patient2.getId(),"memoria","memoriaolvidosbenignos",false);
                                    Sintoma sintomamemorialistascortas = new Sintoma(null,patient2.getId(),"memoria","memorialistascortas",false);


                                    sintomaDao.insert(sintomamemoriatendenciarememorar);
                                    sintomaDao.insert(sintomamemoriaolvidosbenignos);
                                    sintomaDao.insert(sintomamemorialistascortas);


                                    //ORIENTACION

                                    Sintoma sintomaorientacioncasa= new Sintoma(null,patient2.getId(),"orientacion","orientacioncasa",false);
                                    Sintoma sintomaorientacioncalle = new Sintoma(null,patient2.getId(),"orientacion","orientacioncalle",false);
                                    Sintoma sintomaorientacionentorno = new Sintoma(null,patient2.getId(),"orientacion","orientacionentorno",false);


                                    sintomaDao.insert(sintomaorientacioncasa);
                                    sintomaDao.insert(sintomaorientacioncalle);
                                    sintomaDao.insert(sintomaorientacionentorno);

                                    //ALIMENTACION
                                    Sintoma sintomaalimentacioncuchara = new Sintoma(null,patient2.getId(),"alimentacion","alimentacioncuchara",false);
                                    Sintoma sintomaalimentacionsolidos = new Sintoma(null,patient2.getId(),"alimentacion","alimentacionsolidos",false);
                                    Sintoma sintomaalimentaciondependientes = new Sintoma(null,patient2.getId(),"alimentacion","alimentaciondependientes",false);
                                    sintomaDao.insert(sintomaalimentacioncuchara);
                                    sintomaDao.insert(sintomaalimentacionsolidos);
                                    sintomaDao.insert(sintomaalimentaciondependientes);


                                    //ESTADO DE ANIMO
                                    Sintoma sintomaanimosonrisa = new Sintoma(null,patient2.getId(),"animo","animosonrisa",false);
                                    sintomaDao.insert(sintomaanimosonrisa);

                                    //PERSONALIDAD Y COMPORTAMIENTO

                                    //    private CheckBox rdgPersonalidadRetraimiento,rdgPersonalidadEgocentrismo,rdgPersonalidadPerdidaInteres,rdgPersonalidadAfectividadEmbotada,rdgPersonalidadPerturbacionEmocional,rdgPersonalidadHilaridadInapropiada,rdgPersonalidadRespuestaEmocional,rdgPersonalidadIndiscrecionesSexuales,rdgPersonalidadFaltaInteres,rdgPersonalidadDisminucionIniciativa,rdgPersonalidadHiperactividadJustificada;
                                    Sintoma sintomapersonalidadretraimiento = new Sintoma(null,patient2.getId(),"personalidad","personalidadretraimiento",false);
                                    Sintoma sintomapersonalidadegocentrismo = new Sintoma(null,patient2.getId(),"personalidad","personalidadegocentrismo",false);
                                    Sintoma sintomapersonalidadperdidainteres = new Sintoma(null,patient2.getId(),"personalidad","personalidadperdidainteres",false);
                                    Sintoma sintomapersonalidadafectividadembotada = new Sintoma(null,patient2.getId(),"personalidad","personalidadafectividadembotada",false);
                                    Sintoma sintomapersonalidadperturbacionemocional = new Sintoma(null,patient2.getId(),"personalidad","personalidadperturbacionemocional",false);

                                    Sintoma sintomapersonalidadindiscrecionessexuales = new Sintoma(null,patient2.getId(),"personalidad","personalidadindiscrecionessexuales",false);


                                    Sintoma sintomapersonalidadhilaridadinapropiada = new Sintoma(null,patient2.getId(),"personalidad","personalidadhilaridadinapropiada",false);
                                    Sintoma sintomapersonalidadrespuestaemocional = new Sintoma(null,patient2.getId(),"personalidad","personalidadrespuestaemocional",false);
                                    Sintoma sintomapersonalidadfaltainteres = new Sintoma(null,patient2.getId(),"personalidad","personalidadfaltainteres",false);
                                    Sintoma sintomapersonalidaddisminucioniniciativa = new Sintoma(null,patient2.getId(),"personalidad","personalidaddisminucioniniciativa",false);
                                    Sintoma sintomapersonalidadhiperactividadjustificada = new Sintoma(null,patient2.getId(),"personalidad","personalidadhiperactividadjustificada",false);

                                    sintomaDao.insert(sintomapersonalidadretraimiento);
                                    sintomaDao.insert(sintomapersonalidadegocentrismo);
                                    sintomaDao.insert(sintomapersonalidadperdidainteres);
                                    sintomaDao.insert(sintomapersonalidadafectividadembotada);
                                    sintomaDao.insert(sintomapersonalidadperturbacionemocional);
                                    sintomaDao.insert(sintomapersonalidadindiscrecionessexuales);

                                    sintomaDao.insert(sintomapersonalidadhilaridadinapropiada);
                                    sintomaDao.insert(sintomapersonalidadrespuestaemocional);
                                    sintomaDao.insert(sintomapersonalidadfaltainteres);
                                    sintomaDao.insert(sintomapersonalidaddisminucioniniciativa);
                                    sintomaDao.insert(sintomapersonalidadhiperactividadjustificada);



                                    //INICIAR ESCALAS

                                /* detallefast.addStringProperty("stagename");
                                detallefast.addStringProperty("characteristics");
                                detallefast.addStringProperty("mentalage");
                                detallefast.addStringProperty("mmsescore");*/




                                    Scale scalehigieneayudabanarse = new Scale(null,sintomahigieneayudabanarse.getId(),"FAST","62");

                                    //Scale scalehigieneayudainodoro = new Scale(null,sintomahigieneayudainodoro.getId(),"FAST","63");
                                    Scale scalehigieneayudasoltarbano = new Scale(null,sintomahigieneayudasoltarbano.getId(),"FAST","63");

                                    Scale scaleincontinensiaurinaria1 = new Scale(null,sintomaincontinensiaurinaria.getId(),"FAST","64");
                                    Scale scaleincontinensiaurinaria2 = new Scale(null,sintomaincontinensiaurinaria.getId(),"Blessed","2.0");

                                    Scale scalehigieneayudaincontinensiafecal1 = new Scale(null,sintomahigieneayudaincontinensiafecal.getId(),"FAST","65");
                                    Scale scalehigieneayudaincontinensiafecal2 = new Scale(null,sintomahigieneayudaincontinensiafecal.getId(),"Blessed","3.0");

                                    escalaDao.insert(scalehigieneayudabanarse);
                                    //escalaDao.insert(scalehigieneayudainodoro);
                                    escalaDao.insert(scalehigieneayudasoltarbano);
                                    escalaDao.insert(scaleincontinensiaurinaria1);
                                    escalaDao.insert(scaleincontinensiaurinaria2);
                                    escalaDao.insert(scalehigieneayudaincontinensiafecal1);
                                    escalaDao.insert(scalehigieneayudaincontinensiafecal2);






                                    Scale scalemovilidadsitioslejanos = new Scale(null,sintomamovilidadsitioslejanos.getId(),"FAST","30");
                                    Scale scalemovilidadcaminar = new Scale(null,sintomamovilidadcaminar.getId(),"FAST","73");
                                    Scale scalemovilidadsentarse = new Scale(null,sintomamovilidadsentarse.getId(),"FAST","74");
                                    Scale scalemovilidadcabeza = new Scale(null,sintomamovilidadcabeza.getId(),"FAST","76");
                                    escalaDao.insert(scalemovilidadsitioslejanos);
                                    escalaDao.insert(scalemovilidadcaminar);
                                    escalaDao.insert(scalemovilidadsentarse);
                                    escalaDao.insert(scalemovilidadcabeza);


                                    Scale scalevestimentaactividades = new Scale(null,sintomavestimentaactividades.getId(),"FAST","40");



                                    Scale scalevestimentafallosocasionales = new Scale(null,sintomavestimentafallosocasionales.getId(),"Blessed","1.0");
                                    Scale scalevestimentaseleccionar = new Scale(null,sintomavestimentaseleccionar.getId(),"FAST","50");

                                    Scale scalevestimentasecuencia = new Scale(null,sintomavestimentasecuencia.getId(),"Blessed","2.0");
                                    Scale scalevestimentaayudavestirse = new Scale(null,sintomavestimentaayudavestirse.getId(),"FAST","61");
                                    Scale scalevestimentaincapaz = new Scale(null,sintomavestimentaincapaz.getId(),"Blessed","3.0");

                                    escalaDao.insert(scalevestimentaactividades);

                                    escalaDao.insert(scalevestimentafallosocasionales);
                                    escalaDao.insert(scalevestimentaseleccionar);
                                    escalaDao.insert(scalevestimentasecuencia);
                                    escalaDao.insert(scalevestimentaayudavestirse);
                                    escalaDao.insert(scalevestimentaincapaz);

                                    Scale scalelenguajelimitado = new Scale(null,sintomalenguajelimitado.getId(),"FAST","71");
                                    Scale scalelenguajepalabra = new Scale(null,sintomalenguajepalabra.getId(),"FAST","72");
                                    escalaDao.insert(scalelenguajelimitado);
                                    escalaDao.insert(scalelenguajepalabra);

                                    Scale scalememoriatendenciarememorar = new Scale(null,sintomamemoriatendenciarememorar.getId(),"Blessed","1.0");
                                    Scale scalememoriaolvidosbenignos1 = new Scale(null,sintomamemoriaolvidosbenignos.getId(),"FAST","20");
                                    Scale scalememoriaolvidosbenignos2 = new Scale(null,sintomamemoriaolvidosbenignos.getId(),"Blessed","1.0");
                                    Scale scalememorialistascortas = new Scale(null,sintomamemorialistascortas.getId(),"Blessed","1.0");

                                    escalaDao.insert(scalememoriatendenciarememorar);
                                    escalaDao.insert(scalememoriaolvidosbenignos1);
                                    escalaDao.insert(scalememoriaolvidosbenignos2);
                                    escalaDao.insert(scalememorialistascortas);



                                    Scale scaleorientacioncasa = new Scale(null,sintomaorientacioncasa.getId(),"Blessed","1.0");
                                    Scale scaleorientacioncalle = new Scale(null,sintomaorientacioncalle.getId(),"Blessed","1.0");
                                    Scale scaleorientacionentorno = new Scale(null,sintomaorientacionentorno.getId(),"Blessed","1.0");
                                    escalaDao.insert(scaleorientacioncasa);
                                    escalaDao.insert(scaleorientacioncalle);
                                    escalaDao.insert(scaleorientacionentorno);




                                    Scale scalealimentacioncuchara = new Scale(null,sintomaalimentacioncuchara.getId(),"Blessed","1.0");
                                    Scale scalealimentacionsolidos = new Scale(null,sintomaalimentacionsolidos.getId(),"Blessed","2.0");
                                    Scale scalealimentaciondependientes = new Scale(null,sintomaalimentaciondependientes.getId(),"Blessed","3.0");
                                    escalaDao.insert(scalealimentacioncuchara);
                                    escalaDao.insert(scalealimentacionsolidos);
                                    escalaDao.insert(scalealimentaciondependientes);


                                    Scale scaleanimosonrisa = new Scale(null,sintomaanimosonrisa.getId(),"FAST","75");
                                    escalaDao.insert(scaleanimosonrisa);


                                    Scale scalepersonalidadretraimiento = new Scale(null,sintomapersonalidadretraimiento.getId(),"Blessed","1.0");
                                    Scale scalepersonalidadegocentrismo = new Scale(null,sintomapersonalidadegocentrismo.getId(),"Blessed","1.0");
                                    Scale scalepersonalidadperdidainteres = new Scale(null,sintomapersonalidadperdidainteres.getId(),"Blessed","1.0");
                                    Scale scalepersonalidadafectividadembotada = new Scale(null,sintomapersonalidadafectividadembotada.getId(),"Blessed","1.0");
                                    Scale scalepersonalidadperturbacionemocional  = new Scale(null,sintomapersonalidadperturbacionemocional.getId(),"Blessed","1.0");

                                    Scale scalepersonalidadindiscrecionessexuales = new Scale(null,sintomapersonalidadindiscrecionessexuales.getId(),"Blessed","1.0");


                                    Scale scalepersonalidadhilaridadinapropiada= new Scale(null,sintomapersonalidadhilaridadinapropiada.getId(),"Blessed","1.0");
                                    Scale scalepersonalidadrespuestaemocional = new Scale(null,sintomapersonalidadrespuestaemocional.getId(),"Blessed","1.0");
                                    Scale scalepersonalidadfaltainteres = new Scale(null,sintomapersonalidadfaltainteres.getId(),"Blessed","1.0");
                                    Scale scalepersonalidaddisminucioniniciativa = new Scale(null,sintomapersonalidaddisminucioniniciativa.getId(),"Blessed","1.0");
                                    Scale scalepersonalidadhiperactividadjustificada = new Scale(null,sintomapersonalidadhiperactividadjustificada.getId(),"Blessed","1.0");

                                    escalaDao.insert(scalepersonalidadretraimiento);
                                    escalaDao.insert(scalepersonalidadegocentrismo);
                                    escalaDao.insert(scalepersonalidadperdidainteres);
                                    escalaDao.insert(scalepersonalidadafectividadembotada);
                                    escalaDao.insert(scalepersonalidadperturbacionemocional);

                                    escalaDao.insert(scalepersonalidadindiscrecionessexuales);

                                    escalaDao.insert(scalepersonalidadhilaridadinapropiada);
                                    escalaDao.insert(scalepersonalidadrespuestaemocional);
                                    escalaDao.insert(scalepersonalidadfaltainteres);
                                    escalaDao.insert(scalepersonalidaddisminucioniniciativa);
                                    escalaDao.insert(scalepersonalidadhiperactividadjustificada);



                                    //PERSONALIDAD

                                    Scale scaleincapacidadtareasdomesticas1 = new Scale(null,sintomaincapacidadtareasdomesticas.getId(),"Blessed","1.0");

                                    Scale scaleincapacidadpequenasdinero1 = new Scale(null,sintomaincapacidadpequenasdinero.getId(),"Blessed","1.0");
                                    Scale scaleincapacidadpequenasdinero2 = new Scale(null,sintomaincapacidadpequenasdinero.getId(),"Lawton","1");

                                    Scale scaleinstrumentalestelefono = new Scale(null,sintomainstrumentalestelefono.getId(),"Lawton","1");
                                    Scale scaleinstrumentalescompras = new Scale(null,sintomainstrumentalescompras.getId(),"Lawton","1");

                                    Scale scaleinstrumentalescomida = new Scale(null,sintomainstrumentalescomida.getId(),"Lawton","1");
                                    Scale scaleinstrumentalescuidacasa = new Scale(null,sintomainstrumentalescuidacasa.getId(),"Lawton","1");
                                    Scale scaleinstrumentaleslavaropa = new Scale(null,sintomainstrumentaleslavaropa.getId(),"Lawton","1");
                                    Scale scaleinstrumentalestransporte = new Scale(null,sintomainstrumentalestransporte.getId(),"Lawton","1");
                                    Scale scaleinstrumentalesmedicacion = new Scale(null,sintomainstrumentalesmedicacion.getId(),"Lawton","1");


                                    escalaDao.insert(scaleincapacidadtareasdomesticas1);
                                    escalaDao.insert(scaleincapacidadpequenasdinero1);
                                    escalaDao.insert(scaleincapacidadpequenasdinero2);
                                    escalaDao.insert(scaleinstrumentalestelefono);
                                    escalaDao.insert(scaleinstrumentalescompras);
                                    escalaDao.insert(scaleinstrumentalescomida);
                                    escalaDao.insert(scaleinstrumentalescuidacasa);
                                    escalaDao.insert(scaleinstrumentaleslavaropa);
                                    escalaDao.insert(scaleinstrumentalestransporte);
                                    escalaDao.insert(scaleinstrumentalesmedicacion);




                                    try{
                                        tareasdomesticas = helper.getSintoma(patient2.getId(),"Blessed","instrumentales","incapacidadtareasdomesticas");
                                        tareasdomesticas.setActivo(activeList[0]);
                                        tareasdomesticas.getScaleList().get(0).setPuntaje(calificationTareasDomesticos);
                                        sintomaDao.update(tareasdomesticas);
                                        escalaDao.update(tareasdomesticas.getScaleList().get(0));

                                        Log.e("guardado y actualizado",tareasdomesticas.getScaleList().get(0).getPuntaje().toString());


                                    }catch (Exception e){}
                                    try{
                                        pequenasdinero = helper.getSintoma(patient2.getId(),"Blessed","instrumentales","incapacidadpequenasdinero");
                                        pequenasdinero.setActivo(activeList[1]);
                                        pequenasdinero.getScaleList().get(0).setPuntaje(calificationPequenasDinero);
                                        sintomaDao.update(pequenasdinero);
                                        escalaDao.update(pequenasdinero.getScaleList().get(0));
                                        //pequenasdinero.update();
                                        //pequenasdinero.refresh();
                                        Log.e("guardado y actualizado",pequenasdinero.getScaleList().get(0).getPuntaje().toString());

                                    }catch (Exception e){}

                                    try{
                                        memorialistascortas = helper.getSintoma(patient2.getId(),"Blessed","memoria","memorialistascortas");
                                        memorialistascortas.setActivo(activeList[2]);
                                        memorialistascortas.getScaleList().get(0).setPuntaje(calificationListasCortas);
                                        sintomaDao.update(memorialistascortas);
                                        escalaDao.update(memorialistascortas.getScaleList().get(0));
                                        //memorialistascortas.update();
                                        //memorialistascortas.refresh();
                                        Log.e("guardado y actualizado",memorialistascortas.getScaleList().get(0).getPuntaje().toString());


                                    }catch (Exception e){
                                    }
                                    try{
                                        orientacioncasa = helper.getSintoma(patient2.getId(),"Blessed","orientacion","orientacioncasa");
                                        orientacioncasa.setActivo(activeList[3]);
                                        orientacioncasa.getScaleList().get(0).setPuntaje(calificationOrientarseCasa);
                                        sintomaDao.update(orientacioncasa);
                                        escalaDao.update(orientacioncasa.getScaleList().get(0));
                                        //orientacioncasa.update();
                                        //orientacioncasa.refresh();
                                        Log.e("guardado y actualizado",orientacioncasa.getScaleList().get(0).getPuntaje().toString());


                                    }catch (Exception e){
                                    }
                                    try{
                                        orientacioncalle = helper.getSintoma(patient2.getId(),"Blessed","orientacion","orientacioncalle");
                                        orientacioncalle.setActivo(activeList[4]);
                                        orientacioncalle.getScaleList().get(0).setPuntaje(calificationOrientarseCalle);
                                        sintomaDao.update(orientacioncalle);
                                        escalaDao.update(orientacioncalle.getScaleList().get(0));
                                        //orientacioncalle.update();
                                        //orientacioncalle.refresh();
                                        Log.e("guardado y actualizado",orientacioncalle.getScaleList().get(0).getPuntaje().toString());


                                    }catch (Exception e){
                                    }
                                    try{
                                        orientacionentorno = helper.getSintoma(patient2.getId(),"Blessed","orientacion","orientacionentorno");
                                        orientacionentorno.setActivo(activeList[5]);
                                        orientacionentorno.getScaleList().get(0).setPuntaje(calificationValorarEntorno);
                                        sintomaDao.update(orientacionentorno);
                                        escalaDao.update(orientacionentorno.getScaleList().get(0));
                                        //orientacionentorno.update();
                                        Log.e("guardado y actualizado",orientacionentorno.getScaleList().get(0).getPuntaje().toString());

                                    }catch (Exception e){
                                    }

                                    try{
                                        memoriaolvidosbenignos = helper.getSintoma(patient2.getId(),"Blessed","memoria","memoriaolvidosbenignos");
                                        memoriaolvidosbenignos.setActivo(activeList[6]);
                                        memoriaolvidosbenignos.getScaleList().get(0).setPuntaje(calificationRecodarPacientes);
                                        sintomaDao.update(memoriaolvidosbenignos);
                                        escalaDao.update(memoriaolvidosbenignos.getScaleList().get(0));
                                        //memoriaolvidosbenignos.update();
                                        //memoriaolvidosbenignos.refresh();

                                        Log.e("guardado y actualizado", memoriaolvidosbenignos.getScaleList().get(0).getPuntaje().toString());


                                    }catch (Exception e){
                                    }

                                    try{
                                        tendenciarememorar = helper.getSintoma(patient2.getId(),"Blessed","memoria","memoriatendenciarememorar");
                                        tendenciarememorar.setActivo(activeList[7]);
                                        tendenciarememorar.getScaleList().get(0).setPuntaje(calificationRememorarPasado);
                                        sintomaDao.update(tendenciarememorar);
                                        escalaDao.update(tendenciarememorar.getScaleList().get(0));
                                        //tareasdomesticas.update();
                                        //tareasdomesticas.refresh();
                                        Log.e("guardado y actualizado", tendenciarememorar.getScaleList().get(0).getPuntaje().toString());

                                    }catch (Exception e){    }


                                }

                                //-------------------------------


                            }catch (Exception e)
                            {
                                Log.e("Error blessed","Atrapo la excepcion en blessed data");

                            }
                        }
                    }).start();
                    Intent ir_main = new Intent(this, MainActivity.class);
                    startActivity(ir_main);
                    overridePendingTransition(R.anim.left_in, R.anim.left_out);
                    finish();
                /*if (validar(e_peso.getText().toString(), e_color.getText().toString(), e_raza.getText().toString(), e_id_padre.getText().toString(), e_id_madre.getText().toString()) == false) {
                    Toast.makeText(this, "Debe   @OverrideLlenar Todos Los Campos", Toast.LENGTH_LONG).show();
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


                    break;
                case R.id.btn_atras_paciente:
                    super.onBackPressed();
                    overridePendingTransition(R.anim.right_in, R.anim.right_out);
                    break;
            }
    }


}
