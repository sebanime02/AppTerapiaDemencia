package co.edu.unicauca.appterapiademencia.principal.userprofile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.edu.unicauca.appterapiademencia.R;

/**
 * Created by ENF on 25/10/2016.
 */

public class UserProfileFragment extends Fragment {
    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_userprofile, container, false);
        return rootView;


    }
}
