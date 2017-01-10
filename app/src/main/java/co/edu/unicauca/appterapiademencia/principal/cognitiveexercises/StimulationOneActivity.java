package co.edu.unicauca.appterapiademencia.principal.cognitiveexercises;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.concurrent.ExecutionException;

import co.edu.unicauca.appterapiademencia.R;
import co.edu.unicauca.appterapiademencia.domain.Rutina;
import co.edu.unicauca.appterapiademencia.domain.dao.GreenDaoHelper;
import co.edu.unicauca.appterapiademencia.domain.dao.RutinaDao;

/**
 * Created by SEBAS on 26/12/2016.
 */

public class StimulationOneActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ActionBar actionBar;
    private EditText edtMecInicial,edtMecComentario;
    private Button btnNoAdministrar,btnGuardarMecInicial;
    private Intent intent;
    private GreenDaoHelper daoHelper;
    private SharedPreferences preferences;
    private Intent intentBundle;
    private Bundle bundle;
    private Long idpatient,idrutina;
    private Rutina rutina;
    private RutinaDao rutinaDao;
    private int numberMec;


    public StimulationOneActivity()
    {
        daoHelper = GreenDaoHelper.getInstance();
        rutinaDao = daoHelper.getRutinaDao();


        //preferences = getSharedPreferences("appdata", Context.MODE_PRIVATE);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stimulation_1);

        bundle = getIntent().getExtras();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        edtMecInicial = (EditText) findViewById(R.id.edt_mec_inicial);
        btnNoAdministrar = (Button) findViewById(R.id.btn_cancelar_mec_inicial);
        btnGuardarMecInicial = (Button) findViewById(R.id.btn_guardar_mec_inicial);
        edtMecComentario = (EditText) findViewById(R.id.edt_mec_inicial_comentario);



        try
        {
            idpatient = bundle.getLong("idpatient");
            idrutina = bundle.getLong("idrutina");

            Log.e("Stimulation 1","idpatient recibido "+idpatient);
            Log.e("Stimulation 1","idrutina recibido "+idrutina);


        }catch (Exception e)
        {
            Log.e("Stimulation 1","Error trayendo ids");

        }


        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);


        btnNoAdministrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    rutina = daoHelper.getRutina(idrutina);

                    rutina.setState(2);
                    rutinaDao.update(rutina);
                    intent = new Intent(getApplicationContext(),StimulationTwoActivity.class);
                    intent.putExtra("idpatient",idpatient);
                    intent.putExtra("idrutina",idrutina);
                    goToPass2(intent);


            }
        });

        btnGuardarMecInicial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                rutina = daoHelper.getRutina(idrutina);


                if(edtMecInicial.getText().toString()=="")
                {

                    setMec(rutina,0);

                }
                else if(Integer.parseInt(edtMecInicial.getText().toString())>35)
                {
                    new MaterialDialog.Builder(StimulationOneActivity.this).title(getResources().getString(R.string.txt_mec_rango_title)).content(R.string.txt_mec_rango_content).positiveText(R.string.dialog_succes_agree).icon(getResources().getDrawable(R.drawable.sadface)).show();

                }
                else
                {
                    rutina.setMecinicial(Integer.parseInt(edtMecInicial.getText().toString()));
                    setMec(rutina,1);


                }


            }
        });




    }

   public void setMec(Rutina rutina,int indicator)
   {
       rutina.setState(2);
       if(indicator==1)
       {
           if(edtMecComentario.getText().toString()!="")
           {
               rutina.setMecinicialcomentario(edtMecComentario.getText().toString());

           }
       }
       else
       {
           new MaterialDialog.Builder(getApplicationContext()).title(getResources().getString(R.string.txt_mec_incongruente_title)).content(R.string.txt_mec_incongruente_content).positiveText(R.string.dialog_succes_agree).icon(getResources().getDrawable(R.drawable.sadface)).show();

       }
       rutinaDao.update(rutina);
       intent = new Intent(getApplicationContext(),StimulationTwoActivity.class);
       intent.putExtra("idpatient",idpatient);
       intent.putExtra("idrutina",idrutina);
       goToPass2(intent);
   }

    public void goToPass2(Intent intent)
    {
        startActivity(intent);

    }
}
