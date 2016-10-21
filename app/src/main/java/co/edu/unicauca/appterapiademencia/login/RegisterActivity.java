package co.edu.unicauca.appterapiademencia.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.afollestad.materialdialogs.MaterialDialog;

import co.edu.unicauca.appterapiademencia.R;

/**
 * Created by ENF on 14/10/2016.
 */

public class RegisterActivity extends AppCompatActivity implements RegisterView{

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_register);
    }

    @Override
    public void newUserError() {

    }

    @Override
    public void handleSingUp() {

    }

    @Override
    public void navigateToLogin() {

            startActivity(new Intent(this, LoginActivity.class));

    }

    @Override
    public void newUserSucess() {
        //Alerta diciendo que se registro
        new MaterialDialog.Builder(this).title(R.string.dialog_succes_title).content(R.string.dialog_succes_content).positiveText(R.string.dialog_succes_agree).show();


    }

}
