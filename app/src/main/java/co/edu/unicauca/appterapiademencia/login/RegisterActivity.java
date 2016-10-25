package co.edu.unicauca.appterapiademencia.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import butterknife.ButterKnife;
import co.edu.unicauca.appterapiademencia.R;

/**
 * Created by ENF on 14/10/2016.
 */

public class RegisterActivity extends AppCompatActivity implements RegisterView{
private EditText input_username,input_password_supervisor,input_completename,input_password_supervisor_aprobal;

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

        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        input_username = (EditText) findViewById(R.id.txt_username);
        input_password_supervisor = (EditText) findViewById(R.id.txt_password_supervisor);
        input_completename = (EditText) findViewById(R.id.txt_completename);
        input_password_supervisor_aprobal = (EditText) findViewById(R.id.txt_password_supervisor_aprobar);
        registerPresenter = new RegisterPresenterImplementation(this);
        registerPresenter.OnCreate();
    }


    @Override
    protected void onDestroy() {
        //registerPresenter.OnDestroy();
        super.onDestroy();
    }


    @Override
    public void newUserError(int error) {
        switch (error) {
            case 1:
            new MaterialDialog.Builder(this).title(R.string.dialog_succes_title).content(R.string.dialog_succes_content).positiveText(R.string.dialog_succes_agree).show();
            break;
            case 2:
            new MaterialDialog.Builder(this).title(R.string.dialog_register_emptyinput_title).content(R.string.dialog_register_emptyinput_content).positiveText(R.string.dialog_succes_agree).show();
            break;

        }
    }


    @Override
    public void handleSingUp() {
        Log.e("Registro","va a validar");
        if(validateInputs(input_username.getText().toString(),input_password_supervisor.getText().toString(),input_completename.getText().toString(),input_password_supervisor_aprobal.getText().toString())==false)
        {
            Log.e("Registro","se valido");
            registerPresenter.registerUser(input_username.getText().toString(),input_password_supervisor.getText().toString(),input_completename.getText().toString(),input_password_supervisor_aprobal.getText().toString());
        }
        else
        {
            Log.e("Registro","la validacion no funciono");

            new MaterialDialog.Builder(this).title("campos vacios").content("Hay campos vacios, por favor completelos").positiveText(R.string.dialog_succes_agree).show();
        }
    }
    private boolean validateInputs(String username, String password, String completeName, String passwordaprobal) {
        if(username.equals("") || password.equals("") || completeName.equals("") || passwordaprobal.equals("")){
            return true;
        }
        else{
            return  false;
        }
    }


    @Override
    public void navigateToLogin() {
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public void newUserSuccess() {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(this);
        builder.title("Registro con éxito").content("Su registro como supervisor ha sido completado con éxito").positiveText(R.string.dialog_succes_agree).show();;
        builder.onPositive(new MaterialDialog().SingleButtonCallback(){
            @Override
            public void onClick(@NonNull MaterialDialog dialog, DialogAction which){

            }
        });


    }


    public void saveRegister(View v){
        Log.e("Registro","Funciono el boton");
        handleSingUp();

    }
    public void toLogin(View v){
        navigateToLogin();
    }


}
