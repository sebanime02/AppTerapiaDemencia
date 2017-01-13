package co.edu.unicauca.appterapiademencia.principal.reminiscence;

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
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

import org.greenrobot.greendao.query.QueryBuilder;

import java.io.File;
import java.io.IOException;
import java.util.List;

import co.edu.unicauca.appterapiademencia.R;
import co.edu.unicauca.appterapiademencia.domain.Reminiscence;
import co.edu.unicauca.appterapiademencia.domain.dao.GreenDaoHelper;
import co.edu.unicauca.appterapiademencia.domain.dao.ReminiscenceDao;
import co.edu.unicauca.appterapiademencia.principal.MainActivity;
import co.edu.unicauca.appterapiademencia.util.BitmapUtil;

/**
 * Created by SEBAS on 10/01/2017.
 */

public class AddReminiscenceExercise extends AppCompatActivity {

    private EditText edtTitle,edtDescription;
    private Button btnGuardar;
    private ActionBar actionBar;
    private Toolbar toolbar;
    private FloatingActionButton imgbtn;
    private ImageView img;
    private String name2 = "";
    private String foto_rq;
    private String name = "";
    private String imagen;
    private String actualizar="";
    private GreenDaoHelper daoHelper;
    private QueryBuilder queryBuilder;
    private SharedPreferences prefs;
    int eleccion;
    private ReminiscenceDao reminiscenceDao;
    private String title;
    private Long idreminiscence;
    private String username;

    private String var_title;


    public AddReminiscenceExercise()
    {
        this.reminiscenceDao = GreenDaoHelper.getInstance().getReminiscenceDao();

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_reminscence);
        prefs = getSharedPreferences("datos", Context.MODE_PRIVATE);

        img = (ImageView) findViewById(R.id.reminiscence_photo);
        imgbtn = (FloatingActionButton) findViewById(R.id.add_photo_reminiscence);


        edtTitle = (EditText) findViewById(R.id.edt_reminiscense_title);
        edtDescription = (EditText) findViewById(R.id.edt_reminiscense_description);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Volver Al Menu Principal");
        daoHelper = GreenDaoHelper.getInstance();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            try
            {
                registerForContextMenu(imgbtn);
                idreminiscence =bundle.getLong("idreminiscence");
                Log.e("Add Reminiscence","el id es "+idreminiscence);

                queryBuilder= reminiscenceDao.queryBuilder();
                List<Reminiscence> reminiscenceList=  queryBuilder.where(ReminiscenceDao.Properties.Id.eq(idreminiscence)).limit(1).list();
                Reminiscence reminiscenceone= reminiscenceList.get(0);


                Log.e("Add reminiscence","El id long devuelvo por dao es "+reminiscenceone.getId().toString());
                Log.e("Add reminiscence","La descripcion del ejercicio es "+reminiscenceone.getDescription().toString());
                actualizar = bundle.getString("actualizar");


                edtTitle.setText(reminiscenceone.getTitle());
                edtDescription.setText(reminiscenceone.getDescription());
                if(reminiscenceone.getPhotopath().toString().equalsIgnoreCase("")){

                }
                else {
                    img.setBackground(Drawable.createFromPath(reminiscenceone.getPhotopath()));
                    name2= reminiscenceone.getPhotopath();
                }

            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }

    }


    public void saveReminiscence(View view)
    {
        if(validar(edtTitle.getText().toString())==false)
        {
            new MaterialDialog.Builder(this).title(getResources().getString(R.string.dialog_reminiscence_empty_title)).positiveText(R.string.dialog_succes_agree).show();
            edtTitle.setError("");

        }else
        {

            if(prefs.getBoolean("supervisor",true)) {

                username = prefs.getString("username","Nombre de Usuario");
            }
            else
            {
                username = getResources().getString(R.string.txt_carer_type);
            }
                queryBuilder = GreenDaoHelper.getInstance().getReminiscenceDao().queryBuilder();


            List<Reminiscence> reminiscenceList = queryBuilder.where(ReminiscenceDao.Properties.Title.eq(edtTitle.getText().toString())).limit(1).list();

            if (actualizar.equals("actualizar"))
            {

                queryBuilder = daoHelper.getReminiscenceDao().queryBuilder();

                List<Reminiscence> reminiscenceList1 = queryBuilder.where(ReminiscenceDao.Properties.Id.eq(idreminiscence)).limit(1).list();
                Reminiscence reminiscence = reminiscenceList1.get(0);

                reminiscence.setDescription(edtDescription.getText().toString());
                reminiscence.setTitle(edtTitle.getText().toString());
                reminiscence.setPhotopath(name2);


            }
            else
            {
                String var_title_final;
                if (reminiscenceList.size()==0)
                {
                  var_title_final = edtTitle.getText().toString();
                }else
                {
                    if(var_title=="")
                    {
                        String nombre_foto_nueva;
                        String title_nuevo;
                        int contador = 0;
                        do
                        {
                            title_nuevo = edtTitle.getText().toString()+contador;
                            reminiscenceList = queryBuilder.where(ReminiscenceDao.Properties.Title.eq(title_nuevo)).limit(1).list();
                            contador = contador +1;
                        }
                        while (reminiscenceList.size()>0);
                        var_title = title_nuevo;


                    }

                    var_title_final = var_title;


                }
                Reminiscence reminiscence = new Reminiscence(null,var_title_final,edtDescription.getText().toString(),name2,username,"");
                reminiscenceDao.insert(reminiscence);
                reminiscenceDao.update(reminiscence);
            }
            Intent ir_reg = new Intent(this, MainActivity.class);
            startActivity(ir_reg);
            overridePendingTransition(R.anim.left_in, R.anim.left_out);
        }

    }






    public void openContextM(View view){
        imgbtn.performLongClick();
        registerForContextMenu(imgbtn);
        openContextMenu(view);
    }


    public Boolean validar(String titulo) {
        if (titulo.equals("")) {
            return false;
        }else {
            return true;
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
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
            String nombre_foto = "/ModTerapia/" + edtTitle.getText().toString() + ".jpg";
            name = Environment.getExternalStorageDirectory().getPath() + nombre_foto;
            getMenuInflater().inflate(R.menu.menu_foto_perfil, menu);
        } else {
            queryBuilder = daoHelper.getReminiscenceDao().queryBuilder();
            //queryBuilder = GreenDaoHelper.getInstance().getPatientDao().queryBuilder();
            if (edtTitle.getText().toString() != "") {

                List<Reminiscence> reminiscenceList;
                String nombre_foto;


                reminiscenceList = queryBuilder.where(ReminiscenceDao.Properties.Title.eq(edtTitle.getText().toString())).limit(1).list();

                if(reminiscenceList.size()==0)
                {
                    nombre_foto = "/ModTerapia/" + edtTitle.getText().toString() + ".jpg";
                    name = Environment.getExternalStorageDirectory().getPath() + nombre_foto;
                    getMenuInflater().inflate(R.menu.menu_foto_perfil, menu);

                }
                else
                {
                    String nombre_foto_nueva;
                    String title_nuevo;
                    int contador = 1;
                    do
                    {
                        title_nuevo = edtTitle.getText().toString()+contador;
                        nombre_foto_nueva = "/ModTerapia/" +"-"+ title_nuevo+ ".jpg";
                        reminiscenceList = queryBuilder.where(ReminiscenceDao.Properties.Title.eq(title_nuevo)).limit(1).list();

                        contador = contador +1;
                    }
                    while (reminiscenceList.size()>0);
                    var_title = nombre_foto_nueva;
                    name = Environment.getExternalStorageDirectory().getPath() + title_nuevo;
                    getMenuInflater().inflate(R.menu.menu_foto_perfil, menu);
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
