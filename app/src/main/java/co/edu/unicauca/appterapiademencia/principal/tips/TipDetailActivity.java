package co.edu.unicauca.appterapiademencia.principal.tips;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

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

    private TextView textView;
    private FloatingActionButton floatingActionButton;
    Bundle bundle;
    private GreenDaoHelper helper;
    private TipDao tipDao;
    private Long idtip;

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

        floatingActionButton=(FloatingActionButton)findViewById(R.id.btn_edit_tip);

        textView=(TextView)findViewById(R.id.datos_tip);

        Tip man = helper.getTip(idtip);

        String dato="";
        String Autor;
        String Notificaciones;

        if(man.getActive()){Notificaciones ="Activas";}
        else{Notificaciones="Desactivadas";}

        User user = helper.getUserInformationUsingId(man.getUserId());
        Autor = user.getCompleteName();


        dato = "\n\n" + "Titulo: " + man.getTitle() + "\n\n" + "Descripcion :" + man.getDescription()+
                "\n\n" + "Registrado por : " + Autor.toString()+ "\n\n" + "Notificaciones: " + Notificaciones;

        textView.setText(dato);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ir=new Intent(TipDetailActivity.this,AddTipActivity.class);
                ir.putExtra("idtip",idtip);
                startActivity(ir);
                overridePendingTransition(R.anim.left_in, R.anim.left_out);
            }
        });
    }
    public void onClick_delete_tip(View view) {
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

}
