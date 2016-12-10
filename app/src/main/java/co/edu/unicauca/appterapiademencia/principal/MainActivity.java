package co.edu.unicauca.appterapiademencia.principal;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import co.edu.unicauca.appterapiademencia.items.RowItem;
import co.edu.unicauca.appterapiademencia.R;
import co.edu.unicauca.appterapiademencia.adapters.MenuAdapter;
import co.edu.unicauca.appterapiademencia.domain.User;
import co.edu.unicauca.appterapiademencia.domain.dao.GreenDaoHelper;
import co.edu.unicauca.appterapiademencia.login.LoginActivity;
import co.edu.unicauca.appterapiademencia.principal.help.HelpFragment;
import co.edu.unicauca.appterapiademencia.principal.notification.NotificationListFragment;
import co.edu.unicauca.appterapiademencia.principal.patientlist.PatientListFragment;
import co.edu.unicauca.appterapiademencia.principal.tips.TipsListFragment;
import co.edu.unicauca.appterapiademencia.principal.userprofile.UserProfileFragment;
import co.edu.unicauca.appterapiademencia.util.CircleTransform;
import service.NotificationService;

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
    public static final Integer[] imagescarer ={R.drawable.ic_list_black_24dp,R.drawable.ic_action_toggle_star,R.drawable.ic_action_action_help,R.drawable.ic_action_content_report};
    public static final String[] titlescarer ={"Lista de Pacientes","Tips para el cuidador","Ayuda","Salir"};
    private String username;
    private GreenDaoHelper helper;

    private boolean supervisormode;

    public MainActivity(){
        this.helper = GreenDaoHelper.getInstance();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences loginpreference = getSharedPreferences("appdata", Context.MODE_PRIVATE);

        setContentView(R.layout.activity_masterdetail);

        userAvatarNavbar= (ImageView) findViewById(R.id.image_header);
        completeNameNavbar = (TextView) findViewById(R.id.completename_navbar);
        QueryBuilder queryBuilder = helper.getUserDao().queryBuilder();
        rowItems = new ArrayList<RowItem>();

         if(loginpreference.getBoolean("supervisor",true))
            {
                Log.e("supervisor","la preferencia es supervisor");

                for (int i = 0; i < titlessupervisor.length; i++)
                {
                    RowItem    item = new RowItem(imagessupervisor[i], titlessupervisor[i]);
                    rowItems.add(item);
                }
                titleMessage = "Sesión de Supervisor";

                if(loginpreference.getString("username",username)!=null)
                {
                    username = loginpreference.getString("username","Nombre de Usuario");
                    User user = helper.getUserInformation(username);
                    String completename = user.getCompleteName();

                    completeNameNavbar.setText("Supervisor "+completename);


                    if(user.getPhotopath().equals(""))
                    {
                        userAvatarNavbar.setImageDrawable(getResources().getDrawable(R.drawable.emptyuser));

                    }
                    else{
                        userAvatarNavbar.setBackground(Drawable.createFromPath(user.getPhotopath()));
                    }

                }

                    //completeNameNavbar.setText("Supervisor");
                Picasso.with(getApplicationContext()).load(R.drawable.emptyuser).resize(50,50).transform(new CircleTransform()).into(userAvatarNavbar);
                    //userAvatarNavbar.setImageDrawable(getResources().getDrawable(R.drawable.emptyuser));
                //}




            }
         else
            {
                for (int i = 0; i < titlescarer.length; i++)
                {
                    RowItem    item = new RowItem(imagescarer[i], titlescarer[i]);
                    rowItems.add(item);
                }
                titleMessage = "Sesión de Cuidador";
                completeNameNavbar.setText("Cuidador");

                //userAvatarNavbar.setImageDrawable(getResources().getDrawable(R.drawable.emptyuser));
                Picasso.with(getApplicationContext()).load(R.drawable.emptyuser).resize(50,50).transform(new CircleTransform()).into(userAvatarNavbar);
            }



        listView = (ListView) findViewById(R.id.listaPacientes);
        MenuAdapter adapter = new MenuAdapter(this,
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
            supervisormode=false;

        }
        if(loginpreference.getBoolean("supervisor",true)){
            actionBar.setTitle("Sesión de Supervisor");
            supervisormode=true;
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
        SharedPreferences loginpreference = getSharedPreferences("appdata", Context.MODE_PRIVATE);
        if(loginpreference.getBoolean("supervisor",true))
        {
            Log.e("supervisor","la preferencia es supervisor");



            if(loginpreference.getString("username",username)!=null)
            {
                username = loginpreference.getString("username","Nombre de Usuario");
                User user = helper.getUserInformation(username);
                String completename = user.getCompleteName();

                completeNameNavbar.setText("Supervisor "+completename);


                if(user.getPhotopath().equals(""))
                {
                    userAvatarNavbar.setImageDrawable(getResources().getDrawable(R.drawable.emptyuser));

                }
                else{
                    userAvatarNavbar.setBackground(Drawable.createFromPath(user.getPhotopath()));
                }

            }

            //completeNameNavbar.setText("Supervisor");
            Picasso.with(getApplicationContext()).load(R.drawable.emptyuser).resize(50,50).transform(new CircleTransform()).into(userAvatarNavbar);
            //userAvatarNavbar.setImageDrawable(getResources().getDrawable(R.drawable.emptyuser));
            //





        }
        else
        {

            titleMessage = "Sesión de Cuidador";
            completeNameNavbar.setText("Cuidador");

            //userAvatarNavbar.setImageDrawable(getResources().getDrawable(R.drawable.emptyuser));
            Picasso.with(getApplicationContext()).load(R.drawable.emptyuser).resize(50,50).transform(new CircleTransform()).into(userAvatarNavbar);
        }

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        /* SharedPreferences preferencias=getSharedPreferences("appdata", Context.MODE_PRIVATE);
        if(preferencias.getBoolean("supervisor",true))
        {
           supervisormode= true;
            Log.d("modo","Supervisor activado");
        }
        if(preferencias.getBoolean("supervisor",false)){
            supervisormode=false;
            Log.d("modo","Supervisor desactivado");
        }
        */

        Log.d("Presionado",position+"");

        switch (position) {
            case 0:

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new PatientListFragment())
                        .commit();
                actionBar.setTitle("Lista de Pacientes");


                //-----------------------------inicio servicio------------------------------------
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.SECOND, 10);
                Intent intent = new Intent(this, NotificationService.class);
                PendingIntent pintent = PendingIntent.getService(this, 0, intent, 0);
                AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                int j;
                j=15;
                alarm.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),j* 1000, pintent);
                startService(intent);
                ////-----------------------------final servicio------------------------------------

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
                    callHelp();

                }

                break;
            case 3:
                if(supervisormode==true)
                {
                    callUserProfile();
                }
                else{
                    callSignOff();
                }
                break;
            case 4:
                if (supervisormode==true){
                    callHelp();
                }

                break;

            case 5:

                    callSignOff();

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

        //-----------------------------inicio servicio------------------------------------
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, 10);
        Intent intent = new Intent(this, NotificationService.class);
        PendingIntent pintent = PendingIntent.getService(this, 0, intent, 0);
        AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        int j;
        j=15;
        alarm.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),j* 1000, pintent);
        startService(intent);
        ////-----------------------------final servicio------------------------------------
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
        editor.putBoolean("supervisor",false);
        editor.putString("username",null);
        editor.commit();
        Intent i2 = new Intent(MainActivity.this, LoginActivity.class);
        i2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i2);
        finish();
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Salir")
                .setMessage("Estás seguro?").setNegativeButton(android.R.string.cancel, null)//sin listener
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {//un listener que al pulsar, cierre la aplicacion
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                     callSignOff();
                    }
                }).show();
            // Si el listener devuelve true, significa que el evento esta procesado, y nadie debe hacer nada mas
            return true;
        }
        //para las demas cosas, se reenvia el evento al listener habitual
        return super.onKeyDown(keyCode, event);
    }

}