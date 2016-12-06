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
import co.edu.unicauca.appterapiademencia.domain.Scale;
import co.edu.unicauca.appterapiademencia.domain.Sintoma;
import co.edu.unicauca.appterapiademencia.domain.User;
import co.edu.unicauca.appterapiademencia.domain.dao.GreenDaoHelper;
import co.edu.unicauca.appterapiademencia.domain.dao.NoteDao;
import co.edu.unicauca.appterapiademencia.domain.dao.ScaleDao;
import co.edu.unicauca.appterapiademencia.domain.dao.SintomaDao;
import co.edu.unicauca.appterapiademencia.principal.MainActivity;
import co.edu.unicauca.appterapiademencia.principal.patientlist.PatientProfileActivity;

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
    private ImageButton movility,eating,fall,medication,estadodeanimo,otro,changeBehaviour,higiene,memoria,lenguaje,vestimenta;
    private RadioGroup rdgGrupo,rdgMovilidad,rdgAlimentacion,rdgCambioPersonalidad,rdgOrientacion,rdgLenguaje,rdgMemoria,rdgHigiene,rdgVestimenta,rdgAnimo;
    private CheckBox rdgMovilidadOtro,rdgMovilidadSitiosLejanos,rdgMovilidadCaminar,rdgMovilidadSentarse,rdgMovilidadCabeza;
    private CheckBox rdgHigieneAyudaBanarse,rdgHigieneSoltarBano,rdgHigieneAyudaInodoro,rdgHigieneIncontinensiaUrinaria,rdgHigieneIncontinensiaFecal;
    private CheckBox rdgVestimentaActividades,rdgVestimentaFallosOcasionales,rdgVestimentaSeleccionar,rdgVestimentaSecuencia,rdgVestimentaAyudaVestirse,rdgVestimentaIncapaz;
    private CheckBox rdgMemoriaTendenciaRememorar,rdgMemoriaOlvidosBenignos,rdgMemoriaListasCortas;
    private CheckBox rdgLenguajeLimitado,rdgLenguajePalabra;
    private CheckBox rdgAlimentacionCuchara,rdgAlimentacionSolidos,rdgAlimentacionDependiente;
    private CheckBox rdgAnimoSonrisa;
    private ArrayList<String> sintomasList;
    private ArrayList<String> nameTestList;
    private ArrayList<String> puntajeList;
    private ArrayList<Boolean> stateList;
    private RadioButton rdgTardia;
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
        lenguaje= (ImageButton) findViewById(R.id.btn_language);
        estadodeanimo= (ImageButton) findViewById(R.id.btn_estadodeanimo);
        higiene = (ImageButton) findViewById(R.id.btn_higiene);
        changeBehaviour = (ImageButton) findViewById(R.id.btn_changebehaviour);
        vestimenta = (ImageButton) findViewById(R.id.btn_vestimenta);
        memoria = (ImageButton) findViewById(R.id.btn_memory);
        rdgTardia = (RadioButton) findViewById(R.id.rdgTardia);

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

        //rdgGrupo.setOnCheckedChangeListener(this);


        //HIGIENE
        rdgHigieneAyudaBanarse = (CheckBox) findViewById(R.id.rdgHigieneAyudaBanarse);
        rdgHigieneSoltarBano = (CheckBox) findViewById(R.id.rdgHigieneSoltarBano);
        rdgHigieneAyudaInodoro = (CheckBox) findViewById(R.id.rdgHigieneAyudaInodoro);
        rdgHigieneIncontinensiaUrinaria = (CheckBox) findViewById(R.id.rdgHigieneIncontinensiaUrinaria);
        rdgHigieneIncontinensiaFecal = (CheckBox) findViewById(R.id.rdgHigieneIncontinensiaFecal);

        //MOVILIDAD

        rdgMovilidadSitiosLejanos = (CheckBox) findViewById(R.id.rdgMovilidadSitiosLejanos);
        rdgMovilidadCaminar = (CheckBox) findViewById(R.id.rdgMovilidadCaminar);
        rdgMovilidadSentarse = (CheckBox) findViewById(R.id.rdgMovilidadSentarse);
        rdgMovilidadCabeza = (CheckBox) findViewById(R.id.rdgMovilidadCabeza);


        //VESTIMENTA

        rdgVestimentaActividades = (CheckBox) findViewById(R.id.rdgVestimentaActividades);
        rdgVestimentaFallosOcasionales = (CheckBox) findViewById(R.id.rdgVestimentaFallosOcasionales);
        rdgVestimentaAyudaVestirse = (CheckBox) findViewById(R.id.rdgVestimentaAyudaVestirse);
        rdgVestimentaSecuencia = (CheckBox) findViewById(R.id.rdgVestimentaSecuenciaVestirse);
        rdgVestimentaIncapaz = (CheckBox) findViewById(R.id.rdgVestimentaIncapaz);

        //MEMORIA

        rdgMemoriaListasCortas = (CheckBox) findViewById(R.id.rdgMemoriaListasCortas);
        rdgMemoriaTendenciaRememorar = (CheckBox) findViewById(R.id.rdgMemoriaTendenciaRememorar);
        rdgMemoriaOlvidosBenignos = (CheckBox) findViewById(R.id.rdgMemoriaOlvidosBenignos);


        //LENGUAJE

        rdgMemoriaListasCortas = (CheckBox) findViewById(R.id.rdgMemoriaListasCortas);
        rdgLenguajeLimitado = (CheckBox) findViewById(R.id.rdgLenguajeLimitado);
        rdgLenguajePalabra= (CheckBox) findViewById(R.id.rdgLenguajePalabra);

        //ALIMENTACION
        rdgAlimentacionCuchara= (CheckBox) findViewById(R.id.rdgAlimentacionCuchara);
        rdgAlimentacionSolidos= (CheckBox) findViewById(R.id.rdgAlimentacionSolidos);
        rdgAlimentacionDependiente= (CheckBox) findViewById(R.id.rdgAlimentacionDependiente);

        //ANIMO SONRISA
        rdgAnimoSonrisa= (CheckBox) findViewById(R.id.rdgAnimoSonrisa);


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

    }




    public void saveNote(View view){

        Log.e("addnote",""+optionOwner);


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
            if(note!=null)
            {
                for(int i=0;i<sintomasList.size();i++)
                {

                    switch (sintomasList.get(i))
                    {
                        //----------VESTIMENTA-------------
                        case "incapacidadtareasdomesticas":
                            Sintoma tareasdomesticas;
                        try{
                            tareasdomesticas = helper.getSintoma(patientid,"Blessed","vestimenta","incapacidadtareasdomesticas");
                            tareasdomesticas.setActivo(stateList.get(i));
                            sintomaDao.update(tareasdomesticas);

                            Log.e("guardado y actualizado",tareasdomesticas.getScaleList().get(0).getPuntaje().toString());


                        }catch (Exception e){}
                            break;
                        case "incapacidadpequenasdinero":
                        try{
                            Sintoma pequenasdinero;
                            pequenasdinero = helper.getSintoma(patientid,"Blessed","vestimenta","incapacidadpequenasdinero");
                            pequenasdinero.setActivo(stateList.get(i));
                            sintomaDao.update(pequenasdinero);


                            Log.e("guardado y actualizado",pequenasdinero.getScaleList().get(0).getPuntaje().toString());

                        }catch (Exception e){}
                            break;




                        //----------ORIENTACION-------------

                        case "orientacioncasa":
                        try{
                            Sintoma orientacioncasa;
                            orientacioncasa = helper.getSintoma(patientid,"Blessed","orientacion","orientacioncasa");
                            orientacioncasa.setActivo(stateList.get(i));
                            sintomaDao.update(orientacioncasa);

                            Log.e("guardado y actualizado",orientacioncasa.getScaleList().get(0).getPuntaje().toString());


                        }catch (Exception e){
                        }
                            break;
                        case "orientacioncalle":
                        try{
                            Sintoma orientacioncalle;
                            orientacioncalle = helper.getSintoma(patientid,"Blessed","orientacion","orientacioncalle");
                            orientacioncalle.setActivo(stateList.get(i));
                            sintomaDao.update(orientacioncalle);

                            Log.e("guardado y actualizado",orientacioncalle.getScaleList().get(0).getPuntaje().toString());


                        }catch (Exception e){
                        }
                            break;

                        case "orientacionentorno":
                        try{
                            Sintoma orientacionentorno;
                            orientacionentorno = helper.getSintoma(patientid,"Blessed","orientacion","orientacionentorno");
                            orientacionentorno.setActivo(stateList.get(i));
                            sintomaDao.update(orientacionentorno);
                            Log.e("guardado y actualizado",orientacionentorno.getScaleList().get(0).getPuntaje().toString());

                        }catch (Exception e){
                        }

                            //----------MEMORIA-------------

                        case "memorialistascortas":

                            try{
                                Sintoma memorialistascortas;
                                memorialistascortas = helper.getSintoma(patientid,"Blessed","memoria","memorialistascortas");
                                memorialistascortas.setActivo(stateList.get(i));
                                sintomaDao.update(memorialistascortas);

                                Log.e("guardado y actualizado",memorialistascortas.getScaleList().get(0).getPuntaje().toString());


                            }catch (Exception e){
                            }
                            break;

                        case "memoriaolvidosbenignos":

                        try{
                            Sintoma memoriaolvidosbenignos;
                            memoriaolvidosbenignos = helper.getSintoma(patientid,"Blessed","memoria","memoriaolvidosbenignos");
                            memoriaolvidosbenignos.setActivo(stateList.get(i));
                            sintomaDao.update(memoriaolvidosbenignos);

                            Log.e("guardado y actualizado", memoriaolvidosbenignos.getScaleList().get(0).getPuntaje().toString());


                        }catch (Exception e){
                        }
                            break;

                        case "memoriatendenciarememorar":

                        try{
                            Sintoma tendenciarememorar;
                            tendenciarememorar = helper.getSintoma(patientid,"Blessed","memoria","memoriatendenciarememorar");
                            tendenciarememorar.setActivo(stateList.get(i));
                            sintomaDao.update(tendenciarememorar);
                            //tareasdomesticas.update();
                            //tareasdomesticas.refresh();
                            Log.e("guardado y actualizado", tendenciarememorar.getScaleList().get(0).getPuntaje().toString());

                        }catch (Exception e){    }
                            break;

                    }



                    /*

                    Sintoma sintoma = new Sintoma(null,patientid, election, sintomasList.get(i),true);
                    Scale scale = new Scale(null,sintoma.getId(),nameTestList.get(i),puntajeList.get(i));
                    sintomaDao.insert(sintoma);
                    scaleDao.insert(scale);

                    Log.e("sintomadao nuevo",sintoma.getId()+"");
                    Log.e("sintomadao nuevo",sintoma.getAmbito()+"");
                    Log.e("sintomadao nuevo",sintoma.getSigno()+"");
                    Log.e("sintomadao nuevo"," TEST:"+sintoma.getScaleList().get(0).getEscalaname());
                    */

                }
            }

            if(!var_state)
            {
                MaterialDialog.Builder builder = new MaterialDialog.Builder(this);
                builder.title("Nota Guardada").content("Su Nota ha sido guardada en la bandeja de supervisores, para aprobación").positiveText(R.string.dialog_succes_agree).show();;
                builder.onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog materialDialog, DialogAction dialogAction) {

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

    public void goToProfile()
    {
        Intent ir_main = new Intent(this, PatientProfileActivity.class);
        ir_main.putExtra("carerIndicator",carerMessageIndicator);
        ir_main.putExtra("cedula", cedula);
        startActivity(ir_main);
        overridePendingTransition(R.anim.left_in, R.anim.left_out);
        finish();
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
                startActivity(intent);
                overridePendingTransition(R.anim.right_in, R.anim.right_out);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_movility:
                election = "movilidad";
                var_tipo=1;
                setDefaultImageButton();
                rdgMovilidad.setVisibility(View.VISIBLE);
                //election= Uri.parse("android.resource://co.edu.unicauca.appterapiademencia/mipmap/movility72px").toString();
                movility.setBackgroundColor(getResources().getColor(R.color.accent_color));

                break;
            case R.id.btn_eating:
                election="alimentacion";
                var_tipo=1;
                setDefaultImageButton();
                rdgAlimentacion.setVisibility(View.VISIBLE);
                //election=Uri.parse("android.resource://co.edu.unicauca.appterapiademencia/mipmap/eating72px").toString();
                eating.setBackgroundColor(getResources().getColor(R.color.accent_color));

                break;
            case R.id.btn_fall:
                election="caidas";
                var_tipo=0;
                setDefaultImageButton();
                //txt_adverso.setVisibility(View.GONE);
                //election=Uri.parse("android.resource://co.edu.unicauca.appterapiademencia/mipmap/caida72px").toString();
                fall.setBackgroundColor(getResources().getColor(R.color.accent_color));
                //gravedad = 2;
                var_color= "centinela";
                break;
            case R.id.btn_language:
                election="lenguaje";
                var_tipo=1;
                setDefaultImageButton();
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
                rdgHigiene.setVisibility(View.VISIBLE);
                //election=Uri.parse("android.resource://co.edu.unicauca.appterapiademencia/mipmap/otro72px").toString();
                higiene.setBackgroundColor(getResources().getColor(R.color.accent_color));
                break;
            case R.id.btn_estadodeanimo:
                election="animo";
                var_tipo=1;
                setDefaultImageButton();
                rdgAnimo.setVisibility(View.VISIBLE);
                //election=Uri.parse("android.resource://co.edu.unicauca.appterapiademencia/mipmap/ic_insert_emoticon_black_48dp").toString();
                estadodeanimo.setBackgroundColor(getResources().getColor(R.color.accent_color));
                break;
            case R.id.btn_vestimenta:
                election="vestimenta";
                var_tipo=1;
                setDefaultImageButton();
                rdgVestimenta.setVisibility(View.VISIBLE);
                //election=Uri.parse("android.resource://co.edu.unicauca.appterapiademencia/mipmap/ic_insert_emoticon_black_48dp").toString();
                vestimenta.setBackgroundColor(getResources().getColor(R.color.accent_color));
                break;
            case R.id.btn_changebehaviour:
                election="cambiocomportamiento";
                var_tipo=1;
                setDefaultImageButton();

                //election=Uri.parse("android.resource://co.edu.unicauca.appterapiademencia/mipmap/changebehavior72px").toString();
                changeBehaviour.setBackgroundColor(getResources().getColor(R.color.accent_color));
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


        rdgVestimenta.setVisibility(View.GONE);
        rdgAlimentacion.setVisibility(View.GONE);
        rdgLenguaje.setVisibility(View.GONE);
        rdgHigiene.setVisibility(View.GONE);
        //rdgCambioPersonalidad.setVisibility(View.INVISIBLE);
        rdgMemoria.setVisibility(View.GONE);
        rdgMovilidad.setVisibility(View.GONE);



        linear_efecto.setVisibility(View.VISIBLE);

        sintomasList.clear();
        nameTestList.clear();
        puntajeList.clear();
        stateList.clear();

        /*
        rdgHigieneAyudaBanarse.setOnCheckedChangeListener(this);
        rdgHigieneAyudaInodoro.setOnCheckedChangeListener(this);
        rdgHigieneSoltarBano.setOnCheckedChangeListener(this);
        rdgHigieneIncontinensiaUrinaria.setOnCheckedChangeListener(this);
        rdgHigieneIncontinensiaFecal.setOnCheckedChangeListener(this);
        rdgMovilidadSitiosLejanos.setOnCheckedChangeListener(this);
        rdgMovilidadCaminar.setOnCheckedChangeListener(this);
        rdgMovilidadSentarse.setOnCheckedChangeListener(this);
        rdgMovilidadCabeza.setOnCheckedChangeListener(this);
        rdgVestimentaActividades.setOnCheckedChangeListener(this);
        rdgVestimentaFallosOcasionales.setOnClickListener(this);
        rdgVestimentaSeleccionar.setOnCheckedChangeListener(this);
        rdgVestimentaSecuencia.setOnClickListener(this);
        rdgVestimentaAyudaVestirse.setOnCheckedChangeListener(this);
        rdgVestimentaIncapaz.setOnCheckedChangeListener(this);
        */
        rdgMemoriaListasCortas.setOnCheckedChangeListener(this);
        rdgMemoriaTendenciaRememorar.setOnCheckedChangeListener(this);
        rdgMemoriaOlvidosBenignos.setOnCheckedChangeListener(this);


        /*
        rdgLenguajeLimitado.setOnCheckedChangeListener(this);
        rdgLenguajePalabra.setOnCheckedChangeListener(this);
        rdgAlimentacionCuchara.setOnCheckedChangeListener(this);
        rdgAlimentacionSolidos.setOnCheckedChangeListener(this);
        rdgAlimentacionDependiente.setOnCheckedChangeListener(this);
        */




    }



    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {


        int separator;
        switch(compoundButton.getId()){

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
            case R.id.rdgHigieneAyudaInodoro:
                var_seleccion="higieneayudainodoro";
                //nameTest = "FAST";
                //puntaje = "6c";
                stateList.add(b);
                //sintomasList.add(var_seleccion);
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

            case R.id.rdgVestimentaActividades:
                stateList.add(b);
                stateList.add(b);
                stateList.add(b);
                var_seleccion="vestimentaactividades";
                var_seleccion2="incapacidadtareasdomesticas";
                var_seleccion2="incapacidadpequenasdinero";
                //nameTest = "FAST";
                //puntaje = "4";
                //nameTest2="Blessed";
                //puntaje2="1";
                //nameTest3="Blessed";
                //puntaje3="2";
                sintomasList.add(var_seleccion);
                //nameTestList.add(nameTest);
                //puntajeList.add(puntaje);

                sintomasList.add(var_seleccion2);
                //nameTestList.add(nameTest2);
                //puntajeList.add(puntaje2);

                sintomasList.add(var_seleccion3);
                //nameTestList.add(nameTest3);
                //puntajeList.add(puntaje3);

                break;

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
                //nameTest = "FAST";
                //puntaje = "7e";
                sintomasList.add(var_seleccion);
                //nameTestList.add(nameTest);
                //puntajeList.add(puntaje);
                break;






        }

    }
}
