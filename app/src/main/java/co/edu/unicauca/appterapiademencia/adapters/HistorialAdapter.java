package co.edu.unicauca.appterapiademencia.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import co.edu.unicauca.appterapiademencia.R;
import co.edu.unicauca.appterapiademencia.domain.Rutina;
import co.edu.unicauca.appterapiademencia.domain.dao.GreenDaoHelper;
import co.edu.unicauca.appterapiademencia.lib.GreenRobotEventBus;
import co.edu.unicauca.appterapiademencia.principal.cognitiveexercises.StimulationTwoActivity;

/**
 * Created by ENF on 14/01/2017.
 */

public class HistorialAdapter extends RecyclerView.Adapter<HistorialAdapter.HistorialViewHolder>{
    private List<Rutina> rutinaList;
    private Activity activity;
    private LayoutInflater layoutInflater;
    private GreenDaoHelper helper;
    private GreenRobotEventBus eventBus;
    private Long idpatient;


    public HistorialAdapter(List<Rutina> rutinaList, Activity activity,Long idpatient)
    {
        super();
        this.activity = activity;
        this.rutinaList = rutinaList;
        this.layoutInflater = activity.getLayoutInflater();
        this.helper = GreenDaoHelper.getInstance();
        this.eventBus = GreenRobotEventBus.getInstance();
        this.idpatient  = idpatient;
    }


    @Override
    public HistorialAdapter.HistorialViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cognitive_historic, parent, false);
        return new HistorialAdapter.HistorialViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HistorialAdapter.HistorialViewHolder holder, int position) {

        String txt_rutina_state,txt_rutina_starter,txt_rutina_count,txt_rutina_date,txt_rutina_mec,txt_rutina_mec_comentario;
        int rutina_state;

        rutina_state = rutinaList.get(position).getState();

        txt_rutina_state =rutina_state+"";

        txt_rutina_starter =  rutinaList.get(position).getStartername();
        txt_rutina_date = rutinaList.get(position).getDatestart();

        txt_rutina_mec = rutinaList.get(position).getMecinicial()+"";
        txt_rutina_mec_comentario =  rutinaList.get(position).getMecinicialcomentario();


        if(rutinaList.get(position).getMecinicial()!=null)
        {
            holder.tvMec.setText(txt_rutina_mec);
        }else
        {
            holder.mecCardView.setVisibility(View.GONE);
        }

        if(txt_rutina_mec_comentario!=null)
        {
            holder.tvComentarioMec.setText(txt_rutina_mec_comentario);
        }



        if(rutina_state==0)
        {
            holder.tvState.setText(activity.getResources().getString(R.string.txt_ultima_rutina_estado) +activity.getResources().getString(R.string.txt_rutina_finished));
            holder.tvState.setTextColor(activity.getResources().getColor(R.color.material_red));
        }
        else if(rutina_state==1)
        {
            holder.tvState.setText(activity.getResources().getString(R.string.txt_ultima_rutina_estado) + activity.getResources().getString(R.string.txt_rutina_pass_1));
            holder.tvState.setTextColor(activity.getResources().getColor(R.color.material_green));

        }
        else
        {
            holder.tvState.setText(activity.getResources().getString(R.string.txt_ultima_rutina_estado) + activity.getResources().getString(R.string.txt_rutina_pass_2));
            holder.tvState.setTextColor(activity.getResources().getColor(R.color.material_green));

        }





        //txt_rutina_state = activity.getResources().getString(R.string.txt_ultima_rutina_estado)+" "+txt_rutina_state;
        //holder.tvState.setText(txt_rutina_state);
        holder.tvStarter.setText( activity.getResources().getString(R.string.txt_ultima_rutina_autor)+" "+txt_rutina_starter);
        holder.tvDateStart.setText( activity.getResources().getString(R.string.txt_ultima_rutina_fecha)+" "+txt_rutina_date);




    }

    @Override
    public int getItemCount() {

        if(rutinaList==null)
        {
            return 0;
        }
        else
        {
            return rutinaList.size();
        }


    }
    @Override
    public long getItemId(int position) {

        return rutinaList.get(position).getId();
    }


    public long getItemState(int position) {

        return rutinaList.get(position).getState();
    }

    public class HistorialViewHolder extends RecyclerView.ViewHolder {
        TextView tvState,tvStarter,tvCount,tvDateStart,tvMec,tvComentarioMec;
        CardView mecCardView;
        Button btnDetail;


        public HistorialViewHolder(View itemView) {
            super(itemView);



            tvState = (TextView) itemView.findViewById(R.id.tv_rutina_state);
            tvStarter = (TextView) itemView.findViewById(R.id.tv_rutina_starter);
            tvCount = (TextView) itemView.findViewById(R.id.tv_rutina_count);
            tvDateStart = (TextView) itemView.findViewById(R.id.tv_rutina_date);
            tvMec = (TextView) itemView.findViewById(R.id.tv_rutina_mec);
            tvComentarioMec = (TextView) itemView.findViewById(R.id.tv_rutina_mec_comentario);
            mecCardView = (CardView) itemView.findViewById(R.id.mecCardview);
            btnDetail = (Button) itemView.findViewById(R.id.btn_detail_historic);


            btnDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("id del card",""+HistorialAdapter.this.getItemId(getPosition()));

                    Intent intent=new Intent(activity,StimulationTwoActivity.class);
                    intent.putExtra("idrutina",HistorialAdapter.this.getItemId(getPosition()));
                    intent.putExtra("idpatient",idpatient);

                    if(HistorialAdapter.this.getItemState(getPosition())==0)
                    {
                        intent.putExtra("rutinaterminada",true);

                    }



                    view.getContext().startActivity(intent);

                    activity.overridePendingTransition(R.anim.left_in, R.anim.left_out);
                }
            });


        }
    }
}
