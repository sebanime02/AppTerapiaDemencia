package co.edu.unicauca.appterapiademencia.adapters;

import android.app.Activity;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import co.edu.unicauca.appterapiademencia.R;
import co.edu.unicauca.appterapiademencia.domain.Patient;
import co.edu.unicauca.appterapiademencia.principal.patientlist.PatientListFragment;
import co.edu.unicauca.appterapiademencia.util.CircleTransform;

/**
 * Created by ENF on 28/10/2016.
 */

public class PatientListAdapter extends RecyclerView.Adapter<PatientListAdapter.PatientViewHolder> implements Filterable {

    private List<Patient> patientList;
    private List<Patient> filteredList;
    private Activity activity;
    private CustomFilter mfilter;
    private LayoutInflater layoutInflater;
    private int imageSize;




    public PatientListAdapter(List<Patient> patientList,List<Patient> filteredList, Activity activity) {
        super();
        this.patientList = new ArrayList<Patient>(patientList);
        if (patientList != null){
            this.patientList = patientList;
        }
        this.filteredList = new ArrayList<Patient>();
        mfilter= new CustomFilter(PatientListAdapter.this);

        layoutInflater = activity.getLayoutInflater();
        this.activity = activity;
        this.imageSize= activity.getResources().getDimensionPixelSize(R.dimen.img_size_item_list);


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
        holder.patient_name.setText(nombre);
        holder.patient_age.setText("C.C " + id);

        return;
    }

    @Override
    public int getItemCount() {
        return patientList.size();
    }
    @Override
    public Filter getFilter()
    {
        return mfilter;
    }



    public class CustomFilter extends Filter{
        private PatientListAdapter mAdapter;
        private CustomFilter(PatientListAdapter mAdapter){
            super();
            this.mAdapter = mAdapter;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint)
        {
            filteredList.clear();
            final FilterResults results = new FilterResults();
            if (constraint.length() == 0)
            {
                filteredList.addAll(patientList);
            } else {
                final String filterPattern = constraint.toString().toLowerCase().trim();
                for (final Patient mWords : patientList) {
                    if (mWords.getName().toLowerCase().startsWith(filterPattern)) {
                        filteredList.add(mWords);
                    }
                }
            }
            System.out.println("Count Number " + filteredList.size());
            results.values = filteredList;
            for(int j=0;j<filteredList.size();j++){
                System.out.println("Count Number " + filteredList.get(j).getName().toString());

            }
            results.count = filteredList.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults)
        {

                new PatientListFragment().setResults(filteredList);

                this.mAdapter.notifyDataSetChanged();

            /*
            Object objetc = filterResults.
            PatientListAdapter acces = new PatientListAdapter(filterResults.values,activity);*/


        }

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
