package co.edu.unicauca.appterapiademencia.principal.tips;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import co.edu.unicauca.appterapiademencia.R;
import co.edu.unicauca.appterapiademencia.domain.Tip;
import co.edu.unicauca.appterapiademencia.domain.User;
import co.edu.unicauca.appterapiademencia.domain.dao.GreenDaoHelper;
import co.edu.unicauca.appterapiademencia.domain.dao.TipDao;
import co.edu.unicauca.appterapiademencia.principal.MainActivity;

/**
 * Created by SEBAS on 29/11/2016.
 */

public class AddTipActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText edtTitle;
    private EditText edtDescription;
    private Switch switchNotifications;
    private Button btnSave;
    private GreenDaoHelper helper;
    private TipDao tipDao;
    private Toolbar toolbar;
    private ActionBar actionBar;
    private String[] tip;
    private String actualizar;
    private String[] datosa;
    private Boolean selectedNotifications;
    private Long idtip;
    private String username;
    private Long iduser;


   public AddTipActivity()
   {
       this.helper= GreenDaoHelper.getInstance();
       this.tipDao = helper.getTipDao();
   }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_tip);
        Bundle bundl = getIntent().getExtras();

        SharedPreferences loginpreference = getSharedPreferences("appdata", Context.MODE_PRIVATE);
        edtTitle = (EditText) findViewById(R.id.txt_title);
        edtDescription = (EditText) findViewById(R.id.txt_description);
        switchNotifications = (Switch) findViewById(R.id.switch_notifications);
        btnSave = (Button) findViewById(R.id.btn_guardar_tip);
        selectedNotifications=false;
        btnSave.setOnClickListener(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        actualizar="";
        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Agregar Tip a Cuidadores");
        if(loginpreference.getString("username",username)!=null)
        {
            username = loginpreference.getString("username", "Nombre de Usuario");
            User user = helper.getUserInformation(username);
            iduser= user.getId();
            Log.e("Add Tip","userid"+user.getId());
        }



        switchNotifications.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked)
                {
                    selectedNotifications = true;

                }else
                {
                    selectedNotifications=false;


                }

            }
        });


        if (bundl != null)
        {

            actualizar = bundl.getString("actualizar");
            if (actualizar == null)
            {
                actualizar = "";
            }
            else
            {
                actionBar.setTitle("Actualizando Tip");
                idtip = bundl.getLong("idtip");

                Tip tip = helper.getTip(idtip);

                edtTitle.setText(tip.getTitle());
                edtDescription.setText(tip.getDescription());
                try {
                    if(tip.getActive()){
                        switchNotifications.setChecked(true);
                        selectedNotifications=true;
                    }
                    else{
                        switchNotifications.setChecked(false);
                        selectedNotifications=false;

                    }

                }catch (NullPointerException e)
                {  switchNotifications.setChecked(false);
                    selectedNotifications=false;}


            }
        }


    }

    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.btn_guardar_tip:

                 if (validar(edtTitle.getText().toString(),edtDescription.getText().toString()) == false)
                 {
                    Toast.makeText(this, "Debe Llenar Los Campos de Título y Descripción", Toast.LENGTH_LONG).show();
                 }
                 else
                 {

                     Long parse_id = idtip;
                     String var_title = edtTitle.getText().toString();
                     String var_description = edtDescription.getText().toString();

                     if (actualizar.equals("actualizar"))
                     {
                         //ACTUALIZAR TIP

                         Tip tipactualizar =helper.getTip(idtip);
                         tipactualizar.setTitle(var_title);
                         tipactualizar.setDescription(var_description);
                         tipactualizar.setActive(selectedNotifications);

                         Log.e("Add tip","actualizado :"+helper.getTip(idtip).getTitle());

                     }
                     else
                     {
                         Tip tip2 = new Tip(null,iduser,var_title,var_description,selectedNotifications,false);
                         tipDao.insert(tip2);


                         //AGREGAR TIP

                     }
                     Intent ir_main = new Intent(this, MainActivity.class);
                     startActivity(ir_main);
                     overridePendingTransition(R.anim.left_in, R.anim.left_out);
                     finish();



                 }

        }
    }

    public Boolean validar(String title,String description) {
        if (title.equals("")  || description.equals("") ) {
            return false;
        }else {
            return true;
        }
    }

}
