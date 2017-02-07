package co.edu.unicauca.appterapiademencia.adapters;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import co.edu.unicauca.appterapiademencia.R;
import co.edu.unicauca.appterapiademencia.domain.Reminiscence;
import co.edu.unicauca.appterapiademencia.domain.User;
import co.edu.unicauca.appterapiademencia.domain.dao.GreenDaoHelper;
import co.edu.unicauca.appterapiademencia.lib.GreenRobotEventBus;
import co.edu.unicauca.appterapiademencia.principal.reminiscence.DetailReminiscenceActivity;

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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reminiscence, parent, false);
        return new ReminiscenceAdapter.ReminiscenceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReminiscenceViewHolder holder, int position) {


        String title = reminiscencesList.get(position).getTitle();
        String imgpath = reminiscencesList.get(position).getPhotopath();
        String author = reminiscencesList.get(position).getAuthor();


        holder.txtTitle.setText(title);
        if(author!=null)
        {
            User user;
            String autorfinal ="";
            try
            {
                user = helper.getUserInformation(author);
                autorfinal = user.getCompleteName();

            }catch (Exception e)
            {
                e.printStackTrace();
                autorfinal = "";
            }

            holder.txtAuthor.setText(activity.getResources().getString(R.string.item_list_reminiscence_autor)+autorfinal);

        }

        holder.img.setBackground(Drawable.createFromPath(imgpath));


    }

    @Override
    public int getItemCount() {
        if(reminiscencesList == null){
            return 0;
        }
        else{
            return reminiscencesList.size();
        }
    }
    @Override
    public long getItemId(int arg0 ) {

        return reminiscencesList.get(arg0).getId();
    }

    public class ReminiscenceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txtTitle;
        TextView txtAuthor;
        ImageView img;

        public ReminiscenceViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            txtTitle = (TextView) itemView.findViewById(R.id.reminiscence_title);
            img = (ImageView) itemView.findViewById(R.id.reminiscence_image);
            txtAuthor = (TextView) itemView.findViewById(R.id.reminiscence_author);

        }

        @Override
        public void onClick(View view) {

                //Toast.makeText(view.getContext(), "position = " + PatientListAdapter.this.getItemId(getPosition()), Toast.LENGTH_SHORT).show();
                Log.e("id del card",""+ReminiscenceAdapter.this.getItemId(getPosition()));
                Intent intent=new Intent(activity,DetailReminiscenceActivity.class);
                intent.putExtra("idreminiscence",ReminiscenceAdapter.this.getItemId(getPosition()));
                view.getContext().startActivity(intent);

                activity.overridePendingTransition(R.anim.left_in, R.anim.left_out);
        }
    }
}
