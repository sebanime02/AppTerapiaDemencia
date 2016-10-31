package co.edu.unicauca.appterapiademencia.principal.patientlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import co.edu.unicauca.appterapiademencia.R;
import co.edu.unicauca.appterapiademencia.domain.Patient;
import co.edu.unicauca.appterapiademencia.domain.dao.GreenDaoHelper;
import co.edu.unicauca.appterapiademencia.domain.dao.PatientDao;
import co.edu.unicauca.appterapiademencia.principal.MainActivity;

/**
 * Created by ENF on 30/10/2016.
 */

public class AddPatient2Activity extends AppCompatActivity implements View.OnClickListener{
    private Button btn_guardar, btn_atras;
    private Spinner s_vision,s_escritura,s_dibujo;
    private String[] vision = {"Visión Normal","Baja visión","Ceguera"};
    private String[] escritura = {"Puede Escribir","No puede escribir"};
    private String[] dibujo = {"Puede Dibujar","No puede Dibujar"};
    private QueryBuilder queryBuilder;
    String[] paciente,datosa;
    private String actualizar="";
    private PatientDao patientDao;
    private GreenDaoHelper helper;
    private Patient patientObj;

    public AddPatient2Activity()
    {
        this.helper= GreenDaoHelper.getInstance();
        this.patientDao = GreenDaoHelper.getPatientDao();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpatient_physicalinformation);
        Bundle bundl = getIntent().getExtras();

        btn_guardar = (Button) findViewById(R.id.btn_guardar_paciente);
        btn_atras = (Button) findViewById(R.id.btn_atras_paciente);
        btn_guardar.setOnClickListener(this);
        btn_atras.setOnClickListener(this);

        s_vision = (Spinner) findViewById(R.id.spi_vision);
        s_escritura = (Spinner) findViewById(R.id.spi_vision);
        s_dibujo = (Spinner) findViewById(R.id.spi_vision);

        ArrayAdapter<String> adaptervision = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, vision);
        ArrayAdapter<String> adapterescritura = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, escritura);
        ArrayAdapter<String> adapterdibujo = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dibujo);

        s_vision.setAdapter(adaptervision);
        s_escritura.setAdapter(adapterescritura);
        s_dibujo.setAdapter(adapterdibujo);


        if (bundl != null) {
            paciente = bundl.getStringArray("paciente");
            actualizar = bundl.getString("actualizar");
            if (actualizar == null) {
                actualizar = "";
            } else {
                datosa = new String[10];
                datosa = bundl.getStringArray("datosa");
                if (datosa[7].toString().equals("0")) {

                    s_vision.setSelection(0);
                }
                if (datosa[7].toString().equals("1")) {
                    s_vision.setSelection(1);
                }
                if (datosa[7].toString().equals("2")) {

                    s_vision.setSelection(2);
                }

                 if (datosa[8].toString().equals("0")) {
                        s_escritura.setSelection(0);
                    }
                 if (datosa[8].toString().equals("1")) {
                        s_escritura.setSelection(1);
                    }

                  if (datosa[9].toString().equals("0")) {
                        s_dibujo.setSelection(0);
                    }
                  if (datosa[9].toString().equals("1")) {
                        s_dibujo.setSelection(1);
                    }


                }

            }
        }



    @Override
    public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_guardar_paciente:
                /*if (validar(e_peso.getText().toString(), e_color.getText().toString(), e_raza.getText().toString(), e_id_padre.getText().toString(), e_id_madre.getText().toString()) == false) {
                    Toast.makeText(this, "Debe Llenar Todos Los Campos", Toast.LENGTH_LONG).show();
                } else {*/
                    String var_foto;
                    if(actualizar.equals(""))
                    {
                        var_foto = paciente[1];
                    }else {
                        if (paciente[1].equals("")) {
                            var_foto = datosa[2];
                        } else {
                            var_foto = paciente[1];
                        }
                    }

                    String var_id = paciente[0];
                    int parse_id = Integer.parseInt(var_id);

                    String var_nombre = paciente[2];
                    String var_fecha = paciente[3];
                    String var_eps= paciente[4];
                    String var_antecedentes = paciente[5];
                    String var_sindromes = paciente[6];
                    String var_observaciones = paciente[7];



                    int var_vision = s_vision.getSelectedItemPosition();
                    int var_escritura = s_escritura.getSelectedItemPosition();
                    int var_dibujo = s_dibujo.getSelectedItemPosition();

                    if (actualizar.equals("actualizar")) {


                        String[] fechas = new String[7];
                        queryBuilder = GreenDaoHelper.getPatientDao().queryBuilder();

                        List<Patient> patientList = queryBuilder.where(PatientDao.Properties.Identity.eq(var_id)).limit(1).list();
                        Patient patient = patientList.get(0);
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



                    } else {
                        int var_mec = 0;
                        int var_gds = 0;
                        Patient patient = new Patient(null,var_nombre,var_fecha,var_foto,var_eps,parse_id,var_antecedentes,var_sindromes,var_observaciones,null,null,var_vision,var_escritura,var_dibujo);
                        this.patientDao.insert(patient);


                        }




                    //-------------------------------
                    Intent ir_main = new Intent(this, MainActivity.class);
                    startActivity(ir_main);
                    //overridePendingTransition(R.anim.left_in, R.anim.left_out);
                    finish();
                    break;
                case R.id.btn_atras_paciente:
                    super.onBackPressed();
                    //overridePendingTransition(R.anim.right_in, R.anim.right_out);
                    break;
            }
    }
}
