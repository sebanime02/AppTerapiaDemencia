package co.edu.unicauca.appterapiademencia.principal.reminiscence;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;
import java.util.List;

import co.edu.unicauca.appterapiademencia.R;
import co.edu.unicauca.appterapiademencia.adapters.ReminiscenceAdapter;
import co.edu.unicauca.appterapiademencia.domain.Reminiscence;

/**
 * Created by SEBAS on 10/01/2017.
 */

public class ReminiscenceListFragment extends Fragment implements ReminiscenceListView{


    private View rootView;
    private ReminiscenceListPresenter reminiscenceListPresenter;

    private FloatingActionButton floatingActionButton;
    private RecyclerView recycler;
    private ReminiscenceAdapter adapter;
    private RecyclerView.Adapter newadapter;
    private RecyclerView.LayoutManager LManager;
    private List<Reminiscence> list = new ArrayList<Reminiscence>();
    private StaggeredGridLayoutManager gaggeredGridLayoutManager;
    private GridLayoutManager lLayout;
    private LinearLayout linearReminiscenceListEmpty;
    private ImageView imgArrowReminiscence;
    private ImageButton imgBtnHelpLisReminiscence;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_reminiscence, container, false);
        floatingActionButton = (FloatingActionButton) rootView.findViewById(R.id.add_reminiscence);
        recycler = (RecyclerView) rootView.findViewById(R.id.reciclador);
        linearReminiscenceListEmpty = (LinearLayout) rootView.findViewById(R.id.containerEmptyReminiscenceList);
        imgArrowReminiscence = (ImageView) rootView.findViewById(R.id.arrow_reminiscence);
        imgBtnHelpLisReminiscence = (ImageButton) rootView.findViewById(R.id.btn_instructions_reminiscence);

        recycler.setHasFixedSize(true);
        //LManager = new LinearLayoutManager(getActivity().getApplicationContext());
        //gaggeredGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        lLayout = new GridLayoutManager(getActivity(), 2);
        //recycler.setLayoutManager(gaggeredGridLayoutManager);
        recycler.setLayoutManager(lLayout);
        try
        {
            getReminiscenceList();
            adapter = new ReminiscenceAdapter(list, getActivity());
            recycler.setAdapter(adapter);

            registerForContextMenu(recycler);
        }catch (Exception e)
        {

        }

        imgBtnHelpLisReminiscence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInstructions(view);
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addReminiscence();
            }
        });

        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list.clear();
        reminiscenceListPresenter = new ReminiscencePresenterImplementation(this);
        reminiscenceListPresenter.onCreate();
    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);list.clear();



        try {
            getReminiscenceList();
            recycler.setHasFixedSize(true);
            //LManager = new LinearLayoutManager(getActivity().getApplicationContext());
            //gaggeredGridLayoutManager = new StaggeredGridLayoutManager(3, 1);
            lLayout = new GridLayoutManager(getActivity(), 2);
            //recycler.setLayoutManager(LManager);
            recycler.setLayoutManager(lLayout);
            adapter = new ReminiscenceAdapter(list, getActivity());
            recycler.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            //callListenerText();
        } catch (Exception e) {
            adapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            getReminiscenceList();
            recycler.setHasFixedSize(true);
            //LManager = new LinearLayoutManager(getActivity().getApplicationContext());
            //gaggeredGridLayoutManager = new StaggeredGridLayoutManager(3, 1);
            lLayout = new GridLayoutManager(getActivity(), 2);
            //recycler.setLayoutManager(LManager);
            recycler.setLayoutManager(lLayout);
            adapter = new ReminiscenceAdapter(list, getActivity());
            recycler.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            //callListenerText();
        } catch (Exception e) {
            adapter.notifyDataSetChanged();
        }

    }


    @Override
    public void addReminiscence() {
        startActivity(new Intent(getActivity(), AddReminiscenceExercise.class));
    }

    @Override
    public int showReminiscenceList(List<Reminiscence> reminiscenceList) {
        {
            list.clear();
            for (int j = 0; j < reminiscenceList.size(); j++) {
                list.add(reminiscenceList.get(j));
            }
            return list.size();


        }
    }

    @Override
    public void getReminiscenceList() {
        reminiscenceListPresenter.getReminiscence();
    }

    @Override
    public void emtpyReminiscenceList()
    {
        recycler.setVisibility(View.GONE);
        linearReminiscenceListEmpty.setVisibility(View.VISIBLE);
        imgArrowReminiscence.setVisibility(View.VISIBLE);
    }

    @Override
    public void refreshView() {
        super.onResume();
        recycler.setVisibility(View.VISIBLE);
        linearReminiscenceListEmpty.setVisibility(View.GONE);
        imgArrowReminiscence.setVisibility(View.GONE);
    }

    @Override
    public void showInstructions(View view)
    {
        new MaterialDialog.Builder(view.getContext()).title("Ayuda Bandeja de Ejercicios Reminiscencia").content(R.string.txt_info_reminiscence).positiveText("Bueno").icon(getResources().getDrawable(R.drawable.ic_action_action_help)).show();

    }
}
