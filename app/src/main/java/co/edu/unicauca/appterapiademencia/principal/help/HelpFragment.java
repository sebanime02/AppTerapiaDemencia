package co.edu.unicauca.appterapiademencia.principal.help;

import android.support.v4.app.Fragment;

/**
 * Created by ENF on 25/10/2016.
 */


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.edu.unicauca.appterapiademencia.R;

/**
 * Created by ENF on 25/10/2016.
 */

public class HelpFragment extends Fragment {
    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_help, container, false);
        return rootView;


    }
}
