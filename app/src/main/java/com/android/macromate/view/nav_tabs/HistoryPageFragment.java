package com.android.macromate.view.nav_tabs;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.macromate.R;
import com.android.macromate.data.repositories.MockProductRepository;
import com.android.macromate.view.activities.ScanActivity;
import com.android.macromate.view.adapters.CardAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * A simple {@link Fragment} subclass.
 */
@AndroidEntryPoint
public class HistoryPageFragment extends Fragment {

    @Inject
    MockProductRepository mockProductRepository;

    private RecyclerView recyclerView;
    private CardAdapter cardAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);             // Field injection is done here
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history_page, container, false);

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(view1 -> {
            Intent intent = new Intent(requireActivity(), ScanActivity.class);
            startActivity(intent);
        });

        fab.setBackgroundColor(Color.parseColor("#040000"));

        // Instanciar el RecyclerView y el CardAdapter
        recyclerView = view.findViewById(R.id.recycler_view);

        cardAdapter = new CardAdapter(mockProductRepository.getAllMockProducts().getValue());
        mockProductRepository.getAllMockProducts().observe(getViewLifecycleOwner(), mockProducts -> {
            if (cardAdapter != null) {
                cardAdapter.setMockProducts(mockProducts);
            }
        });

        // Configurar el RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(cardAdapter);
        recyclerView.setHasFixedSize(true);

        return view;
    }
}

