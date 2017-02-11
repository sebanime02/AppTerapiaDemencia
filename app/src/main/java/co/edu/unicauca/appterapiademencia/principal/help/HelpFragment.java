package co.edu.unicauca.appterapiademencia.principal.help;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import co.edu.unicauca.appterapiademencia.R;

/**
 * Created by ENF on 25/10/2016.
 */

/**
 * Created by ENF on 25/10/2016.
 */

public class HelpFragment extends Fragment {
    private View rootView;
    private LinearLayout linearPreguntasFrecuentes,linearContacto;
    private LinearLayout desplegablePreguntasFrecuentes,desplegableContacto;
    private boolean preguntasFrecuentesSwitch = false;
    private boolean contactoSwitch = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_help, container, false);
        return rootView;


    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState)
    {
        linearContacto = (LinearLayout) view.findViewById(R.id.help_contact);
        linearPreguntasFrecuentes = (LinearLayout) view.findViewById(R.id.help_frequently_questions);
        desplegableContacto = (LinearLayout) view.findViewById(R.id.linearContacto);
        desplegablePreguntasFrecuentes  = (LinearLayout) view.findViewById(R.id.linearPreguntasFrecuentes);


        linearContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                if(!contactoSwitch)
                {
                    enableContacto(true);
                    enablePreguntasFrecuentes(false);
                    contactoSwitch =true;
                }
                else
                {
                    enableContacto(false);
                    enablePreguntasFrecuentes(false);
                    contactoSwitch =false;
                }


            }
        });

        linearPreguntasFrecuentes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if(!preguntasFrecuentesSwitch)
                {
                    enableContacto(false);
                    enablePreguntasFrecuentes(true);
                    preguntasFrecuentesSwitch =true;
                }
                else
                {
                    enableContacto(false);
                    enablePreguntasFrecuentes(false);
                    preguntasFrecuentesSwitch =false;
                }

            }
        });

    }

    public void enablePreguntasFrecuentes(boolean mode)
    {
        if(mode)
        {
            desplegablePreguntasFrecuentes.setVisibility(View.VISIBLE);

        }else
        {
            desplegablePreguntasFrecuentes.setVisibility(View.GONE);

        }
    }

    public void enableContacto(boolean mode)
    {
        if(mode)
        {
            desplegableContacto.setVisibility(View.VISIBLE);

        }else
        {
            desplegableContacto.setVisibility(View.GONE);

        }
    }



}
