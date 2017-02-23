package co.edu.unicauca.appterapiademencia.adapters;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import co.edu.unicauca.appterapiademencia.R;
import co.edu.unicauca.appterapiademencia.domain.Patient;
import co.edu.unicauca.appterapiademencia.principal.patientlist.PatientListFragment;
import co.edu.unicauca.appterapiademencia.principal.patientlist.PatientProfileActivity;
import co.edu.unicauca.appterapiademencia.util.CircleTransform;

/**
 * Created by ENF on 28/10/2016.
 */

public class PatientListAdapter extends RecyclerView.Adapter<PatientListAdapter.PatientViewHolder> {

    public static final String fotodefault = Uri.parse("android.resource://co.edu.unicauca.appterapiademencia/"+R.drawable.emptyuser).toString();

    private List<Patient> patientList;
    private List<Patient> filteredList;
    private Activity activity;
    //private CustomFilter mfilter;
    private LayoutInflater layoutInflater;
    private int imageSize;
    private PatientListFragment patientListFragment;


    public PatientListAdapter(List<Patient> patientList, Activity activity) {
        super();
        this.patientList = patientList;
      /*  if (patientList != null){
            this.patientList = patientList;
        }
        */

        //mfilter= new CustomFilter(PatientListAdapter.this);

        layoutInflater = activity.getLayoutInflater();
        this.activity = activity;
        this.imageSize = activity.getResources().getDimensionPixelSize(R.dimen.img_size_item_list);


    }


    @Override
    public PatientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_patient, parent, false);

        return new PatientViewHolder(view);


    }

    @Override
    public void onBindViewHolder(PatientViewHolder holder, int position) {



        String foto = patientList.get(position).getPhotopath();
        String nombre = patientList.get(position).getName();
        long id = patientList.get(position).getIdentity();


        if(patientList.get(position).getSex().equals("masculino"))
        {
            holder.itemView.setBackgroundColor(activity.getResources().getColor(R.color.soft_blue));
        }else
        {
            holder.itemView.setBackgroundColor(activity.getResources().getColor(R.color.soft_pink));

        }

        Log.e("adapter information","Nombre"+nombre+" Foto :"+foto);


        try {

            Picasso.with(this.activity).load(activity.getDatabasePath(foto)).resize(imageSize, imageSize).transform(new CircleTransform()).into(holder.img_patient);
        } catch (Exception e) {

            Picasso.with(this.activity).load(Uri.parse(fotodefault)).resize(imageSize, imageSize).transform(new CircleTransform()).into(holder.img_patient);
        }
        holder.patient_name.setText(nombre);
        holder.patient_age.setText("C.C " + id);
        holder.itemView.setLongClickable(true);

    }


    @Override
    public int getItemCount() {
        return patientList.size();
    }


    @Override
    public long getItemId(int arg0 )
    {
        return patientList.get(arg0).getIdentity();
    }


    public class PatientViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener {
        public ImageView img_patient;
        public TextView patient_name;
        public TextView patient_age;

        public PatientViewHolder(View itemView) {

            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            img_patient = (ImageView) itemView.findViewById(R.id.img_patient);
            patient_name = (TextView) itemView.findViewById(R.id.patient_name);
            patient_age = (TextView) itemView.findViewById(R.id.patient_age);




        }

        @Override
        public void onClick(View view) {
            Log.d("Time count","Activity to activity empieza a contar");

            //Toast.makeText(view.getContext(), "position = " + PatientListAdapter.this.getItemId(getPosition()), Toast.LENGTH_SHORT).show();
            Log.e("id del card",""+PatientListAdapter.this.getItemId(getPosition()));
            Intent intent=new Intent(activity,PatientProfileActivity.class);
            intent.putExtra("cedula",PatientListAdapter.this.getItemId(getPosition()));
            view.getContext().startActivity(intent);

            activity.overridePendingTransition(R.anim.left_in, R.anim.left_out);

            //patientListFragment.navigateToDetail((int)PatientListAdapter.this.getItemId(getPosition()));

        }

        @Override
        public boolean onLongClick(View view) {
            return false;
        }
    }



}
