package co.edu.unicauca.appterapiademencia.principal.tips;

import java.util.List;

import co.edu.unicauca.appterapiademencia.domain.Tip;

/**
 * Created by ENF on 26/10/2016.
 */

public interface TipsListView {
    void getListTips();
    int showListTips(List<Tip> listTips);
    void showTipsListEmpty();
    void refreshView();

}
