package co.edu.unicauca.appterapiademencia.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import co.edu.unicauca.appterapiademencia.R;
import co.edu.unicauca.appterapiademencia.domain.Reminiscence;
import co.edu.unicauca.appterapiademencia.domain.dao.GreenDaoHelper;
import co.edu.unicauca.appterapiademencia.lib.GreenRobotEventBus;

/**
 * Created by SEBAS on 10/01/2017.
 */

public class ReminiscenceAdapter extends RecyclerView.Adapter<ReminiscenceAdapter.ReminiscenceViewHolder> {

    private List<Reminiscence> reminiscencesList;
    private Activity activity;
    private LayoutInflater layoutInflater;
    private GreenDaoHelper helper;
    private GreenRobotEventBus eventBus;

    public ReminiscenceAdapter(List<Reminiscence> reminiscenceList,Activity activity)
    {
        super();
        this.activity = activity;
        this.reminiscencesList = reminiscenceList;
        this.layoutInflater = activity.getLayoutInflater();
        this.helper = GreenDaoHelper.getInstance();
        this.eventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public ReminiscenceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cognitive_exercise, parent, false);
        return new ReminiscenceAdapter.ReminiscenceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReminiscenceViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ReminiscenceViewHolder extends RecyclerView.ViewHolder {
        public ReminiscenceViewHolder(View itemView) {
            super(itemView);
        }
    }
}
