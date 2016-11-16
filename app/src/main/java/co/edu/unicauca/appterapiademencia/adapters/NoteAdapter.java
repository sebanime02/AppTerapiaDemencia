package co.edu.unicauca.appterapiademencia.adapters;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import co.edu.unicauca.appterapiademencia.R;
import co.edu.unicauca.appterapiademencia.domain.Note;
import co.edu.unicauca.appterapiademencia.domain.User;
import co.edu.unicauca.appterapiademencia.domain.dao.GreenDaoHelper;
import co.edu.unicauca.appterapiademencia.util.CircleTransform;

/**
 * Created by SEBAS on 12/11/2016.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    private List<Note> noteList;
    private LayoutInflater layoutInflater;
    private Activity activity;
    private List<User> userList;
    private GreenDaoHelper helper;

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

    String description = noteList.get(position).getDescription();
    String fecha = noteList.get(position).getDate().toString();
    String hour = noteList.get(position).getHour().toString();
    String noteType= noteList.get(position).getNoteType();
    String color = noteList.get(position).getColor();
    Boolean late = noteList.get(position).getLate();
    String userName;
        try{
            Long iduser = noteList.get(position).getUserId();
            userName = helper.getUserInformationUsingId(iduser).getCompleteName().toString();
        }
        catch (NullPointerException e){
            userName = noteList.get(position).getOwner();
        }



        try {

            Picasso.with(this.activity).load(activity.getDatabasePath(noteType)).resize(imageSize, imageSize).transform(new CircleTransform()).into(holder.imgNoteType);
        } catch (Exception e) {

            //Picasso.with(this.activity).load(Uri.parse(fotodefault)).resize(imageSize, imageSize).transform(new CircleTransform()).into(holder.imgNoteType);
        }

        holder.txtDescription.setText(description);
        holder.txtHour.setText(hour);
        holder.txtDate.setText(fecha);
        holder.txtOwner.setText(userName);

        if(late==true){
            holder.txtLate.setVisibility(View.VISIBLE);

        }
        else{
            holder.txtLate.setVisibility(View.GONE);

        }
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

        public NoteViewHolder(View itemView) {
            super(itemView);
            imgNoteType = (ImageView) itemView.findViewById(R.id.img_note);
            txtDescription = (TextView) itemView.findViewById(R.id.note_description);
            txtOwner = (TextView) itemView.findViewById(R.id.note_type);//El titulo
            txtDate = (TextView) itemView.findViewById(R.id.note_date);
            txtHour = (TextView) itemView.findViewById(R.id.note_hour);
            txtLate = (TextView) itemView.findViewById(R.id.note_late);
            cardView = (CardView) itemView.findViewById(R.id.noteCardview);

        }
    }
}
