package co.edu.unicauca.appterapiademencia.principal.tips;

import android.view.View;

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
    void turnNotifications(boolean mode);
    void showInstructions(View view);
    void showNotificationsChange(View view, boolean state);

}
