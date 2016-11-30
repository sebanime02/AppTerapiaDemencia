package co.edu.unicauca.appterapiademencia.principal.tips;

import java.util.List;

import co.edu.unicauca.appterapiademencia.domain.Tip;
import co.edu.unicauca.appterapiademencia.principal.PrincipalListInteractor;
import co.edu.unicauca.appterapiademencia.principal.PrincipalListInteractorImplementation;

/**
 * Created by SEBAS on 29/11/2016.
 */

public class TipListPresenterImplementation implements TipsListPresenter {

    TipsListView tipsListView;
    PrincipalListInteractor principalListInteractor;

    public TipListPresenterImplementation(TipsListView tipsListView)
    {
        this.tipsListView = tipsListView;
        this.principalListInteractor = new PrincipalListInteractorImplementation();
    }

    @Override
    public void getTips()
    {
      tipsListView.showListTips(principalListInteractor.getTips());

    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {
        tipsListView= null;

    }
}
