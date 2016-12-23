package co.edu.unicauca.appterapiademencia.principal.patientprofile;

import co.edu.unicauca.appterapiademencia.events.BlessedGraphEvent;
import co.edu.unicauca.appterapiademencia.lib.EventBus;
import co.edu.unicauca.appterapiademencia.lib.GreenRobotEventBus;
import co.edu.unicauca.appterapiademencia.principal.PrincipalListInteractor;
import co.edu.unicauca.appterapiademencia.principal.PrincipalListInteractorImplementation;

/**
 * Created by ENF on 22/12/2016.
 */

public class StatisticsPresenterImplementation implements StatisticsPresenter {

    private StatisticsView statisticsView;
    private PrincipalListInteractor principalListInteractor;
    private EventBus eventBus;

    public StatisticsPresenterImplementation(StatisticsView statisticsView)
    {
        this.statisticsView = statisticsView;
        this.principalListInteractor = new PrincipalListInteractorImplementation();
        this.eventBus = GreenRobotEventBus.getInstance();
    }


    @Override
    public void onCreate() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onEventMainThread(BlessedGraphEvent event) {

    }

    @Override
    public void getBlessedData(Long id, int mode) {

    }


}

