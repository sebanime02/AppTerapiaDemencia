package co.edu.unicauca.appterapiademencia.principal.cognitiveexercises;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import co.edu.unicauca.appterapiademencia.R;
import co.edu.unicauca.appterapiademencia.adapters.ExerciseAdapter;
import co.edu.unicauca.appterapiademencia.adapters.PatientListAdapter;
import co.edu.unicauca.appterapiademencia.domain.Exercise;
import co.edu.unicauca.appterapiademencia.domain.Rutina;
import co.edu.unicauca.appterapiademencia.domain.dao.GreenDaoHelper;

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
            idpatient = intentBundle.getExtras().getLong("idpatient");
            idrutina = intentBundle.getExtras().getLong("idrutina");
        }catch (Exception e)
        {
        }
        btnAddExercise = (FloatingActionButton) findViewById(R.id.add_exercise);
        recycler = (RecyclerView) findViewById(R.id.reciclador);

        recycler.setHasFixedSize(true);
        LManager = new LinearLayoutManager(getApplicationContext());
        recycler.setLayoutManager(LManager);

        try
        {   exerciseList = getExercises(idrutina);
            adapter = new ExerciseAdapter(exerciseList, this);
            recycler.setAdapter(adapter);



        }catch (Exception e){}


        btnAddExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AddCognitiveExercise.class);
                intent.putExtra("idrutina",idrutina);
                intent.putExtra("idpatient",idpatient);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        exerciseList.clear();
        exerciseList = getExercises(idrutina);

        try {
            recycler.setHasFixedSize(true);
            LManager = new LinearLayoutManager(getApplicationContext());
            recycler.setLayoutManager(LManager);
            adapter = new ExerciseAdapter(exerciseList,this);
            recycler.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            //callListenerText();
        } catch (Exception e) {
            adapter.notifyDataSetChanged();
        }
    }

    public List<Exercise> getExercises(Long idrutina)
    {
       exerciseList.clear();
       return helper.getExercises(idrutina);
    }
}
