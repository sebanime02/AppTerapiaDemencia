package co.edu.unicauca.appterapiademencia.principal.patientlist;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import co.edu.unicauca.appterapiademencia.R;
import co.edu.unicauca.appterapiademencia.adapters.ViewPagerAdapter;
import co.edu.unicauca.appterapiademencia.domain.User;
import co.edu.unicauca.appterapiademencia.domain.dao.GreenDaoHelper;
import co.edu.unicauca.appterapiademencia.principal.cognitiveexercises.GraphicsExercises;
import co.edu.unicauca.appterapiademencia.principal.notes.NotesFragment;
import co.edu.unicauca.appterapiademencia.principal.patientprofile.PatientProfileFragment;

/**
 * Created by ENF on 04/11/2016.
 */

public class PatientProfileActivity extends AppCompatActivity{
    private TextView inputprofileidentity;
    private String cedula;
    private Toolbar toolbar;
    public static ViewPager viewPager;
    private TabLayout tabLayout;
    private ActionBar actionBar;
    private boolean supervisorState;
    private SharedPreferences preferences;
    private String username;
    private GreenDaoHelper helper;
    private boolean tabCondition = true;
    private boolean editCondition = true;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_profile);
        helper = GreenDaoHelper.getInstance();
        preferences = getSharedPreferences("appdata", Context.MODE_PRIVATE);

        if(preferences.getString("username",username)!=null)
        {
            username = preferences.getString("username", "Nombre de Usuario");
            User user = helper.getUserInformation(username);
            int usermode = user.getAccessType();

            if(usermode==1)
            {
                tabCondition = false;
                editCondition=true;
            }else{
                tabCondition=true;
                editCondition=true;
            }

        }else
        {
            editCondition=false;
            tabCondition=false;

        }

        if(getIntent().getExtras()!=null){
            Bundle bundle = getIntent().getExtras();
            cedula = bundle.get("cedula").toString();

        }

        final Bundle args = new Bundle();
        args.putLong("cedula",Long.parseLong(cedula));
        args.putString("username",username);

        Log.e("patienprofile","Cedula "+cedula);
        Log.e("patienprofile","Username "+username);




        //inputprofileidentity= (TextView) findViewById(R.id.txt_profile);
        //inputprofileidentity.setText(cedula);
       toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);

        actionBar.setTitle(getResources().getString(R.string.txt_back_principal));

        //getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager,args);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if(editCondition)
        {
            getMenuInflater().inflate(R.menu.menu_edit,menu);

        }

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_editar:



                Intent intent = new Intent(this, AddPatientActivity.class);
                intent.putExtra("cedula",cedula);
                intent.putExtra("actualizar","actualizar");

                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume() {

        super.onResume();
        Bundle bundle = getIntent().getExtras();
        cedula = bundle.get("cedula").toString();
        final Bundle args = new Bundle();
        args.putLong("cedula",Long.parseLong(cedula));
        try {
            setupViewPager(viewPager,args);
            tabLayout.setupWithViewPager(viewPager);
        }catch (Exception e){

        }


    }


    private void setupViewPager(ViewPager viewPager, Bundle args) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(),args);
        adapter.addFragment(new PatientProfileFragment(),getResources().getString(R.string.tab_pager_ficha));
        adapter.addFragment(new NotesFragment(), getResources().getString(R.string.tab_pager_notas));
        if(tabCondition)
        {
            adapter.addFragment(new GraphicsExercises(),getResources().getString(R.string.tab_pager_terapia));

        }
        try {
            viewPager.setAdapter(adapter);
            viewPager.endFakeDrag(); // habilita el swipe horizontal
        }catch (Exception e){
            adapter.notifyDataSetChanged();
        }


    }





}
