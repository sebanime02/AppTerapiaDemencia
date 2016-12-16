package co.edu.unicauca.appterapiademencia.principal.userprofile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.afollestad.materialdialogs.MaterialDialog;

import co.edu.unicauca.appterapiademencia.R;
import co.edu.unicauca.appterapiademencia.domain.User;
import co.edu.unicauca.appterapiademencia.domain.dao.GreenDaoHelper;
import co.edu.unicauca.appterapiademencia.domain.dao.UserDao;
import co.edu.unicauca.appterapiademencia.login.RegisterActivity;
import co.edu.unicauca.appterapiademencia.principal.MainActivity;

/**
 * Created by ENF on 25/10/2016.
 */

public class UserProfileFragment extends Fragment implements View.OnClickListener{
    private View rootView;
    private EditText edtUsername,edtPassword,edtCompleteName;
    private AppCompatImageButton btnEditUsername,btnEditPassword,btnEditCompleteName;
    private Button btnSave;
    private SharedPreferences preferences;
    private GreenDaoHelper helper;
    private UserDao userDao;
    private String username;
    private User user;
    private Boolean nothingIndicator= false;
    private AppCompatImageButton btnAddUser;

    public UserProfileFragment()
    {
        helper = GreenDaoHelper.getInstance();
        userDao = helper.getUserDao();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_userprofile, container, false);
        preferences= getActivity().getSharedPreferences("appdata", Context.MODE_PRIVATE);
        edtUsername = (EditText) rootView.findViewById(R.id.txt_username);
        edtPassword = (EditText) rootView.findViewById(R.id.txt_password_user);
        edtCompleteName = (EditText) rootView.findViewById(R.id.txt_completename);

        btnEditUsername  = (AppCompatImageButton) rootView.findViewById(R.id.editUsername);
        btnEditPassword  = (AppCompatImageButton) rootView.findViewById(R.id.editPassword);
        btnEditCompleteName  = (AppCompatImageButton) rootView.findViewById(R.id.editCompleteName);
        btnSave = (Button) rootView.findViewById(R.id.save_data_user);
        btnAddUser = (AppCompatImageButton) rootView.findViewById(R.id.btn_ir_register);
        btnSave.setOnClickListener(this);
        btnAddUser.setOnClickListener(this);

        username = preferences.getString("username","Nombre de Usuario");

        try {
            user = helper.getUserInformation(username);
            edtUsername.setText(user.getUsername());
            edtPassword.setText(user.getPassword());
            edtCompleteName.setText(user.getCompleteName());

        }catch (Exception e)
        {
            Log.e("userprofile","Error trayendo informacion del usuario");
        }




        btnEditUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtUsername.setEnabled(true);
                nothingIndicator = true;

            }
        });
        btnEditPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtPassword.setEnabled(true);
                nothingIndicator = true;

            }
        });
        btnEditCompleteName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtCompleteName.setEnabled(true);
                nothingIndicator = true;

            }
        });


        return rootView;


    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public void saveUserProfile()
    {

        Log.e("Registro","va a validar");
        if(validateInputs(edtUsername.getText().toString(),edtPassword.getText().toString(),edtCompleteName.getText().toString())==false)
        {
            try {
                User useredit = helper.getUserInformation(username);

                useredit.setUsername(edtUsername.getText().toString());
                useredit.setPassword(edtPassword.getText().toString());
                useredit.setCompleteName(edtCompleteName.getText().toString());
                useredit.update();
                //Log.e("userprofile","usereditado "+" username"+useredit.getUsername()+" CompleteName"+useredit.getCompleteName());
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("username",edtUsername.getText().toString());
                editor.commit();
                preferences.edit();


                new MaterialDialog.Builder(getContext()).title(R.string.succes_set_user_information_title).positiveText(R.string.dialog_succes_agree).show();



            }catch (Exception e)
            {
                new MaterialDialog.Builder(getContext()).title(R.string.error_set_user_title).content(R.string.error_set_user_content).positiveText(R.string.dialog_succes_agree).icon(getResources().getDrawable(R.drawable.sadface)).show();
                Log.e("userprofile","Error seteando datos");

                /*
                try {
                    User useredit = helper.getUserInformation(username);

                    edtUsername.setText(useredit.getUsername().toString());
                    edtUsername.setText(useredit.getUsername().toString());
                    edtUsername.setText(useredit.getUsername().toString());

                }catch (Exception exc){

                    edtUsername.setText("");
                    edtUsername.setText("");
                    edtUsername.setText("");
                }
                */
            }
            edtUsername.setEnabled(false);
            edtPassword.setEnabled(false);
            edtCompleteName.setEnabled(false);
            startActivity(new Intent(getContext(), MainActivity.class));

        }
        else
        {
            Log.e("Registro","la validacion no funciono");

            new MaterialDialog.Builder(getContext()).title(R.string.dialog_register_emptyinput_title).positiveText(R.string.dialog_succes_agree).show();

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
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.save_data_user:
                if(!nothingIndicator)
                {
                    new MaterialDialog.Builder(getContext()).title(R.string.error_disable_inputs).positiveText(R.string.dialog_succes_agree).show();

                }
                else
                {
                    saveUserProfile();
                }
                break;
            case R.id.btn_ir_register:

                Intent ir=new Intent(getContext(), RegisterActivity.class);
                ir.putExtra("msg","msg");

                startActivity(ir);

                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        username = preferences.getString("username","Nombre de Usuario");

        try {
            user = helper.getUserInformation(username);
            edtUsername.setText(user.getUsername());
            edtPassword.setText(user.getPassword());
            edtCompleteName.setText(user.getCompleteName());

        }catch (Exception e)
        {
            Log.e("userprofile","Error trayendo informacion del usuario");
        }
    }
}
