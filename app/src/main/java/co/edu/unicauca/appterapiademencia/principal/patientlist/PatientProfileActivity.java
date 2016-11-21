package co.edu.unicauca.appterapiademencia.principal.patientlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import co.edu.unicauca.appterapiademencia.R;
import co.edu.unicauca.appterapiademencia.adapters.ViewPagerAdapter;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_profile_activity);
        if(getIntent().getExtras()!=null){
            Bundle bundle = getIntent().getExtras();
            cedula = bundle.get("cedula").toString();

        }

        final Bundle args = new Bundle();
        args.putLong("cedula",Long.parseLong(cedula));


        //inputprofileidentity= (TextView) findViewById(R.id.txt_profile);
        //inputprofileidentity.setText(cedula);
       toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager,args);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_edit,menu);
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
        adapter.addFragment(new PatientProfileFragment(), "Ficha");
        adapter.addFragment(new NotesFragment(), "Notas");
        adapter.addFragment(new GraphicsExercises(), "Estado de Consciencia ");
        try {
            viewPager.setAdapter(adapter);
            viewPager.endFakeDrag(); // para hacer swipe
        }catch (Exception e){
            adapter.notifyDataSetChanged();
        }


    }





}
