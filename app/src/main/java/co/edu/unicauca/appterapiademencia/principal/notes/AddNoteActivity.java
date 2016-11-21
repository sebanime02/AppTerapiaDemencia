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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import co.edu.unicauca.appterapiademencia.R;
import co.edu.unicauca.appterapiademencia.domain.Note;
import co.edu.unicauca.appterapiademencia.domain.Patient;
import co.edu.unicauca.appterapiademencia.domain.User;
import co.edu.unicauca.appterapiademencia.domain.dao.GreenDaoHelper;
import co.edu.unicauca.appterapiademencia.domain.dao.NoteDao;
import co.edu.unicauca.appterapiademencia.principal.patientlist.PatientProfileActivity;

/**
 * Created by SEBAS on 14/11/2016.
 */

public class AddNoteActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener{
    private Calendar calendar;
    private int year, month, day;
    private String ownertext;
    private EditText description,owner;
    private String date;
    private String hour;
    private RadioButton rdgMejora,rdgNeutral,rdgRetroceso,rdgIncidente,rdgAdverso,rdgCentinela;
    private TextView txt_adverso;
    private ImageButton movility,eating,fall,medication,estadodeanimo,otro,changeBehaviour;
    private RadioGroup rdgGrupo;
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

    public AddNoteActivity(){
        this.helper = GreenDaoHelper.getInstance();
        this.noteDao = helper.getNoteDao();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_note);
        Bundle bundl=getIntent().getExtras();

        description= (EditText) findViewById(R.id.txt_description);

        movility= (ImageButton) findViewById(R.id.btn_movility);
        eating= (ImageButton) findViewById(R.id.btn_eating);
        fall= (ImageButton) findViewById(R.id.btn_fall);
        medication= (ImageButton) findViewById(R.id.btn_language);
        estadodeanimo= (ImageButton) findViewById(R.id.btn_estadodeanimo);
        otro = (ImageButton) findViewById(R.id.btn_other);
        changeBehaviour = (ImageButton) findViewById(R.id.btn_changebehaviour);



        rdgTardia = (RadioButton) findViewById(R.id.rdgTardia);
        rdgGrupo = (RadioGroup) findViewById(R.id.rdgGrupo);
        //rdgRutinario = (RadioButton) findViewById(R.id.rdgRutinario);

        rdgMejora = (RadioButton) findViewById(R.id.rdgMejora);
        rdgAdverso = (RadioButton) findViewById(R.id.rdgAdverso);
        rdgRetroceso = (RadioButton) findViewById(R.id.rdgDeterioro);
        rdgNeutral = (RadioButton) findViewById(R.id.rdgNeutral);
        rdgMejora = (RadioButton) findViewById(R.id.rdgMejora);
        linear_efecto = (LinearLayout) findViewById(R.id.linear_efecto);
        txt_adverso = (TextView) findViewById(R.id.txt_adverso);


        rdgGrupo.setOnCheckedChangeListener(this);

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
        medication.setOnClickListener(this);
        estadodeanimo.setOnClickListener(this);
        otro.setOnClickListener(this);
        changeBehaviour.setOnClickListener(this);

    }

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

            Note note = new Note(null, patientid, userId, election, var_fecha, var_hora, var_description,"CUIDADO", var_color, var_owner, var_late, var_state);
            noteDao.insert(note);
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
                setDefaultImageButton();
                //election= Uri.parse("android.resource://co.edu.unicauca.appterapiademencia/mipmap/movility72px").toString();
                movility.setBackgroundColor(getResources().getColor(R.color.accent_color));

                break;
            case R.id.btn_eating:
                election="eating";
                setDefaultImageButton();
                //election=Uri.parse("android.resource://co.edu.unicauca.appterapiademencia/mipmap/eating72px").toString();
                eating.setBackgroundColor(getResources().getColor(R.color.accent_color));

                break;
            case R.id.btn_fall:
                election="fall";
                setDefaultImageButton();
                linear_efecto.setVisibility(View.GONE);
                rdgMejora.setVisibility(View.GONE);
                rdgNeutral.setVisibility(View.GONE);
                rdgRetroceso.setVisibility(View.GONE);
                txt_adverso.setVisibility(View.GONE);
                //election=Uri.parse("android.resource://co.edu.unicauca.appterapiademencia/mipmap/caida72px").toString();
                fall.setBackgroundColor(getResources().getColor(R.color.accent_color));
                gravedad = 2;

                var_color= "centinela";
                break;
            case R.id.btn_language:
                election="medication";
                setDefaultImageButton();
                //election=Uri.parse("android.resource://co.edu.unicauca.appterapiademencia/mipmap/medication72px").toString();
                medication.setBackgroundColor(getResources().getColor(R.color.accent_color));
                gravedad=1;
                rdgAdverso.setVisibility(View.VISIBLE);
                txt_adverso.setVisibility(View.VISIBLE);

                break;
            case R.id.btn_other:
                election="otro";
                setDefaultImageButton();
                //election=Uri.parse("android.resource://co.edu.unicauca.appterapiademencia/mipmap/otro72px").toString();
                otro.setBackgroundColor(getResources().getColor(R.color.accent_color));
                break;
            case R.id.btn_estadodeanimo:
                election="health";
                setDefaultImageButton();
                //election=Uri.parse("android.resource://co.edu.unicauca.appterapiademencia/mipmap/ic_insert_emoticon_black_48dp").toString();
                estadodeanimo.setBackgroundColor(getResources().getColor(R.color.accent_color));
                break;
            case R.id.btn_changebehaviour:
                election="changebehaviour";
                setDefaultImageButton();
                //election=Uri.parse("android.resource://co.edu.unicauca.appterapiademencia/mipmap/changebehavior72px").toString();
                changeBehaviour.setBackgroundColor(getResources().getColor(R.color.accent_color));
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
        medication.setBackgroundColor(getResources().getColor(R.color.material_red));
        otro.setBackgroundColor(getResources().getColor(R.color.gray_soft));
        estadodeanimo.setBackgroundColor(getResources().getColor(R.color.material_blue));
        changeBehaviour.setBackgroundColor(getResources().getColor(R.color.material_purple));
        linear_efecto.setVisibility(View.VISIBLE);
        rdgMejora.setVisibility(View.VISIBLE);
        rdgNeutral.setVisibility(View.VISIBLE);
        rdgRetroceso.setVisibility(View.VISIBLE);

    }
}
