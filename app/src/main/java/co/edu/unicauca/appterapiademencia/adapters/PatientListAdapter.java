package co.edu.unicauca.appterapiademencia.adapters;

import android.app.Activity;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import co.edu.unicauca.appterapiademencia.R;
import co.edu.unicauca.appterapiademencia.domain.Patient;
import co.edu.unicauca.appterapiademencia.util.CircleTransform;

/**
 * Created by ENF on 28/10/2016.
 */

public class PatientListAdapter extends RecyclerView.Adapter<PatientListAdapter.PatientViewHolder> {

    private List<Patient> patientList;
    private Activity activity;
    private LayoutInflater layoutInflater;
    private int imageSize;



    public PatientListAdapter(List<Patient> patientList, Activity activity) {
        super();
        this.patientList = new ArrayList<Patient>();
        if (patientList != null){
            this.patientList = patientList;
        }
        layoutInflater = activity.getLayoutInflater();
        this.activity = activity;
        this.imageSize= activity.getResources().getDimensionPixelSize(R.dimen.patient_image_size);


    }


    @Override
    public PatientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_patient,parent,false);

        return new PatientViewHolder(view);


    }

    @Override
    public void onBindViewHolder(PatientViewHolder holder, int position) {


        String foto = patientList.get(position).getPhotopath();
        String nombre = patientList.get(position).getName();
        int id = patientList.get(position).getIdentity();

        try {
            //Picasso.with(this.a).load(a.getDatabasePath(foto)).transform(new CircleTransform()).memoryPolicy(MemoryPolicy.NO_CACHE).into(myholder.foto_bov);
            Picasso.with(this.activity).load(activity.getDatabasePath(foto)).resize(imageSize, imageSize).transform(new CircleTransform()).into(holder.img_patient);
        }catch (Exception e){
            //Picasso.with(this.a).load(Uri.parse(foto)).transform(new CircleTransform()).memoryPolicy(MemoryPolicy.NO_CACHE).into(myholder.foto_bov);
            Picasso.with(this.activity).load(Uri.parse(foto)).resize(imageSize, imageSize).transform(new CircleTransform()).into(holder.img_patient);
        }
        holder.patient_name.setText("Nombre: " + id);
        holder.patient_age.setText("Id: " + id);

        return;
    }

    @Override
    public int getItemCount() {
        return patientList.size();
    }


    public class PatientViewHolder extends RecyclerView.ViewHolder{
        public ImageView img_patient;
        public TextView patient_name;
        public TextView patient_age;
        public PatientViewHolder(View itemView) {

            super(itemView);
            img_patient = (ImageView) itemView.findViewById(R.id.img_patient);
            patient_name= (TextView) itemView.findViewById(R.id.patient_name);
            patient_age = (TextView) itemView.findViewById(R.id.patient_age);



            /*
            try {
                Picasso.with(context).load(context.getDatabasePath(foto)).transform(new CircleTransform()).memoryPolicy(MemoryPolicy.NO_CACHE).into(img_patient);
            }catch (Exception e){
                Picasso.with(context).load(Uri.parse(foto)).transform(new CircleTransform()).memoryPolicy(MemoryPolicy.NO_CACHE).into(img_patient);
            }
            */



        }
    }
}
