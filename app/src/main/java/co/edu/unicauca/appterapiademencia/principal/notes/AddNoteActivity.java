package co.edu.unicauca.appterapiademencia.principal.notes;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.afollestad.materialdialogs.MaterialDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import co.edu.unicauca.appterapiademencia.R;
import co.edu.unicauca.appterapiademencia.domain.Note;
import co.edu.unicauca.appterapiademencia.domain.Patient;
import co.edu.unicauca.appterapiademencia.domain.User;
import co.edu.unicauca.appterapiademencia.domain.dao.GreenDaoHelper;
import co.edu.unicauca.appterapiademencia.domain.dao.NoteDao;

/**
 * Created by SEBAS on 14/11/2016.
 */

public class AddNoteActivity extends AppCompatActivity implements View.OnClickListener{
    private Calendar calendar;
    private int year, month, day;
    private String ownertext;
    private EditText description,owner;
    private String date;
    private String hour;
    private RadioButton rdgMejora,rdgIncidente,rdgAdverso,rdgCentinela;
    private ImageButton movility,eating,fall,medication,health,otro,changeBehaviour;
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

    public AddNoteActivity(){
        this.helper = GreenDaoHelper.getInstance();

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
        medication= (ImageButton) findViewById(R.id.btn_medication);
        health= (ImageButton) findViewById(R.id.btn_health);
        otro = (ImageButton) findViewById(R.id.btn_other);
        changeBehaviour = (ImageButton) findViewById(R.id.btn_changebehaviour);
        rdgTardia = (RadioButton) findViewById(R.id.rdgTardia);
        owner = (EditText) findViewById(R.id.txt_responsable);

        SharedPreferences loginpreference = getSharedPreferences("appdata", Context.MODE_PRIVATE);
        if(loginpreference.getString("username","")!=null)
        {
            username = loginpreference.getString("username", "");
            if(!username.equals(""))
            {
                Log.e("addnote","preference username"+username);
                User user = helper.getUserInformation(username);
                userId=user.getId();
                owner.setVisibility(View.GONE);
                ownertext ="";
            }
        }

        if(bundl!=null)
        {

            Log.e("addnote","bundle: "+bundl.getString("idpatient"));
            cedula =Long.parseLong(bundl.getString("idpatient")) ;
            Patient patient = helper.getPatientInformation(cedula);
            patientid = patient.getId();
        }
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);




    }


    public void saveNote(View view){


        if(validar(description.getText().toString())==false)
        {

            new MaterialDialog.Builder(this).title(R.string.addnote_empty_title).content(R.string.addnote_empty_content).positiveText(R.string.dialog_succes_agree).icon(getResources().getDrawable(R.drawable.sadface)).show();

        }
        else
        {

            String var_description = description.getText().toString();

            SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a");
            var_hora= dateFormat.format(new Date()).toString();
            Note note = new Note(null,patientid,userId,election,var_fecha,var_hora,var_description,"","",false,false);
            noteDao.insert(note);
        }
    }

    public Boolean validar(String description) {
        if (description.equals("")) {
            return false;
        }else {
            return true;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_movility:
                election= Uri.parse("android.resource://co.edu.unicauca.appterapiademencia/mipmap/movility72px").toString();
                break;
            case R.id.btn_eating:
                election=Uri.parse("android.resource://co.edu.unicauca.appterapiademencia/mipmap/eating72px").toString();
                break;
            case R.id.btn_fall:
                election=Uri.parse("android.resource://co.edu.unicauca.appterapiademencia/mipmap/fall72px").toString();;
                break;
            case R.id.btn_medication:
                election=Uri.parse("android.resource://co.edu.unicauca.appterapiademencia/mipmap/medication72px").toString();
                break;
            case R.id.btn_other:
                election=Uri.parse("android.resource://co.edu.unicauca.appterapiademencia/mipmap/otro72px").toString();
                break;
            case R.id.btn_health:
                election=Uri.parse("android.resource://co.edu.unicauca.appterapiademencia/mipmap/ic_insert_emoticon_black_48dp").toString();

            case R.id.btn_changebehaviour:
                election=Uri.parse("android.resource://co.edu.unicauca.appterapiademencia/mipmap/changebehavior72px").toString();
                break;


        }
    }
    private void showDate(int year, int month, int day) {
        var_fecha = (new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year)).toString();

    }
}
