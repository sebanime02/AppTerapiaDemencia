package service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import co.edu.unicauca.appterapiademencia.R;
import co.edu.unicauca.appterapiademencia.domain.Tip;
import co.edu.unicauca.appterapiademencia.domain.dao.GreenDaoHelper;
import co.edu.unicauca.appterapiademencia.domain.dao.TipDao;
import co.edu.unicauca.appterapiademencia.principal.tips.TipDetailActivity;
import co.edu.unicauca.appterapiademencia.util.ManagerFechas;

/**
 * Created by ENF on 30/11/2016.
 */

public class NotificationService extends Service {

    private SharedPreferences prefs;
    private GreenDaoHelper helper;
    private TipDao tipDao;
    private  List<Tip> arrayList;
    private Boolean notificar;
    private Long idselected;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");

    }
    @Override
    public void onCreate()
    {
        this.helper = GreenDaoHelper.getInstance();
        this.tipDao = helper.getTipDao();
        this.arrayList = new ArrayList<Tip>();
        prefs = getSharedPreferences("appdata", Context.MODE_PRIVATE);

    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        //Toast.makeText(getApplicationContext(), "hola", Toast.LENGTH_SHORT).show();

        String hora=new ManagerFechas().horaActual();

        if(hora.equals("09:00")||hora.equals("13:00")||hora.equals("17:00")||hora.equals("15:00"))
        {
            if(prefs.getBoolean("notificaciones",true))
            {
                Log.e("Notifications","Detecta hora para notificaciones");
                selectNotification();

                SharedPreferences preferencias=getSharedPreferences("appdata",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=preferencias.edit();
                editor.putBoolean("notificaciones", false);
                editor.commit();
            }


        }
        else{
            SharedPreferences preferencias=getSharedPreferences("appdata",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor=preferencias.edit();
            editor.putBoolean("notificaciones", true);
            editor.commit();
        }

        return START_NOT_STICKY;
    }

    public void selectNotification()
    {
        Runnable runnable = new Runnable() {

            @Override
            public void run()
            {
                List<Tip> tipList;

                try {
                    tipList = helper.getTipsNotifications();
                    if(tipList.size()==0)
                    {
                        //NADA
                    }
                    if (tipList.size()==1)
                    {
                        publishNotification(tipList.get(0).getId());
                    }
                    if (tipList.size()>=2)
                    {
                        randomNumberMayor2(tipList);
                    }
                }catch (NullPointerException e)
                {
                    Log.e("Service","nulo al recibir gettipsnotificacion");
                }
            }
        };
        new Thread(runnable).start();




    }

    public void randomNumberMayor2(List<Tip> list)
    {
        Long[] ids = new Long[list.size()];
        for(int m=0; m < list.size();m++)
        {
            Log.e("selectnotification","Titulo "+list.get(m).getId());

            Log.e("selectnotification","Titulo "+list.get(m).getTitle());
            ids[m]= list.get(m).getId();
        }



        Long result;
        int origin =Integer.parseInt(ids[0]+"");
        int destination = Integer.parseInt(ids[list.size()-1]+"");

        Random ran = new Random();

        Log.e("Entra while","");
        Log.e("ids[0]",""+ids[0]);
        Log.e("ids[final]",""+ids[list.size()-1]);

        Long randomLong;
        Boolean iterator = true;

        while (iterator)
        {



            //Long randomLong = ids[0] + (Long)(random.nextLong()*(ids[tipList.size()-1]-ids[0]));
            //long randomLong =  ThreadLocalRandom.current().nextLong();

            int randomInt = ran.nextInt(destination+1) + origin;
            randomLong = Long.parseLong(randomInt+"");


            for(int n=0;n<ids.length;n++)
            {

                if(ids[n]==randomLong)
                {
                    Log.e("notificacion","id seleccionado"+randomLong);
                    //publishNotification(ids[n]);
                     idselected = ids[n];
                    notificar =true;
                    iterator=false;
                }
            }

        }
        if(notificar){
            publishNotification(idselected);
        }
    }




    public void publishNotification(Long id)
    {

        Tip tip;
        String idstring = id+"";
        tip = helper.getTip(id);
        NotificationCompat.Builder notificacion = new NotificationCompat.Builder(this);
        notificacion.setSmallIcon(R.mipmap.ic_launcher);
        notificacion.setTicker("Tip Para Cuidadores");
        notificacion.setWhen(System.currentTimeMillis());
        notificacion.setContentTitle(tip.getTitle());
        notificacion.setContentText(tip.getDescription());
        notificacion.setAutoCancel(true);

        Uri sonido = RingtoneManager.getDefaultUri(Notification.DEFAULT_SOUND);
        notificacion.setSound(sonido);

        Bitmap icono = BitmapFactory.decodeResource(getResources(), R.drawable.ic_action_content_report);
        notificacion.setLargeIcon(icono);
        //notificacion.setVibrate(new long[3]);
        try {
            PendingIntent mi_ventana_pendiente;
            Intent ir_notificacion = new Intent();
            Context micontexto = getApplicationContext();
            ir_notificacion.setClass(micontexto, TipDetailActivity.class);
            int aler = 1;
            ir_notificacion.putExtra("ids", aler);
            ir_notificacion.putExtra("idtip", id);

            mi_ventana_pendiente = PendingIntent.getActivity(micontexto, Integer.parseInt(idstring), ir_notificacion, Intent.FILL_IN_ACTION);
            notificacion.setContentIntent(mi_ventana_pendiente);
            Notification n = notificacion.build();
            NotificationManager nn = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            nn.notify(Integer.parseInt(idstring) + 1, n);
        }catch (NullPointerException e){}

    }


}
