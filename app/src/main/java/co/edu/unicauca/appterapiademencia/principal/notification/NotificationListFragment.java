package co.edu.unicauca.appterapiademencia.principal.notification;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import co.edu.unicauca.appterapiademencia.R;
import co.edu.unicauca.appterapiademencia.adapters.NotificationsAdapter;
import co.edu.unicauca.appterapiademencia.domain.Note;

/**
 * Created by ENF on 25/10/2016.
 */

public class NotificationListFragment extends Fragment implements NotificationView{
    private View rootView;
    private NotificationPresenter notificationPresenter;
    private List<Note> notificationList;
    private RecyclerView recycler;
    private LinearLayoutManager lLayout;
    private NotificationsAdapter adapter;

    public NotificationListFragment()
    {
        this.notificationList = new ArrayList<Note>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_notifications, container, false);
        notificationPresenter = new NotificationPresenterImplementation(this);


        return rootView;


    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState) {


        try {
            recycler = (RecyclerView) view.findViewById(R.id.reciclador);


            recycler.setHasFixedSize(true);
            //LManager = new LinearLayoutManager(getActivity().getApplicationContext());
            //gaggeredGridLayoutManager = new StaggeredGridLayoutManager(3, 1);
            lLayout = new LinearLayoutManager(getActivity());
            //recycler.setLayoutManager(LManager);
            recycler.setLayoutManager(lLayout);
            getNotifications();
            adapter = new NotificationsAdapter(this.notificationList, getActivity());
            recycler.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            //callListenerText();
        } catch (Exception e) {
            adapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.notificationList.clear();
        notificationPresenter = new NotificationPresenterImplementation(this);
        notificationPresenter.onCreate();


    }





    @Override
    public void onDestroy() {
        super.onDestroy();
        notificationPresenter.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            this.notificationList.clear();
            notificationPresenter.onResume();
            getNotifications();
            recycler.setHasFixedSize(true);
            //LManager = new LinearLayoutManager(getActivity().getApplicationContext());
            //gaggeredGridLayoutManager = new StaggeredGridLayoutManager(3, 1);
            lLayout = new LinearLayoutManager(getActivity());
            //recycler.setLayoutManager(LManager);
            recycler.setLayoutManager(lLayout);
            adapter = new NotificationsAdapter(this.notificationList, getActivity());
            recycler.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            //callListenerText();
        } catch (Exception e) {
            adapter.notifyDataSetChanged();
        }

    }

    @Override
    public void getNotifications() {
        notificationPresenter.getNotification();
    }

    @Override
    public void showNotifications(List<Note> notificationList) {
        Log.e("shownotifications","va a mostrar las notificaciones");
        this.notificationList.clear();
        for(int m=0;m<notificationList.size();m++)
        {
            this.notificationList.add(notificationList.get(m));
        }
    }

    @Override
    public void refreshNotification() {
        try {
            this.notificationList.clear();
            notificationPresenter.onResume();
            getNotifications();
            recycler.setHasFixedSize(true);
            //LManager = new LinearLayoutManager(getActivity().getApplicationContext());
            //gaggeredGridLayoutManager = new StaggeredGridLayoutManager(3, 1);
            lLayout = new LinearLayoutManager(getActivity());
            //recycler.setLayoutManager(LManager);
            recycler.setLayoutManager(lLayout);
            adapter = new NotificationsAdapter(this.notificationList, getActivity());
            recycler.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            //callListenerText();
        } catch (Exception e) {
            adapter.notifyDataSetChanged();
        }

    }
}
