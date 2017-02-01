package co.edu.unicauca.appterapiademencia.principal.tips;

import co.edu.unicauca.appterapiademencia.lib.EventBus;
import co.edu.unicauca.appterapiademencia.lib.GreenRobotEventBus;
import co.edu.unicauca.appterapiademencia.principal.PrincipalListInteractor;
import co.edu.unicauca.appterapiademencia.principal.PrincipalListInteractorImplementation;

/**
 * Created by SEBAS on 29/11/2016.
 */

public class TipListPresenterImplementation implements TipsListPresenter {

    private TipsListView tipsListView;
    private PrincipalListInteractor principalListInteractor;
    private EventBus eventBus;


    public TipListPresenterImplementation(TipsListView tipsListView)
    {
        this.tipsListView = tipsListView;
        this.principalListInteractor = new PrincipalListInteractorImplementation();
        this.eventBus = GreenRobotEventBus.getInstance();

    }

    @Override
    public void getTips()
    {
      int countResultados;

      countResultados = tipsListView.showListTips(principalListInteractor.getTips());
        if(countResultados==0)
        {
            tipsListView.showTipsListEmpty();
        }

    }

    @Override
    public void onCreate() {
        //eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        //eventBus.unregister(this);
        tipsListView= null;

    }
}
