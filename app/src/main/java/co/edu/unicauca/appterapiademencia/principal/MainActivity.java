package co.edu.unicauca.appterapiademencia.principal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

import co.edu.unicauca.appterapiademencia.Item.RowItem;
import co.edu.unicauca.appterapiademencia.R;
import co.edu.unicauca.appterapiademencia.adapters.Adapter;
import co.edu.unicauca.appterapiademencia.domain.User;
import co.edu.unicauca.appterapiademencia.domain.dao.GreenDaoHelper;
import co.edu.unicauca.appterapiademencia.domain.dao.UserDao;
import co.edu.unicauca.appterapiademencia.login.LoginActivity;
import co.edu.unicauca.appterapiademencia.principal.help.HelpFragment;
import co.edu.unicauca.appterapiademencia.principal.notification.NotificationListFragment;
import co.edu.unicauca.appterapiademencia.principal.patientlist.PatientListFragment;
import co.edu.unicauca.appterapiademencia.principal.tips.TipsListFragment;
import co.edu.unicauca.appterapiademencia.principal.userprofile.UserProfileFragment;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView listView;
    private String[] items;
    private ActionBar actionBar;
    private Toolbar toolbar;
    private List<RowItem> rowItems;
    private String titleMessage;
    private TextView completeNameNavbar;
    private ImageView userAvatarNavbar;
    public static final Integer[] imagessupervisor = {R.drawable.ic_list_black_24dp,R.drawable.ic_action_content_report,R.drawable.ic_action_toggle_star,R.drawable.ic_action_action_settings,R.drawable.ic_action_action_help,R.drawable.ic_action_content_report};
    public static final String[] titlessupervisor= {"Lista de Pacientes","Notificaciones","Tips para el cuidador","Perfil de usuario","Ayuda","Salir"};
    public static final Integer[] imagescarer ={R.drawable.ic_list_black_24dp,R.drawable.ic_action_toggle_star,R.drawable.ic_action_action_settings,R.drawable.ic_action_action_help,R.drawable.ic_action_content_report};
    public static final String[] titlescarer ={"Lista de Pacientes","Tips para el cuidador","Perfil de usuario","Ayuda","Salir"};
    private String username;


    private boolean supervisormode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences loginpreference = getSharedPreferences("appdata", Context.MODE_PRIVATE);

        setContentView(R.layout.activity_masterdetail);

        userAvatarNavbar= (ImageView) findViewById(R.id.image_header);
        completeNameNavbar = (TextView) findViewById(R.id.completename_navbar);
        QueryBuilder queryBuilder = GreenDaoHelper.getUserDao().queryBuilder();
        rowItems = new ArrayList<RowItem>();
         if(loginpreference.getBoolean("supervisor",true))
            {
                for (int i = 0; i < titlessupervisor.length; i++)
                {
                    RowItem    item = new RowItem(imagessupervisor[i], titlessupervisor[i]);
                    rowItems.add(item);
                }
                titleMessage = "Sesión de Supervisor";
                loginpreference.getString("username",username);
                List<User> users= queryBuilder.where(UserDao.Properties.Username.eq(username)).limit(1).list();


            }
         else
            {
                for (int i = 0; i < titlescarer.length; i++)
                {
                    RowItem    item = new RowItem(imagescarer[i], titlescarer[i]);
                    rowItems.add(item);
                }
                titleMessage = "Sesión de Cuidador";

            }





        listView = (ListView) findViewById(R.id.listaPacientes);
        Adapter adapter = new Adapter(this,
                R.layout.item_list, rowItems);
        //listView = (ListView)  findViewById(R.id.lista);
        listView.setOnItemClickListener(this);


        listView.setAdapter(adapter);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        actionBar.setDisplayHomeAsUpEnabled(false);


        if(loginpreference.getBoolean("supervisor",false)){
            actionBar.setTitle("Sesión de Cuidador");

        }
        if(loginpreference.getBoolean("supervisor",true)){
            actionBar.setTitle("Sesión de Supervisor");
        }



        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new PatientListFragment())
                .commit();



/*
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                   getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, new ListPatientFragment())
                            .commit();
                    case 1:
                     getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, new ListNotificationsFragment())
                                .commit();
                    case 2:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, new ListTipsFragment())
                                .commit();
                }
            }
        });
      */
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        SharedPreferences preferencias=getSharedPreferences("appdata", Context.MODE_PRIVATE);
        if(preferencias.getBoolean("supervisor",true))
        {
           supervisormode= true;
        }
        if(preferencias.getBoolean("supervisor",true)){
            supervisormode=false;
        }
        Log.d("Presionado",position+"");
        switch (position) {
            case 0:

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new PatientListFragment())
                        .commit();
                actionBar.setTitle("Lista de Pacientes");
                break;
            case 1:
                if(supervisormode==true){
                    callNotifications();
                }
                else {
                   callTips();
                    }

                break;
            case 2:
                if(supervisormode==true)
                {
                    callTips();
                }
                else{
                    callUserProfile();
                }

                break;
            case 3:
                if(supervisormode==true){
                    callUserProfile();
                }
                else{
                    callHelp();
                }
                break;
            case 4:
                if (supervisormode==true){
                    callHelp();
                }
                else{
                    callSignOff();
                }
                break;

            case 5:
                if(supervisormode==true){
                    callSignOff();
                }

                break;

        }
    }
    public void callNotifications(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new NotificationListFragment())
                .commit();
        actionBar.setTitle("Notificaciones de Supervisor");
    }
    public void callTips(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new TipsListFragment())
                .commit();
        actionBar.setTitle("Tips para el cuidador");
    }
    public void callUserProfile(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new UserProfileFragment())
                .commit();
        actionBar.setTitle("Perfil de Usuario");
    }
    public void callHelp(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new HelpFragment())
                .commit();
        actionBar.setTitle("Ayuda");
    }
    public void callSignOff(){
        SharedPreferences preferencias=getSharedPreferences("appdata", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferencias.edit();
        editor.putBoolean("sessionValidation", false);
        editor.commit();
        Intent i2 = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(i2);
        finish();
    }

}