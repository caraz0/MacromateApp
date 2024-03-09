package com.android.macromate.view.nav_tabs;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.macromate.R;
import com.android.macromate.data.dao.ListDAO;
import com.android.macromate.data.model.ListModel;
import com.android.macromate.view.adapters.ListAdapter;
import com.android.macromate.view.dialogs.CreateDialog;
import com.android.macromate.view.touchHelpers.RecyclerListItemTouchHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Date;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * A simple {@link Fragment} subclass.
 */

@AndroidEntryPoint
public class ListsPageFragment extends Fragment {

    @Inject
    ListDAO listDAO;

    private CreateDialog createDialog;

    private RecyclerView recyclerView;
    private ListAdapter listAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);             // Field injection is done here

        createDialog = new CreateDialog("Input new list name", s -> {
            listDAO.insert(new ListModel(0, s, new Date()));
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lists_page, container, false);

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(this::executeAddButton);

        fab.setBackgroundColor(Color.parseColor("#040000"));

        recyclerView = view.findViewById(R.id.recyclerview);

        listAdapter = new ListAdapter(this, listDAO);

        // Configurar el RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(listAdapter);
        recyclerView.setHasFixedSize(true);

        var itemTouch = new ItemTouchHelper(new RecyclerListItemTouchHelper(this, listAdapter));
        itemTouch.attachToRecyclerView(recyclerView);

        return view;
    }

    private void executeAddButton(View view) {
        createDialog.show(getParentFragmentManager(), "example");
    }

}