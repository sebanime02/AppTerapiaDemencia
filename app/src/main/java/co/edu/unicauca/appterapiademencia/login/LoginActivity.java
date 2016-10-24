package co.edu.unicauca.appterapiademencia.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

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
    @BindView(R.id.btn_soycuidador) Button btn_soycuidador;
    @BindView(R.id.btn_soysupervisor) Button btn_soysupervisor;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        loginPresenter = new LoginPresenterImplementation(this);
        loginPresenter.OnCreate();
        //loginPresenter.checkForAuthenticatedUser();

    }


    protected  void onDestroy(){
        loginPresenter.OnDestroy();
        super.onDestroy();
    }

    @OnClick(R.id.btn_soysupervisor) private void setInputs() {loginPresenter.manageInputs();}

    @Override
    public void enableInputs() {
        container.setVisibility(View.VISIBLE);
        btn_supervisor.setBackgroundColor(getResources().getColor(R.color.accent_color));

    }

    @Override
    public void disableInputs() {
        container.setVisibility(View.GONE);
    }


    @Override
    public void handleSingIn() {
        loginPresenter.validateLogin(edt_username.getText().toString(), edt_password.getText().toString());
    }

    @OnClick(R.id.btn_soycuidador) @Override public void navigateToMainScreen() {startActivity(new Intent(this, PrincipalActivity.class));}

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
    public void navigateToRegister() {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    private void setInputs(boolean enabled) {
        if (enabled) {
            container.setVisibility(View.VISIBLE);
            btn_supervisor.setBackgroundColor(getResources().getColor(R.color.accent_color));
        } else {
            container.setVisibility(View.GONE);
        }
    }



}




