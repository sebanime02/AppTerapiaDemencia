package co.edu.unicauca.appterapiademencia.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.afollestad.materialdialogs.MaterialDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.edu.unicauca.appterapiademencia.R;

/**
 * Created by ENF on 14/10/2016.
 */

public class RegisterActivity extends AppCompatActivity implements RegisterView{


    @BindView(R.id.txt_username)
    EditText input_username;
    @BindView(R.id.txt_password_supervisor)
    EditText input_password_supervisor;
    @BindView(R.id.txt_completename)
    EditText input_completename;
    @BindView(R.id.txt_password_supervisor_aprobar)
    EditText input_password_supervisor_aprobal;

    private RegisterPresenter registerPresenter;
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        registerPresenter = new RegisterPresenterImplementation(this);
        registerPresenter.OnCreate();
    }


    @Override
    protected void onDestroy() {
        registerPresenter.OnDestroy();
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

    @OnClick(R.id.btn_soysupervisor)
    @Override
    public void handleSingUp() {
        registerPresenter.registerUser(input_username.getText().toString(),input_password_supervisor.getText().toString(),input_completename.getText().toString(),input_password_supervisor_aprobal.getText().toString());


    }



    @Override
    public void navigateToLogin() {

        startActivity(new Intent(this, LoginActivity.class));

    }

    @Override
    public void newUserSucess() {
        //Alerta diciendo que se registro
        new MaterialDialog.Builder(this).title(R.string.dialog_register_error_title).content(R.string.dialog_register_error_content).positiveText(R.string.dialog_register_error_agree).show();


    }

}
