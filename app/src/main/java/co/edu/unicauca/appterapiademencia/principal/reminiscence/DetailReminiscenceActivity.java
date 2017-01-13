package co.edu.unicauca.appterapiademencia.principal.reminiscence;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import co.edu.unicauca.appterapiademencia.R;
import co.edu.unicauca.appterapiademencia.domain.Reminiscence;
import co.edu.unicauca.appterapiademencia.domain.dao.GreenDaoHelper;
import co.edu.unicauca.appterapiademencia.domain.dao.ReminiscenceDao;
import co.edu.unicauca.appterapiademencia.domain.dao.TipDao;
import co.edu.unicauca.appterapiademencia.principal.MainActivity;

/**
 * Created by ENF on 12/01/2017.
 */

public class DetailReminiscenceActivity extends AppCompatActivity {
    private TextView tvTitle,tvDescription,tvAutor,tvNotificaciones;
    private ImageView imgPhoto;
    private FloatingActionButton floatingActionButton;
    Bundle bundle;
    private GreenDaoHelper helper;
    private ReminiscenceDao reminiscenceDao;
    private TipDao tipDao;
    private Long idreminiscence;
    private Toolbar toolbar;
    private ActionBar actionBar;
    private ImageButton btnMakeLike;
    private TextView likeCount;
    private SharedPreferences preferences;
    private Boolean likeInteruptor = false;
    private String username;
    private Long iduser;
    private BottomNavigationView bottomNavigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_reminiscence);
        preferences = getSharedPreferences("appdata", Context.MODE_PRIVATE);
        helper = GreenDaoHelper.getInstance();
        reminiscenceDao = helper.getReminiscenceDao();

        bundle=getIntent().getExtras();

        idreminiscence = bundle.getLong("idreminiscence");

        //floatingActionButton=(FloatingActionButton)findViewById(R.id.btn_edit_tip);
        //tvAutor = (TextView) findViewById(R.id.detail_re);
        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/arial.ttf");

        tvTitle = (TextView) findViewById(R.id.detail_reminiscence_title);
        tvDescription = (TextView) findViewById(R.id.detail_reminiscence_description);
        imgPhoto = (ImageView) findViewById(R.id.detail_reminiscence_photo);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);


        //textView=(TextView)findViewById(R.id.datos_tip);

        toolbar = (Toolbar) findViewById(R.id.toolbarreminiscence);

        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(getResources().getString(R.string.txt_back_reminiscence));

        tvDescription.setTypeface(tf);

        try
        {
            Reminiscence reminiscence = helper.getReminiscence(idreminiscence);
            tvTitle.setText(reminiscence.getTitle());
            tvDescription.setText(reminiscence.getDescription());

            imgPhoto.setBackground(Drawable.createFromPath(reminiscence.getPhotopath()));

        }catch (Exception e)
        {
            e.printStackTrace();
        }


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

               switch (item.getItemId())
               {
                   case R.id.action_edit:
                       Intent ir=new Intent(DetailReminiscenceActivity.this,AddReminiscenceExercise.class);
                       ir.putExtra("idreminiscence",idreminiscence);
                       ir.putExtra("actualizar","actualizar");
                       startActivity(ir);
                       overridePendingTransition(R.anim.left_in, R.anim.left_out);
                       break;
                   case R.id.action_delete:

                       new MaterialDialog.Builder(DetailReminiscenceActivity.this).title("Desea Borrar El Ejercicio de Reminiscencias actual?").positiveText("SI").negativeText("NO").negativeColor(getResources().getColor(R.color.colorPrimaryDark)).icon(getResources().getDrawable(R.drawable.trash))
                               .onPositive(new MaterialDialog.SingleButtonCallback() {
                                   @Override
                                   public void onClick(@NonNull MaterialDialog dialog,DialogAction which) {
                                       final Reminiscence tipreminiscence = helper.getReminiscence(idreminiscence);
                                       reminiscenceDao.delete(tipreminiscence);
                                       Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                       startActivity(intent);
                                       finish();
                                   }


                               })

                               .onNegative(new MaterialDialog.SingleButtonCallback()
                               {
                                   @Override
                                   public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which)
                                   {
                                   }
                               }).show()
                       ;
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
    }


}


