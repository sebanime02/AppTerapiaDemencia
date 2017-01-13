package co.edu.unicauca.appterapiademencia.principal.reminiscence;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import co.edu.unicauca.appterapiademencia.R;
import co.edu.unicauca.appterapiademencia.domain.Reminiscence;
import co.edu.unicauca.appterapiademencia.domain.dao.GreenDaoHelper;
import co.edu.unicauca.appterapiademencia.domain.dao.ReminiscenceDao;
import co.edu.unicauca.appterapiademencia.domain.dao.TipDao;

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



    }

    @Override
    protected void onResume() {
        super.onResume();
    }


}


