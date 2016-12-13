package co.edu.unicauca.appterapiademencia.adapters;

import android.app.Activity;
import android.app.Notification;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import co.edu.unicauca.appterapiademencia.R;
import co.edu.unicauca.appterapiademencia.domain.Note;
import co.edu.unicauca.appterapiademencia.domain.dao.GreenDaoHelper;
import co.edu.unicauca.appterapiademencia.events.ItemNoteEvent;
import co.edu.unicauca.appterapiademencia.events.ItemNotificationEvent;
import co.edu.unicauca.appterapiademencia.lib.GreenRobotEventBus;
import de.greenrobot.event.EventBus;

/**
 * Created by ENF on 17/11/2016.
 */

public class NotificationsAdapter  extends RecyclerView.Adapter<NotificationsAdapter.NotificationsViewHolder> {

    public List<Note> notificationList;
    private LayoutInflater layoutInflater;
    private Activity activity;
    private GreenDaoHelper helper;
    private GreenRobotEventBus eventBus;

    public NotificationsAdapter(List<Note> notificationList,Activity activity)
    {
        super();
        this.notificationList = notificationList;
        layoutInflater = activity.getLayoutInflater();
        this.activity = activity;
        this.helper = GreenDaoHelper.getInstance();
        this.eventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public NotificationsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification, parent, false);

        return new NotificationsAdapter.NotificationsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NotificationsViewHolder holder, final int position) {

        String title = notificationList.get(position).getAmbito();

        String description = notificationList.get(position).getDescription();
        String date = notificationList.get(position).getDate();
        String hour = notificationList.get(position).getHour();
        Boolean tardia = notificationList.get(position).getLate();
        String ambito = notificationList.get(position).getAmbito();


        holder.txtTitle.setText(title);

        holder.txtDescription.setText(description);
        holder.txtDate.setText(date);
        holder.txtHour.setText(hour);


        if(tardia==true){
            holder.txtTardia.setVisibility(View.VISIBLE);

        }
        else{
            holder.txtTardia.setVisibility(View.INVISIBLE);

        }

        String userName;

        try{
            Long iduser = notificationList.get(position).getUserId();
            userName = helper.getUserInformationUsingId(iduser).getCompleteName().toString();
        }
        catch (NullPointerException e){
            userName = notificationList.get(position).getOwner();
        }
        holder.txtAutor.setText(userName);


        switch (ambito){
            case "movilidad":
                description="Movilidad";
                ambito= Uri.parse("android.resource://co.edu.unicauca.appterapiademencia/mipmap/movility72px").toString();
                break;
            case "alimentacion":
                description="Independencia Alimentaria";
                ambito=Uri.parse("android.resource://co.edu.unicauca.appterapiademencia/mipmap/eating72px").toString();

                break;
            case "caidas":
                description="Caida";
                ambito=Uri.parse("android.resource://co.edu.unicauca.appterapiademencia/mipmap/caida72px").toString();

                break;
            case "medicacion":

                description="Medicación";
                ambito=Uri.parse("android.resource://co.edu.unicauca.appterapiademencia/mipmap/medication72px").toString();

                break;
            case "higiene":
                description="Higiene y Aseo Personal";
                ambito=Uri.parse("android.resource://co.edu.unicauca.appterapiademencia/mipmap/higiene72px").toString();

                break;
            case "animo":
                description="Estado de Animo";
                ambito=Uri.parse("android.resource://co.edu.unicauca.appterapiademencia/mipmap/ic_insert_emoticon_black_48dp").toString();

                break;
            case "cambiocomportamiento":
                description="Comportamiento";
                ambito=Uri.parse("android.resource://co.edu.unicauca.appterapiademencia/mipmap/changebehavior72px").toString();

                break;
            case "vestimenta":
                description="Vestimenta y Asuntos personales";
                ambito=Uri.parse("android.resource://co.edu.unicauca.appterapiademencia/mipmap/clothes72px").toString();

                break;
            case "memoria":
                description="Memoria";
                ambito=Uri.parse("android.resource://co.edu.unicauca.appterapiademencia/mipmap/memory72px").toString();


                break;
            case "lenguaje":
                description="Lenguaje";
                ambito=Uri.parse("android.resource://co.edu.unicauca.appterapiademencia/mipmap/language72px").toString();

                break;



        }
        holder.imgAmbito.setImageURI(Uri.parse(ambito));

        holder.btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    de.greenrobot.event.EventBus.getDefault().removeAllStickyEvents();

                    de.greenrobot.event.EventBus.clearCaches();

                    ItemNotificationEvent itemNotificationEvent = new ItemNotificationEvent();
                    itemNotificationEvent.setIdnote(getItemId(position));
                    itemNotificationEvent.setMode(1);
                    eventBus.post(itemNotificationEvent);




                }catch (Exception e)
                {

                }
            }
        });

        holder.btnDecline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {



                    de.greenrobot.event.EventBus.getDefault().removeAllStickyEvents();

                    de.greenrobot.event.EventBus.clearCaches();

                    ItemNotificationEvent itemNotificationEvent = new ItemNotificationEvent();
                    itemNotificationEvent.setIdnote(getItemId(position));
                    itemNotificationEvent.setMode(0);
                    eventBus.post(itemNotificationEvent);


                }catch (Exception e)
                {

                }
            }
        });


    }

    @Override
    public int getItemCount() {
        if(notificationList == null){
            return 0;
        }
        else{
            return notificationList.size();
        }
    }

    @Override
    public long getItemId(int arg0 ) {
        return notificationList.get(arg0).getId();
    }

    public class NotificationsViewHolder extends RecyclerView.ViewHolder {


        TextView txtTitle;
        TextView txtAutor;
        TextView txtDescription;
        TextView txtDate;
        TextView txtHour;
        TextView txtTardia;
        ImageView imgAmbito;
        CardView cardView;
        ImageButton btnAccept;
        ImageButton btnDecline;




        public NotificationsViewHolder(View itemView)
        {
            super(itemView);
            txtTitle = (TextView) itemView.findViewById(R.id.note_type);
            txtAutor = (TextView) itemView.findViewById(R.id.note_description);
            txtDescription = (TextView) itemView.findViewById(R.id.note_content);
            txtDate = (TextView) itemView.findViewById(R.id.note_date);
            txtHour = (TextView) itemView.findViewById(R.id.note_hour);
            imgAmbito = (ImageView) itemView.findViewById(R.id.img_note);
            btnAccept = (ImageButton) itemView.findViewById(R.id.btn_accept_note);
            btnDecline = (ImageButton) itemView.findViewById(R.id.bnt_delete_note);
            //
            txtTardia = (TextView) itemView.findViewById(R.id.note_late);



        }
    }
}
