package co.edu.unicauca.appterapiademencia.principal.tips;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import co.edu.unicauca.appterapiademencia.R;
import co.edu.unicauca.appterapiademencia.adapters.TipAdapter;
import co.edu.unicauca.appterapiademencia.domain.Tip;

/**
 * Created by ENF on 25/10/2016.
 */

public class TipsListFragment extends Fragment implements  TipsListView {
    private View rootView;
    private TipsListPresenter tipListPresenter;

    private FloatingActionButton floatingActionButton;
    private RecyclerView recycler;
    private TipAdapter adapter;
    private RecyclerView.Adapter newadapter;
    private RecyclerView.LayoutManager LManager;
    private List<Tip> list = new ArrayList<Tip>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_tips, container, false);
        floatingActionButton = (FloatingActionButton) rootView.findViewById(R.id.add_tip);
        recycler = (RecyclerView) rootView.findViewById(R.id.reciclador);

        recycler.setHasFixedSize(true);
        LManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recycler.setLayoutManager(LManager);
        getListTips();
        adapter = new TipAdapter(list, getActivity());
        recycler.setAdapter(adapter);

        registerForContextMenu(recycler);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTip();
            }
        });


        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        tipListPresenter = new TipListPresenterImplementation(this);
        tipListPresenter.onCreate();
    }


    public void addTip() {
        startActivity(new Intent(getActivity(), AddTipActivity.class));
    }

    @Override
    public void onResume()
    {
        super.onResume();
        list.clear();
        getListTips();


        try {
            recycler.setHasFixedSize(true);
            LManager = new LinearLayoutManager(getActivity().getApplicationContext());
            recycler.setLayoutManager(LManager);
            adapter = new TipAdapter(list, getActivity());
            recycler.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            //callListenerText();
        } catch (Exception e) {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void getListTips()
    {
        tipListPresenter.getTips();
    }

    @Override
    public void showListTips(List<Tip> listTips)
    {
        for (int j = 0; j < listTips.size(); j++) {
            list.add(listTips.get(j));
        }
    }
}
