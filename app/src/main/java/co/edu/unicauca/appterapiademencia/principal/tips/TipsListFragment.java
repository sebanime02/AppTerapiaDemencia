package co.edu.unicauca.appterapiademencia.principal.tips;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

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
    private StaggeredGridLayoutManager gaggeredGridLayoutManager;
    private GridLayoutManager lLayout;
    private LinearLayout linearTipsListEmpty;
    private ImageView imgArrowTip;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_tips, container, false);
        floatingActionButton = (FloatingActionButton) rootView.findViewById(R.id.add_tip);
        Log.d("Time count","Fragment to fragment termina de contar");
        linearTipsListEmpty = (LinearLayout) rootView.findViewById(R.id.containerEmptyTipsList);
        imgArrowTip = (ImageView) rootView.findViewById(R.id.arrow_tip);


        recycler = (RecyclerView) rootView.findViewById(R.id.reciclador);

        recycler.setHasFixedSize(true);
        //LManager = new LinearLayoutManager(getActivity().getApplicationContext());
        //gaggeredGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        lLayout = new GridLayoutManager(getActivity(), 2);
        //recycler.setLayoutManager(gaggeredGridLayoutManager);
        recycler.setLayoutManager(lLayout);
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
        list.clear();
        tipListPresenter = new TipListPresenterImplementation(this);
        tipListPresenter.onCreate();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list.clear();
        getListTips();


        try {
            recycler.setHasFixedSize(true);
            //LManager = new LinearLayoutManager(getActivity().getApplicationContext());
            //gaggeredGridLayoutManager = new StaggeredGridLayoutManager(3, 1);
            lLayout = new GridLayoutManager(getActivity(), 2);
            //recycler.setLayoutManager(LManager);
            recycler.setLayoutManager(lLayout);
            adapter = new TipAdapter(list, getActivity());
            recycler.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            //callListenerText();
        } catch (Exception e) {
            adapter.notifyDataSetChanged();
        }
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
            //LManager = new LinearLayoutManager(getActivity().getApplicationContext());
            //gaggeredGridLayoutManager = new StaggeredGridLayoutManager(3, 1);
            lLayout = new GridLayoutManager(getActivity(), 2);
            //recycler.setLayoutManager(LManager);
            recycler.setLayoutManager(lLayout);
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
    public int showListTips(List<Tip> listTips)
    {
        list.clear();
        for (int j = 0; j < listTips.size(); j++)
        {
            list.add(listTips.get(j));
        }
        return list.size();
    }

    @Override
    public void showTipsListEmpty()
    {
        recycler.setVisibility(View.GONE);
        linearTipsListEmpty.setVisibility(View.VISIBLE);
        imgArrowTip.setVisibility(View.VISIBLE);
    }
}
