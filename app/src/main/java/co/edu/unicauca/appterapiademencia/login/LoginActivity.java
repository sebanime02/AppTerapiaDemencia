package co.edu.unicauca.appterapiademencia.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;

import java.io.File;
import java.io.FileNotFoundException;

import co.edu.unicauca.appterapiademencia.R;
import co.edu.unicauca.appterapiademencia.domain.Reminiscence;
import co.edu.unicauca.appterapiademencia.domain.User;
import co.edu.unicauca.appterapiademencia.domain.dao.GreenDaoHelper;
import co.edu.unicauca.appterapiademencia.principal.MainActivity;
import co.edu.unicauca.appterapiademencia.security.Security;

import static co.edu.unicauca.appterapiademencia.R.string.reminiscence_demo_autor;


//LoginActivity implementa las funciones de LoginView,
// las funciones se limitan unicamente a despligue de datos y eventos E/S
public class LoginActivity extends AppCompatActivity implements LoginView {
    private EditText input_username,input_password;
    private Button btn_salir,btn_cuidador,btn_supervisor;
    private CoordinatorLayout container;
    private TextView txt_error;
    private String username,password;
    private String pathFolder;
    SharedPreferences loginpreference;
    private LoginPresenter loginPresenter;
    private int intentos = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createFolder();
        createCarerUser();

        loginpreference = getSharedPreferences("appdata", Context.MODE_PRIVATE);
        setContentView(R.layout.activity_login);
        input_username = (EditText) findViewById(R.id.txt_username);
        input_password = (EditText) findViewById(R.id.txt_password);
        btn_cuidador= (Button) findViewById(R.id.btn_soycuidador);
        btn_supervisor= (Button) findViewById(R.id.btn_soysupervisor);
        container = (CoordinatorLayout) findViewById(R.id.container_SingIn);
        txt_error = (TextView) findViewById(R.id.txt_error);


        loginPresenter = new LoginPresenterImplementation(this);
        loginPresenter.OnCreate();
        //loginPresenter.checkForAuthenticatedUser();

        try
        {
            Bundle bundle = getIntent().getExtras();

            if(bundle.getBoolean("registrado"))
            {
                enableInputs();
            }
        }catch (Exception e)
        {

        }

    }


    protected void onDestroy() {
        loginPresenter.OnDestroy();
        super.onDestroy();
    }
    @Override
    public void setInputs() {
        Log.e("Login","entro a setinputs del activity");
        loginPresenter.manageInputs();

    }

    @Override
    public void enableInputs() {
        Log.e("Login","De nuevo en el activity voy a habilitar los inputs");

        container.setVisibility(View.VISIBLE);

        btn_supervisor.setBackground(getResources().getDrawable(R.drawable.corner_button_selected));


    }

    @Override
    public void disableInputs() {

        Log.e("Login","De nuevo en el activity voy a deshabilitar los inputs");
        container.setVisibility(View.GONE);
        btn_supervisor.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

    }


    @Override
    public void handleSingIn() {

            Log.e("Login","inputs completos");
            loginPresenter.validateLogin(input_username.getText().toString(), input_password.getText().toString());
    }


    @Override
    public void navigateToMainScreen() {
        Log.e("Principal", "navega al menu principal");
        startActivity(new Intent(this, MainActivity.class));
    }


    @Override
    public void exitLogin()
    {

    }

    @Override
    public void loginError()
    {

        String msgErr = getResources().getString(R.string.error_loguin);

        if(intentos!= Security.MAXIMAL_INTENTS_LOGUIN)
        {
            new MaterialDialog.Builder(this).title("Usuario no Encontrado").content(R.string.error_loguin).positiveText(R.string.dialog_succes_agree).icon(getResources().getDrawable(R.drawable.sadface)).show();
            intentos = intentos +1;
        }else
        {
            shotTooManyTries();
        }





    }


    @Override
    public void navigateToRegister()
    {
        Log.e("Registro", "entro a registro");
        startActivity(new Intent(this, RegisterActivity.class));
    }

    @Override
    public void saveLoginPreference() {

        /*
        SharedPreferences.Editor editor = loginpreference.edit();
        editor.putBoolean("sessionValidation",true);
        editor.commit();
        */
    }

    @Override
    public void setSupervisorPreference() {
        SharedPreferences.Editor editor = loginpreference.edit();
        editor.putBoolean("supervisor",true);
        editor.putString("username",input_username.getText().toString());
        editor.commit();
    }

    @Override
    public void shotTooManyTries() {
        new MaterialDialog.Builder(this).title("Muchos Intentos Fallidos").content(R.string.too_many_tries).positiveText(R.string.dialog_succes_agree).show();

    }


    private void validateInputs()
    {
        username = input_username.getText().toString();
        password = input_password.getText().toString();

        if (username.equals("")) {
            txt_error.setText(R.string.input_empty_username);
        }
        if (password.equals("")) {
            txt_error.setText(R.string.input_empty_password);
        }
        if (username.equals("") || password.equals("")) {
            txt_error.setText(R.string.input_empty_both);

        }
        else{
            handleSingIn();

        }
    }


    public void toMain(View v){
            loginpreference = getSharedPreferences("appdata", Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = loginpreference.edit();
            editor.putBoolean("supervisor",false);
            editor.commit();

            navigateToMainScreen();


    }
    public void deployInputs(View v){
        setInputs();
    }
    public void toRegister(View v){
        navigateToRegister();
    }
    public void autenticateUser(View v){
        validateInputs();

    }
    private void createFolder() {
        File folder = new File(Environment.getExternalStorageDirectory() + "/ModTerapia");
        boolean success = true;
        if (!folder.exists()) {
            //Toast.makeText(this, "carpeta creada", Toast.LENGTH_SHORT).show();
            success = folder.mkdir();

        }
        if (success) {
            //Toast.makeText(this, "La carpeta ya esxiste", Toast.LENGTH_SHORT).show();
        } else {
            //Toast.makeText(this, "fallo al crear carpeta", Toast.LENGTH_SHORT).show();
        }
        pathFolder = folder.getPath();
        Log.e("createFolder","pathFolder "+pathFolder);
    }
    private void createCarerUser() {


        if(GreenDaoHelper.getInstance().carerIdDetector()==false){

            String username ="Cuidador";
            String password ="Cuidador";
            String completeName ="Cuidador";
            int accessType=0;
            User user = new User(null,username,password,completeName,accessType,"");
            GreenDaoHelper.getInstance().getUserDao().insert(user);


            /*
            try {
                createDemoReminiscence();
            }catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
            */
            Log.e("setup","ingreso usuario cuidador");
        }
        else{
            Log.e("setup","usuario carer ya existe");
        }
        GreenDaoHelper.getInstance().getUsers();


    }


    private void createDemoReminiscence() throws FileNotFoundException {
        String title;
        String description;
        String author;
        String img;
        String audio;



        title = getResources().getString(R.string.reminiscence_demo_title);
        description = getResources().getString(R.string.reminiscence_demo_contenido);
        author = getResources().getString(reminiscence_demo_autor);
        //img = R.drawable.emptyuser+"";
        /*

        pathFolder =Environment.getExternalStorageDirectory().getPath() +"/ModTerapia/";

        Log.e("createFolder","pathFolder reminiscencia"+pathFolder);


        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.popayanimagen);
        FileOutputStream out = null;
        try
        {

            out = new FileOutputStream(pathFolder);
            bitmap.compress(Bitmap.CompressFormat.JPEG,85,out);
        }catch (Exception e)
        {
            Log.e("login"," Error comprimiendo o determinando el pathfolder");
            e.printStackTrace();
        } finally {
            try
            {
                if(out != null)
                {
                 out.close();
                }
            }catch (Exception e)
            {
                Log.e("login"," Error cerrando el buffer");

                e.printStackTrace();
            }
        }

        try
        {
            MediaStore.Images.Media.insertImage(getContentResolver(),pathFolder,"popayanimagen.jpg","popayanimagen.jpg");

        }catch (Exception e)
        {
            Log.e("login"," Error insertando la imagen en el pathfolder");

            e.printStackTrace();
        }


        img = pathFolder+"/popayanimagen.jpg";
        */


        img = Uri.parse("android.resource://co.edu.unicauca.appterapiademencia/"+R.mipmap.popayanimagen).toString();
        audio = Uri.parse("android.assets://co.edu.unicauca.appterapiademencia/torrereloj").toString();



        Reminiscence reminiscence = new Reminiscence(null,title,description,img,author,audio);
        GreenDaoHelper.getInstance().getReminiscenceDao().insert(reminiscence);
        GreenDaoHelper.getInstance().getReminiscenceDao().update(reminiscence);

        Log.e("createreminis","id "+reminiscence.getId());
        Log.e("createreminis","title "+reminiscence.getTitle());
        Log.e("createreminis","photo "+reminiscence.getPhotopath());
        Log.e("createreminis","audiopath "+reminiscence.getAudiopath());




    }

}




