package co.edu.unicauca.appterapiademencia.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import co.edu.unicauca.appterapiademencia.R;
import co.edu.unicauca.appterapiademencia.domain.PreferenceTip;
import co.edu.unicauca.appterapiademencia.domain.Tip;
import co.edu.unicauca.appterapiademencia.domain.User;
import co.edu.unicauca.appterapiademencia.domain.dao.GreenDaoHelper;
import co.edu.unicauca.appterapiademencia.principal.tips.TipDetailActivity;

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
    private SharedPreferences preferences;
    private String username;
    private Long iduser;
    private Long idtip;


    public TipAdapter(List<Tip> tipList, Activity activity){
        super();
        this.tipList = tipList;
        layoutInflater = activity.getLayoutInflater();
        this.activity = activity;
        this.helper = GreenDaoHelper.getInstance();
        preferences = activity.getSharedPreferences("appdata", Context.MODE_PRIVATE);


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

        Boolean noteState = tipList.get(position).getActive().booleanValue();
        int likesCount = tipList.get(position).getLikes();

        try {
            User user;

            username = preferences.getString("username","Nombre de Usuario");


            user  = helper.getUserInformation(username);

            iduser = user.getId();
            idtip = getItemId(position);

            PreferenceTip preferenceTip = helper.getPreferenceTip(idtip,iduser);
            Boolean favoriteState;

            favoriteState = preferenceTip.getFavorite();

            Log.e("favoritestate",""+favoriteState);

            if (favoriteState==true)
            {
                holder.imgFavorite.setVisibility(View.VISIBLE);
            }
            else
            {
                holder.imgFavorite.setVisibility(View.GONE);
            }
            holder.txtDescription.setText("Autor: "+user.getCompleteName());
        }catch (Exception e){
            holder.txtDescription.setText("Autor: Cuidador");
        }



        holder.txtTitle.setText(title);


        if(likesCount==0)
        {
            holder.txtLikesCount.setText(" 0 ");
        }
        else
        {
            holder.txtLikesCount.setText("+"+likesCount);
        }


        //try
        //{

            if (noteState==true)
            {
                holder.imgState.setBackgroundColor(activity.getResources().getColor(R.color.material_green));
            } else
            {
                holder.imgState.setBackgroundColor(activity.getResources().getColor(R.color.material_red));
            }
       // }catch (Exception e)
        //{
        //    holder.imgState.setBackgroundColor(activity.getResources().getColor(R.color.material_red));
       // }
       // try {








       // }catch (Exception e)
       // {
          //  holder.imgFavorite.setVisibility(View.GONE);

        //}





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

    @Override
    public long getItemId(int arg0 ) {

        return tipList.get(arg0).getId();
    }

    public class TipViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {
        TextView txtTitle;
        TextView txtDescription;
        ImageView imgState;
        CardView cardView;
        ImageView imgFavorite;
        TextView txtLikesCount;



        public TipViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            txtTitle = (TextView) itemView.findViewById(R.id.tip_title);
            txtDescription = (TextView) itemView.findViewById(R.id.tip_description);
            imgState = (ImageView) itemView.findViewById(R.id.tip_state);
            cardView = (CardView) itemView.findViewById(R.id.tipCardView);
            imgFavorite = (ImageView) itemView.findViewById(R.id.tip_favorite);
            txtLikesCount = (TextView) itemView.findViewById(R.id.tip_likes);

        }

        @Override
        public void onClick(View view) {
            Log.e("id del card",""+TipAdapter.this.getItemId(getPosition()));

            Intent intent=new Intent(activity,TipDetailActivity.class);
            intent.putExtra("idtip",TipAdapter.this.getItemId(getPosition()));

            view.getContext().startActivity(intent);

            activity.overridePendingTransition(R.anim.left_in, R.anim.left_out);

            //patientListFragment.navigateToDetail((int)PatientListAdapter.this.getItemId(getPosition()));

        }
    }
}
