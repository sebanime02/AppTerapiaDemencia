package co.edu.unicauca.appterapiademencia.principal.cognitiveexercises;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import co.edu.unicauca.appterapiademencia.R;
import co.edu.unicauca.appterapiademencia.adapters.HistorialAdapter;
import co.edu.unicauca.appterapiademencia.domain.Patient;
import co.edu.unicauca.appterapiademencia.domain.Rutina;
import co.edu.unicauca.appterapiademencia.domain.User;
import co.edu.unicauca.appterapiademencia.domain.dao.GreenDaoHelper;
import co.edu.unicauca.appterapiademencia.domain.dao.RutinaDao;
import co.edu.unicauca.appterapiademencia.util.ManagerFechas;

/**
 * Created by SEBAS on 07/11/2016.
 */

public class GraphicsExercises extends Fragment{

    private GreenDaoHelper daoHelper;
    private Long idpatient,idsistema;
    private TextView txtSate,txtStarter,txtDate,txtDemasRutinas;
    private Button btnNewRutina,btnLastRutina;
    private Rutina lastRutina;
    private LinearLayout containerLastRutina;
    private RutinaDao rutinaDao;
    private String starterName,rutinaDate;
    private int year,month,day;
    private java.util.Calendar calendar;
    private SharedPreferences preferences;
    private String username;
    private RecyclerView recycler;
    private HistorialAdapter adapter;
    private android.support.v7.widget.RecyclerView.Adapter newadapter;
    private RecyclerView.LayoutManager LManager;
    private List<Rutina> rutinaList;
    private LinearLayoutManager lLayout;



    public GraphicsExercises()
    {
        daoHelper = GreenDaoHelper.getInstance();
        rutinaDao = daoHelper.getRutinaDao();
        calendar = java.util.Calendar.getInstance();


    }


    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_profile_graphics,container,false);
        return rootView;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Bundle args = getArguments();
        idpatient=args.getLong("cedula");

        try {
            username = args.getString("username");
            Log.e("graphics","username "+username);

            User user =daoHelper.getUserInformation(username);
            starterName = user.getCompleteName();
            Log.e("graphics","startname traido del helper");

        }catch (Exception e)
        {
            starterName= getResources().getString(R.string.txt_cuidador_graphics);
            Log.e("graphics","startname cuidador por excepcion");

        }
        try {
            Patient patient = daoHelper.getPatientInformationUsingCedula(idpatient);
            idsistema = patient.getId();
            Log.e("Tab Terapia"," Id del paciente"+idsistema);

        }
        catch (Exception e)
        {
            Log.e("Tab Terapia","error al traer id del paciente");

        }

        containerLastRutina = (LinearLayout) view.findViewById(R.id.containerLastRutina);
        txtSate = (TextView) view.findViewById(R.id.tv_last_rutina_state);
        txtDate = (TextView) view.findViewById(R.id.tv_last_rutina_date);
        txtStarter = (TextView) view.findViewById(R.id.tv_last_rutina_starter);
        btnLastRutina = (Button) view.findViewById(R.id.btnLastRutina);
        btnNewRutina = (Button) view.findViewById(R.id.btnNewRutina);
        recycler = (RecyclerView) view.findViewById(R.id.recicladorHistoric);
        txtDemasRutinas = (TextView) view.findViewById(R.id.txt_demas_rutinas);


        try
        {
            lastRutina = daoHelper.getLastRutina(idsistema);

            Log.e("Tab Terapia","id de ultima rutina "+lastRutina.getId());

            containerLastRutina.setVisibility(View.VISIBLE);

            if(lastRutina.getState()==0)
            {
                btnLastRutina.setVisibility(View.INVISIBLE);
            }

            if(lastRutina.getState()==0)
            {
                txtSate.setText(getResources().getString(R.string.txt_ultima_rutina_estado) +getResources().getString(R.string.txt_rutina_finished));

            }
            else if(lastRutina.getState()==1)
            {
                txtSate.setText(getResources().getString(R.string.txt_ultima_rutina_estado) + getResources().getString(R.string.txt_rutina_pass_1));


            }
            else
            {
                txtSate.setText(getResources().getString(R.string.txt_ultima_rutina_estado) + getResources().getString(R.string.txt_rutina_pass_2));

            }


            txtStarter.setText(getResources().getString(R.string.txt_ultima_rutina_autor)+" "+lastRutina.getStartername());
            txtDate.setText(getResources().getString(R.string.txt_ultima_rutina_fecha)+" "+lastRutina.getDatestart());




        }catch (Exception e)
        {
            Log.e("Tab Terapia","error al traer la ultima rutina");

            containerLastRutina.setVisibility(View.GONE);


        }

        recycler.setHasFixedSize(true);
        lLayout = new LinearLayoutManager(getContext());
        //recycler.setLayoutManager(gaggeredGridLayoutManager);
        recycler.setLayoutManager(lLayout);


        try {
            rutinaList = daoHelper.getRutinaList(idsistema);

            if(rutinaList.size()==0)
            {
                txtDemasRutinas.setText(getResources().getString(R.string.txt_demas_rutinas_vacias));
            }

        }catch (Exception e){}
        adapter = new HistorialAdapter(rutinaList, getActivity(),idsistema);
        recycler.setAdapter(adapter);




        btnNewRutina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(preferences.getString("username",username)!=null) {
                    username = preferences.getString("username", "Nombre de Usuario");

                    User user =daoHelper.getUserInformation(username);
                    starterName = user.getCompleteName();
                }
                else
                {
                    starterName="Cuidador";
                }






                ManagerFechas managerFechas = new ManagerFechas();
                rutinaDate = managerFechas.fechaActualCalender();

                Intent intent = new Intent(getActivity().getBaseContext(),StimulationOneActivity.class);
                intent.putExtra("nuevo",true);
                intent.putExtra("idpatient",idsistema);
                Rutina rutina = new Rutina(null,idsistema,1,starterName,rutinaDate,null,null,null,null);
                rutinaDao.insert(rutina);
                Log.e("Tab Terapia","idrutina ingresado "+rutina.getId());
                intent.putExtra("idrutina",rutina.getId());
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.left_in, R.anim.left_out);


            }
        });

        btnLastRutina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Intent intent;

                    if(lastRutina.getState()==1)
                    {
                        intent = new Intent(getActivity().getBaseContext(),StimulationOneActivity.class);



                    }
                    else if(lastRutina.getState()==2)
                    {
                        intent = new Intent(getActivity().getBaseContext(),StimulationTwoActivity.class);

                    }else
                    {
                        intent = new Intent(getActivity().getBaseContext(),StimulationThreeActivity.class);


                    }
                    intent.putExtra("paso",true);
                    intent.putExtra("idpatient",idsistema);
                    intent.putExtra("idrutina",lastRutina.getId());
                    startActivity(intent);
                    getActivity().overridePendingTransition(R.anim.left_in, R.anim.left_out);




                }catch (Exception e){}


            }
        });





    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = getActivity().getSharedPreferences("appdata", Context.MODE_PRIVATE);

    }

    @Override
    public void onResume() {
        super.onResume();
        Bundle args = getArguments();
        idpatient=args.getLong("cedula");
        username = args.getString("username");

        try {
            Patient patient = daoHelper.getPatientInformationUsingCedula(idpatient);
            idsistema = patient.getId();
        }
        catch (Exception e){}

        try
        {
            lastRutina = daoHelper.getLastRutina(idsistema);
            containerLastRutina.setVisibility(View.VISIBLE);
            if(lastRutina.getState()==0)
            {
                txtSate.setText(getResources().getString(R.string.txt_state_0));
            }else if(lastRutina.getState()==1)
            {
                txtSate.setText(getResources().getString(R.string.txt_state_1));

            }else
            {
                txtSate.setText(getResources().getString(R.string.txt_state_2));

            }


            txtStarter.setText(getResources().getString(R.string.txt_ultima_rutina_autor)+" "+lastRutina.getStartername());
            txtDate.setText(getResources().getString(R.string.txt_ultima_rutina_fecha)+" "+lastRutina.getDatestart());




        }catch (Exception e)
        {

            containerLastRutina.setVisibility(View.GONE);


        }

        try {
            recycler.setHasFixedSize(true);
            lLayout = new LinearLayoutManager(getContext());
            //recycler.setLayoutManager(gaggeredGridLayoutManager);
            recycler.setLayoutManager(lLayout);


            try {
                rutinaList = daoHelper.getRutinaList(idsistema);
                if(rutinaList.size()==0)
                {

                    txtDemasRutinas.setText(getResources().getString(R.string.txt_demas_rutinas_vacias));
                }

            }catch (Exception e){}
            adapter = new HistorialAdapter(rutinaList, getActivity(),idsistema);
            recycler.setAdapter(adapter);

        }catch (Exception e)
        {

        }
        adapter.notifyDataSetChanged();


    }



}
