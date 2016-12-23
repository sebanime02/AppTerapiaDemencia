package co.edu.unicauca.appterapiademencia.principal.patientprofile;

import co.edu.unicauca.appterapiademencia.events.BlessedGraphEvent;

/**
 * Created by ENF on 22/12/2016.
 */

public interface StatisticsPresenter {
    void onCreate();
    void onResume();
    void onDestroy();
    void onEventMainThread(BlessedGraphEvent event);
    void getBlessedData(Long id,int mode);

}
