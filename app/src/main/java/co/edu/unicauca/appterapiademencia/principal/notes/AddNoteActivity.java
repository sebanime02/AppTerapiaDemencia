package co.edu.unicauca.appterapiademencia.principal.notes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import co.edu.unicauca.appterapiademencia.R;
import co.edu.unicauca.appterapiademencia.domain.Note;
import co.edu.unicauca.appterapiademencia.domain.Patient;
import co.edu.unicauca.appterapiademencia.domain.Sintoma;
import co.edu.unicauca.appterapiademencia.domain.User;
import co.edu.unicauca.appterapiademencia.domain.dao.GreenDaoHelper;
import co.edu.unicauca.appterapiademencia.domain.dao.NoteDao;
import co.edu.unicauca.appterapiademencia.domain.dao.ScaleDao;
import co.edu.unicauca.appterapiademencia.domain.dao.SintomaDao;
import co.edu.unicauca.appterapiademencia.principal.patientlist.PatientProfileActivity;
import de.greenrobot.event.EventBus;

/**
 * Created by SEBAS on 14/11/2016.
 */

public class AddNoteActivity extends AppCompatActivity implements View.OnClickListener, CheckBox.OnCheckedChangeListener{
    private Calendar calendar;
    private int year, month, day;
    private String ownertext;
    private EditText description,owner;
    private String date;
    private String hour;
    private RadioButton rdgMejora,rdgNeutral,rdgRetroceso,rdgIncidente,rdgAdverso,rdgCentinela;
    private TextView txt_adverso;
    private ImageButton instrumentales,orientacion,movility,eating,fall,medication,estadodeanimo,otro,changeBehaviour,higiene,memoria,lenguaje,vestimenta;
    private RadioGroup rdgGrupo,rdgInstrumentales,rdgMedicacion,rdgMovilidad,rdgAlimentacion,rdgCambioPersonalidad,rdgOrientacion,rdgLenguaje,rdgMemoria,rdgHigiene,rdgVestimenta,rdgAnimo,rdgPersonalidad;
    private CheckBox rdgMovilidadOtro,rdgMovilidadSitiosLejanos,rdgMovilidadCaminar,rdgMovilidadSentarse,rdgMovilidadCabeza;
    private CheckBox rdgHigieneAyudaBanarse,rdgHigieneSoltarBano,rdgHigieneAyudaInodoro,rdgHigieneIncontinensiaUrinaria,rdgHigieneIncontinensiaFecal;
    private CheckBox rdgVestimentaActividades,rdgVestimentaFallosOcasionales,rdgVestimentaSeleccionar,rdgVestimentaSecuencia,rdgVestimentaAyudaVestirse,rdgVestimentaIncapaz;
    private CheckBox rdgMemoriaTendenciaRememorar,rdgMemoriaOlvidosBenignos,rdgMemoriaListasCortas;
    private CheckBox rdgLenguajeLimitado,rdgLenguajePalabra;
    private CheckBox rdgAlimentacionCuchara,rdgAlimentacionSolidos,rdgAlimentacionDependiente;
    private CheckBox rdgPersonalidadRetraimiento,rdgPersonalidadEgocentrismo,rdgPersonalidadPerdidaInteres,rdgPersonalidadAfectividadEmbotada,rdgPersonalidadPerturbacionEmocional,rdgPersonalidadHilaridadInapropiada,rdgPersonalidadRespuestaEmocional,rdgPersonalidadIndiscrecionesSexuales,rdgPersonalidadFaltaInteres,rdgPersonalidadDisminucionIniciativa,rdgPersonalidadHiperactividadJustificada;
    private CheckBox rdgOrientacionCasa,rdgOrientacionCalle,rdgOrientacionEntorno;
    private CheckBox rdgAnimoSonrisa;
    private CheckBox rdgMedicamentos;
    private CheckBox rdgInstrumentalesTareasDomesticas,rdgInstrumentalesPequenasDinero,rdgInstrumentalesTelefono,rdgInstrumentalesCompras,rdgInstrumentalesComida,rdgInstrumentalesCasa,rdgInstrumentalesRopa,rdgInstrumentalesTransporte,rdgInstrumentalesMedicacion;
    private RadioButton rdgMedicacionRutinario,rdgMedicacionAdverso;
    private ArrayList<String> sintomasList;
    private ArrayList<String> nameTestList;
    private ArrayList<String> puntajeList;
    private ArrayList<Boolean> stateList;
    private CheckBox rdgTardia;
    private NoteDao noteDao;
    private ScaleDao scaleDao;
    private Long cedula;
    private GreenDaoHelper helper;
    private Long patientid;
    private Long userId;
    private String username;
    private String election;
    private String var_fecha;
    private String var_hora;
    private Toolbar toolbar;
    private ActionBar actionBar;
    private boolean optionOwner;
    private String var_owner;
    private boolean resultValidation=false;
    private boolean var_state;
    private String var_color;
    private int gravedad;
    private LinearLayout linear_efecto;
    private Boolean carerMessageIndicator = false;
    private SintomaDao sintomaDao;
    private int var_tipo;
    private String var_seleccion;
    private String nameTest;
    private String puntaje;
    private String nameTest2;
    private String puntaje2;
    private String nameTest3;
    private String puntaje3;
    private String var_seleccion2;
    private String var_seleccion3;

    public AddNoteActivity(){
        this.helper = GreenDaoHelper.getInstance();
        this.noteDao = helper.getNoteDao();
        this.scaleDao = helper.getScaleDao();
        this.sintomaDao= helper.getSintomaDao();
        this.sintomasList = new ArrayList<String>();
        this.puntajeList = new ArrayList<String>();
        this.nameTestList = new ArrayList<String>();
        this.stateList = new ArrayList<Boolean>();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_note);
        Bundle bundl=getIntent().getExtras();
        sintomasList.clear();
        puntajeList.clear();
        nameTestList.clear();
        stateList.clear();
        description= (EditText) findViewById(R.id.txt_description);

        movility= (ImageButton) findViewById(R.id.btn_movility);
        eating= (ImageButton) findViewById(R.id.btn_eating);
        fall= (ImageButton) findViewById(R.id.btn_fall);
        medication = (ImageButton) findViewById(R.id.btn_medication);
        lenguaje= (ImageButton) findViewById(R.id.btn_language);
        estadodeanimo= (ImageButton) findViewById(R.id.btn_estadodeanimo);
        higiene = (ImageButton) findViewById(R.id.btn_higiene);
        changeBehaviour = (ImageButton) findViewById(R.id.btn_changebehaviour);
        vestimenta = (ImageButton) findViewById(R.id.btn_vestimenta);
        memoria = (ImageButton) findViewById(R.id.btn_memory);
        orientacion = (ImageButton) findViewById(R.id.btn_orientation);
        instrumentales = (ImageButton) findViewById(R.id.btn_instrumentals);



        rdgTardia = (CheckBox) findViewById(R.id.rdgTardia);

       /*
        rdgGrupo = (RadioGroup) findViewById(R.id.rdgGrupo);
        //rdgRutinario = (RadioButton) findViewById(R.id.rdgRutinario);
        rdgMejora = (RadioButton) findViewById(R.id.rdgMejora);
        rdgAdverso = (RadioButton) findViewById(R.id.rdgAdverso);
        rdgRetroceso = (RadioButton) findViewById(R.id.rdgDeterioro);
        rdgNeutral = (RadioButton) findViewById(R.id.rdgNeutral);
        rdgMejora = (RadioButton) findViewById(R.id.rdgMejora);
        txt_adverso = (TextView) findViewById(R.id.txt_adverso);
        */

        linear_efecto = (LinearLayout) findViewById(R.id.linear_efecto);

        rdgMovilidad = (RadioGroup) findViewById(R.id.rdgMovilidad);
        rdgAlimentacion = (RadioGroup) findViewById(R.id.rdgAlimentacion);
        rdgLenguaje = (RadioGroup) findViewById(R.id.rdgLenguaje);
        rdgMemoria = (RadioGroup) findViewById(R.id.rdgMemoria);  //Aceptado
        rdgHigiene = (RadioGroup) findViewById(R.id.rdgHigiene);
        rdgVestimenta = (RadioGroup) findViewById(R.id.rdgVestimenta);
        rdgAnimo = (RadioGroup) findViewById(R.id.rdgAnimo);
        rdgMedicacion = (RadioGroup) findViewById(R.id.rdgMedicacion);
        rdgPersonalidad = (RadioGroup) findViewById(R.id.rdgPersonalidad);
        rdgOrientacion = (RadioGroup) findViewById(R.id.rdgOrientacion);
        rdgInstrumentales = (RadioGroup) findViewById(R.id.rdgInstrumentales);


        //rdgGrupo.setOnCheckedChangeListener(this);


        //HIGIENE
        rdgHigieneAyudaBanarse = (CheckBox) findViewById(R.id.rdgHigieneAyudaBanarse);
        rdgHigieneSoltarBano = (CheckBox) findViewById(R.id.rdgHigieneSoltarBano);

        rdgHigieneIncontinensiaUrinaria = (CheckBox) findViewById(R.id.rdgHigieneIncontinensiaUrinaria);
        rdgHigieneIncontinensiaFecal = (CheckBox) findViewById(R.id.rdgHigieneIncontinensiaFecal);

        //MOVILIDAD

        rdgMovilidadSitiosLejanos = (CheckBox) findViewById(R.id.rdgMovilidadSitiosLejanos);
        rdgMovilidadCaminar = (CheckBox) findViewById(R.id.rdgMovilidadCaminar);
        rdgMovilidadSentarse = (CheckBox) findViewById(R.id.rdgMovilidadSentarse);
        rdgMovilidadCabeza = (CheckBox) findViewById(R.id.rdgMovilidadCabeza);


        //VESTIMENTA

        //rdgVestimentaActividades = (CheckBox) findViewById(R.id.rdgVestimentaActividades);
        rdgVestimentaFallosOcasionales = (CheckBox) findViewById(R.id.rdgVestimentaFallosOcasionales);
        rdgVestimentaAyudaVestirse = (CheckBox) findViewById(R.id.rdgVestimentaAyudaVestirse);
        rdgVestimentaSeleccionar = (CheckBox) findViewById(R.id.rdgVestimentaSeleccionar);
        rdgVestimentaSecuencia = (CheckBox) findViewById(R.id.rdgVestimentaSecuenciaVestirse);
        rdgVestimentaIncapaz = (CheckBox) findViewById(R.id.rdgVestimentaIncapaz);

        //MEMORIA

        rdgMemoriaListasCortas = (CheckBox) findViewById(R.id.rdgMemoriaListasCortas);
        rdgMemoriaTendenciaRememorar = (CheckBox) findViewById(R.id.rdgMemoriaTendenciaRememorar);
        rdgMemoriaOlvidosBenignos = (CheckBox) findViewById(R.id.rdgMemoriaOlvidosBenignos);

        //MEDICACION
        rdgMedicamentos = (CheckBox) findViewById(R.id.rdgMedicacionMedicamentos);

        //INSTRUMENTALES
        //    private CheckBox rdgInstrumentalesTareasDomesticas,rdgInstrumentalesPequenasDinero,rdgInstrumentalesTelefono,rdgInstrumentalesCompras,rdgInstrumentalesComida,rdgInstrumentalesCasa,rdgInstrumentalesRopa,rdgInstrumentalesTransporte,rdgInstrumentalesMedicacion;

        rdgInstrumentalesTareasDomesticas = (CheckBox) findViewById(R.id.rdgInstrumentalesTareasDomesticas);
        rdgInstrumentalesPequenasDinero = (CheckBox) findViewById(R.id.rdgInstrumentalesPequenasDinero);
        rdgInstrumentalesTelefono = (CheckBox) findViewById(R.id.rdgInstrumentalesTelefono);
        rdgInstrumentalesCompras = (CheckBox) findViewById(R.id.rdgInstrumentalesCompras);
        rdgInstrumentalesComida = (CheckBox) findViewById(R.id.rdgInstrumentalesComida);

        rdgInstrumentalesRopa = (CheckBox) findViewById(R.id.rdgInstrumentalesLavaRopa);
        rdgInstrumentalesTransporte = (CheckBox) findViewById(R.id.rdgInstrumentalesTransporte);
        rdgInstrumentalesMedicacion = (CheckBox) findViewById(R.id.rdgInstrumentalesMedicacion);


        //LENGUAJE


        rdgLenguajeLimitado = (CheckBox) findViewById(R.id.rdgLenguajeLimitado);
        rdgLenguajePalabra= (CheckBox) findViewById(R.id.rdgLenguajePalabra);

        //ALIMENTACION
        rdgAlimentacionCuchara= (CheckBox) findViewById(R.id.rdgAlimentacionCuchara);
        rdgAlimentacionSolidos= (CheckBox) findViewById(R.id.rdgAlimentacionSolidos);
        rdgAlimentacionDependiente= (CheckBox) findViewById(R.id.rdgAlimentacionDependiente);

        //ANIMO SONRISA
        rdgAnimoSonrisa= (CheckBox) findViewById(R.id.rdgAnimoSonrisa);

        //rdgMedicacionAdverso = (RadioButton) findViewById(R.id.rdgMedicacionAdverso);
        //rdgMedicacionRutinario = (RadioButton) findViewById(R.id.rdgMedicacionAdverso);


        //PERSONALIDAD
        rdgPersonalidadRetraimiento = (CheckBox) findViewById(R.id.rdgPersonalidadRetraimiento);
         rdgPersonalidadEgocentrismo = (CheckBox) findViewById(R.id.rdgPersonalidadEgocentrismo);
        rdgPersonalidadPerdidaInteres = (CheckBox) findViewById(R.id.rdgPersonalidadPerdidaInteres);
        rdgPersonalidadAfectividadEmbotada = (CheckBox) findViewById(R.id.rdgPersonalidadAfectividadEmbotada);
        rdgPersonalidadPerturbacionEmocional = (CheckBox) findViewById(R.id.rdgPersonalidadPerturbacionEmocional);
        rdgPersonalidadHilaridadInapropiada = (CheckBox) findViewById(R.id.rdgPersonalidadHilaridadInapropiada);
        rdgPersonalidadRespuestaEmocional = (CheckBox) findViewById(R.id.rdgPersonalidadRespuestaEmocional);
        rdgPersonalidadIndiscrecionesSexuales = (CheckBox) findViewById(R.id.rdgPersonalidadIndiscrecionesSexuales);
        rdgPersonalidadFaltaInteres = (CheckBox) findViewById(R.id.rdgPersonalidadFaltaInteres);
        rdgPersonalidadDisminucionIniciativa = (CheckBox) findViewById(R.id.rdgPersonalidadDisminucionIniciativa);
        rdgPersonalidadHiperactividadJustificada = (CheckBox) findViewById(R.id.rdgPersonalidadHiperactividadJustificada);


        //ORIENTACION

        rdgOrientacionCalle = (CheckBox) findViewById(R.id.rdgOrientacionCalle);
        rdgOrientacionCasa = (CheckBox) findViewById(R.id.rdgOrientacionCasa);
        rdgOrientacionEntorno = (CheckBox) findViewById(R.id.rdgOrientacionEntorno);


        owner = (EditText) findViewById(R.id.txt_responsable);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Agregar Nota");
        SharedPreferences loginpreference = getSharedPreferences("appdata", Context.MODE_PRIVATE);
        if(loginpreference.getBoolean("supervisor",true) && loginpreference.getString("username","")!=null)
        {
            username = loginpreference.getString("username", "");
            if(!username.equals(""))
            {
                Log.e("addnote","preference username"+username);
                User user = helper.getUserInformation(username);
                userId=user.getId();
                owner.setVisibility(View.GONE);
                optionOwner = false;
            }
        }
        else{

            Log.e("addnote","va a hacer visible el responsable");
            optionOwner = true;
            owner.setVisibility(View.VISIBLE);


        }
        Log.e("addnote","optionowner"+optionOwner);


        if(bundl!=null)
        {
            Log.e("addnote","bundle: "+bundl.getLong("idpatient"));
            cedula =bundl.getLong("idpatient");
            Patient patient = helper.getPatientInformation(cedula);
            patientid = patient.getId();
        }
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        /*
        movility= (ImageButton) findViewById(R.id.btn_movility);
        eating= (ImageButton) findViewById(R.id.btn_eating);
        fall= (ImageButton) findViewById(R.id.btn_fall);
        medication= (ImageButton) findViewById(R.id.btn_medication);
        health= (ImageButton) findViewById(R.id.btn_health);
        otro = (ImageButton) findViewById(R.id.btn_other);
        */
        movility.setOnClickListener(this);
        eating.setOnClickListener(this);
        fall.setOnClickListener(this);
        higiene.setOnClickListener(this);
        lenguaje.setOnClickListener(this);
        estadodeanimo.setOnClickListener(this);
        memoria.setOnClickListener(this);
        changeBehaviour.setOnClickListener(this);
        vestimenta.setOnClickListener(this);
        medication.setOnClickListener(this);
        orientacion.setOnClickListener(this);
        instrumentales.setOnClickListener(this);



    }




    public void saveNote(View view){

        Log.e("addnote",""+optionOwner);

        if(validarCategoria(election))
        {


        if(optionOwner)
        {


            Log.e("addnote","Entro a owner true");
            if(validar(description.getText().toString(),owner.getText().toString())==false)
            {

                new MaterialDialog.Builder(this).title(R.string.addnote_empty_title2).content(R.string.addnote_empty_content2).positiveText(R.string.dialog_succes_agree).icon(getResources().getDrawable(R.drawable.sadface)).show();

            }
            else{
                userId = helper.getCarerInformation().getId();
                Log.e("addnote","userid"+userId);
                var_owner =owner.getText().toString();
                var_state = false;
                resultValidation = true;



            }
        }
        if(!optionOwner){
            Log.e("addnote","Entro a owner false");
            if(validar(description.getText().toString())==false)
            {


                new MaterialDialog.Builder(this).title(R.string.addnote_empty_title).content(R.string.addnote_empty_content).positiveText(R.string.dialog_succes_agree).icon(getResources().getDrawable(R.drawable.sadface)).show();

            }
            else{


                var_owner= username;
                var_state=true;
                resultValidation = true;
                carerMessageIndicator=true;
            }
        }


        if(resultValidation) {


            String var_description = description.getText().toString();
            Boolean var_late = rdgTardia.isChecked();

            Locale spanish = new Locale("es", "ES");
            SimpleDateFormat hourFormat = new SimpleDateFormat("hh:mm a");
            DateFormat dateFormat = new SimpleDateFormat("EEE, MMM d, yyyy",spanish);

            var_hora = hourFormat.format(new Date()).toString();

            var_fecha = dateFormat.format(Calendar.getInstance().getTime());

            Log.e("addnote","patienid: "+patientid);
            Log.e("addnote","userid: "+userId);
            Log.e("addnote","election: "+election);
            Log.e("addnote","var_fecha: "+var_fecha);
            Log.e("addnote","var_hora: "+var_hora);
            Log.e("addnote","var_description: "+var_description);
            Log.e("addnote","var_color: "+var_color);
            Log.e("addnote","var_owner: "+var_owner);




            Note note = new Note(null, patientid, userId, var_tipo, var_fecha, var_hora, var_description,election, var_seleccion, var_owner, var_late, var_state);
            noteDao.insert(note);
            //noteDao.update(note);



            if(var_tipo==1)
            {

            if(note!=null) {
                for (int i = 0; i < sintomasList.size(); i++) {

                    switch (sintomasList.get(i)) {


                        //----------INSTRUMENTALES-------------
                        case "incapacidadtareasdomesticas":
                            Sintoma tareasdomesticas;
                            try {
                                tareasdomesticas = helper.getSintoma(patientid, "Blessed", "instrumentales", "incapacidadtareasdomesticas");
                                tareasdomesticas.setActivo(stateList.get(i));
                                if (stateList.get(i).booleanValue()) {
                                    tareasdomesticas.getScaleList().get(0).setPuntaje("1.0");

                                } else {
                                    tareasdomesticas.getScaleList().get(0).setPuntaje("0.0");
                                }

                                sintomaDao.update(tareasdomesticas);
                                scaleDao.update(tareasdomesticas.getScaleList().get(0));

                                Log.e("guardado y actualizado", tareasdomesticas.getScaleList().get(0).getPuntaje().toString());


                            } catch (Exception e) {
                            }
                            break;


                        case "incapacidadpequenasdinero":
                            try {
                                Sintoma pequenasdinero;
                                pequenasdinero = helper.getSintoma(patientid, "Blessed", "instrumentales", "incapacidadpequenasdinero");
                                pequenasdinero.setActivo(stateList.get(i));
                                if (stateList.get(i).booleanValue()) {
                                    pequenasdinero.getScaleList().get(0).setPuntaje("1.0");

                                } else {
                                    pequenasdinero.getScaleList().get(0).setPuntaje("0.0");

                                }
                                sintomaDao.update(pequenasdinero);
                                scaleDao.update(pequenasdinero.getScaleList().get(0));


                                Log.e("guardado y actualizado", pequenasdinero.getScaleList().get(0).getPuntaje().toString());

                            } catch (Exception e) {
                            }
                            break;


                        case "instrumentalestelefono":
                            try {
                                Sintoma instrumentalestelefono;
                                instrumentalestelefono = helper.getSintoma(patientid, "Blessed", "instrumentales", "instrumentalestelefono");
                                instrumentalestelefono.setActivo(stateList.get(i));

                                sintomaDao.update(instrumentalestelefono);
                                scaleDao.update(instrumentalestelefono.getScaleList().get(0));


                                Log.e("guardado y actualizado", instrumentalestelefono.getScaleList().get(0).getPuntaje().toString());

                            } catch (Exception e) {
                            }
                            break;

                        case "instrumentalescompras":
                            try {
                                Sintoma instrumentalescompras;
                                instrumentalescompras = helper.getSintoma(patientid, "Blessed", "instrumentales", "instrumentalescompras");
                                instrumentalescompras.setActivo(stateList.get(i));

                                sintomaDao.update(instrumentalescompras);
                                scaleDao.update(instrumentalescompras.getScaleList().get(0));


                                Log.e("guardado y actualizado", instrumentalescompras.getScaleList().get(0).getPuntaje().toString());

                            } catch (Exception e) {
                            }
                            break;

                        case "instrumentalescuidacasa":
                            try {
                                Sintoma instrumentalescuidacasa;
                                instrumentalescuidacasa = helper.getSintoma(patientid, "Blessed", "instrumentales", "incapacidadpequenasdinero");
                                instrumentalescuidacasa.setActivo(stateList.get(i));

                                sintomaDao.update(instrumentalescuidacasa);
                                scaleDao.update(instrumentalescuidacasa.getScaleList().get(0));


                                Log.e("guardado y actualizado", instrumentalescuidacasa.getScaleList().get(0).getPuntaje().toString());

                            } catch (Exception e) {
                            }
                            break;


                        case "instrumentaleslavaropa":
                            try {
                                Sintoma instrumentaleslavaropa;
                                instrumentaleslavaropa = helper.getSintoma(patientid, "Blessed", "instrumentales", "incapacidadpequenasdinero");
                                instrumentaleslavaropa.setActivo(stateList.get(i));

                                sintomaDao.update(instrumentaleslavaropa);
                                scaleDao.update(instrumentaleslavaropa.getScaleList().get(0));


                                Log.e("guardado y actualizado",instrumentaleslavaropa.getScaleList().get(0).getPuntaje().toString());

                            } catch (Exception e) {
                            }
                            break;


                        case "instrumentalestransporte":
                            try {
                                Sintoma instrumentalestransporte;
                                instrumentalestransporte = helper.getSintoma(patientid, "Blessed", "instrumentales", "instrumentalestransporte");
                                instrumentalestransporte.setActivo(stateList.get(i));

                                sintomaDao.update(instrumentalestransporte);
                                scaleDao.update(instrumentalestransporte.getScaleList().get(0));


                                Log.e("guardado y actualizado",instrumentalestransporte.getScaleList().get(0).getPuntaje().toString());

                            } catch (Exception e) {
                            }
                            break;


                        case "instrumentalesmedicacion":
                            try {
                                Sintoma instrumentalesmedicacion;
                                instrumentalesmedicacion = helper.getSintoma(patientid, "Blessed", "instrumentales", "instrumentalesmedicacion");
                                instrumentalesmedicacion.setActivo(stateList.get(i));

                                sintomaDao.update(instrumentalesmedicacion);
                                scaleDao.update(instrumentalesmedicacion.getScaleList().get(0));


                                Log.e("guardado y actualizado",instrumentalesmedicacion.getScaleList().get(0).getPuntaje().toString());

                            } catch (Exception e) {
                            }
                            break;







                        //----------VESTIMENTA y AVD-------------




                        case "vestimentafallosocasionales":
                            try {
                                Sintoma vestimentafallosocasionales;
                                vestimentafallosocasionales = helper.getSintoma(patientid, "Blessed", "vestimenta", "vestimentafallosocasionales");
                                vestimentafallosocasionales.setActivo(stateList.get(i));
                                if (stateList.get(i).booleanValue()) {
                                    //vestimentafallosocasionales.getScaleList().get(0).setPuntaje("1.0");

                                } else {
                                    //vestimentafallosocasionales.getScaleList().get(0).setPuntaje("0.0");

                                }
                                sintomaDao.update(vestimentafallosocasionales);
                                scaleDao.update(vestimentafallosocasionales.getScaleList().get(0));


                                Log.e("guardado y actualizado", vestimentafallosocasionales.getScaleList().get(0).getPuntaje().toString());

                            } catch (Exception e) {
                                Log.e("AddNote", "Error al traer vestimentafallosocasionales");
                            }
                            break;


                        case "vestimentaseleccionar":
                            try {
                                Sintoma vestimentaseleccionar;
                                vestimentaseleccionar = helper.getSintoma(patientid, "FAST", "vestimenta", "vestimentaseleccionar");
                                vestimentaseleccionar.setActivo(stateList.get(i));
                                if (stateList.get(i).booleanValue()) {
                                    //vestimentaseleccionar.getScaleList().get(0).setPuntaje("5");

                                } else {
                                    //vestimentafallosocasionales.getScaleList().get(0).setPuntaje("0");

                                }
                                sintomaDao.update(vestimentaseleccionar);
                                scaleDao.update(vestimentaseleccionar.getScaleList().get(0));


                                Log.e("guardado y actualizado", vestimentaseleccionar.getScaleList().get(0).getPuntaje().toString());

                            } catch (Exception e) {
                                Log.e("AddNote", "Error al traer vestimentaseleccionar");
                            }
                            break;


                        case "vestimentasecuencia":
                            try {
                                Sintoma vestimentasecuencia;
                                vestimentasecuencia = helper.getSintoma(patientid, "Blessed", "vestimenta", "vestimentasecuencia");
                                vestimentasecuencia.setActivo(stateList.get(i));
                                if (stateList.get(i).booleanValue()) {
                                    vestimentasecuencia.getScaleList().get(0).setPuntaje("2.0");

                                } else {
                                    vestimentasecuencia
                                            .getScaleList().get(0).setPuntaje("0");

                                }
                                sintomaDao.update(vestimentasecuencia);
                                scaleDao.update(vestimentasecuencia.getScaleList().get(0));


                                Log.e("guardado y actualizado", vestimentasecuencia.getScaleList().get(0).getPuntaje().toString());

                            } catch (Exception e) {
                                Log.e("AddNote", "Error al traer vestimentasecuencia");
                            }
                            break;
                        case "vestimentaayudavestirse":
                            try {
                                Sintoma vestimentayudavestirse;
                                vestimentayudavestirse = helper.getSintoma(patientid, "FAST", "vestimenta", "vestimentaayudavestirse");
                                vestimentayudavestirse.setActivo(stateList.get(i));
                                if (stateList.get(i).booleanValue()) {
                                    Log.e("higieneayudavestirse", "ahora esta activo");
                                    //vestimentayudavestirse.getScaleList().get(0).setPuntaje("6A");

                                } else {
                                    //vestimentayudavestirse.getScaleList().get(0).setPuntaje("0");

                                }
                                sintomaDao.update(vestimentayudavestirse);
                                scaleDao.update(vestimentayudavestirse.getScaleList().get(0));


                                Log.e("guardado y actualizado", vestimentayudavestirse.getScaleList().get(0).getPuntaje().toString());

                            } catch (Exception e) {
                                Log.e("AddNote", "Error al traer vestimentaayudavestirse");
                            }
                            break;

                        case "vestimentaincapaz":
                            try {

                                Sintoma vestimentaincapaz;
                                vestimentaincapaz = helper.getSintoma(patientid, "Blessed", "vestimenta", "vestimentaincapaz");
                                vestimentaincapaz.setActivo(stateList.get(i));
                                if (stateList.get(i).booleanValue()) {
                                    vestimentaincapaz.getScaleList().get(0).setPuntaje("3.0");

                                } else {
                                    vestimentaincapaz.getScaleList().get(0).setPuntaje("0");

                                }
                                sintomaDao.update(vestimentaincapaz);
                                scaleDao.update(vestimentaincapaz.getScaleList().get(0));


                                Log.e("guardado y actualizado", vestimentaincapaz.getScaleList().get(0).getPuntaje().toString());

                            } catch (Exception e) {
                                Log.e("AddNote", "Error al traer vestimentaincapaz");
                            }
                            break;

                        //----------LENGUAJE-------------
                        case "lenguajelimitado":
                            try {
                                Sintoma lenguajelimitado;
                                lenguajelimitado = helper.getSintoma(patientid, "FAST", "lenguaje", "lenguajelimitado");
                                lenguajelimitado.setActivo(stateList.get(i));
                                if (stateList.get(i).booleanValue()) {
                                    //lenguajelimitado.getScaleList().get(0).setPuntaje("7a");

                                } else {
                                    //lenguajelimitado.getScaleList().get(0).setPuntaje("0");

                                }
                                sintomaDao.update(lenguajelimitado);
                                scaleDao.update(lenguajelimitado.getScaleList().get(0));


                                Log.e("guardado y actualizado", lenguajelimitado.getScaleList().get(0).getPuntaje().toString());

                            } catch (Exception e) {
                            }

                            break;

                        case "lenguajepalabra":
                            try {
                                Sintoma lenguajepalabra;
                                lenguajepalabra = helper.getSintoma(patientid, "FAST", "lenguaje", "lenguajepalabra");
                                lenguajepalabra.setActivo(stateList.get(i));
                                if (stateList.get(i).booleanValue()) {
                                    //lenguajelimitado.getScaleList().get(0).setPuntaje("7b");

                                } else {
                                    //lenguajelimitado.getScaleList().get(0).setPuntaje("0");

                                }
                                sintomaDao.update(lenguajepalabra);
                                scaleDao.update(lenguajepalabra.getScaleList().get(0));


                                Log.e("guardado y actualizado", lenguajepalabra.getScaleList().get(0).getPuntaje().toString());

                            } catch (Exception e) {
                            }
                            break;

                        //----------CAMBIO DE ANIMO-------------

                        case "animosonrisa":
                            try {
                                Sintoma animosonrisa;
                                animosonrisa = helper.getSintoma(patientid, "Blessed", "animo", "animosonrisa");
                                animosonrisa.setActivo(stateList.get(i));
                                if (stateList.get(i).booleanValue()) {
                                    //animosonrisa.getScaleList().get(0).setPuntaje("7e");
                                } else {
                                    //animosonrisa.getScaleList().get(0).setPuntaje("0");

                                }
                                sintomaDao.update(animosonrisa);
                                scaleDao.update(animosonrisa.getScaleList().get(0));

                                Log.e("guardado y actualizado", animosonrisa.getScaleList().get(0).getPuntaje().toString());


                            } catch (Exception e) {
                            }
                            break;


                        //----------ORIENTACION-------------

                        case "orientacioncasa":
                            try {
                                Sintoma orientacioncasa;
                                orientacioncasa = helper.getSintoma(patientid, "Blessed", "orientacion", "orientacioncasa");
                                orientacioncasa.setActivo(stateList.get(i));
                                if (stateList.get(i).booleanValue()) {
                                    orientacioncasa.getScaleList().get(0).setPuntaje("1.0");
                                } else {
                                    orientacioncasa.getScaleList().get(0).setPuntaje("0.0");

                                }
                                sintomaDao.update(orientacioncasa);
                                scaleDao.update(orientacioncasa.getScaleList().get(0));

                                Log.e("guardado y actualizado", orientacioncasa.getScaleList().get(0).getPuntaje().toString());


                            } catch (Exception e) {
                            }
                            break;
                        case "orientacioncalle":
                            try {
                                Sintoma orientacioncalle;
                                orientacioncalle = helper.getSintoma(patientid, "Blessed", "orientacion", "orientacioncalle");
                                orientacioncalle.setActivo(stateList.get(i));
                                if (stateList.get(i).booleanValue()) {
                                    orientacioncalle.getScaleList().get(0).setPuntaje("1.0");

                                } else {
                                    orientacioncalle.getScaleList().get(0).setPuntaje("0.0");

                                }

                                sintomaDao.update(orientacioncalle);
                                scaleDao.update(orientacioncalle.getScaleList().get(0));

                                Log.e("guardado y actualizado", orientacioncalle.getScaleList().get(0).getPuntaje().toString());


                            } catch (Exception e) {
                            }
                            break;

                        case "orientacionentorno":
                            try {
                                Sintoma orientacionentorno;
                                orientacionentorno = helper.getSintoma(patientid, "Blessed", "orientacion", "orientacionentorno");
                                orientacionentorno.setActivo(stateList.get(i));
                                if (stateList.get(0).booleanValue()) {
                                    orientacionentorno.getScaleList().get(0).setPuntaje("1.0");

                                } else {
                                    orientacionentorno.getScaleList().get(0).setPuntaje("0.0");

                                }
                                sintomaDao.update(orientacionentorno);
                                scaleDao.update(orientacionentorno.getScaleList().get(0));
                                Log.e("guardado y actualizado", orientacionentorno.getScaleList().get(0).getPuntaje().toString());

                            } catch (Exception e) {


                            }

                            //----------MEMORIA-------------

                        case "memorialistascortas":

                            try {
                                Sintoma memorialistascortas;
                                memorialistascortas = helper.getSintoma(patientid, "Blessed", "memoria", "memorialistascortas");
                                memorialistascortas.setActivo(stateList.get(i));
                                if (stateList.get(i).booleanValue()) {
                                    memorialistascortas.getScaleList().get(0).setPuntaje("1.0");

                                } else {
                                    memorialistascortas.getScaleList().get(0).setPuntaje("0.0");

                                }

                                sintomaDao.update(memorialistascortas);
                                scaleDao.update(memorialistascortas.getScaleList().get(0));

                                Log.e("guardado y actualizado", memorialistascortas.getScaleList().get(0).getPuntaje().toString());


                            } catch (Exception e) {
                            }
                            break;

                        case "memoriaolvidosbenignos":

                            try {
                                Sintoma memoriaolvidosbenignos1,memoriaolvidosbenignos2;
                                memoriaolvidosbenignos1 = helper.getSintoma(patientid, "Blessed", "memoria", "memoriaolvidosbenignos");
                                memoriaolvidosbenignos1.setActivo(stateList.get(i));
                                memoriaolvidosbenignos2 = helper.getSintoma(patientid, "FAST", "memoria", "memoriaolvidosbenignos");
                                memoriaolvidosbenignos2.setActivo(stateList.get(i));

                                if (stateList.get(i).booleanValue()) {
                                    memoriaolvidosbenignos1.getScaleList().get(0).setPuntaje("1.0");

                                } else {
                                    memoriaolvidosbenignos1.getScaleList().get(0).setPuntaje("0.0");

                                }

                                sintomaDao.update(memoriaolvidosbenignos1);
                                sintomaDao.update(memoriaolvidosbenignos2);
                                scaleDao.update(memoriaolvidosbenignos1.getScaleList().get(0));
                                scaleDao.update(memoriaolvidosbenignos2.getScaleList().get(0));


                                Log.e("guardado y actualizado", memoriaolvidosbenignos1.getScaleList().get(0).getPuntaje().toString());
                                Log.e("guardado y actualizado", memoriaolvidosbenignos2.getScaleList().get(0).getPuntaje().toString());


                            } catch (Exception e) {
                            }
                            break;

                        case "memoriatendenciarememorar":

                            try {
                                Sintoma tendenciarememorar;
                                tendenciarememorar = helper.getSintoma(patientid, "Blessed", "memoria", "memoriatendenciarememorar");
                                tendenciarememorar.setActivo(stateList.get(i));

                                if (stateList.get(i).booleanValue()) {
                                    tendenciarememorar.getScaleList().get(0).setPuntaje("1.0");

                                } else {
                                    tendenciarememorar.getScaleList().get(0).setPuntaje("0.0");

                                }


                                sintomaDao.update(tendenciarememorar);
                                scaleDao.update(tendenciarememorar.getScaleList().get(0));
                                //tareasdomesticas.update();
                                //tareasdomesticas.refresh();
                                Log.e("guardado y actualizado", tendenciarememorar.getScaleList().get(0).getPuntaje().toString());

                            } catch (Exception e) {
                            }

                            break;


                        //----------MOVILIDAD-------------

                        case "movilidadsitioslejanos":

                            try {
                                Sintoma movilidadsitiosalejados;
                                movilidadsitiosalejados = helper.getSintoma(patientid, "FAST", "movilidad", "movilidadsitioslejanos");
                                movilidadsitiosalejados.setActivo(stateList.get(i));
                                if (stateList.get(i).booleanValue()) {
                                    //movilidadsitiosalejados.getScaleList().get(0).setPuntaje("3");

                                } else {
                                    //movilidadsitiosalejados.getScaleList().get(0).setPuntaje("0");

                                }

                                sintomaDao.update(movilidadsitiosalejados);
                                scaleDao.update(movilidadsitiosalejados.getScaleList().get(0));

                                Log.e("guardado y actualizado", movilidadsitiosalejados.getScaleList().get(0).getPuntaje().toString());


                            } catch (Exception e) {
                            }
                        case "movilidadcaminar":

                            try {
                                Sintoma movilidadcaminar;
                                movilidadcaminar = helper.getSintoma(patientid, "FAST", "movilidad", "movilidadcaminar");
                                movilidadcaminar.setActivo(stateList.get(i));
                                if (stateList.get(i).booleanValue()) {
                                    //movilidadcaminar.getScaleList().get(0).setPuntaje("7c");

                                } else {
                                    //movilidadcaminar.getScaleList().get(0).setPuntaje("0");

                                }

                                sintomaDao.update(movilidadcaminar);
                                scaleDao.update(movilidadcaminar.getScaleList().get(0));

                                Log.e("guardado y actualizado", movilidadcaminar.getScaleList().get(0).getPuntaje().toString());


                            } catch (Exception e) {
                            }


                            break;

                        case "movilidadsentarse":

                            try {
                                Sintoma movilidadsentarse;
                                movilidadsentarse = helper.getSintoma(patientid, "FAST", "movilidad", "movilidadsentarse");
                                movilidadsentarse.setActivo(stateList.get(i));
                                if (stateList.get(i).booleanValue()) {
                                    //movilidadsentarse.getScaleList().get(0).setPuntaje("7d");

                                } else {
                                    //movilidadsentarse.getScaleList().get(0).setPuntaje("0");

                                }

                                sintomaDao.update(movilidadsentarse);
                                scaleDao.update(movilidadsentarse.getScaleList().get(0));

                                Log.e("guardado y actualizado", movilidadsentarse.getScaleList().get(0).getPuntaje().toString());


                            } catch (Exception e) {
                            }


                            break;

                        case "movilidadcabeza":

                            try {
                                Sintoma movilidadcabeza;
                                movilidadcabeza = helper.getSintoma(patientid, "FAST", "movilidad", "movilidadcabeza");
                                movilidadcabeza.setActivo(stateList.get(i));
                                if (stateList.get(i).booleanValue()) {
                                    //movilidadcabeza.getScaleList().get(0).setPuntaje("7e");

                                } else {
                                    //movilidadcabeza.getScaleList().get(0).setPuntaje("0");

                                }

                                sintomaDao.update(movilidadcabeza);
                                scaleDao.update(movilidadcabeza.getScaleList().get(0));

                                Log.e("guardado y actualizado", movilidadcabeza.getScaleList().get(0).getPuntaje().toString());


                            } catch (Exception e) {
                            }


                            break;

                        //----------ALIMENTACION-------------

                        case "alimentacioncuchara":

                            try {
                                Sintoma alimentacioncuchara;
                                alimentacioncuchara = helper.getSintoma(patientid, "FAST", "alimentacion", "alimentacioncuchara");
                                alimentacioncuchara.setActivo(stateList.get(i));
                                if (stateList.get(i).booleanValue()) {
                                    //movilidadcabeza.getScaleList().get(0).setPuntaje("7e");

                                } else {
                                    //movilidadcabeza.getScaleList().get(0).setPuntaje("0");

                                }

                                sintomaDao.update(alimentacioncuchara);
                                scaleDao.update(alimentacioncuchara.getScaleList().get(0));

                                Log.e("guardado y actualizado", alimentacioncuchara
                                        .getScaleList().get(0).getPuntaje().toString());


                            } catch (Exception e) {
                            }


                            break;

                        case "alimentacionsolidos":

                            try {
                                Sintoma alimentacionsolidos;
                                alimentacionsolidos = helper.getSintoma(patientid, "FAST", "alimentacion", "alimentacionsolidos");
                                alimentacionsolidos.setActivo(stateList.get(i));
                                if (stateList.get(i).booleanValue()) {
                                    //movilidadcabeza.getScaleList().get(0).setPuntaje("7e");

                                } else {
                                    //movilidadcabeza.getScaleList().get(0).setPuntaje("0");

                                }

                                sintomaDao.update(alimentacionsolidos);
                                scaleDao.update(alimentacionsolidos.getScaleList().get(0));

                                Log.e("guardado y actualizado", alimentacionsolidos.getScaleList().get(0).getPuntaje().toString());


                            } catch (Exception e) {
                            }


                            break;

                        case "alimentaciondependientes":

                            try {
                                Sintoma alimentaciondependiente;
                                alimentaciondependiente = helper.getSintoma(patientid, "FAST", "alimentacion", "alimentaciondependientes");
                                alimentaciondependiente.setActivo(stateList.get(i));
                                if (stateList.get(i).booleanValue()) {
                                    //movilidadcabeza.getScaleList().get(0).setPuntaje("7e");

                                } else {
                                    //movilidadcabeza.getScaleList().get(0).setPuntaje("0");

                                }

                                sintomaDao.update(alimentaciondependiente);
                                scaleDao.update(alimentaciondependiente.getScaleList().get(0));

                                Log.e("guardado y actualizado", alimentaciondependiente.getScaleList().get(0).getPuntaje().toString());


                            } catch (Exception e) {
                            }


                            break;


                        //----HIGIENE Y ASEO-----

                        case "higieneayudabanarse":
                            try {
                                Sintoma higieneayudabanarse;
                                higieneayudabanarse = helper.getSintoma(patientid, "FAST", "higiene", "higieneayudabanarse");
                                higieneayudabanarse.setActivo(stateList.get(i));
                                if (stateList.get(i).booleanValue()) {
                                    //higieneayudabanarse.getScaleList().get(0).setPuntaje("6b");

                                } else {
                                    //higieneayudabanarse.getScaleList().get(0).setPuntaje("0");

                                }

                                sintomaDao.update(higieneayudabanarse);
                                scaleDao.update(higieneayudabanarse.getScaleList().get(0));

                                Log.e("guardado y actualizado", higieneayudabanarse.getScaleList().get(0).getPuntaje().toString());


                            } catch (Exception e) {
                            }

                            break;


                        case "higieneayudasoltarbano":
                            try {
                                Sintoma higieneayudasoltarbano;
                                higieneayudasoltarbano = helper.getSintoma(patientid, "FAST", "higiene", "higieneayudasoltarbano");
                                higieneayudasoltarbano.setActivo(stateList.get(i));
                                if (stateList.get(i).booleanValue()) {
                                    //movilidadcabeza.getScaleList().get(0).setPuntaje("6c");

                                } else {
                                    //movilidadcabeza.getScaleList().get(0).setPuntaje("0");

                                }

                                sintomaDao.update(higieneayudasoltarbano);
                                scaleDao.update(higieneayudasoltarbano.getScaleList().get(0));

                                Log.e("guardado y actualizado", higieneayudasoltarbano.getScaleList().get(0).getPuntaje().toString());


                            } catch (Exception e) {
                            }

                            break;


                        case "higieneayudaincontinensiaurinaria":
                            try {
                                Sintoma higieneayudaincontinensiaurinaria;
                                higieneayudaincontinensiaurinaria = helper.getSintoma(patientid, "Blessed", "higiene", "higieneayudaincontinensiaurinaria");
                                Log.e("ayuda incontinensia", "estado " + higieneayudaincontinensiaurinaria.getActivo().toString());

                                higieneayudaincontinensiaurinaria.setActivo(stateList.get(i));
                                if (stateList.get(i).booleanValue()) {
                                    Log.e("addnote", "Incontinensia urinaria activada");
                                    //movilidadcabeza.getScaleList().get(0).setPuntaje("6d");

                                } else {
                                    Log.e("addnote", "Incontinensia urinaria desactivada");

                                    //movilidadcabeza.getScaleList().get(0).setPuntaje("0");

                                }

                                sintomaDao.update(higieneayudaincontinensiaurinaria);
                                scaleDao.update(higieneayudaincontinensiaurinaria.getScaleList().get(0));

                                Log.e("addnote", "Entro higiene incontinensia urinaria");
                                Log.e("guardado y actualizado", higieneayudaincontinensiaurinaria.getScaleList().get(0).getPuntaje().toString());


                            } catch (Exception e) {
                                Log.e("addnote", "Error al actualizar incontinensia urinaria");

                            }

                            break;

                        case "higieneayudaincontinensiafecal":

                            try {
                                Sintoma higieneayudaincontinensiafecal;
                                higieneayudaincontinensiafecal = helper.getSintoma(patientid, "FAST", "higiene", "higieneayudaincontinensiafecal");
                                higieneayudaincontinensiafecal.setActivo(stateList.get(i));
                                if (stateList.get(i).booleanValue()) {
                                    //movilidadcabeza.getScaleList().get(0).setPuntaje("6e
                                    //
                                    //  ");

                                } else {
                                    //movilidadcabeza.getScaleList().get(0).setPuntaje("0");

                                }

                                sintomaDao.update(higieneayudaincontinensiafecal);
                                scaleDao.update(higieneayudaincontinensiafecal.getScaleList().get(0));

                                Log.e("guardado y actualizado", higieneayudaincontinensiafecal.getScaleList().get(0).getPuntaje().toString());


                            } catch (Exception e) {
                            }


                            break;

                        //----PERSONALIDAD Y CONDUCTA-----

                        case "personalidadretraimiento":

                            try {
                                Sintoma personalidadretraimiento;
                                personalidadretraimiento = helper.getSintoma(patientid, "Blessed", "personalidad", "personalidadretraimiento");
                                personalidadretraimiento.setActivo(stateList.get(i));
                                if (stateList.get(i).booleanValue()) {
                                    //movilidadcabeza.getScaleList().get(0).setPuntaje("6e
                                    //
                                    //  ");

                                } else {
                                    //movilidadcabeza.getScaleList().get(0).setPuntaje("0");

                                }

                                sintomaDao.update(personalidadretraimiento);
                                scaleDao.update(personalidadretraimiento.getScaleList().get(0));

                                Log.e("guardado y actualizado", personalidadretraimiento.getScaleList().get(0).getPuntaje().toString());


                            } catch (Exception e) {
                            }

                            break;



                        case "personalidadegocentrismo":

                            try {
                                Sintoma personalidadegocentrismo;
                                personalidadegocentrismo = helper.getSintoma(patientid, "Blessed", "personalidad", "personalidadegocentrismo");
                                personalidadegocentrismo.setActivo(stateList.get(i));
                                if (stateList.get(i).booleanValue()) {
                                    //movilidadcabeza.getScaleList().get(0).setPuntaje("6e
                                    //
                                    //  ");

                                } else {
                                    //movilidadcabeza.getScaleList().get(0).setPuntaje("0");

                                }

                                sintomaDao.update(personalidadegocentrismo);
                                scaleDao.update(personalidadegocentrismo.getScaleList().get(0));

                                Log.e("guardado y actualizado", personalidadegocentrismo.getScaleList().get(0).getPuntaje().toString());


                            } catch (Exception e) {
                            }

                            break;



                        case "personalidadperdidainteres":

                            try {
                                Sintoma personalidadperdidainteres;
                                personalidadperdidainteres = helper.getSintoma(patientid, "Blessed", "personalidad", "personalidadperdidainteres");
                                personalidadperdidainteres.setActivo(stateList.get(i));
                                if (stateList.get(i).booleanValue()) {
                                    //movilidadcabeza.getScaleList().get(0).setPuntaje("6e
                                    //
                                    //  ");

                                } else {
                                    //movilidadcabeza.getScaleList().get(0).setPuntaje("0");

                                }

                                sintomaDao.update(personalidadperdidainteres);
                                scaleDao.update(personalidadperdidainteres.getScaleList().get(0));

                                Log.e("guardado y actualizado", personalidadperdidainteres.getScaleList().get(0).getPuntaje().toString());


                            } catch (Exception e) {
                            }

                            break;



                        case "personalidadafectividadembotada":

                            try {
                                Sintoma personalidadafectividadembotada;
                                personalidadafectividadembotada = helper.getSintoma(patientid, "Blessed", "personalidad", "personalidadafectividadembotada");
                                personalidadafectividadembotada.setActivo(stateList.get(i));
                                if (stateList.get(i).booleanValue()) {
                                    //movilidadcabeza.getScaleList().get(0).setPuntaje("6e
                                    //
                                    //  ");

                                } else {
                                    //movilidadcabeza.getScaleList().get(0).setPuntaje("0");

                                }

                                sintomaDao.update(personalidadafectividadembotada);
                                scaleDao.update(personalidadafectividadembotada.getScaleList().get(0));

                                Log.e("guardado y actualizado", personalidadafectividadembotada.getScaleList().get(0).getPuntaje().toString());


                            } catch (Exception e) {
                            }

                            break;

                        case "personalidadperturbacionemocional":

                            try {
                                Sintoma personalidadperturbacionemocional;
                                personalidadperturbacionemocional = helper.getSintoma(patientid, "Blessed", "personalidad", "personalidadperturbacionemocional");
                                personalidadperturbacionemocional.setActivo(stateList.get(i));
                                if (stateList.get(i).booleanValue()) {
                                    //movilidadcabeza.getScaleList().get(0).setPuntaje("6e
                                    //
                                    //  ");

                                } else {
                                    //movilidadcabeza.getScaleList().get(0).setPuntaje("0");

                                }

                                sintomaDao.update(personalidadperturbacionemocional);
                                scaleDao.update(personalidadperturbacionemocional.getScaleList().get(0));

                                Log.e("guardado y actualizado",personalidadperturbacionemocional.getScaleList().get(0).getPuntaje().toString());


                            } catch (Exception e) {
                            }

                            break;

                        case "personalidadhilaridadinapropiada":

                            try {
                                Sintoma personalidadhilaridadinapropiada;
                                personalidadhilaridadinapropiada = helper.getSintoma(patientid, "Blessed", "personalidad", "personalidadhilaridadinapropiada");
                                personalidadhilaridadinapropiada.setActivo(stateList.get(i));
                                if (stateList.get(i).booleanValue()) {
                                    //movilidadcabeza.getScaleList().get(0).setPuntaje("6e
                                    //
                                    //  ");

                                } else {
                                    //movilidadcabeza.getScaleList().get(0).setPuntaje("0");

                                }

                                sintomaDao.update(personalidadhilaridadinapropiada);
                                scaleDao.update(personalidadhilaridadinapropiada.getScaleList().get(0));

                                Log.e("guardado y actualizado",personalidadhilaridadinapropiada.getScaleList().get(0).getPuntaje().toString());


                            } catch (Exception e) {
                            }

                            break;

                        case "personalidadrespuestaemocional":

                            try {
                                Sintoma personalidadrespuestaemocional;
                                personalidadrespuestaemocional = helper.getSintoma(patientid, "Blessed", "personalidad", "personalidadrespuestaemocional");
                                personalidadrespuestaemocional.setActivo(stateList.get(i));
                                if (stateList.get(i).booleanValue()) {
                                    //movilidadcabeza.getScaleList().get(0).setPuntaje("6e
                                    //
                                    //  ");

                                } else {
                                    //movilidadcabeza.getScaleList().get(0).setPuntaje("0");

                                }

                                sintomaDao.update(personalidadrespuestaemocional);
                                scaleDao.update(personalidadrespuestaemocional.getScaleList().get(0));

                                Log.e("guardado y actualizado",personalidadrespuestaemocional.getScaleList().get(0).getPuntaje().toString());


                            } catch (Exception e) {
                            }

                            break;

                        case "personalidadindiscrecionessexuales":

                            try {
                                Sintoma personalidadindiscrecionessexuales;
                                personalidadindiscrecionessexuales = helper.getSintoma(patientid, "Blessed", "personalidad", "personalidadindiscrecionessexuales");
                                personalidadindiscrecionessexuales.setActivo(stateList.get(i));
                                if (stateList.get(i).booleanValue()) {
                                    //movilidadcabeza.getScaleList().get(0).setPuntaje("6e
                                    //
                                    //  ");

                                } else {
                                    //movilidadcabeza.getScaleList().get(0).setPuntaje("0");

                                }

                                sintomaDao.update(personalidadindiscrecionessexuales);
                                scaleDao.update(personalidadindiscrecionessexuales.getScaleList().get(0));

                                Log.e("guardado y actualizado",personalidadindiscrecionessexuales.getScaleList().get(0).getPuntaje().toString());


                            } catch (Exception e) {
                            }

                            break;


                        case "personalidadfaltainteres":

                            try {
                                Sintoma personalidadfaltainteres;
                                personalidadfaltainteres= helper.getSintoma(patientid, "Blessed", "personalidad", "personalidadfaltainteres");
                                personalidadfaltainteres.setActivo(stateList.get(i));
                                if (stateList.get(i).booleanValue()) {
                                    //movilidadcabeza.getScaleList().get(0).setPuntaje("6e
                                    //
                                    //  ");

                                } else {
                                    //movilidadcabeza.getScaleList().get(0).setPuntaje("0");

                                }

                                sintomaDao.update(personalidadfaltainteres);
                                scaleDao.update(personalidadfaltainteres.getScaleList().get(0));

                                Log.e("guardado y actualizado",personalidadfaltainteres.getScaleList().get(0).getPuntaje().toString());


                            } catch (Exception e) {
                            }

                            break;


                        case "personalidaddisminucioniniciativa":

                            try {
                                Sintoma personalidaddisminucioniniciativa;
                                personalidaddisminucioniniciativa= helper.getSintoma(patientid, "Blessed", "personalidad", "personalidaddisminucioniniciativa");
                                personalidaddisminucioniniciativa.setActivo(stateList.get(i));
                                if (stateList.get(i).booleanValue()) {
                                    //movilidadcabeza.getScaleList().get(0).setPuntaje("6e
                                    //
                                    //  ");

                                } else {
                                    //movilidadcabeza.getScaleList().get(0).setPuntaje("0");

                                }

                                sintomaDao.update(personalidaddisminucioniniciativa);
                                scaleDao.update(personalidaddisminucioniniciativa.getScaleList().get(0));

                                Log.e("guardado y actualizado",personalidaddisminucioniniciativa.getScaleList().get(0).getPuntaje().toString());


                            } catch (Exception e) {
                            }

                            break;


                        case "personalidadhiperactividadjustificada":

                            try {
                                Sintoma personalidadhiperactividadjustificada;
                                personalidadhiperactividadjustificada= helper.getSintoma(patientid, "Blessed", "personalidad", "personalidadhiperactividadjustificada");
                                personalidadhiperactividadjustificada.setActivo(stateList.get(i));
                                if (stateList.get(i).booleanValue()) {
                                    //movilidadcabeza.getScaleList().get(0).setPuntaje("6e
                                    //
                                    //  ");

                                } else {
                                    //movilidadcabeza.getScaleList().get(0).setPuntaje("0");

                                }

                                sintomaDao.update(personalidadhiperactividadjustificada);
                                scaleDao.update(personalidadhiperactividadjustificada.getScaleList().get(0));

                                Log.e("guardado y actualizado",personalidadhiperactividadjustificada.getScaleList().get(0).getPuntaje().toString());


                            } catch (Exception e) {
                            }

                            break;

                        //----MEDICACION---

                        case "medicacionmedicamentos":

                            try {
                                Sintoma medicacionmedicamentos;
                                medicacionmedicamentos= helper.getSintoma(patientid, "Blessed", "medicacion", "medicacionmedicamentos");
                                medicacionmedicamentos.setActivo(stateList.get(i));
                                if (stateList.get(i).booleanValue()) {
                                    //movilidadcabeza.getScaleList().get(0).setPuntaje("6e
                                    //
                                    //  ");

                                } else {
                                    //movilidadcabeza.getScaleList().get(0).setPuntaje("0");

                                }

                                sintomaDao.update(medicacionmedicamentos);
                                scaleDao.update(medicacionmedicamentos.getScaleList().get(0));

                                Log.e("guardado y actualizado",medicacionmedicamentos.getScaleList().get(0).getPuntaje().toString());


                            } catch (Exception e) {
                            }

                            break;








                    }






                }
                }
            }

            if(!var_state)
            {
                final MaterialDialog.Builder builder = new MaterialDialog.Builder(this);
                builder.autoDismiss(true);
                builder.title("Nota Guardada").content("Su Nota ha sido guardada en la bandeja de supervisores, para aprobación").positiveText(R.string.dialog_succes_agree).show();
                builder.onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog materialDialog, DialogAction dialogAction) {

                        builder.show().dismiss();

                        goToProfile();

                    }
                });
                builder.show();
            }
            else
            {
                goToProfile();
            }





        }
        }
        else
        {
            new MaterialDialog.Builder(this).title(R.string.addnote_empty_title2).content(R.string.addnote_empty_category).positiveText(R.string.dialog_succes_agree).icon(getResources().getDrawable(R.drawable.sadface)).show();

        }

    }

    public void goToProfile()
    {
        Intent ir_main = new Intent(this, PatientProfileActivity.class);
        ir_main.putExtra("carerIndicator",carerMessageIndicator);
        ir_main.putExtra("cedula", cedula);
        EventBus.clearCaches();
        EventBus.getDefault().removeAllStickyEvents();
        startActivity(ir_main);
        if(var_state)
        {
            overridePendingTransition(R.anim.left_in, R.anim.left_out);

        }
        finish();


    }

    public Boolean validarCategoria(String election) {
        if (election.equals("")) {
            return false;
        }else {
            return true;
        }
    }


    public Boolean validar(String description) {
        if (description.equals("")) {
            return false;
        }else {
            return true;
        }
    }

    public Boolean validar(String description,String responsable){
        if (responsable.equals("")  || responsable.equals("")) {
            return false;
        }else {
            return true;
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                Intent intent=new Intent(getApplicationContext(),PatientProfileActivity.class);
                intent.putExtra("cedula",cedula);
                EventBus.clearCaches();
                EventBus.getDefault().removeAllStickyEvents();
                startActivity(intent);
                overridePendingTransition(R.anim.right_in, R.anim.right_out);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btn_medication:
                election ="medicacion";
                var_tipo=1;
                var_seleccion="medicacion";
                setDefaultImageButton();

                try{

                    Sintoma medicacionmedicamentos = helper.getSintoma(patientid,"Downton","medicacion"," medicacionmedicamentos");
                    if(medicacionmedicamentos.getActivo().booleanValue())
                    {
                        rdgMedicamentos.setChecked(true);
                    }


                }catch (Exception e){rdgMovilidadSitiosLejanos.setChecked(false);}
                medication.setBackgroundColor(getResources().getColor(R.color.accent_color));
                rdgMedicacion.setVisibility(View.VISIBLE);
                break;

            case R.id.btn_movility:
                election = "movilidad";
                var_tipo=1;
                setDefaultImageButton();
                try{

                    Sintoma movilidadsitiosalejados = helper.getSintoma(patientid,"FAST","movilidad","movilidadsitioslejanos");
                    if(movilidadsitiosalejados.getActivo().booleanValue())
                    {
                        rdgMovilidadSitiosLejanos.setChecked(true);
                    }


                }catch (Exception e){rdgMovilidadSitiosLejanos.setChecked(false);}

                try{

                    Sintoma movilidadsinasistencia = helper.getSintoma(patientid,"FAST","movilidad","movilidadcaminar");
                    if(movilidadsinasistencia.getActivo().booleanValue())
                    {
                        rdgMovilidadCaminar.setChecked(true);
                    }


                }catch (Exception e){rdgMovilidadCaminar.setChecked(false);}


                try{

                    Sintoma movilidadsentarse = helper.getSintoma(patientid,"FAST","movilidad","movilidadsentarse");
                    if(movilidadsentarse.getActivo().booleanValue())
                    {
                        rdgMovilidadSentarse.setChecked(true);
                    }


                }catch (Exception e){rdgMovilidadSentarse.setChecked(false);}

                try{

                    Sintoma movilidadcabeza = helper.getSintoma(patientid,"FAST","movilidad","movilidadcabeza");
                    if(movilidadcabeza.getActivo().booleanValue())
                    {
                        rdgMovilidadCabeza.setChecked(true);
                    }


                }catch (Exception e){rdgMovilidadCabeza.setChecked(false);}


                rdgMovilidad.setVisibility(View.VISIBLE);
                //election= Uri.parse("android.resource://co.edu.unicauca.appterapiademencia/mipmap/movility72px").toString();
                movility.setBackgroundColor(getResources().getColor(R.color.accent_color));

                break;
            case R.id.btn_eating:
                election="alimentacion";
                var_tipo=1;
                setDefaultImageButton();

                try{

                    Sintoma alimentacioncuchara = helper.getSintoma(patientid,"FAST","alimentacion","alimentacioncuchara");
                    if(alimentacioncuchara.getActivo().booleanValue())
                    {
                        rdgAlimentacionCuchara.setChecked(true);
                    }


                }catch (Exception e){rdgAlimentacionCuchara.setChecked(false);}

                try{

                    Sintoma alimentacionsolidos = helper.getSintoma(patientid,"FAST","alimentacion","alimentacionsolidos");
                    if(alimentacionsolidos.getActivo().booleanValue())
                    {
                        rdgAlimentacionSolidos.setChecked(true);
                    }


                }catch (Exception e){rdgAlimentacionSolidos.setChecked(false);}

                try{

                    Sintoma alimentaciondependiente= helper.getSintoma(patientid,"FAST","alimentacion","alimentaciondependientes");
                    if(alimentaciondependiente.getActivo().booleanValue())
                    {
                        rdgAlimentacionDependiente.setChecked(true);
                    }


                }catch (Exception e){rdgAlimentacionDependiente.setChecked(false);}

                rdgAlimentacion.setVisibility(View.VISIBLE);
                //election=Uri.parse("android.resource://co.edu.unicauca.appterapiademencia/mipmap/eating72px").toString();
                eating.setBackgroundColor(getResources().getColor(R.color.accent_color));

                break;
            case R.id.btn_fall:
                election="caidas";
                var_tipo=0;
                var_seleccion="caidas";
                setDefaultImageButton();
                linear_efecto.setVisibility(View.GONE);
                //txt_adverso.setVisibility(View.GONE);
                //election=Uri.parse("android.resource://co.edu.unicauca.appterapiademencia/mipmap/caida72px").toString();
                fall.setBackgroundColor(getResources().getColor(R.color.accent_color));
                //gravedad = 2;

                break;
            case R.id.btn_language:
                election="lenguaje";
                var_tipo=1;
                setDefaultImageButton();

                try
                {
                    Sintoma lenguajelimitado = helper.getSintoma(patientid,"FAST","lenguaje","lenguajelimitado");
                    if(lenguajelimitado.getActivo().booleanValue())
                    {
                        rdgLenguajeLimitado.setChecked(true);
                    }

                }catch (Exception e){ rdgLenguajeLimitado.setChecked(false);}

                try
                {
                    Sintoma lenguajepalabra = helper.getSintoma(patientid,"FAST","lenguaje","lenguajepalabra");
                    if(lenguajepalabra.getActivo().booleanValue())
                    {
                        rdgLenguajePalabra.setChecked(true);
                    }

                }catch (Exception e){ rdgLenguajeLimitado.setChecked(false);}


                rdgLenguaje.setVisibility(View.VISIBLE);
                //election=Uri.parse("android.resource://co.edu.unicauca.appterapiademencia/mipmap/medication72px").toString();
                lenguaje.setBackgroundColor(getResources().getColor(R.color.accent_color));

                /*
                gravedad=1;
                rdgAdverso.setVisibility(View.VISIBLE);
                txt_adverso.setVisibility(View.VISIBLE);
                */

                break;
            case R.id.btn_higiene:

                election="higiene";
                var_tipo=1;
                setDefaultImageButton();

                try
                {
                    Sintoma higieneayudabanarse = helper.getSintoma(patientid,"FAST","higiene","higieneayudabanarse");
                    if(higieneayudabanarse.getActivo().booleanValue())
                    {
                        rdgHigieneAyudaBanarse.setChecked(true);
                    }

                }catch (Exception e){ rdgHigieneAyudaBanarse.setChecked(false);}

                try
                {
                    Sintoma higieneayudainodoro = helper.getSintoma(patientid,"FAST","higiene","higieneayudainodoro");
                    if(higieneayudainodoro.getActivo().booleanValue())
                    {
                        Log.e("Higiene ayuda","Entro a checkear");
                        rdgHigieneAyudaInodoro.setChecked(true);
                    }

                }catch (Exception e){
                    Log.e("Higiene ayuda","No chequeo");
                    rdgHigieneAyudaInodoro.setChecked(false);}


                try
                {
                    Sintoma higieneayudasoltarbano= helper.getSintoma(patientid,"FAST","higiene","higieneayudasoltarbano");

                    if(higieneayudasoltarbano.getActivo().booleanValue())
                    {

                        rdgHigieneSoltarBano.setChecked(true);
                    }

                }catch (Exception e){

                    rdgHigieneSoltarBano.setChecked(false);}



                try
                {
                    Sintoma higieneincontinensiaurinaria= helper.getSintoma(patientid,"FAST","higiene","higieneincontinensiaurinaria");
                    Log.e("Higiene urinaria","activo: "+higieneincontinensiaurinaria.getActivo().booleanValue());
                    if(higieneincontinensiaurinaria.getActivo().booleanValue())
                    {
                        Log.e("Higiene urinaria","Entro a checkear");
                        rdgHigieneIncontinensiaUrinaria.setChecked(true);
                    }

                }catch (Exception e){
                    Log.e("Higiene urinaria","No chequeo");
                    rdgHigieneIncontinensiaUrinaria.setChecked(false);}


                try
                {
                    Sintoma higieneayudaincontinensiafecal= helper.getSintoma(patientid,"FAST","higiene","higieneayudaincontinensiafecal");
                    if(higieneayudaincontinensiafecal.getActivo().booleanValue())
                    {
                        rdgHigieneIncontinensiaFecal.setChecked(true);
                    }

                }catch (Exception e){ rdgHigieneIncontinensiaFecal.setChecked(false);}




                rdgHigiene.setVisibility(View.VISIBLE);
                //election=Uri.parse("android.resource://co.edu.unicauca.appterapiademencia/mipmap/otro72px").toString();
                higiene.setBackgroundColor(getResources().getColor(R.color.accent_color));
                break;
            case R.id.btn_estadodeanimo:
                election="animo";
                var_tipo=1;
                setDefaultImageButton();

                try
                {
                    Sintoma animosonrisa = helper.getSintoma(patientid,"FAST","animo","animosonrisa");
                    if(animosonrisa.getActivo().booleanValue())
                    {
                        rdgAnimoSonrisa.setChecked(true);
                    }

                }catch (Exception e){ rdgAnimoSonrisa.setChecked(false);}

                rdgAnimo.setVisibility(View.VISIBLE);
                //election=Uri.parse("android.resource://co.edu.unicauca.appterapiademencia/mipmap/ic_insert_emoticon_black_48dp").toString();
                estadodeanimo.setBackgroundColor(getResources().getColor(R.color.accent_color));
                break;
            case R.id.btn_vestimenta:
                election="vestimenta";
                var_tipo=1;
                setDefaultImageButton();



                try
                {
                    Sintoma vestimentafallosocasionales = helper.getSintoma(patientid,"Blessed","vestimenta","vestimentafallosocasionales");
                    if(vestimentafallosocasionales.getActivo().booleanValue())
                    {
                        rdgVestimentaFallosOcasionales.setChecked(true);
                    }

                }catch (Exception e){ rdgVestimentaFallosOcasionales.setChecked(false);}

                try{
                    Sintoma vestimentaseleccionar = helper.getSintoma(patientid,"FAST","vestimenta","vestimentaseleccionar");
                    if(vestimentaseleccionar.getActivo().booleanValue())
                    {
                        rdgVestimentaSeleccionar.setChecked(true);
                    }

                }catch (Exception e){rdgVestimentaSeleccionar.setChecked(false);
                }


                try{
                    Sintoma vestimentasecuencia = helper.getSintoma(patientid,"Blessed","vestimenta","vestimentasecuencia");
                    if(vestimentasecuencia.getActivo().booleanValue())
                    {
                        rdgVestimentaSecuencia.setChecked(true);
                    }

                }catch (Exception e){rdgVestimentaSecuencia.setChecked(false);
                }


                try{
                    Sintoma vestimentaayudavestirse = helper.getSintoma(patientid,"FAST","vestimenta","vestimentaayudavestirse");
                    if(vestimentaayudavestirse.getActivo().booleanValue())
                    {
                        rdgVestimentaAyudaVestirse.setChecked(true);
                    }

                }catch (Exception e){rdgVestimentaAyudaVestirse.setChecked(false);
                }


                try{
                    Sintoma vestimentaincapaz = helper.getSintoma(patientid,"FAST","vestimenta","vestimentaincapaz");
                    if(vestimentaincapaz.getActivo().booleanValue())
                    {
                        rdgVestimentaIncapaz.setChecked(true);
                    }

                }catch (Exception e){rdgVestimentaIncapaz.setChecked(false);
                }



                rdgVestimenta.setVisibility(View.VISIBLE);
                //election=Uri.parse("android.resource://co.edu.unicauca.appterapiademencia/mipmap/ic_insert_emoticon_black_48dp").toString();
                vestimenta.setBackgroundColor(getResources().getColor(R.color.accent_color));
                break;

            case R.id.btn_instrumentals:
                election="instrumentales";
                var_tipo=1;
                setDefaultImageButton();

                try{

                    Sintoma incapacidadtareasdomesticas = helper.getSintoma(patientid,"Blessed","instrumentales","incapacidadtareasdomesticas");
                    if(incapacidadtareasdomesticas.getActivo().booleanValue())
                    {
                        rdgInstrumentalesTareasDomesticas.setChecked(true);
                    }


                }catch (Exception e){rdgInstrumentalesTareasDomesticas.setChecked(false);}


                try{

                    Sintoma incapacidadpequenasdinero = helper.getSintoma(patientid,"Blessed","instrumentales","incapacidadpequenasdinero");
                    if(incapacidadpequenasdinero.getActivo().booleanValue())
                    {
                        rdgInstrumentalesPequenasDinero.setChecked(true);
                    }


                }catch (Exception e){rdgInstrumentalesPequenasDinero.setChecked(false);}

                try{

                    Sintoma instrumentalestelefono = helper.getSintoma(patientid,"Blessed","instrumentales","instrumentalestelefono");
                    if(instrumentalestelefono.getActivo().booleanValue())
                    {
                        rdgInstrumentalesTelefono.setChecked(true);
                    }


                }catch (Exception e){rdgInstrumentalesTelefono.setChecked(false);}


                try{

                    Sintoma instrumentalescompras = helper.getSintoma(patientid,"Blessed","instrumentales","instrumentalescompras");
                    if(instrumentalescompras.getActivo().booleanValue())
                    {
                        rdgInstrumentalesCompras.setChecked(true);
                    }


                }catch (Exception e){rdgInstrumentalesCompras.setChecked(false);}



              


                try{

                    Sintoma instrumentaleslavaropa = helper.getSintoma(patientid,"Blessed","instrumentales","instrumentaleslavaropa");
                    if(instrumentaleslavaropa.getActivo().booleanValue())
                    {
                        rdgInstrumentalesRopa.setChecked(true);
                    }


                }catch (Exception e){rdgInstrumentalesRopa.setChecked(false);}



                try{

                    Sintoma instrumentalestransporte = helper.getSintoma(patientid,"Blessed","instrumentales","instrumentalestransporte");
                    if(instrumentalestransporte.getActivo().booleanValue())
                    {
                        rdgInstrumentalesTransporte.setChecked(true);
                    }


                }catch (Exception e){rdgInstrumentalesTransporte.setChecked(false);}


                try{

                    Sintoma instrumentalesmedicacion = helper.getSintoma(patientid,"Blessed","instrumentales","instrumentalesmedicacion");
                    if(instrumentalesmedicacion.getActivo().booleanValue())
                    {
                        rdgInstrumentalesMedicacion.setChecked(true);
                    }


                }catch (Exception e){rdgInstrumentalesMedicacion.setChecked(false);}



                rdgInstrumentales.setVisibility(View.VISIBLE);
                //election=Uri.parse("android.resource://co.edu.unicauca.appterapiademencia/mipmap/ic_insert_emoticon_black_48dp").toString();
                vestimenta.setBackgroundColor(getResources().getColor(R.color.accent_color));

                break;


            case R.id.btn_changebehaviour:
                election="personalidad";
                var_tipo=1;
                setDefaultImageButton();
                try{

                    Sintoma personalidadretraimiento = helper.getSintoma(patientid,"Blessed","personalidad","personalidadretraimiento");
                    if(personalidadretraimiento.getActivo().booleanValue())
                    {
                        rdgPersonalidadRetraimiento.setChecked(true);
                    }


                }catch (Exception e){rdgPersonalidadRetraimiento.setChecked(false);}


                try{

                    Sintoma personalidadegocentrismo = helper.getSintoma(patientid,"Blessed","personalidad","personalidadegocentrismo");
                    if(personalidadegocentrismo.getActivo().booleanValue())
                    {
                        rdgPersonalidadEgocentrismo.setChecked(true);
                    }


                }catch (Exception e){rdgPersonalidadEgocentrismo.setChecked(false);}

                try{

                    Sintoma personalidadperdidainteres = helper.getSintoma(patientid,"Blessed","personalidad","personalidadperdidainteres");
                    if(personalidadperdidainteres.getActivo().booleanValue())
                    {
                        rdgPersonalidadPerdidaInteres.setChecked(true);
                    }


                }catch (Exception e){rdgPersonalidadPerdidaInteres.setChecked(false);}

                try{

                    Sintoma personalidadafectividadembotada = helper.getSintoma(patientid,"Blessed","personalidad","personalidadafectividadembotada");
                    if(personalidadafectividadembotada.getActivo().booleanValue())
                    {
                        rdgPersonalidadAfectividadEmbotada.setChecked(true);
                    }


                }catch (Exception e){rdgPersonalidadAfectividadEmbotada.setChecked(false);}

                try{

                    Sintoma personalidadperturbacionemocional = helper.getSintoma(patientid,"Blessed","personalidad","personalidadperturbacionemocional");
                    if(personalidadperturbacionemocional.getActivo().booleanValue())
                    {
                        rdgPersonalidadPerturbacionEmocional.setChecked(true);
                    }


                }catch (Exception e){rdgPersonalidadPerturbacionEmocional.setChecked(false);}

                try{

                    Sintoma personalidadhilaridadinapropiada = helper.getSintoma(patientid,"Blessed","personalidad","personalidadhilaridadinapropiada");
                    if(personalidadhilaridadinapropiada.getActivo().booleanValue())
                    {
                        rdgPersonalidadHilaridadInapropiada.setChecked(true);
                    }


                }catch (Exception e){rdgPersonalidadHilaridadInapropiada.setChecked(false);}




                try{

                    Sintoma personalidadrespuestaemocional = helper.getSintoma(patientid,"Blessed","personalidad","personalidadrespuestaemocional");
                    if(personalidadrespuestaemocional.getActivo().booleanValue())
                    {
                        rdgPersonalidadRespuestaEmocional.setChecked(true);
                    }


                }catch (Exception e){rdgPersonalidadRespuestaEmocional.setChecked(false);}



                try{

                    Sintoma personalidadindiscrecionessexuales = helper.getSintoma(patientid,"Blessed","personalidad","personalidadindiscrecionessexuales");
                    if(personalidadindiscrecionessexuales.getActivo().booleanValue())
                    {
                        rdgPersonalidadIndiscrecionesSexuales.setChecked(true);
                    }


                }catch (Exception e){rdgPersonalidadIndiscrecionesSexuales.setChecked(false);}


                try{

                    Sintoma personalidadfaltainteres = helper.getSintoma(patientid,"Blessed","personalidad","personalidadfaltainteres");
                    if(personalidadfaltainteres.getActivo().booleanValue())
                    {
                        rdgPersonalidadFaltaInteres.setChecked(true);
                    }


                }catch (Exception e){rdgPersonalidadFaltaInteres.setChecked(false);}


                try{

                    Sintoma personalidaddisminucioniniciativa = helper.getSintoma(patientid,"Blessed","personalidad","personalidaddisminucioniniciativa");
                    if(personalidaddisminucioniniciativa.getActivo().booleanValue())
                    {
                        rdgPersonalidadDisminucionIniciativa.setChecked(true);
                    }


                }catch (Exception e){rdgPersonalidadDisminucionIniciativa.setChecked(false);}



                try{

                    Sintoma personalidadhiperactividadjustificada = helper.getSintoma(patientid,"Blessed","personalidad","personalidadhiperactividadjustificada");
                    if(personalidadhiperactividadjustificada.getActivo().booleanValue())
                    {
                        rdgPersonalidadHiperactividadJustificada.setChecked(true);
                    }


                }catch (Exception e){rdgPersonalidadHiperactividadJustificada.setChecked(false);}





                //election=Uri.parse("android.resource://co.edu.unicauca.appterapiademencia/mipmap/changebehavior72px").toString();
                changeBehaviour.setBackgroundColor(getResources().getColor(R.color.accent_color));
                rdgPersonalidad.setVisibility(View.VISIBLE);

                break;
            case R.id.btn_memory:
                election="memoria";
                var_tipo=1;
                setDefaultImageButton();
                try{

                    Sintoma tareasdomesticas = helper.getSintoma(patientid,"Blessed","memoria","memorialistascortas");
                    if(tareasdomesticas.getActivo().booleanValue())
                    {
                        rdgMemoriaListasCortas.setChecked(true);
                    }


                }catch (Exception e){rdgMemoriaListasCortas.setChecked(false);}

                try
                {
                    Sintoma pequenasdinero = helper.getSintoma(patientid,"Blessed","memoria","memoriaolvidosbenignos");
                    if(pequenasdinero.getActivo().booleanValue())
                    {
                        rdgMemoriaOlvidosBenignos.setChecked(true);
                    }

                }catch (Exception e){ rdgMemoriaOlvidosBenignos.setChecked(false);}

                try{
                    Sintoma tendenciarememorar = helper.getSintoma(patientid,"Blessed","memoria","memoriatendenciarememorar");
                    if(tendenciarememorar.getActivo().booleanValue())
                    {
                        rdgMemoriaTendenciaRememorar.setChecked(true);
                    }

                }catch (Exception e){rdgMemoriaTendenciaRememorar.setChecked(false);
                }

                memoria.setBackgroundColor(getResources().getColor(R.color.accent_color));
                rdgMemoria.setVisibility(View.VISIBLE);
                break;


            case R.id.btn_orientation:
                election="orientacion";
                var_tipo=1;
                setDefaultImageButton();
                try{

                    Sintoma orientacioncasa = helper.getSintoma(patientid,"Blessed","orientacion","orientacioncasa");
                    if(orientacioncasa.getActivo().booleanValue())
                    {
                        rdgOrientacionCasa.setChecked(true);
                    }


                }catch (Exception e){rdgOrientacionCasa.setChecked(false);}

                try
                {
                    Sintoma orientacioncalle = helper.getSintoma(patientid,"Blessed","orientacion","orientacioncalle");
                    if(orientacioncalle.getActivo().booleanValue())
                    {
                        rdgOrientacionCalle.setChecked(true);
                    }

                }catch (Exception e){ rdgOrientacionCalle.setChecked(false);}

                try{
                    Sintoma orientacionentorno = helper.getSintoma(patientid,"Blessed","orientacion","orientacionentorno");
                    if(orientacionentorno.getActivo().booleanValue())
                    {
                        rdgOrientacionEntorno.setChecked(true);
                    }

                }catch (Exception e){rdgOrientacionEntorno.setChecked(false);
                }

                orientacion.setBackgroundColor(getResources().getColor(R.color.accent_color));
                rdgOrientacion.setVisibility(View.VISIBLE);
                break;




        }
        Log.e("add note","Elección"+election);
    }
    private void showDate(int year, int month, int day)
    {
        var_fecha = (new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year)).toString();

    }

    public void setDefaultImageButton(){
        movility.setBackgroundColor(getResources().getColor(R.color.material_amber));
        eating.setBackgroundColor(getResources().getColor(R.color.material_green));
        fall.setBackgroundColor(getResources().getColor(R.color.material_pink));
        lenguaje.setBackgroundColor(getResources().getColor(R.color.material_red));
        higiene.setBackgroundColor(getResources().getColor(R.color.gray_soft));
        estadodeanimo.setBackgroundColor(getResources().getColor(R.color.material_blue));
        changeBehaviour.setBackgroundColor(getResources().getColor(R.color.material_purple));
        vestimenta.setBackgroundColor(getResources().getColor(R.color.material_brown));
        memoria.setBackgroundColor(getResources().getColor(R.color.material_teal));
        medication.setBackgroundColor(getResources().getColor(R.color.md_material_blue_800));
        orientacion.setBackgroundColor(getResources().getColor(R.color.material_deepblue));
        instrumentales.setBackgroundColor(getResources().getColor(R.color.material_grey));




        rdgMovilidad.setVisibility(View.GONE);
        rdgAlimentacion.setVisibility(View.GONE);
        rdgLenguaje.setVisibility(View.GONE);
        rdgHigiene.setVisibility(View.GONE);
        rdgAnimo.setVisibility(View.GONE);
        //rdgCambioPersonalidad.setVisibility(View.GONE);
        rdgVestimenta.setVisibility(View.GONE);
        //rdgCambioPersonalidad.setVisibility(View.INVISIBLE);
        rdgMemoria.setVisibility(View.GONE);
        rdgMedicacion.setVisibility(View.GONE);
        rdgPersonalidad.setVisibility(View.GONE);
        rdgOrientacion.setVisibility(View.GONE);
        rdgInstrumentales.setVisibility(View.GONE);



        linear_efecto.setVisibility(View.VISIBLE);

        sintomasList.clear();
        nameTestList.clear();
        puntajeList.clear();
        stateList.clear();


        rdgMovilidadSitiosLejanos.setOnCheckedChangeListener(this);
        rdgMovilidadCaminar.setOnCheckedChangeListener(this);
        rdgMovilidadSentarse.setOnCheckedChangeListener(this);
        rdgMovilidadCabeza.setOnCheckedChangeListener(this);


        rdgAlimentacionCuchara.setOnCheckedChangeListener(this);
        rdgAlimentacionSolidos.setOnCheckedChangeListener(this);
        rdgAlimentacionDependiente.setOnCheckedChangeListener(this);

        rdgLenguajeLimitado.setOnCheckedChangeListener(this);
        rdgLenguajePalabra.setOnCheckedChangeListener(this);


        rdgHigieneAyudaBanarse.setOnCheckedChangeListener(this);

        rdgHigieneSoltarBano.setOnCheckedChangeListener(this);
        rdgHigieneIncontinensiaUrinaria.setOnCheckedChangeListener(this);
        rdgHigieneIncontinensiaFecal.setOnCheckedChangeListener(this);

        rdgAnimoSonrisa.setOnCheckedChangeListener(this);



        rdgInstrumentalesMedicacion.setOnCheckedChangeListener(this);
        rdgInstrumentalesTareasDomesticas.setOnCheckedChangeListener(this);
        rdgInstrumentalesTransporte.setOnCheckedChangeListener(this);
        rdgInstrumentalesRopa.setOnCheckedChangeListener(this);
        rdgInstrumentalesPequenasDinero.setOnCheckedChangeListener(this);
        rdgInstrumentalesComida.setOnCheckedChangeListener(this);
        rdgInstrumentalesTelefono.setOnCheckedChangeListener(this);
        rdgInstrumentalesCompras.setOnCheckedChangeListener(this);


        rdgMedicamentos.setOnCheckedChangeListener(this);





        //rdgVestimentaActividades.setOnCheckedChangeListener(this);
        rdgVestimentaFallosOcasionales.setOnCheckedChangeListener(this);
        rdgVestimentaSeleccionar.setOnCheckedChangeListener(this);
        rdgVestimentaSecuencia.setOnCheckedChangeListener(this);
        rdgVestimentaAyudaVestirse.setOnCheckedChangeListener(this);
        rdgVestimentaIncapaz.setOnCheckedChangeListener(this);

        rdgMemoriaListasCortas.setOnCheckedChangeListener(this);
        rdgMemoriaTendenciaRememorar.setOnCheckedChangeListener(this);
        rdgMemoriaOlvidosBenignos.setOnCheckedChangeListener(this);

        rdgOrientacionCalle.setOnCheckedChangeListener(this);
        rdgOrientacionCasa.setOnCheckedChangeListener(this);
        rdgOrientacionEntorno.setOnCheckedChangeListener(this);


        rdgPersonalidadRetraimiento.setOnCheckedChangeListener(this);
        rdgPersonalidadEgocentrismo.setOnCheckedChangeListener(this);
        rdgPersonalidadPerdidaInteres.setOnCheckedChangeListener(this);
        rdgPersonalidadAfectividadEmbotada.setOnCheckedChangeListener(this);
        rdgPersonalidadPerturbacionEmocional.setOnCheckedChangeListener(this);
        rdgPersonalidadHilaridadInapropiada.setOnCheckedChangeListener(this);
        rdgPersonalidadRespuestaEmocional.setOnCheckedChangeListener(this);
        rdgPersonalidadIndiscrecionesSexuales.setOnCheckedChangeListener(this);
        rdgPersonalidadFaltaInteres.setOnCheckedChangeListener(this);
        rdgPersonalidadDisminucionIniciativa.setOnCheckedChangeListener(this);
        rdgPersonalidadHiperactividadJustificada.setOnCheckedChangeListener(this);










    }



    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

        int separator;
        switch(compoundButton.getId())
        {

            //Instrumentales


            case R.id.rdgInstrumentalesTareasDomesticas:
                var_seleccion="incapacidadtareasdomesticas";
                stateList.add(b);
                sintomasList.add(var_seleccion);

                break;


            case R.id.rdgInstrumentalesPequenasDinero:
                var_seleccion="incapacidadpequenasdinero";
                stateList.add(b);
                sintomasList.add(var_seleccion);

                break;

            case R.id.rdgInstrumentalesTelefono:
                var_seleccion="instrumentalestelefono";
                stateList.add(b);
                sintomasList.add(var_seleccion);

                break;
            case R.id.rdgInstrumentalesCompras:
                var_seleccion="instrumentalescompras";
                stateList.add(b);
                sintomasList.add(var_seleccion);

                break;


            case R.id.rdgInstrumentalesComida:
                var_seleccion="instrumentalescomida";
                stateList.add(b);
                sintomasList.add(var_seleccion);

                break;



            case R.id.rdgInstrumentalesLavaRopa:
                var_seleccion="instrumentaleslavaropa";
                stateList.add(b);
                sintomasList.add(var_seleccion);

                break;

            case R.id.rdgInstrumentalesTransporte:
                var_seleccion="instrumentalestransporte";
                stateList.add(b);
                sintomasList.add(var_seleccion);

                break;

            case R.id.rdgInstrumentalesMedicacion:
                var_seleccion="instrumentalesmedicacion";
                stateList.add(b);
                sintomasList.add(var_seleccion);

                break;


            //Medicacion


            case R.id.rdgMedicacionMedicamentos:
                var_seleccion="medicacionmedicamentos";
                stateList.add(b);
                sintomasList.add(var_seleccion);

                break;


            //Higiene
            case R.id.rdgHigieneAyudaBanarse:
                var_seleccion="higieneayudabanarse";
                stateList.add(b);
                //nameTest = "FAST";
                //puntaje = "6b";
                sintomasList.add(var_seleccion);
                //nameTestList.add(nameTest);
                //puntajeList.add(puntaje);
                //do stuff
                break;

            case R.id.rdgHigieneSoltarBano:
                var_seleccion="higieneayudasoltarbano";
                stateList.add(b);
                //nameTest = "FAST";
                //puntaje = "6c";
                sintomasList.add(var_seleccion);
                //sintomasList.add(var_seleccion);
                //nameTestList.add(nameTest);
                //puntajeList.add(nameTest2);
                //puntajeList.add(puntaje);
                //puntajeList.add(puntaje2);
                //do stuff
                break;
            case R.id.rdgHigieneIncontinensiaUrinaria:
                var_seleccion="higieneayudaincontinensiaurinaria";
                stateList.add(b);
                //nameTest = "FAST";
                //puntaje = "6d";
                //nameTest2="Blessed";
                //puntaje2 ="3";
                sintomasList.add(var_seleccion);
                //sintomasList.add(var_seleccion);
                //nameTestList.add(nameTest);
                //puntajeList.add(nameTest2);
                //puntajeList.add(puntaje);
                //puntajeList.add(puntaje2);
                //do stuff
                break;
            case R.id.rdgHigieneIncontinensiaFecal:
                var_seleccion="higieneayudaincontinensiafecal";
                stateList.add(b);
                //nameTest = "FAST";
                //puntaje = "6e";
                //nameTest2="Blessed";
                //puntaje2 ="2";
                sintomasList.add(var_seleccion);
                //sintomasList.add(var_seleccion);
                //nameTestList.add(nameTest);
                //puntajeList.add(puntaje);
                //nameTestList.add(nameTest2);
                //puntajeList.add(puntaje2);
                //do stuff
                break;



            //Movilidad
            case R.id.rdgMovilidadSitiosLejanos:
                var_seleccion="movilidadsitioslejanos";
                stateList.add(b);
                //nameTest = "FAST";
                //puntaje = "3";
                sintomasList.add(var_seleccion);
                //nameTestList.add(nameTest);
                //puntajeList.add(puntaje);
                //do stuff
                break;
            case R.id.rdgMovilidadCaminar:
                var_seleccion="movilidadcaminar";
                stateList.add(b);
                //nameTest = "FAST";
                //puntaje = "7c";
                sintomasList.add(var_seleccion);
                //nameTestList.add(nameTest);
                //puntajeList.add(puntaje);
                //do stuff
                break;
            case R.id.rdgMovilidadSentarse:
                var_seleccion="movilidadsentarse";
                stateList.add(b);
                //nameTest = "FAST";
                //puntaje = "7d";
                sintomasList.add(var_seleccion);
                //nameTestList.add(nameTest);
                //puntajeList.add(puntaje);
                //do stuff
                break;
            case R.id.rdgMovilidadCabeza:
                var_seleccion="movilidadcabeza";
                stateList.add(b);
                //nameTest = "FAST";
                //puntaje = "7e";
                sintomasList.add(var_seleccion);
                //nameTestList.add(nameTest);
                //puntajeList.add(puntaje);
                //do stuff
                break;

            //VESTIMENTA


            case R.id.rdgVestimentaFallosOcasionales:
                var_seleccion="vestimentafallosocasionales";
                stateList.add(b);
                //nameTest = "Blessed";
                //puntaje = "1";
                sintomasList.add(var_seleccion);
                //nameTestList.add(nameTest);
                //puntajeList.add(puntaje);
                break;

            case R.id.rdgVestimentaSeleccionar:
                var_seleccion="vestimentaseleccionar";
                stateList.add(b);
                //nameTest = "FAST";
                //puntaje = "5";
                sintomasList.add(var_seleccion);
                //nameTestList.add(nameTest);
                //puntajeList.add(puntaje);
                break;

            case R.id.rdgVestimentaSecuenciaVestirse:
                var_seleccion="vestimentasecuencia";
                stateList.add(b);
                //nameTest = "Blessed";
                //puntaje = "3";
                sintomasList.add(var_seleccion);
                //nameTestList.add(nameTest);
                //puntajeList.add(puntaje);
                break;

            case R.id.rdgVestimentaAyudaVestirse:
                var_seleccion="vestimentaayudavestirse";
                stateList.add(b);
                //nameTest = "FAST";
                //puntaje = "6a";
                sintomasList.add(var_seleccion);
                //nameTestList.add(nameTest);
                //puntajeList.add(puntaje);
                break;
            case R.id.rdgVestimentaIncapaz:
                var_seleccion="vestimentaincapaz";
                stateList.add(b);
                //nameTest = "Blessed";
                //puntaje = "3";
                sintomasList.add(var_seleccion);
                //nameTestList.add(nameTest);
                //puntajeList.add(puntaje);
                break;

            //LENGUAJE

            case R.id.rdgLenguajeLimitado:
                var_seleccion="lenguajelimitado";
                stateList.add(b);
                //nameTest = "FAST";
                //puntaje = "7a";
                sintomasList.add(var_seleccion);
                //nameTestList.add(nameTest);
                //puntajeList.add(puntaje);
                break;
            case R.id.rdgLenguajePalabra:
                var_seleccion="lenguajepalabra";
                stateList.add(b);
                //nameTest = "FAST";
                //puntaje = "7b";
                sintomasList.add(var_seleccion);
                //nameTestList.add(nameTest);
                //puntajeList.add(puntaje);
                break;

            //MEMORIA
            case R.id.rdgMemoriaListasCortas:
                var_seleccion="memorialistascortas";
                stateList.add(b);
                //nameTest = "Blessed";
                //puntaje = "1";
                sintomasList.add(var_seleccion);
                //nameTestList.add(nameTest);
                //puntajeList.add(puntaje);

            case R.id.rdgMemoriaTendenciaRememorar:
                var_seleccion="memoriatendenciarememorar";
                stateList.add(b);
                //nameTest = "Blessed";
                //puntaje = "1";
                sintomasList.add(var_seleccion);
                //nameTestList.add(nameTest);
                //puntajeList.add(puntaje);
                break;
            case R.id.rdgMemoriaOlvidosBenignos:
                var_seleccion="memoriaolvidosbenignos";
                stateList.add(b);
                //nameTest = "FAST";
                //puntaje = "2";
                //nameTest2 ="Blessed";
                //puntaje2="1";
                sintomasList.add(var_seleccion);
                //sintomasList.add(var_seleccion);
                //nameTestList.add(nameTest);
                //puntajeList.add(puntaje);
                //nameTestList.add(nameTest2);
                //puntajeList.add(puntaje2);
                break;

            //ALIMENTACION


            case R.id.rdgAlimentacionCuchara:
                var_seleccion="alimentacioncuchara";
                stateList.add(b);
                //nameTest = "Blessed";
                //puntaje = "1";
                sintomasList.add(var_seleccion);
                //nameTestList.add(nameTest);
                //puntajeList.add(puntaje);
                break;
            case R.id.rdgAlimentacionSolidos:
                var_seleccion="alimentacionsolidos";
                stateList.add(b);
                //nameTest = "Blessed";
                //puntaje = "2";
                sintomasList.add(var_seleccion);
                //nameTestList.add(nameTest);
                //puntajeList.add(puntaje);
                break;
            case R.id.rdgAlimentacionDependiente:
                var_seleccion="alimentaciondependientes";
                stateList.add(b);
                //nameTest = "Blessed";
                //puntaje = "3";
                sintomasList.add(var_seleccion);
                //nameTestList.add(nameTest);
                //puntajeList.add(puntaje);
                break;


            //ESTADO DE ANIMO
            case R.id.rdgAnimoSonrisa:
                var_seleccion="animosonrisa";
                stateList.add(b);

                sintomasList.add(var_seleccion);

                break;

            //ORIENTACION

            case R.id.rdgOrientacionCalle:
                var_seleccion="orientacioncalle";
                stateList.add(b);

                sintomasList.add(var_seleccion);

                break;
            case R.id.rdgOrientacionCasa:
                var_seleccion="orientacioncasa";
                stateList.add(b);

                sintomasList.add(var_seleccion);

                break;
            case R.id.rdgOrientacionEntorno:
                var_seleccion="orientacionentorno";
                stateList.add(b);

                sintomasList.add(var_seleccion);

                break;


            //MEDICACION
            /*
            case  R.id.rdgMedicacionRutinario:
                var_seleccion="medicacionrutinaria";
                break;
            case R.id.rdgMedicacionAdverso:
                var_seleccion="medicacionadverso";
                break;
                */


            //PERSONALIDAD

            case  R.id.rdgPersonalidadRetraimiento:
                var_seleccion="personalidadretraimiento";
                stateList.add(b);
                sintomasList.add(var_seleccion);
                break;
            case R.id.rdgPersonalidadEgocentrismo:
                var_seleccion="personalidadegocentrismo";
                stateList.add(b);
                sintomasList.add(var_seleccion);
                break;
            case R.id.rdgPersonalidadPerdidaInteres:
                var_seleccion="personalidadperdidainteres";
                stateList.add(b);
                sintomasList.add(var_seleccion);
                break;
            case R.id.rdgPersonalidadAfectividadEmbotada:
                var_seleccion="personalidadafectividadembotada";
                stateList.add(b);
                sintomasList.add(var_seleccion);
                break;
            case R.id.rdgPersonalidadPerturbacionEmocional:
                var_seleccion="personalidadperturbacionemocional";
                stateList.add(b);
                sintomasList.add(var_seleccion);
                break;
            case R.id.rdgPersonalidadHilaridadInapropiada:
                var_seleccion="personalidadhilaridadinapropiada";
                stateList.add(b);
                sintomasList.add(var_seleccion);
                break;
            case R.id.rdgPersonalidadRespuestaEmocional:
                var_seleccion="personalidadrespuestaemocional";
                stateList.add(b);
                sintomasList.add(var_seleccion);
                break;

            case R.id.rdgPersonalidadIndiscrecionesSexuales:
                var_seleccion="personalidadindiscrecionessexuales";
                stateList.add(b);
                sintomasList.add(var_seleccion);
                break;

            case R.id.rdgPersonalidadFaltaInteres:
                var_seleccion="personalidadfaltainteres";
                stateList.add(b);
                sintomasList.add(var_seleccion);
                break;

            case R.id.rdgPersonalidadDisminucionIniciativa:
                var_seleccion="personalidaddisminucioniniciativa";
                stateList.add(b);
                sintomasList.add(var_seleccion);
                break;

            case R.id.rdgPersonalidadHiperactividadJustificada:
                var_seleccion="personalidadhiperactividadjustificada";
                stateList.add(b);
                sintomasList.add(var_seleccion);
                break;




        }

    }
}