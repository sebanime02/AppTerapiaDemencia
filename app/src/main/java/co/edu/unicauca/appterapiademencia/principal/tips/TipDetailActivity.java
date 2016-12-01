package co.edu.unicauca.appterapiademencia.principal.tips;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import co.edu.unicauca.appterapiademencia.R;
import co.edu.unicauca.appterapiademencia.domain.Tip;
import co.edu.unicauca.appterapiademencia.domain.User;
import co.edu.unicauca.appterapiademencia.domain.dao.GreenDaoHelper;
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
    private TipDao tipDao;
    private Long idtip;
    private Toolbar toolbar;
    private ActionBar actionBar;

    public TipDetailActivity()
    {
        this.helper = GreenDaoHelper.getInstance();
        this.tipDao = helper.getTipDao();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tip);
        bundle=getIntent().getExtras();

        idtip = bundle.getLong("idtip");

        floatingActionButton=(FloatingActionButton)findViewById(R.id.btn_edit_tip);
        tvAutor = (TextView) findViewById(R.id.detail_tip_autor);
        tvDescription = (TextView) findViewById(R.id.detail_tip_description);
        tvNotificaciones = (TextView) findViewById(R.id.detail_tip_notifications);

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
    }
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit,menu);
        getMenuInflater().inflate(R.menu.menu_delete,menu);
        getMenuInflater().inflate(R.menu.menu_favorite,menu);
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
                if(helper.getTip(idtip).getFavorite())
                {
                    helper.getTip(idtip).setFavorite(false);
                }
                else
                {
                    helper.getTip(idtip).setFavorite(true);

                }

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
