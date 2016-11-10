package co.edu.unicauca.appterapiademencia.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import co.edu.unicauca.appterapiademencia.R;
import co.edu.unicauca.appterapiademencia.principal.MainActivity;


public class LoginActivity extends AppCompatActivity implements LoginView {
    private EditText input_username,input_password;
    private Button btn_salir,btn_cuidador,btn_supervisor;
    private CoordinatorLayout container;
    private TextView txt_error;
    private String username,password;
    SharedPreferences loginpreference;
    /*
    @BindView(R.id.btn_salir)
    Button btn_salir;
    @BindView(R.id.btn_soycuidador)
    Button btn_cuidador;
    @BindView(R.id.btn_soysupervisor)
    Button btn_supervisor;
    @BindView(R.id.txt_username)
    EditText edt_username;
    @BindView(R.id.txt_password)
    EditText edt_password;

    @BindView(R.id.container_SingIn)
    CoordinatorLayout container;

    @BindView(R.id.txt_error)
    TextView txt_error;
    */


    private LoginPresenter loginPresenter;


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createFolder();

        loginpreference = getSharedPreferences("appdata", Context.MODE_PRIVATE);
        if(loginpreference.getBoolean("sessionValidation",true)){
            SharedPreferences.Editor editor = loginpreference.edit();
            editor.putBoolean("supervisor",true);
            editor.commit();
            navigateToMainScreen();
        }


        setContentView(R.layout.activity_login);
        //ButterKnife.bind(this);
        input_username = (EditText) findViewById(R.id.txt_username);
        input_password = (EditText) findViewById(R.id.txt_password);
        btn_cuidador= (Button) findViewById(R.id.btn_soycuidador);
        btn_supervisor= (Button) findViewById(R.id.btn_soysupervisor);
        container = (CoordinatorLayout) findViewById(R.id.container_SingIn);
        txt_error = (TextView) findViewById(R.id.txt_error);


        loginPresenter = new LoginPresenterImplementation(this);
        loginPresenter.OnCreate();
        //loginPresenter.checkForAuthenticatedUser();

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

        btn_supervisor.setBackgroundColor(getResources().getColor(R.color.accent_color));

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
        new MaterialDialog.Builder(this).title("Supervisor No Encontrado").content(R.string.error_loguin).positiveText(R.string.dialog_succes_agree).icon(getResources().getDrawable(R.drawable.sadface)).show();

        //input_password.setError(msgErr);
       /* txt_error.setEnabled(true);
        txt_error.setVisibility(View.VISIBLE);
        txt_error.setText(msgErr);*/


    }


    @Override
    public void navigateToRegister()
    {
        Log.e("Registro", "entro a registro");
        startActivity(new Intent(this, RegisterActivity.class));
    }

    @Override
    public void saveLoginPreference() {

        SharedPreferences.Editor editor = loginpreference.edit();
        editor.putBoolean("sessionValidation",true);
        editor.commit();
    }

    @Override
    public void setSupervisorPreference() {
        SharedPreferences.Editor editor = loginpreference.edit();
        editor.putBoolean("supervisor",true);
        editor.putString("username",input_username.getText().toString());
        editor.commit();
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
    }

}




