package co.edu.unicauca.appterapiademencia.principal.tips;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.edu.unicauca.appterapiademencia.R;

/**
 * Created by ENF on 25/10/2016.
 */

public class TipsListFragment extends Fragment {
    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_tips, container, false);
        return rootView;


    }
}
