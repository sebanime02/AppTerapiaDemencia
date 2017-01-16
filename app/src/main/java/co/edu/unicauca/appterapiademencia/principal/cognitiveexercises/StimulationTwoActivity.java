package co.edu.unicauca.appterapiademencia.principal.cognitiveexercises;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import co.edu.unicauca.appterapiademencia.R;
import co.edu.unicauca.appterapiademencia.adapters.ExerciseAdapter;
import co.edu.unicauca.appterapiademencia.domain.Exercise;
import co.edu.unicauca.appterapiademencia.domain.Patient;
import co.edu.unicauca.appterapiademencia.domain.Rutina;
import co.edu.unicauca.appterapiademencia.domain.dao.GreenDaoHelper;
import co.edu.unicauca.appterapiademencia.domain.dao.RutinaDao;
import co.edu.unicauca.appterapiademencia.principal.patientlist.PatientProfileActivity;

/**
 * Created by SEBAS on 26/12/2016.
 */

public class StimulationTwoActivity extends AppCompatActivity {

    private Intent intentBundle;
    private Long idpatient,idrutina;
    private FloatingActionButton btnAddExercise;
    private RecyclerView recycler;
    private ExerciseAdapter adapter;
    private RecyclerView.Adapter newadapter;
    private RecyclerView.LayoutManager LManager;
    private List<Exercise> exerciseList;
    private GreenDaoHelper helper;
    private Bundle bundle;
    private Toolbar toolbar;
    private ActionBar actionBar;
    private boolean tabRestricter = false;
    private GridLayoutManager lLayout;
    private BottomNavigationView bottomBar;

    public StimulationTwoActivity()
    {
        helper = GreenDaoHelper.getInstance();
        exerciseList = new ArrayList<>();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stimulation_2);
        try
        {
            bundle = getIntent().getExtras();
            idpatient = bundle.getLong("idpatient");
            idrutina = bundle.getLong("idrutina");

        }catch (Exception e)
        {
            Log.e("stimulation 2","idpatient "+idpatient);
            Log.e("stimulation 2","idrutina "+idrutina);

        }
        btnAddExercise = (FloatingActionButton) findViewById(R.id.add_exercise);
        recycler = (RecyclerView) findViewById(R.id.recicladorStimulation2);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        bottomBar = (BottomNavigationView) findViewById(R.id.bottom_navigation);



        bottomBar = (BottomNavigationView) findViewById(R.id.bottomBar);




        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Volver A La Ficha de Paciente");
        Log.e("stimulation 2"," Total numero ejercicios "+helper.getExerciseCount()+"");

        recycler.setHasFixedSize(true);
        lLayout = new GridLayoutManager(this, 4);
        //recycler.setLayoutManager(gaggeredGridLayoutManager);
        recycler.setLayoutManager(lLayout);


        try {
            exerciseList = getExercises(idrutina);

        }catch (Exception e){}
        adapter = new ExerciseAdapter(exerciseList, this);
         recycler.setAdapter(adapter);




        btnAddExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AddCognitiveExercise.class);
                intent.putExtra("idrutina",idrutina);
                intent.putExtra("idpatient",idpatient);
                startActivity(intent);
            }
        });



        bottomBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.action_add:
                        Intent intent = new Intent(getApplicationContext(),AddCognitiveExercise.class);
                        intent.putExtra("idrutina",idrutina);
                        intent.putExtra("idpatient",idpatient);
                        startActivity(intent);
                        break;
                    case R.id.action_finish:

                        Log.e("stimulation 2","Presiono el menu finish");
                        Rutina rutina = helper.getRutina(idrutina);
                        rutina.setState(0);
                        RutinaDao rutinaDao = helper.getRutinaDao();
                        rutinaDao.update(rutina);
                        goToProfile();
                        break;
                }

                // handle desired action here
                // One possibility of action is to replace the contents above the nav bar
                // return true if you want the item to be displayed as the selected item
                return true;
            }
        });






    }

    @Override
    protected void onResume() {
        super.onResume();
        /*
        exerciseList.clear();

        try
        {
            exerciseList = getExercises(idrutina);
            recycler.setHasFixedSize(true);
            LManager = new LinearLayoutManager(getApplicationContext());
            recycler.setLayoutManager(LManager);
            adapter = new ExerciseAdapter(exerciseList,this);
            recycler.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        } catch (Exception e) {
            adapter.notifyDataSetChanged();
        }
        */
    }

    public List<Exercise> getExercises(Long idrutina)
    {
       exerciseList.clear();
       return helper.getExercises(idrutina);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        /*
        MenuInflater menuinflater = getMenuInflater();
        menuinflater.inflate(R.menu.menu_finish, menu);
        */
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.menu_finalizar:
                /*
                Log.e("stimulation 2","Presiono el menu finish");
                Rutina rutina = helper.getRutina(idrutina);
                rutina.setState(0);
                RutinaDao rutinaDao = helper.getRutinaDao();
                rutinaDao.update(rutina);
                goToProfile();
                */
                return  true;
            case android.R.id.home:
                goToProfile();
                return  true;



            default:
                return super.onOptionsItemSelected(item);

        }





    }

    public void goToProfile()
    {
        Long cedula;
        Patient patient;
        Intent i2 = new Intent(getApplicationContext(), PatientProfileActivity.class);
        patient = helper.getPatientInformationUsingId(idpatient);
        cedula = patient.getIdentity();
        i2.putExtra("cedula",cedula);
        startActivity(i2);
        finish();
    }



    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            Long cedula;
            Patient patient;
            Intent i2 = new Intent(getApplicationContext(), PatientProfileActivity.class);
            patient = helper.getPatientInformationUsingId(idpatient);
            cedula = patient.getIdentity();
            i2.putExtra("cedula",cedula);
            startActivity(i2);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }



}
