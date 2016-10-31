package co.edu.unicauca.appterapiademencia.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import co.edu.unicauca.appterapiademencia.R;
import co.edu.unicauca.appterapiademencia.domain.Patient;

/**
 * Created by ENF on 28/10/2016.
 */

public class PatientListAdapter extends RecyclerView.Adapter<PatientListAdapter.PatientViewHolder> {

    private List<Patient> patientList;
    private Context context;



    public PatientListAdapter(List<Patient> patientList, Context context) {
        this.patientList = patientList;
        this.context = context;


    }


    @Override
    public PatientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_patient,parent,false);

        return new PatientViewHolder(view);


    }

    @Override
    public void onBindViewHolder(PatientViewHolder holder, int position) {
        /*
        URL url = new URL("/ModTerapia"+patientList.get(position).getPhoto());
        Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        holder.img_patient.setImageBitmap();
        */


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
