package co.edu.unicauca.appterapiademencia.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import org.greenrobot.greendao.query.QueryBuilder;

import co.edu.unicauca.appterapiademencia.R;
import co.edu.unicauca.appterapiademencia.principal.MainActivity;
import co.edu.unicauca.appterapiademencia.security.Security;

/**
 * Created by ENF on 14/10/2016.
 */

public class RegisterActivity extends AppCompatActivity implements RegisterView{
private EditText input_username,input_password_supervisor,input_completename,input_password_supervisor_aprobal;
    private String name2 = "";
    private String foto_rq;
    private String name = "";
    private String imagen;
    int eleccion;
    private FloatingActionButton add_image_user;
    private QueryBuilder queryBuilder;
    private Button irLogin;
    private CoordinatorLayout coordinatorLayout;
    private Toolbar toolbar;
    private ActionBar actionBar;
    private RadioGroup rdgModeUser;
    private RadioButton rdgModeSupervisor;
    private RadioButton rdgModePsicologia;
    private int modeUser;
    private boolean textErrorSyntaxis = false;
    private boolean textShortSyntaxis = false;


/*
    @BindView(R.id.txt_username)
    EditText input_username;
    @BindView(R.id.txt_password_supervisor)
    EditText input_password_supervisor;
    @BindView(R.id.txt_completename)
    EditText input_completename;
    @BindView(R.id.txt_password_supervisor_aprobar)
    EditText input_password_supervisor_aprobal;
    */



    private RegisterPresenter registerPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundl = getIntent().getExtras();
        setContentView(R.layout.activity_register);
        //ButterKnife.bind(this);
        input_username = (EditText) findViewById(R.id.txt_username);
        input_password_supervisor = (EditText) findViewById(R.id.txt_password_supervisor);
        input_completename = (EditText) findViewById(R.id.txt_completename);

        add_image_user = (FloatingActionButton) findViewById(R.id.add_user_image);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorRegister);
        irLogin = (Button) findViewById(R.id.btn_irLogin);
        toolbar = (Toolbar) findViewById(R.id.toolbarRegister);
        rdgModeUser = (RadioGroup) findViewById(R.id.rdgUserMode);
        rdgModePsicologia = (RadioButton) findViewById(R.id.rdg_user_psicologia);
        rdgModeSupervisor = (RadioButton) findViewById(R.id.rdg_user_supervisor);


        registerPresenter = new RegisterPresenterImplementation(this);

        registerPresenter.OnCreate();
        if(bundl!=null)
        {
            if(bundl.getString("msg")!=null)
            {
                coordinatorLayout.setVisibility(View.VISIBLE);
                irLogin.setVisibility(View.GONE);



                setSupportActionBar(toolbar);

                actionBar = getSupportActionBar();

                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setTitle("Volver A Perfil de Usuario");
            }
        }

        rdgModeUser.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {

                if(checkedId==R.id.rdg_user_supervisor)
                {
                 modeUser=1;
                }
                if(checkedId==R.id.rdg_user_psicologia)
                {
                 modeUser=2;
                }

            }
        });


        input_username.addTextChangedListener(new LoginValidator(input_username) {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void validate(EditText editText, String text) {
                //Implementamos la validación que queramos
                if( text.length() < Security.MINIMAL_USERNAME_LENGHT ){
                    input_username.setError(getResources().getString(R.string.txt_username_short));
                    textShortSyntaxis=true;
                }else
                {
                    textShortSyntaxis=false;
                }

                /*

                if(text.matches("[a-zA-Z0-9\\s]"))
                {
                    textErrorSyntaxis = false;


                }else
                {
                    //input_username.setError(getResources().getString(R.string.txt_username_specialcharacter));
                    textErrorSyntaxis = true;
                }
                */


            }

            @Override
            public void validateSpecialCharacters(EditText editText, String text) {


            }


        });



        input_password_supervisor.addTextChangedListener(new LoginValidator(input_password_supervisor) {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void validate(EditText editText, String text) {
                //Implementamos la validación que queramos

                if (text.length() < Security.MINIMAL_PASSWORD_LENGTH)
                {
                    input_password_supervisor.setError(getResources().getString(R.string.txt_password_short));
                    textShortSyntaxis = true;
                } else
                {
                    textShortSyntaxis = false;
                }
            }

            @Override
            public void validateSpecialCharacters(EditText editText, String text) {
                /*if(!editText.getText().toString().matches("[a-zA-Z0-9_]"))
                {
                    input_password_supervisor.setError(getResources().getString(R.string.txt_password_specialcharacter));
                    textErrorSyntaxis = true;

                }else
                {
                    textErrorSyntaxis=false;
                }*/

            }


        });


        input_completename.addTextChangedListener(new LoginValidator(input_completename) {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void validate(EditText editText, String text) {
                //Implementamos la validación que queramos
                if( text.length() < 6 ){
                    input_completename.setError(getResources().getString(R.string.txt_name_short));
                    textShortSyntaxis = true;
                }else
                {
                    textShortSyntaxis = false;
                }

                /*
                if(text.matches("[a-zA-Z_\\s]"))
                {

                    textErrorSyntaxis = false;
                }
                else
                {


                    //input_completename.setError(getResources().getString(R.string.txt_name_specialcharacter));
                    textErrorSyntaxis = true;

                }
                */

            }

            @Override
            public void validateSpecialCharacters(EditText editText, String text) {


            }


        });




    }


    @Override
    protected void onDestroy() {
        registerPresenter.OnDestroy();
        super.onDestroy();
    }


    @Override
    public void newUserError(int error) {
        switch (error) {
            case 0:
                Log.e("Registro","dialog de error de registro por username");
                new MaterialDialog.Builder(this).title("Error al registrar").content("Intente mas tarde").positiveText(R.string.dialog_succes_agree).show();
                break;
            case 2:
                Log.e("Registro","dialog de error de registro por username");
                new MaterialDialog.Builder(this).title(R.string.dialog_register_error_title).content(R.string.dialog_register_error_content).positiveText(R.string.dialog_succes_agree).show();

            break;


        }
    }


    @Override
    public void handleSingUp() {

        Log.e("Registro","va a validar");



        if(validateInputs(input_username.getText().toString(),input_password_supervisor.getText().toString(),input_completename.getText().toString())==false)
        {
            Log.e("Registro","se valido");
            Log.e("Registro","Modo de usuario "+modeUser);

            if(textShortSyntaxis)
            {
                new MaterialDialog.Builder(this).title(R.string.dialog_register_short_title).content(R.string.dialog_register_short_content).positiveText(R.string.dialog_succes_agree).show();

            }else if(textErrorSyntaxis)
            {
                new MaterialDialog.Builder(this).title(R.string.dialog_register_sintaxis_title).content(R.string.dialog_register_sintaxis_content).positiveText(R.string.dialog_succes_agree).show();

            }else
            {
                registerPresenter.registerUser(input_username.getText().toString(),input_password_supervisor.getText().toString(),input_completename.getText().toString(),modeUser);

            }

        }
        else
        {
            Log.e("Registro","la validacion no funciono");

            new MaterialDialog.Builder(this).title(R.string.dialog_register_emptyinput_title).content(R.string.dialog_register_emptyinput_content).positiveText(R.string.dialog_succes_agree).show();
        }
    }
    private boolean validateInputs(String username, String password, String completeName) {
        if(username.equals("") || password.equals("") || completeName.equals("")){
            return true;
        }
        else{
            return  false;
        }
    }


    @Override
    public void navigateToLogin(boolean backRegistered) {

       /*
        Bundle bundl = getIntent().getExtras();
        if(bundl!=null)
        {
            if(bundl.getString("msg")!=null)
            {
                startActivity(new Intent(this, MainActivity.class));

            }
        }
        else{
            startActivity(new Intent(this, LoginActivity.class));

        }
        */
        Intent backMain = new Intent(getApplicationContext(), LoginActivity.class);

        if(backRegistered)
        {
            backMain.putExtra("registrado",true);

        }
        overridePendingTransition(R.anim.left_in, R.anim.left_out);
        startActivity(backMain);



    }

    @Override
    public void newUserSuccess() {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(this);
        builder.title("Registro con éxito").content("Su registro como supervisor ha sido completado con éxito").positiveText(R.string.dialog_succes_agree).show();;
        builder.onPositive(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(MaterialDialog materialDialog, DialogAction dialogAction) {
                Bundle bundl = getIntent().getExtras();
                if(bundl!=null)
                {
                    if(bundl.getString("msg")!=null)
                    {
                        Intent backMain = new Intent(getApplicationContext(), MainActivity.class);

                        startActivity(backMain);
                        overridePendingTransition(R.anim.right_in, R.anim.right_out);

                    }
                }
                else{
                    navigateToLogin(true);

                }

            }
        });
        builder.show();



    }


    public void saveRegister(View v){
        Log.e("Registro","Funciono el boton");
        handleSingUp();

    }
    public void toLogin(View v){
        navigateToLogin(false);
    }


    /* CODIGO PARA PERSONALIZAR AVATAR
    public void addImage(View v)
    {
        if(input_username.getText().toString().equals("")){
            new MaterialDialog.Builder(this).title("Primero escriba un nombre de usuario").content(R.string.dialog_register_emptyinput_content).positiveText(R.string.dialog_succes_agree).show();

        }
        else {
            openContextMenu(v);
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
        add_image_user.setImageURI(Uri.parse(imagen));

    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

            queryBuilder = GreenDaoHelper.getPatientDao().queryBuilder();
            if (input_username.getText().toString() != "") {


                List<Patient> patientList = queryBuilder.where(UserDao.Properties.Username.eq(input_username.getText().toString())).limit(1).list();

                if (patientList.size() == 0) {
                    String nombre_foto = "/ModTerapia/" + input_username.getText().toString() + ".jpg";
                    name = Environment.getExternalStorageDirectory().getPath() + nombre_foto;
                    getMenuInflater().inflate(R.menu.menu_foto_perfil, menu);
                } else {
                    Toast.makeText(this, "El nombre de usuario ya esta en uso, escribe uno nuevo", Toast.LENGTH_LONG).show();
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
                        Toast.makeText(getApplicationContext(), "No se eligio la foto!", Toast.LENGTH_SHORT).show();
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
                        //imgbtn.setImageURI(selectedImage);
                        //imgbtn.setBackground(Drawable.createFromPath(name2));
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
            add_image_user.setBackground(Drawable.createFromPath(name2));
        }


    }
    */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
