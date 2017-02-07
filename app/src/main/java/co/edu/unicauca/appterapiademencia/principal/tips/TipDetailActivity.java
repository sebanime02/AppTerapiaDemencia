package co.edu.unicauca.appterapiademencia.principal.tips;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import co.edu.unicauca.appterapiademencia.R;
import co.edu.unicauca.appterapiademencia.domain.PreferenceTip;
import co.edu.unicauca.appterapiademencia.domain.Tip;
import co.edu.unicauca.appterapiademencia.domain.User;
import co.edu.unicauca.appterapiademencia.domain.dao.GreenDaoHelper;
import co.edu.unicauca.appterapiademencia.domain.dao.PreferenceTipDao;
import co.edu.unicauca.appterapiademencia.domain.dao.TipDao;
import co.edu.unicauca.appterapiademencia.principal.MainActivity;

/**
 * Created by SEBAS on 29/11/2016.
 */

public class TipDetailActivity extends ActionBarActivity {

    private TextView textView,tvTitle,tvDescription,tvAutor,tvNotificaciones;
    private FloatingActionButton floatingActionButton;
    Bundle bundle;
    private GreenDaoHelper helper;
    private PreferenceTipDao preferenceTipDao;
    private TipDao tipDao;
    private Long idtip;
    private Toolbar toolbar;
    private ActionBar actionBar;
    private ImageButton btnMakeLike;
    private TextView likeCount;
    private SharedPreferences preferences;
    private Boolean likeInteruptor = false;
    private String username;
    private Long iduser;
    private Menu menufavorite;

    public TipDetailActivity()
    {
        this.helper = GreenDaoHelper.getInstance();
        this.tipDao = helper.getTipDao();
        this.preferenceTipDao = helper.getTipPreferenceDao();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tip);
        preferences = getSharedPreferences("appdata", Context.MODE_PRIVATE);
        username = preferences.getString("username","Nombre de Usuario");
        bundle=getIntent().getExtras();

        idtip = bundle.getLong("idtip");

        floatingActionButton=(FloatingActionButton)findViewById(R.id.btn_edit_tip);
        tvAutor = (TextView) findViewById(R.id.detail_tip_autor);
        tvDescription = (TextView) findViewById(R.id.detail_tip_description);
        tvNotificaciones = (TextView) findViewById(R.id.detail_tip_notifications);
        btnMakeLike = (ImageButton) findViewById(R.id.detail_make_like);
        likeCount = (TextView) findViewById(R.id.detail_like_count);

        //textView=(TextView)findViewById(R.id.datos_tip);

        toolbar = (Toolbar) findViewById(R.id.toolbartip);

        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);

        Tip man = helper.getTip(idtip);

        String dato="";
        String Autor;
        String Notificaciones;


        try {
            if(man.getActive()){Notificaciones ="Activas";}
            else{Notificaciones="Desactivadas";}
        }catch (NullPointerException e)
        {
            Notificaciones="Desactivadas";
        }


        String foto;

        User user = helper.getUserInformationUsingId(man.getUserId());
        Autor = user.getCompleteName();



        tvDescription.setText(man.getDescription());
        tvAutor.setText("Publicado por: "+Autor.toString());

        actionBar.setTitle(man.getTitle());

        int likesCount;
        likesCount = getLikesCount();
        Log.e("tipdetail","likesCount"+likesCount);

        likeCount.setText(String.valueOf(likesCount));


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ir=new Intent(TipDetailActivity.this,AddTipActivity.class);
                ir.putExtra("idtip",idtip);
                ir.putExtra("actualizar","actualizar");
                startActivity(ir);
                overridePendingTransition(R.anim.left_in, R.anim.left_out);
            }
        });

        btnMakeLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("tipdetail","Presiono el boton");
                if(!likeInteruptor) {
                    try {
                        Log.e("tipdetail","interruptor en false");

                        helper.addLike(idtip);
                        reloadView();
                        likeInteruptor = true;
                        btnMakeLike.setEnabled(false);
                        Toast.makeText(view.getContext(),getResources().getString(R.string.toast_like),Toast.LENGTH_LONG).show();

                    } catch (Exception e) {
                        Log.e("tipdetail","error agregando like");

                    }
                }
            }


        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        likeCount.setText(String.valueOf( getLikesCount()));
    }

    /*
    public void onClick_delete_tip(View view)
    {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Aceptar")
                .setMessage("Desea Borrar Este Registro De Forma Permanente?").setNegativeButton(android.R.string.cancel, null)//sin listener
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {//un listener que al pulsar, cierre la aplicacion
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        final Tip tipdelete = helper.getTip(idtip);
                        tipDao.delete(tipdelete);
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }).show();
    }
    */



    public void reloadView()
    {
        likeCount.setText(String.valueOf( getLikesCount()));
    }

    public int getLikesCount()
    {
        int likescount;
        try {
            likescount = helper.getLikesCount(idtip);

        }catch (Exception e)
        {
            Log.e("tipdetail","error trayendo la cuenta de likes");
            likescount= 0;
        }
        return likescount;
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menufavorite = menu;
        //SharedPreferences loginpreference = getSharedPreferences("appdata", Context.MODE_PRIVATE);

        if(preferences.getBoolean("supervisor",true))
        {
            getMenuInflater().inflate(R.menu.menu_edit,menu);
            getMenuInflater().inflate(R.menu.menu_delete,menu);
            getMenuInflater().inflate(R.menu.menu_favorite,menu);

                idtip = bundle.getLong("idtip");
                username = preferences.getString("username","Nombre de Usuario");
                iduser = helper.getUserInformation(username).getId();



            try
            {
                PreferenceTip preferenceTip =  helper.getPreferenceTip(idtip,iduser);





                if(preferenceTip.getFavorite())
                {
                    menu.findItem(R.id.menu_favorito).setIcon(getResources().getDrawable(R.mipmap.ic_star_rate_black_18dp));
                }else
                {
                    menu.findItem(R.id.menu_favorito).setIcon(getResources().getDrawable(R.mipmap.ic_star_border_white_24dp));

                }
            }catch (Exception e)
            {
                e.printStackTrace();
            }



        }


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_editar:

                Intent ir=new Intent(TipDetailActivity.this,AddTipActivity.class);
                ir.putExtra("idtip",idtip);
                ir.putExtra("actualizar","actualizar");
                startActivity(ir);
                overridePendingTransition(R.anim.left_in, R.anim.left_out);
                return true;
            case R.id.menu_borrar:

                new MaterialDialog.Builder(this).title("Desea Borrar El TIP actual?").positiveText("SI").negativeText("NO").negativeColor(getResources().getColor(R.color.colorPrimaryDark)).icon(getResources().getDrawable(R.drawable.trash))
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog,DialogAction which) {
                                final Tip tipdelete = helper.getTip(idtip);
                                tipDao.delete(tipdelete);
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
                return true;

            case R.id.menu_favorito:
                    MenuItem menuItem = menufavorite.findItem(R.id.menu_favorito);

                    try
                    {
                        idtip = bundle.getLong("idtip");

                        iduser = helper.getUserInformation(username).getId();
                    }catch (Exception e)
                    {
                        Log.e("tipdetail","Error al traer iduser y el idtip");

                    }

                    Log.e("tipdetail","iduser "+iduser);
                    Log.e("tipdetail","idtip "+idtip);

                    try {
                        PreferenceTip preferenceTip =  helper.getPreferenceTip(idtip,iduser);

                        if(preferenceTip.getFavorite())
                        {
                            Log.e("tipdetail","el preference tip esta en true");


                            //MenuItem menuItem = menufavorite.findItem(R.id.menu_favorito);
                            menuItem.setIcon(getResources().getDrawable(R.mipmap.ic_star_border_white_24dp));


                            new MaterialDialog.Builder(this).title("Ya no es Favorito").positiveText(R.string.dialog_sucess_agree2).icon(getResources().getDrawable(R.drawable.ic_action_toggle_star)).show();

                            //tipfavorite = helper.getTip(idtip);
                            preferenceTip.setFavorite(false);
                            preferenceTipDao.update(preferenceTip);

                        }
                        else
                        {
                            Log.e("tipdetail","el preference tip esta en false");


                            menuItem.setIcon(getResources().getDrawable(R.mipmap.ic_star_rate_black_18dp));

                            //tipfavorite = helper.getTip(idtip);
                            preferenceTip.setFavorite(true);
                            preferenceTipDao.update(preferenceTip);

                            new MaterialDialog.Builder(this).title("Tip Agregado a Favoritos!").positiveText(R.string.dialog_sucess_agree2).icon(getResources().getDrawable(R.drawable.ic_action_toggle_star)).show();

                        }
                        return true;
                    }catch (Exception e)
                    {
                        Log.e("tipdetail","Error al traer el preference tip");


                    }






            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
