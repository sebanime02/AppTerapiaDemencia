package co.edu.unicauca.appterapiademencia.principal;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Calendar;

import co.edu.unicauca.appterapiademencia.R;
import co.edu.unicauca.appterapiademencia.login.LoginActivity;
import co.edu.unicauca.appterapiademencia.principal.help.HelpFragment;
import co.edu.unicauca.appterapiademencia.principal.notification.NotificationListFragment;
import co.edu.unicauca.appterapiademencia.principal.patientlist.PatientListFragment;
import co.edu.unicauca.appterapiademencia.principal.tips.TipsListFragment;
import co.edu.unicauca.appterapiademencia.principal.userprofile.UserProfileFragment;

/**
 * Created by ENF on 14/10/2016.
 */

public class PrincipalActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, MenuItemCompat.OnActionExpandListener {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBar actionBar;
    private Calendar calendar;
    private int fragment=1;
    SearchView searchView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigationdrawer);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        actionBar.setDisplayHomeAsUpEnabled(true);
        FragmentManager fragmentManager = getSupportFragmentManager();
        actionBar.setTitle("Lista de Pacientes");

        fragmentManager.beginTransaction()
                .replace(R.id.container, new PatientListFragment())
                .commit();

        drawerLayout = (DrawerLayout) findViewById(R.id.navigation_drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        if (navigationView != null) {
            setupNavigationDrawerContent(navigationView);
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search, menu);

        MenuItem searchItem = menu.findItem(R.id.menu3_buscar);

        searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setQueryHint("Id del bovino");
        searchView.setOnQueryTextListener(this);
        MenuItemCompat.setOnActionExpandListener(searchItem, this);

        return super.onCreateOptionsMenu(menu);
    }
    //-------------------
    @Override
    public boolean onMenuItemActionExpand(MenuItem item) {
        // Toast.makeText(getApplicationContext(), "EXPAND", Toast.LENGTH_SHORT).show();
        if(fragment==8){
            searchView.setQueryHint("Id de la Pradera");
        }
        return true;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem item) {
        // Toast.makeText(getApplicationContext(), "COLLAPSE", Toast.LENGTH_SHORT).show();

        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        // Toast.makeText(getApplicationContext(), "Texto a buscar\n\n" + s, Toast.LENGTH_SHORT).show();
        Fragment frag=null;
        switch (fragment){
            case 1:
                frag=new PatientListFragment();break;
            case 2:
                frag=new NotificationListFragment();break;
            case 3:
                frag=new TipsListFragment();break;
            case 4:
                frag=new UserProfileFragment();break;
            case 5:
                frag=new HelpFragment();break;


        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        Bundle bundle = new Bundle();
        bundle.putString("id", s);
        bundle.putBoolean("buscar", true);
        frag.setArguments(bundle);
        fragmentManager.beginTransaction()
                .replace(R.id.container, frag)
                .addToBackStack("tag")
                .commit();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        // Toast.makeText(getApplicationContext(),"Escribiendo texto...\n\n" + s, Toast.LENGTH_SHORT).show();

        return false;
    }

    //----------------------------
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupNavigationDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        FragmentManager fragmentManager = getSupportFragmentManager();

                        switch (menuItem.getItemId()) {
                            case R.id.item_navigation_drawer_listapacientes:
                                menuItem.setChecked(true);
                                fragment=1;
                                fragmentManager.beginTransaction()
                                        .replace(R.id.container, new PatientListFragment())
                                        .commit();
                                actionBar.setTitle(menuItem.getTitle());
                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                            case R.id.item_navigation_drawer_notificaciones:
                                menuItem.setChecked(true);
                                actionBar.setTitle(menuItem.getTitle());
                                fragment=2;
                                fragmentManager.beginTransaction()
                                        .replace(R.id.container, new NotificationListFragment())
                                        .commit();
                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                            case R.id.item_navigation_drawer_tips:
                                menuItem.setChecked(false);
                                fragment=3;
                                actionBar.setTitle(menuItem.getTitle());
                                fragmentManager.beginTransaction()
                                        .replace(R.id.container, new TipsListFragment())
                                        .commit();
                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                            case R.id.item_navigation_drawer_perfilusuario:
                                menuItem.setChecked(true);
                                actionBar.setTitle(menuItem.getTitle());
                                fragment=4;
                                fragmentManager.beginTransaction()
                                        .replace(R.id.container, new UserProfileFragment())
                                        .commit();
                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                            case R.id.item_navigation_drawer_ayuda:
                                menuItem.setChecked(true);
                                fragment=5;
                                actionBar.setTitle(menuItem.getTitle());
                                fragmentManager.beginTransaction()
                                        .replace(R.id.container, new HelpFragment())
                                        .commit();
                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;



                            /*
                            case R.id.item_navigation_drawer_acerca_de:
                                Intent i = new Intent(Main.this, Acerca_de.class);
                                startActivity(i);
                                return true;
                                */
                            case R.id.item_navigation_drawer_salir:
                                SharedPreferences preferencias=getSharedPreferences("appdata", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor=preferencias.edit();
                                editor.putBoolean("sessionValidation", false);
                                editor.commit();
                                Intent i2 = new Intent(PrincipalActivity.this, LoginActivity.class);
                                startActivity(i2);
                                finish();
                                return true;
                            default:
                                actionBar.setTitle("Lista de Pacientes");
                                fragmentManager.beginTransaction()
                                        .replace(R.id.container, new PatientListFragment())
                                        .commit();
                                break;

                        }
                        return true;
                    }
                });
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Salir")
                .setMessage("Estás seguro?").setNegativeButton(android.R.string.cancel, null)//sin listener
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {//un listener que al pulsar, cierre la aplicacion
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        //Salir
                        System.runFinalization();
                        System.exit(0);
                        PrincipalActivity.this.finish();
                    }
                }).show();
            // Si el listener devuelve true, significa que el evento esta procesado, y nadie debe hacer nada mas
            return true;
        }
        //para las demas cosas, se reenvia el evento al listener habitual
        return super.onKeyDown(keyCode, event);
    }
}
