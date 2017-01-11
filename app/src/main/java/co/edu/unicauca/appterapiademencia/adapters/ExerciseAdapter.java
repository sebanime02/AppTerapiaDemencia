package co.edu.unicauca.appterapiademencia.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import co.edu.unicauca.appterapiademencia.R;
import co.edu.unicauca.appterapiademencia.domain.Exercise;
import co.edu.unicauca.appterapiademencia.domain.dao.GreenDaoHelper;
import co.edu.unicauca.appterapiademencia.lib.GreenRobotEventBus;

/**
 * Created by SEBAS on 28/12/2016.
 */

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder> {

    private List<Exercise> exerciseList;
    private Activity activity;
    private LayoutInflater layoutInflater;
    private GreenDaoHelper helper;
    private GreenRobotEventBus eventBus;

    public ExerciseAdapter(List<Exercise> exerciseList, Activity activity)
    {

        super();
        this.activity = activity;
        this.exerciseList = exerciseList;
        this.layoutInflater = activity.getLayoutInflater();
        this.helper = GreenDaoHelper.getInstance();
        this.eventBus = GreenRobotEventBus.getInstance();

    }

    @Override
    public ExerciseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cognitive_exercise, parent, false);
        return new ExerciseAdapter.ExerciseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ExerciseAdapter.ExerciseViewHolder holder, int position) {


        String taller = exerciseList.get(position).getWorkshop().toString();
        int nivel = exerciseList.get(position).getLevel();
        int estado = exerciseList.get(position).getState();
        int therapy = exerciseList.get(position).getTerapy();



        holder.txtType.setText(taller);

        if(therapy==2)
        {
            holder.txtLevel.setText("Nivel "+nivel);


        }


        if(estado==1)
        {
            holder.txtState.setTextColor(activity.getResources().getColor(R.color.red_dark));
            holder.txtState.setText(activity.getResources().getString(R.string.txt_state_finalizado));
        }
        else
        {
            holder.txtState.setTextColor(activity.getResources().getColor(R.color.material_green));
            holder.txtState.setText(activity.getResources().getString(R.string.txt_state_pendiente));
        }


    }

    @Override
    public int getItemCount() {

        if(exerciseList==null)
        {
            return 0;
        }
        else
        {
            return exerciseList.size();
        }


    }

    @Override
    public long getItemId(int position) {

        return exerciseList.get(position).getId();
    }

    public class ExerciseViewHolder extends RecyclerView.ViewHolder {
        TextView txtType,txtLevel,txtState;
        ImageView imgExercise;

        public ExerciseViewHolder(View itemView) {
            super(itemView);

            txtType = (TextView) itemView.findViewById(R.id.exercise_type);
            txtLevel = (TextView) itemView.findViewById(R.id.exercise_level);
            txtState = (TextView) itemView.findViewById(R.id.exercise_state);
            imgExercise = (ImageView) itemView.findViewById(R.id.img_exercise);

        }
    }
}