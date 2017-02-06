package co.edu.unicauca.appterapiademencia.principal.cognitiveexercises;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import co.edu.unicauca.appterapiademencia.R;
import co.edu.unicauca.appterapiademencia.domain.Exercise;
import co.edu.unicauca.appterapiademencia.domain.Patient;
import co.edu.unicauca.appterapiademencia.domain.Reminiscence;
import co.edu.unicauca.appterapiademencia.domain.Rutina;
import co.edu.unicauca.appterapiademencia.domain.dao.ExerciseDao;
import co.edu.unicauca.appterapiademencia.domain.dao.GreenDaoHelper;
import co.edu.unicauca.appterapiademencia.domain.dao.ReminiscenceDao;

/**
 * Created by ENF on 13/01/2017.
 */

public class ReminiscenceExerciseActivity extends AppCompatActivity {

    private Bundle bundle;
    private Long idexercise,idreminiscence,idrutina,idpatient;
    private GreenDaoHelper helper;
    private ReminiscenceDao reminiscenceDao;
    private TextView tvTitle,tvDescription;
    private ImageView imgPhoto;
    private Toolbar toolbar;
    private ActionBar actionBar;
    private ExerciseDao exerciseDao;
    private String txtComentario;
    private Exercise exercise;
    private Rutina rutina;
    private Patient patient;
    private String titleWorkshop;
    private Reminiscence reminiscence;
    private MaterialDialog.Builder builder;
    private Menu menuFinish;

    private boolean dialogIndicator = false;
    private boolean navigateIndicator = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminiscence_exercise);

        helper = GreenDaoHelper.getInstance();
        reminiscenceDao = helper.getReminiscenceDao();
        exerciseDao = helper.getExerciseDao();
        bundle = getIntent().getExtras();

        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/arial.ttf");

        tvTitle = (TextView) findViewById(R.id.detail_reminiscence_title);
        tvDescription = (TextView) findViewById(R.id.detail_reminiscence_description);
        imgPhoto = (ImageView) findViewById(R.id.detail_reminiscence_photo);
        toolbar = (Toolbar) findViewById(R.id.toolbarreminiscence);


        if(bundle!=null)
        {
            try
            {


                try {
                    idexercise = bundle.getLong("idexercise");
                    titleWorkshop = bundle.getString("idtitulo");
                    reminiscence = helper.getReminiscence(titleWorkshop);
                    idreminiscence = reminiscence.getId();
                }catch (Exception e){e.printStackTrace();

                }

                exercise = helper.getExercise(idexercise);
                rutina = helper.getRutina(exercise.getRutinaId());
                idrutina = rutina.getId();
                patient = helper.getPatientInformationUsingId(rutina.getPatientId());
                idpatient = patient.getId();
            }catch (Exception e)
            {
                e.printStackTrace();
            }




        }

        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(getResources().getString(R.string.txt_back_exercise));

        tvDescription.setTypeface(tf);


        try
        {
            Reminiscence reminiscence = helper.getReminiscence(idreminiscence);
            tvTitle.setText(reminiscence.getTitle());
            tvDescription.setText(reminiscence.getDescription());


            imgPhoto.setBackground(Drawable.createFromPath(reminiscence.getPhotopath()));

            imgPhoto.setContentDescription("Imagen "+ reminiscence.getTitle()); //El content Description
            tvDescription.setContentDescription(reminiscence.getDescription());

        }catch (Exception e)
        {
            e.printStackTrace();
        }




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menuFinish = menu;
        MenuInflater menuinflater = getMenuInflater();
        menuinflater.inflate(R.menu.menu_finish, menu);
        MenuItem menuItem = menu.findItem(R.id.menu_finalizar);

        return true;

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_finalizar:
                Log.e("reminiscenceexercise", "Presiono el menu finish");

                exercise.setState(1);
                launchInput();
                return  true;
            case android.R.id.home:
                goToStimulation2();
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }
        public void launchInput()
    {
        builder = new MaterialDialog.Builder(this);

                builder.title(R.string.dialog_input_comentario_title)
                .content(R.string.dialog_input_comentario_content).positiveText(getResources().getString(R.string.dialog_input_positive)).negativeText(getResources().getString(R.string.dialog_input_neutral)).neutralText( getResources().getString(R.string.dialog_input_negative))
                .inputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE)
                .input(R.string.dialog_input_hint, R.string.dialog_input_pre, new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(MaterialDialog dialog, CharSequence input) {
                       txtComentario = input.toString();

                        Log.e("add observations"," txtcomentario "+txtComentario);
                        goToStimulation2();

                    }
                })

                        .onPositive(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, DialogAction which) {






            }}).onNegative(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                goToStimulation2();

            }
        }).onNeutral(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {


                    }
                }).show();

     /*
        if(dialogIndicator)
    {
        builder.show().dismiss();
    }


        if(navigateIndicator)
        {
            Log.e("navigateIndicator"," navigateindicator "+navigateIndicator);
            Intent intent = new Intent(ReminiscenceExerciseActivity.this,StimulationTwoActivity.class);
            intent.putExtra("idpatient",idpatient);
            intent.putExtra("idrutina",idrutina);
            startActivity(intent);
            finish();
        }
        */






    }






    public void goToStimulation2()

    {
        Log.e("add observations"," txtcomentario gotostimulation "+txtComentario);


        if(txtComentario!="")
        {
            exercise.setObservations(txtComentario);
            exerciseDao.update(exercise);

            Log.e("add observations"," Observation: "+exercise.getObservations());


        }

        Intent intent = new Intent(ReminiscenceExerciseActivity.this,StimulationTwoActivity.class);
        intent.putExtra("idpatient",idpatient);
        intent.putExtra("idrutina",idrutina);
        startActivity(intent);
        overridePendingTransition(R.anim.left_in, R.anim.left_out);
        finish();
    }


}


