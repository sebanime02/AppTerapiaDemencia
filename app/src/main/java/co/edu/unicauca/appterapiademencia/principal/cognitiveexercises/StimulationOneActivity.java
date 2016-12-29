package co.edu.unicauca.appterapiademencia.principal.cognitiveexercises;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
    private EditText edtMecInicial;
    private Button btnNoAdministrar,btnGuardarMecInicial;
    private Intent intent;
    private GreenDaoHelper daoHelper;
    private SharedPreferences preferences;
    private Intent intentBundle;
    private Long idpatient,idrutina;
    private Rutina rutina;
    private RutinaDao rutinaDao;


    public StimulationOneActivity()
    {
        daoHelper = GreenDaoHelper.getInstance();
        rutinaDao = daoHelper.getRutinaDao();
        intent = new Intent(getApplicationContext(),StimulationTwoActivity.class);
        preferences = getSharedPreferences("appdata", Context.MODE_PRIVATE);

    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stimulation_1);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        edtMecInicial = (EditText) findViewById(R.id.edt_mec_inicial);
        btnNoAdministrar = (Button) findViewById(R.id.btn_cancelar_mec_inicial);
        btnGuardarMecInicial = (Button) findViewById(R.id.btn_guardar_mec_inicial);

        try
        {
            idpatient = intentBundle.getExtras().getLong("idpatient");
            idrutina = intentBundle.getExtras().getLong("idrutina");

        }catch (Exception e)
        {

        }


        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);


        btnNoAdministrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rutina = daoHelper.getRutina(idrutina);

                if(edtMecInicial.getText().toString()=="")
                {
                    rutina.setMecinicial(0);
                }
                else
                {
                    rutina.setMecinicial(Integer.parseInt(edtMecInicial.getText().toString()));

                }
                rutina.setState(2);
                rutinaDao.update(rutina);
                intent.putExtra("idpatient",idpatient);
                intent.putExtra("idrutina",idrutina);
                goToPass2(intent);
            }
        });

        btnGuardarMecInicial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rutina = daoHelper.getRutina(idrutina);
                rutina.setMecinicial(Integer.parseInt(edtMecInicial.getText().toString()));
                rutina.setState(2);
                rutinaDao.update(rutina);
                intent.putExtra("idpatient",idpatient);
                intent.putExtra("idrutina",idrutina);
                goToPass2(intent);
            }
        });




    }


    public void goToPass2(Intent intent)
    {
        startActivity(intent);

    }
}
