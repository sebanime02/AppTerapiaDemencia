package co.edu.unicauca.appterapiademencia.principal.patientlist;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

import org.greenrobot.greendao.query.QueryBuilder;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import co.edu.unicauca.appterapiademencia.R;
import co.edu.unicauca.appterapiademencia.domain.Patient;
import co.edu.unicauca.appterapiademencia.domain.dao.GreenDaoHelper;
import co.edu.unicauca.appterapiademencia.domain.dao.PatientDao;
import co.edu.unicauca.appterapiademencia.util.BitmapUtil;

/**
 * Created by ENF on 28/10/2016.
 */

public class AddPatientActivity extends AppCompatActivity implements View.OnClickListener{

    private String var_genero="";
    private String var_fecha="";
    private EditText edt_id, edt_nomb,edt_eps,edt_antecedentes,edt_sindromes,edt_observaciones;
    private TextView txt_titulo1,txt_titulo2;
    private String[] paciente;
    int eleccion;
    private Calendar calendar;
    private int year, month, day;
    private Button btn_fecha, btn_guardar;
    private FloatingActionButton imgbtn;
    private String name2 = "";
    private String foto_rq;
    private String name = "";
    private String imagen;
    private Button  b_disparo, b_parar;
    private SharedPreferences prefs;
    private String actualizar="";
    GreenDaoHelper daoHelper;
    private int idpaciente;
    private String[] datosa;
    private PatientDao  patientDao;
    private QueryBuilder queryBuilder;
    private ActionBar actionBar;
    private Toolbar toolbar;
    private long identity;
    private ImageView img;
    private RelativeLayout containerfoto;
    public static final String fotodefault = Uri.parse("android.resource://co.edu.unicauca.appterapiademencia/"+R.drawable.emptyuser).toString();

  public AddPatientActivity(){
      this.patientDao = GreenDaoHelper.getInstance().getPatientDao();

  }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpatient_personalinformation);
        daoHelper = GreenDaoHelper.getInstance();
        prefs = getSharedPreferences("datos", Context.MODE_PRIVATE);
        edt_id = (EditText) findViewById(R.id.edt_cedula);
       // btn_guardar = (Button) findViewById(R.id.btn_guardar_paso1);
        edt_nomb = (EditText) findViewById(R.id.edt_nombre_paciente);
        btn_fecha = (Button) findViewById(R.id.btn_fecha_patiente);
        edt_eps = (EditText) findViewById(R.id.edt_eps);
        edt_antecedentes = (EditText) findViewById(R.id.edt_antecedentes);
        edt_sindromes = (EditText) findViewById(R.id.edt_sindromes);
        edt_observaciones = (EditText) findViewById(R.id.edt_observaciones);
        txt_titulo1 = (TextView) findViewById(R.id.txt_edit);
        txt_titulo2 = (TextView) findViewById(R.id.txt_edit2);



        containerfoto = (RelativeLayout) findViewById(R.id.foto_container);
        img = (ImageView) findViewById(R.id.foto_paciente);
        imgbtn = (FloatingActionButton) findViewById(R.id.add_foto_paciente);

        imagen = Uri.parse("android.resource://co.edu.unicauca.appterapiademencia/drawable/empyuser").toString();
        //colocarImagen();


         toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Agregar Paciente");


        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            try {
                registerForContextMenu(imgbtn);
                identity = Long.parseLong(bundle.getString("cedula"));
                Log.e("Add patient","LLego a adpatient, el id es "+identity);
                queryBuilder= patientDao.queryBuilder();
                List<Patient> patientList=  queryBuilder.where(PatientDao.Properties.Identity.eq(identity)).limit(1).list();
                Patient patientone= patientList.get(0);

                Log.e("Add patient","El nombre del paciente devuelvo por dao es "+patientone.getName().toString());

                actualizar = bundle.getString("actualizar");
                datosa = new String[4];
                //datosa = bundle.getStringArray("datosa");
                datosa[0] = identity+"";
                datosa[1] = patientone.getVisionlimitation().toString();
                datosa[2] = patientone.getWritinglimitation().toString();
                datosa[3] = patientone.getDrawinglimitation().toString();

                txt_titulo1.setText("INFORMACIÓN DEL PACIENTE");
                txt_titulo2.setText("");
                actionBar.setTitle("Actualizando Paciente");
                containerfoto.setVisibility(View.VISIBLE);

                edt_id.setText(patientone.getIdentity()+"");
                edt_id.setEnabled(false);
                edt_nomb.setText(patientone.getName());
                btn_fecha.setText(patientone.getBirthday().toString());
                edt_eps.setText(patientone.getEps());
                edt_antecedentes.setText(patientone.getAntecedents().toString());
                edt_sindromes.setText(patientone.getSyndromes().toString());
                edt_observaciones.setText(patientone.getObservations().toString());



                if(patientone.getPhotopath().toString().equalsIgnoreCase("")){
                    img.setBackgroundResource(R.drawable.emptyuser);

                }
                else {
                    img.setBackground(Drawable.createFromPath(patientone.getPhotopath()));
                    name2= patientone.getPhotopath();
                     }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        edt_id.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                containerfoto.setVisibility(View.VISIBLE);
                img.setBackgroundResource(R.drawable.emptyuser);

            }
        });

    }

    public void savePage1(View view)
    {

        Log.e("Agregar paciente","Presiono el boton siguiente");

        if(validar(edt_id.getText().toString(),btn_fecha.getText().toString(),edt_nomb.getText().toString())==false)
        {
            new MaterialDialog.Builder(this).title("Campos Obligagorios Faltantes").content("Debe poner cédula, fecha de nacimiento y nombre completo").positiveText(R.string.dialog_succes_agree).show();
            //Toast.makeText(this,"Debe poner cédula, fecha de nacimiento y nombre completo",Toast.LENGTH_LONG).show();
            //edt_id.setError("Obligatorio");
            //edt_nomb.setError("Obligatorio");
            btn_fecha.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.red_dark));

            Log.e("Agregar paciente","faltan campos obligatorios");
        }else {

            //queryBuilder = daoHelper.getPatientDao().queryBuilder();
            queryBuilder = GreenDaoHelper.getInstance().getPatientDao().queryBuilder();
            List<Patient> patientList = queryBuilder.where(PatientDao.Properties.Identity.eq(Long.parseLong(edt_id.getText().toString()))).limit(1).list();
            //.limit(1).list();

            for(int j=0;j<patientList.size();j++){
                Log.e("Agregar paciente",""+patientList.get(j).getIdentity());

            }



            if (actualizar.equals("actualizar")) {
                Log.e("Agregar paciente","Listo para enviar los actualizados");
                paciente = new String[8];
                Log.e("name2 a actualizar",name2);
                Intent ir_reg = new Intent(this, AddPatient2Activity.class);

                paciente[0] = datosa[0];
                paciente[1] = name2;
                paciente[2] = edt_nomb.getText().toString();
                paciente[3] = btn_fecha.getText().toString();
                paciente[4] = edt_eps.getText().toString();
                paciente[5] = edt_antecedentes.getText().toString();
                paciente[6] = edt_sindromes.getText().toString();
                paciente[7] = edt_observaciones.getText().toString();


                ir_reg.putExtra("paciente", paciente);

                ir_reg.putExtra("actualizar", actualizar);
                ir_reg.putExtra("datosa", datosa);

                startActivity(ir_reg);
                overridePendingTransition(R.anim.left_in, R.anim.left_out);
            } else {
                if (patientList.size()==0)
                {
                    Log.e("Agregar paciente","La cedula esta libre");

                    paciente = new String[8];
                    Intent ir_reg = new Intent(this, AddPatient2Activity.class);

                    paciente[0] = edt_id.getText().toString();
                    /*
                    if(name2==""){
                        name2=Uri.parse("android.resource://co.edu.unicauca.appterapiademencia/"+R.drawable.emptyuser).toString();
                    }
                    if(name2==Uri.parse("android.resource://co.edu.unicauca.appterapiademencia/"+R.mipmap.ic_launcher).toString()){
                        name2=Uri.parse("android.resource://co.edu.unicauca.appterapiademencia/"+R.drawable.emptyuser).toString();
                    }
                    if(name2.equals(null)){
                        name2=Uri.parse("android.resource://co.edu.unicauca.appterapiademencia/"+R.drawable.emptyuser).toString();

                    }
                    */
                    Log.e("name2",name2);

                    paciente[1] = name2;
                    paciente[2] = edt_nomb.getText().toString();
                    paciente[3] = btn_fecha.getText().toString();
                    paciente[4] = edt_eps.getText().toString();
                    paciente[5] = edt_antecedentes.getText().toString();
                    paciente[6] = edt_sindromes.getText().toString();
                    paciente[7] = edt_observaciones.getText().toString();

                    ir_reg.putExtra("paciente", paciente);

                    startActivity(ir_reg);
                    overridePendingTransition(R.anim.left_in, R.anim.left_out);
                } else
                {
                    Log.e("Agregar paciente","Cedula ya existe");
                    Toast.makeText(this, "La cédula ingresada ya existe, puede  que el paciente haya sido ingresado con anterioridad", Toast.LENGTH_LONG).show();
                }
            }
        }

    }

    public void onClick(View v) {
        switch (v.getId()) {
            /*
            case R.id.add_foto_paciente:

                registerForContextMenu(imgbtn);
                openContextMenu(v);
                //unregisterForContextMenu(v);
                break;
               /*
            case R.id.btn_guardar_paso1:


                break;*/
            case R.id.btn_fecha_patiente:
                showDialog(999);
                break;

        }
    }

    public void openContextM(View view){
        imgbtn.performLongClick();
        registerForContextMenu(imgbtn);
        openContextMenu(view);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            DatePickerDialog dialogDate = new DatePickerDialog(this, myDateListener, year, month, day);
            dialogDate.getDatePicker().setMaxDate(new Date().getTime());
            return dialogDate;
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
            // arg1 = year
            // arg2 = month
            // arg3 = day
            showDate(arg1, arg2 + 1, arg3);
        }
    };

    private void showDate(int year, int month, int day) {
        var_fecha = (new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year)).toString();
        btn_fecha.setText(var_fecha);
        btn_fecha.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.negro));
    }

    public Boolean validar(String id, String fecha, String genero) {
        if (id.equals("")  || fecha.equals("") || genero.equals("") || fecha.equals("DD/MM/AAAA")) {
            return false;
        }else {
            return true;
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.nueva_foto:
                Intent intent_foto =  new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Uri output = Uri.fromFile(new File(name));
                intent_foto.putExtra(MediaStore.EXTRA_OUTPUT, output);
                eleccion = 1;
                startActivityForResult(intent_foto, eleccion);
                return true;

            case R.id.opc_galeria:
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                eleccion = 2;
                startActivityForResult(intent, eleccion);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
    private void colocarImagen() {
        name = imagen;
        img.setImageURI(Uri.parse(imagen));

    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        if (actualizar.equals("actualizar")) {
            String nombre_foto = "/ModTerapia/" + edt_id.getText().toString() + ".jpg";
            name = Environment.getExternalStorageDirectory().getPath() + nombre_foto;
            getMenuInflater().inflate(R.menu.menu_foto_perfil, menu);
        } else {
            queryBuilder = daoHelper.getPatientDao().queryBuilder();
            //queryBuilder = GreenDaoHelper.getInstance().getPatientDao().queryBuilder();
            if (edt_id.getText().toString() != "") {
                Long id = Long.parseLong(edt_id.getText().toString());

                List<Patient> patientList = queryBuilder.where(PatientDao.Properties.Identity.eq(id)).limit(1).list();


                if (patientList.size() == 0) {
                    String nombre_foto = "/ModTerapia/" + edt_id.getText().toString() + ".jpg";
                    name = Environment.getExternalStorageDirectory().getPath() + nombre_foto;
                    getMenuInflater().inflate(R.menu.menu_foto_perfil, menu);
                } else {
                    new MaterialDialog.Builder(this).title("Ya existe un paciente con esa Cèdula").content("La cédula ya ha sido registrada anteriormente, revise la lista de pacientes").positiveText(R.string.dialog_succes_agree).show();

                    //Toast.makeText(this, "La cédula ya ha sido registrada anteriormente, revise la lista de pacientes", Toast.LENGTH_LONG).show();
                }
            }
        }
    }


    @Override protected void onActivityResult(int requestCode,  int resultCode, Intent data) {

        int origin=0;
        switch (requestCode) {

            case 1:

                if (resultCode == RESULT_OK) {
                    origin = BitmapUtil.CAM;
                    //imgbtn.setImageBitmap(BitmapFactory.decodeFile(name));
                    //imgbtn.setBackground(Drawable.createFromPath(name));
                    new MediaScannerConnection.MediaScannerConnectionClient() {
                        private MediaScannerConnection msc = null;

                        {
                            msc = new MediaScannerConnection(getApplicationContext(), this);
                            msc.connect();
                        }

                        public void onMediaScannerConnected() {
                            msc.scanFile(name, null);
                        }

                        public void onScanCompleted(String path, Uri uri) {
                            msc.disconnect();
                        }
                    };
                    name2 = name;
                }
                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    origin = BitmapUtil.GALERY;
                    if (data == null) {
                        Toast.makeText(getApplicationContext(), "No se eligio la foto!", Toast.LENGTH_LONG).show();
                    } else {
                        Uri selectedImage = data.getData();

                        Cursor cursor = getContentResolver().query(selectedImage, null, null, null, null);
                        cursor.moveToFirst();
                        String document_id = cursor.getString(0);
                        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
                        cursor.close();

                        cursor = getContentResolver().query(
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                                null, MediaStore.Images.Media._ID + " =? ", new String[]{document_id}, null);
                        cursor.moveToFirst();
                        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                        cursor.close();


                        name2 = path;
                        Log.d("pathprimero",name2);
                        //imgbtn.setImageURI(selectedImage);
                        img.setBackground(Drawable.createFromPath(name2));
                    }
                }
                break;
        }
        if(!name2.equals("")) {
            String path = null;
            try {
                path = BitmapUtil.resizeImageFile(name2, 800, origin);
            } catch (IOException e) {
                e.printStackTrace();
            }
            name2=path;
            Log.d("path",name2);
            img.setBackground(Drawable.createFromPath(name2));
        }


    }
}




