package service;

import android.app.Notification;
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

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate()
    {
        this.helper = GreenDaoHelper.getInstance();
        this.tipDao = helper.getTipDao();
        this.arrayList = new ArrayList();

    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        //Toast.makeText(getApplicationContext(), "hola", Toast.LENGTH_SHORT).show();

        String hora=new ManagerFechas().horaActual();

        if(hora.equals("09:00")||hora.equals("13:00")||hora.equals("17:00"))
        {
             selectNotification();

        }

        return START_NOT_STICKY;
    }

    public void selectNotification()
    {
        arrayList.clear();
       List<Tip> tipList = helper.getTipsNotifications();
        for(int m=0; m<=tipList.size();m++)
        {
            arrayList.add(tipList.get(m));
        }
        Long[] ids = new Long[arrayList.size()+1];
        //int[] identero = new int[arrayList.size()+1];

        for(int j=0;j<=arrayList.size();j++)
        {
            ids[j]=arrayList.get(j).getId();
            //identero[j] =Integer.parseInt(arrayList.get(j).getId()+"");
        }

      // int resultadoaleatorio = Math.floor(Math.random()* (identero[arrayList.size()] - identero[0]) )+ identero[arrayList.size()];



        Random random = new Random();

        Long result;

       while (true)
       {
           Long randomLong = ids[0] + (Long)(random.nextLong()*(ids[arrayList.size()]-ids[0]));

           for(int n=0;n<=arrayList.size();n++)
           {
               Long id = arrayList.get(n).getId();

               if(id==randomLong)
               {
                   Log.e("notificacion","id seleccionado"+randomLong);
                   break;
               }
           }

       }



    }


    public void publishNotification(Long id)
    {
        Tip tip;
        tip = helper.getTip(id);
        NotificationCompat.Builder notificacion = new NotificationCompat.Builder(this);
        notificacion.setSmallIcon(R.mipmap.ic_launcher);
        notificacion.setTicker("Tip Para Cuidadores");
        notificacion.setWhen(System.currentTimeMillis());
        notificacion.setContentTitle(tip.getTitle());
        notificacion.setContentText(tip.getDescription());

        Uri sonido = RingtoneManager.getDefaultUri(Notification.DEFAULT_SOUND);
        notificacion.setSound(sonido);

        Bitmap icono = BitmapFactory.decodeResource(getResources(), R.drawable.ic_action_content_report);
        notificacion.setLargeIcon(icono);
        //notificacion.setVibrate(new long[3]);
        PendingIntent mi_ventana_pendiente;
        Intent ir_notificacion = new Intent();
        Context micontexto = getApplicationContext();
        ir_notificacion.setClass(micontexto, TipDetailActivity.class);
        int aler=1;
        ir_notificacion.putExtra("ids",aler);
        ir_notificacion.putExtra("idtip", id);

        /* PENDIENTE
        mi_ventana_pendiente = PendingIntent.getActivity(micontexto, Integer.parseInt(id), ir_notificacion, Intent.FILL_IN_ACTION);
        notificacion.setContentIntent(mi_ventana_pendiente);
        Notification n = notificacion.build();
        NotificationManager nn = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nn.notify(Integer.parseInt(id), n);
        */
    }


}
