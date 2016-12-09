package co.edu.unicauca.appterapiademencia.adapters;

import android.app.Activity;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import co.edu.unicauca.appterapiademencia.R;
import co.edu.unicauca.appterapiademencia.domain.Note;
import co.edu.unicauca.appterapiademencia.domain.User;
import co.edu.unicauca.appterapiademencia.domain.dao.GreenDaoHelper;

/**
 * Created by SEBAS on 12/11/2016.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    private List<Note> noteList;
    private LayoutInflater layoutInflater;
    private Activity activity;
    private List<User> userList;
    private GreenDaoHelper helper;
    private String description;

    private int imageSize;

    public NoteAdapter(List<Note> noteList, Activity activity){
        super();
        this.noteList = noteList;
        layoutInflater = activity.getLayoutInflater();
        this.activity = activity;
        this.helper = GreenDaoHelper.getInstance();
        this.imageSize = activity.getResources().getDimensionPixelSize(R.dimen.img_size_item_list);

    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);

        return new NoteAdapter.NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {

    //String description = noteList.get(position).getDescription();
    String fecha = noteList.get(position).getDate().toString();
    String hour = noteList.get(position).getHour().toString();
    //String noteType= noteList.get(position).getType();
    String ambito = noteList.get(position).getAmbito();
    String seleccion = noteList.get(position).getSelection();
    Boolean late = noteList.get(position).getLate();

    String userName;

        try{
            Long iduser = noteList.get(position).getUserId();
            userName = helper.getUserInformationUsingId(iduser).getCompleteName().toString();
        }
        catch (NullPointerException e){
            userName = noteList.get(position).getOwner();
        }
        /*
        if(color=="adverso"){
            DrawableCompat.setTint(holder.imgCalification.getDrawable(), ContextCompat.getColor(activity.getApplicationContext(),R.color.material_red));
            holder.imgCalification.setImageDrawable(activity.getResources().getDrawable(R.mipmap.ic_priority_high_black_24dp));
            holder.imgCalification.setBackgroundColor(activity.getResources().getColor(R.color.material_red));

        }
        else if(color=="centinela"){
            DrawableCompat.setTint(holder.imgCalification.getDrawable(), ContextCompat.getColor(activity.getApplicationContext(),R.color.red_dark));

            holder.imgCalification.setImageDrawable(activity.getResources().getDrawable(R.mipmap.ic_priority_high_black_24dp));
            holder.imgCalification.setBackgroundColor(activity.getResources().getColor(R.color.red_dark));

        }

        else if(color=="deterioro"){
            DrawableCompat.setTint(holder.imgCalification.getDrawable(), ContextCompat.getColor(activity.getApplicationContext(),R.color.material_amber));
            holder.imgCalification.setImageDrawable(activity.getResources().getDrawable(R.mipmap.ic_arrow_downward_black_24dp));
            holder.imgCalification.setBackgroundColor(activity.getResources().getColor(R.color.material_amber));

        }
        else if(color=="mejora"){
            DrawableCompat.setTint(holder.imgCalification.getDrawable(), ContextCompat.getColor(activity.getApplicationContext(),R.color.material_green));

            holder.imgCalification.setImageDrawable(activity.getResources().getDrawable(R.mipmap.ic_arrow_upward_black_24dp));
            holder.imgCalification.setBackgroundColor(activity.getResources().getColor(R.color.material_green));

        }
        else if(color=="neutral"){
            holder.imgCalification.setBackgroundColor(activity.getResources().getColor(R.color.gray));
        }
        */


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
            case "medication":

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
      /*

        try {

            Picasso.with(this.activity).load(activity.getDatabasePath(noteType)).resize(imageSize, imageSize).transform(new CircleTransform()).into(holder.imgNoteType);
        } catch (Exception e) {

            //Picasso.with(this.activity).load(Uri.parse(fotodefault)).resize(imageSize, imageSize).transform(new CircleTransform()).into(holder.imgNoteType);
        }
        */
        holder.imgNoteType.setImageURI(Uri.parse(ambito));

        holder.txtDescription.setText("Registrado por "+userName);
        holder.txtHour.setText(hour);
        holder.txtDate.setText(fecha);
        holder.txtOwner.setText(description);




        if(late==true){
            holder.txtLate.setVisibility(View.VISIBLE);

        }
        else{
            holder.txtLate.setVisibility(View.INVISIBLE);

        }
        /*
        if(color=="rutinario"){
            holder.cardView.setCardBackgroundColor(activity.getResources().getColor(R.color.white));
        }
        if(color=="mejoria"){
            holder.cardView.setCardBackgroundColor(activity.getResources().getColor(R.color.semaforo_verde));
        }
        else if(color=="incidente"){
            holder.cardView.setBackgroundColor(activity.getResources().getColor(R.color.gray));
        }
        else if(color=="centinela"){
            holder.cardView.setBackgroundColor(activity.getResources().getColor(R.color.semaforo_amarillo));
        }
        else if(color=="adverso"){
            holder.cardView.setBackgroundColor(activity.getResources().getColor(R.color.red_dark));
        }
        */



    }

    @Override
    public int getItemCount() {
        if(noteList == null){
            return 0;
        }
        else{
            return noteList.size();
        }

    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {
        ImageView imgNoteType;
        TextView txtDescription;
        TextView txtOwner;
        TextView txtDate;
        TextView txtHour;
        TextView txtLate;
        CardView cardView;
        ImageView imgCalification;

        public NoteViewHolder(View itemView) {
            super(itemView);
            imgNoteType = (ImageView) itemView.findViewById(R.id.img_note);
            txtDescription = (TextView) itemView.findViewById(R.id.note_description);
            txtOwner = (TextView) itemView.findViewById(R.id.note_type);//El titulo
            txtDate = (TextView) itemView.findViewById(R.id.note_date);
            txtHour = (TextView) itemView.findViewById(R.id.note_hour);
            txtLate = (TextView) itemView.findViewById(R.id.note_late);
            cardView = (CardView) itemView.findViewById(R.id.noteCardview);
            imgCalification = (ImageView) itemView.findViewById(R.id.note_calification);



        }
        /*
        public void onClick(View holder.itemView) {

            new MaterialDialog.Builder(activity.getApplicationContext()).title(this.getItemId(getPosition())).content(R.string.error_loguin).positiveText(R.string.dialog_succes_agree).icon(activity.getResources().getDrawable(R.drawable.sadface)).show();

            Toast.makeText(view.getContext(), "position = " + PatientListAdapter.this.getItemId(getPosition()), Toast.LENGTH_SHORT).show();
            Log.e("id del card",""+PatientListAdapter.this.getItemId(getPosition()));
            Intent intent=new Intent(activity,PatientProfileActivity.class);
            intent.putExtra("cedula",PatientListAdapter.this.getItemId(getPosition()));
            view.getContext().startActivity(intent);

            activity.overridePendingTransition(R.anim.left_in, R.anim.left_out);

            //patientListFragment.navigateToDetail((int)PatientListAdapter.this.getItemId(getPosition()));

        }
        */


    }

}
