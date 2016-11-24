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
import co.edu.unicauca.appterapiademencia.domain.dao.SintomaDao;
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
    private CheckBox rdgVestimentaActividades,rdgVestimentaSeleccionar,rdgVestimentaAyudaVestirse,rdgVestimentaIncapaz;
    private CheckBox rdgMemoriaTendenciaRememorar,rdgMemoriaOlvidosBenignos;
    private CheckBox rdgLenguajeLimitado,rdgLenguajePalabra;
    private CheckBox rdgAlimentacionCuchara,rdgAlimentacionSolidos,rdgAlimentacionDependiente;
    private CheckBox rdgAnimoSonrisa;
    private ArrayList<String> sintomasList;
    private RadioButton rdgTardia;
    private NoteDao noteDao;
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

    public AddNoteActivity(){
        this.helper = GreenDaoHelper.getInstance();
        this.noteDao = helper.getNoteDao();
        this.sintomaDao= helper.getSintomaDao();
        this.sintomasList = new ArrayList<String>();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_note);
        Bundle bundl=getIntent().getExtras();
        sintomasList.clear();
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


       /*
        rdgTardia = (RadioButton) findViewById(R.id.rdgTardia);
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
        rdgMemoria = (RadioGroup) findViewById(R.id.rdgMemoria);
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
        rdgVestimentaAyudaVestirse = (CheckBox) findViewById(R.id.rdgVestimentaAyudaVestirse);
        rdgVestimentaIncapaz = (CheckBox) findViewById(R.id.rdgVestimentaIncapaz);

        //MEMORIA

        rdgMemoriaTendenciaRememorar = (CheckBox) findViewById(R.id.rdgMemoriaTendenciaRememorar);
        rdgMemoriaOlvidosBenignos = (CheckBox) findViewById(R.id.rdgMemoriaOlvidosBenignos);


        //LENGUAJE

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
        estadodeanimo.setOnClickListener(this);
        memoria.setOnClickListener(this);
        changeBehaviour.setOnClickListener(this);
        vestimenta.setOnClickListener(this);

    }
   /*
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        // TODO Auto-generated method stub
        if (checkedId == R.id.rdgDeterioro) {
            var_color = "deterioro";
        } else if (checkedId == R.id.rdgMejora)
        {
            var_color = "mejora";
        }else if (checkedId == R.id.rdgNeutral) {
            var_color= "neutral";
        }
        else if (checkedId == R.id.rdgAdverso) {
            var_color= "adverso";
        }


    }
         */
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
            if(var_late==true)
            {
                Log.e("addnote","var_late true ");
            }
            else
            {
                Log.e("addnote","var_late false");
            }
            if(var_state==true)
            {
                Log.e("addnote","var_state true");
            }
            else
            {
                Log.e("addnote","var_state false");
            }

            Log.e("addnote","");

            Note note = new Note(null, patientid, userId, var_tipo, var_fecha, var_hora, var_description,election, var_seleccion, var_owner, var_late, var_state);
            noteDao.insert(note);
            if(note!=null){
                for(int i=0;i<=sintomasList.size();i++)
                {
                    Sintoma sintoma = new Sintoma(null,patientid, userId, election,sintomasList.get(i));
                    sintomaDao.insert(sintoma);
                    Log.e("sintomadao nuevo",sintoma.getId()+"");
                }
            }


            Intent ir_main = new Intent(this, PatientProfileActivity.class);
            ir_main.putExtra("carerIndicator",carerMessageIndicator);
            ir_main.putExtra("cedula", cedula);
            startActivity(ir_main);
            overridePendingTransition(R.anim.left_in, R.anim.left_out);
            finish();
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
                election = "movility";
                var_tipo=1;
                setDefaultImageButton();
                rdgMovilidad.setVisibility(View.VISIBLE);
                //election= Uri.parse("android.resource://co.edu.unicauca.appterapiademencia/mipmap/movility72px").toString();
                movility.setBackgroundColor(getResources().getColor(R.color.accent_color));

                break;
            case R.id.btn_eating:
                election="eating";
                var_tipo=1;
                setDefaultImageButton();
                rdgAlimentacion.setVisibility(View.VISIBLE);
                //election=Uri.parse("android.resource://co.edu.unicauca.appterapiademencia/mipmap/eating72px").toString();
                eating.setBackgroundColor(getResources().getColor(R.color.accent_color));

                break;
            case R.id.btn_fall:
                election="fall";
                var_tipo=0;
                setDefaultImageButton();
                //txt_adverso.setVisibility(View.GONE);
                //election=Uri.parse("android.resource://co.edu.unicauca.appterapiademencia/mipmap/caida72px").toString();
                fall.setBackgroundColor(getResources().getColor(R.color.accent_color));
                //gravedad = 2;
                var_color= "centinela";
                break;
            case R.id.btn_language:
                election="language";
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
                election="changebehaviour";
                var_tipo=1;
                setDefaultImageButton();

                //election=Uri.parse("android.resource://co.edu.unicauca.appterapiademencia/mipmap/changebehavior72px").toString();
                changeBehaviour.setBackgroundColor(getResources().getColor(R.color.accent_color));
                break;
            case R.id.btn_memory:
                election="memory";
                var_tipo=1;
                setDefaultImageButton();
                //election=Uri.parse("android.resource://co.edu.unicauca.appterapiademencia/mipmap/changebehavior72px").toString();
                memoria.setBackgroundColor(getResources().getColor(R.color.accent_color));
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


        rdgVestimenta.setVisibility(View.VISIBLE);
        rdgAlimentacion.setVisibility(View.VISIBLE);
        rdgLenguaje.setVisibility(View.VISIBLE);
        rdgHigiene.setVisibility(View.VISIBLE);
        rdgCambioPersonalidad.setVisibility(View.VISIBLE);
        rdgMemoria.setVisibility(View.VISIBLE);
        rdgMovilidad.setVisibility(View.VISIBLE);



        linear_efecto.setVisibility(View.VISIBLE);


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
        rdgVestimentaSeleccionar.setOnCheckedChangeListener(this);
        rdgVestimentaAyudaVestirse.setOnCheckedChangeListener(this);
        rdgVestimentaIncapaz.setOnCheckedChangeListener(this);
        rdgMemoriaTendenciaRememorar.setOnCheckedChangeListener(this);
        rdgMemoriaOlvidosBenignos.setOnCheckedChangeListener(this);
        rdgLenguajeLimitado.setOnCheckedChangeListener(this);
        rdgLenguajePalabra.setOnCheckedChangeListener(this);
        rdgAlimentacionCuchara.setOnCheckedChangeListener(this);
        rdgAlimentacionSolidos.setOnCheckedChangeListener(this);
        rdgAlimentacionDependiente.setOnCheckedChangeListener(this);



    }



    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch(compoundButton.getId()){
            //Higiene
            case R.id.rdgHigieneAyudaBanarse:
                var_seleccion="higieneayudabanarse";
                sintomasList.add(var_seleccion);
                //do stuff
                break;
            case R.id.rdgHigieneAyudaInodoro:
                var_seleccion="higieneayudainodoro";
                sintomasList.add(var_seleccion);
                //do stuff
                break;
            case R.id.rdgHigieneSoltarBano:
                var_seleccion="higieneayudasoltarbano";
                sintomasList.add(var_seleccion);
                //do stuff
                break;
            case R.id.rdgHigieneIncontinensiaUrinaria:
                var_seleccion="higieneayudaincontinensiaurinaria";
                sintomasList.add(var_seleccion);
                //do stuff
                break;
            case R.id.rdgHigieneIncontinensiaFecal:
                var_seleccion="higieneayudaincontinensiafecal";
                sintomasList.add(var_seleccion);
                //do stuff
                break;



            //Movilidad
            case R.id.rdgMovilidadSitiosLejanos:
                var_seleccion="movilidadsitioslejanos";
                sintomasList.add(var_seleccion);
                //do stuff
                break;
            case R.id.rdgMovilidadCaminar:
                var_seleccion="movilidadcaminar";
               sintomasList.add(var_seleccion);
                //do stuff
                break;
            case R.id.rdgMovilidadSentarse:
                var_seleccion="movilidadsentarse";
                sintomasList.add(var_seleccion);
                //do stuff
                break;
            case R.id.rdgMovilidadCabeza:
                var_seleccion="movilidadcabeza";
                sintomasList.add(var_seleccion);
                //do stuff
                break;

            //VESTIMENTA

            case R.id.rdgVestimentaActividades:
                var_seleccion="vestimentaactividades";
                sintomasList.add(var_seleccion);
                break;
            case R.id.rdgVestimentaSeleccionar:
                var_seleccion="vestimentaseleccionar";
                sintomasList.add(var_seleccion);
                break;
            case R.id.rdgVestimentaAyudaVestirse:
                var_seleccion="vestimentaayudavestirse";
                sintomasList.add(var_seleccion);
                break;
            case R.id.rdgVestimentaIncapaz:
                var_seleccion="vestimentaincapaz";
                sintomasList.add(var_seleccion);
                break;

            //LENGUAJE

            case R.id.rdgLenguajeLimitado:
                var_seleccion="lenguajelimitado";
                sintomasList.add(var_seleccion);
                break;
            case R.id.rdgLenguajePalabra:
                var_seleccion="lenguajepalabra";
                sintomasList.add(var_seleccion);
                break;

            //MEMORIA

            case R.id.rdgMemoriaTendenciaRememorar:
                var_seleccion="memoriatendenciarememorar";
                sintomasList.add(var_seleccion);
                break;
            case R.id.rdgMemoriaOlvidosBenignos:
                var_seleccion="memoriaolvidosbenignos";
                sintomasList.add(var_seleccion);
                break;

            //ALIMENTACION


            case R.id.rdgAlimentacionCuchara:
                var_seleccion="alimentacioncuchara";
                sintomasList.add(var_seleccion);
                break;
            case R.id.rdgAlimentacionSolidos:
                var_seleccion="alimentacionsolidos";
                sintomasList.add(var_seleccion);
                break;
            case R.id.rdgAlimentacionDependiente:
                var_seleccion="alimentaciondependientes";
                sintomasList.add(var_seleccion);
                break;


            //ESTADO DE ANIMO
            case R.id.rdgAnimoSonrisa:
                var_seleccion="animosonrisa";
                sintomasList.add(var_seleccion);
                break;






        }

    }
}
