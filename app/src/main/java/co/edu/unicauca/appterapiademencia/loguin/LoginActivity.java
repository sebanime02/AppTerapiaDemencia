package co.edu.unicauca.appterapiademencia.loguin;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.edu.unicauca.appterapiademencia.R;





public class LoginActivity extends AppCompatActivity  implements LoginView  {

    @BindView(R.id.btn_salir) Button btn_salir;
    @BindView(R.id.btn_soycuidador) Button btn_cuidador;
    @BindView(R.id.btn_soysupervisor) Button btn_supervisor;
    @BindView(R.id.txt_username) EditText edt_username;
    @BindView(R.id.txt_password) EditText edt_password;
    @BindView(R.id.container_SingIn) LinearLayout container;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

    }

   @OnClick(R.id.btn_soysupervisor)

   private void setInputs(){
       setInputs(true);
   }

    @Override
    public void enableInputs() {

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

    }

    @Override
    public void navigateToMainScreen() {

    }

    @Override
    public void exitLogin() {

    }

    @Override
    public void loginError(String error) {

    }

    @Override
    public void newUserError(String Error) {

    }

    @Override
    public void newUserSucess() {

    }

    private void setInputs(boolean enabled)
    {
        if(enabled) {
            container.setVisibility(View.VISIBLE);
        }
        else{
            container.setVisibility(View.GONE);
        }
    }

}




