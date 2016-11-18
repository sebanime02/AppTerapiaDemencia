package co.edu.unicauca.appterapiademencia.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ENF on 17/11/2016.
 */

public class NotificationsAdapter  extends RecyclerView.Adapter<NotificationsAdapter.NotificationsViewHolder> {
    @Override
    public NotificationsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(NotificationsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class NotificationsViewHolder extends RecyclerView.ViewHolder {
        public NotificationsViewHolder(View itemView) {
            super(itemView);
        }
    }
}
