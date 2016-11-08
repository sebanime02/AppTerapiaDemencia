package co.edu.unicauca.appterapiademencia.principal.cognitiveexercises;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.edu.unicauca.appterapiademencia.R;

/**
 * Created by SEBAS on 07/11/2016.
 */

public class GraphicsExercises extends Fragment{

    public GraphicsExercises(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_profile_graphics,container,false);
        return rootView;

    }
}
