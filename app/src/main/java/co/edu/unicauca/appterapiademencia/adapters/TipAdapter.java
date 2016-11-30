package co.edu.unicauca.appterapiademencia.adapters;

import android.app.Activity;
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
import co.edu.unicauca.appterapiademencia.domain.Tip;
import co.edu.unicauca.appterapiademencia.domain.User;
import co.edu.unicauca.appterapiademencia.domain.dao.GreenDaoHelper;

/**
 * Created by SEBAS on 29/11/2016.
 */

public class TipAdapter extends RecyclerView.Adapter<TipAdapter.TipViewHolder>  {
    private List<Tip> tipList;
    private LayoutInflater layoutInflater;
    private Activity activity;
    private List<User> userList;
    private GreenDaoHelper helper;
    private String description;

    public TipAdapter(List<Tip> tipList, Activity activity){
        super();
        this.tipList = tipList;
        layoutInflater = activity.getLayoutInflater();
        this.activity = activity;
        this.helper = GreenDaoHelper.getInstance();


    }
    @Override
    public TipViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tip, parent, false);

        return new TipAdapter.TipViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TipAdapter.TipViewHolder holder, int position) {

        String title = tipList.get(position).getTitle().toString();
        String description = tipList.get(position).getDescription().toString();
        Boolean noteState = tipList.get(position).getActive();

        holder.txtTitle.setText(title);
        holder.txtDescription.setText(description);


        if(noteState)
        {
            holder.imgState.setBackgroundColor(activity.getResources().getColor(R.color.material_green));
        }
        else
        {
            holder.imgState.setBackgroundColor(activity.getResources().getColor(R.color.material_red));
        }



    }

    @Override
    public int getItemCount() {
        if(tipList == null){
            return 0;
        }
        else{
            return tipList.size();
        }
    }

    public class TipViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle;
        TextView txtDescription;
        ImageView imgState;
        CardView cardView;



        public TipViewHolder(View itemView) {
            super(itemView);
            txtTitle = (TextView) itemView.findViewById(R.id.tip_title);
            txtDescription = (TextView) itemView.findViewById(R.id.tip_description);
            imgState = (ImageView) itemView.findViewById(R.id.tip_state);
            cardView = (CardView) itemView.findViewById(R.id.tipCardView);
        }
    }
}
