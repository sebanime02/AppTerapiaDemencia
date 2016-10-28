package co.edu.unicauca.appterapiademencia.principal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import co.edu.unicauca.appterapiademencia.Item.RowItem;
import co.edu.unicauca.appterapiademencia.R;
import co.edu.unicauca.appterapiademencia.adapter.Adapter;
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
    public static final Integer[] images = {R.drawable.ic_action_content_mail,R.drawable.ic_action_content_report,R.drawable.ic_action_toggle_star,R.drawable.ic_action_action_settings,R.drawable.ic_action_action_help,R.drawable.ic_action_content_report};
    public static final String[] titles= {"Lista de Pacientes","Notificaciones","Tips para el cuidador","Perfil de usuario","Ayuda","Salir"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masterdetail);
        rowItems = new ArrayList<RowItem>();
        for (int i = 0; i < titles.length; i++) {
            RowItem item = new RowItem(images[i], titles[i]);
            rowItems.add(item);
        }

        listView = (ListView) findViewById(R.id.lista);
        Adapter adapter = new Adapter(this,
                R.layout.item_list, rowItems);
        listView = (ListView)  findViewById(R.id.lista);
        listView.setOnItemClickListener(this);

      
        listView.setAdapter(adapter);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        actionBar.setDisplayHomeAsUpEnabled(true);

        actionBar.setTitle("Lista de Pacientes");


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

        Log.d("Presionado",position+"");
            switch (position) {
                case 0:


                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, new PatientListFragment())
                            .commit();
                    actionBar.setTitle("Lista de Pacientes");
                    break;
                case 1:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, new NotificationListFragment())
                            .commit();
                   actionBar.setTitle("Notificaciones de Supervisor");

                    break;
                case 2:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, new TipsListFragment())
                            .commit();
                    actionBar.setTitle("Tips para el cuidador");

                    break;
                case 3:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, new UserProfileFragment())
                            .commit();
                    actionBar.setTitle("Perfil de Usuario");
                    break;
                case 4:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, new HelpFragment())
                            .commit();
                    actionBar.setTitle("Ayuda");
                    break;

                case 5:
                    SharedPreferences preferencias=getSharedPreferences("appdata", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=preferencias.edit();
                    editor.putBoolean("sessionValidation", false);
                    editor.commit();
                    Intent i2 = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(i2);
                    finish();
                    break;

        }
    }
}
