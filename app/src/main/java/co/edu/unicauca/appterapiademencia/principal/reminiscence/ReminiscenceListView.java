package co.edu.unicauca.appterapiademencia.principal.reminiscence;

import android.view.View;

import java.util.List;

import co.edu.unicauca.appterapiademencia.domain.Reminiscence;

/**
 * Created by SEBAS on 10/01/2017.
 */

public interface ReminiscenceListView {

    void addReminiscence();
    int showReminiscenceList(List<Reminiscence> reminiscenceList);
    void getReminiscenceList();
    void emtpyReminiscenceList();
    void refreshView();
    void showInstructions(View view);
}
