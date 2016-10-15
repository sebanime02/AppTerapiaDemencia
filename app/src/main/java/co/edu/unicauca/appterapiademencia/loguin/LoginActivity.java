package co.edu.unicauca.appterapiademencia.loguin;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.edu.unicauca.appterapiademencia.R;
import co.edu.unicauca.appterapiademencia.principal.PrincipalActivity;


public class LoginActivity extends AppCompatActivity implements LoginView {

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
    LinearLayout container;
    @BindView(R.id.txt_error)
    TextView txt_error;
    private LoginPresenter loginPresenter;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);


    }




    @OnClick(R.id.btn_soysupervisor)

    private void setInputs() {
        setInputs(true);
    }

    @Override
    public void enableInputs() {setInputs(true);

    }

    @Override
    public void disableInputs() {
        setInputs(false);
    }

    @Override
    public void handleSingUp() {

    }

    @Override
    public void handleSingIn() {
        loginPresenter.validateLogin(edt_username.getText().toString(), edt_password.getText().toString());
    }

    @Override
    public void navigateToMainScreen() {

        startActivity(new Intent(this, PrincipalActivity.class));
    }

    @Override
    public void exitLogin() {

    }

    @Override
    public void loginError(String error) {
        edt_password.setText("");
        String msgErr = getResources().getString(R.string.error_loguin);
        txt_error.setEnabled(true);
        txt_error.setText(msgErr);


    }

    @Override
    public void newUserError(String Error) {

    }

    @Override
    public void newUserSucess() {
        //Alerta diciendo que se registro
        new MaterialDialog.Builder(this).title(R.string.dialog_succes_title).content(R.string.dialog_succes_content).positiveText(R.string.dialog_succes_agree).show();


    }

    @Override
    public void navigateToLogin() {
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public void navigateToRegister() {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    private void setInputs(boolean enabled) {
        if (enabled) {
            container.setVisibility(View.VISIBLE);
        } else {
            container.setVisibility(View.GONE);
        }
    }



}




