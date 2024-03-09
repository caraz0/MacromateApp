package com.android.macromate.view.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.macromate.R;
import com.android.macromate.data.dao.ItemListDAO;
import com.android.macromate.data.model.ItemListModel;
import com.android.macromate.data.model.ListWithItemModel;
import com.android.macromate.view.adapters.ListItemAdapter;
import com.android.macromate.view.dialogs.CreateDialog;
import com.android.macromate.view.touchHelpers.RecyclerItemListTouchHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ListItemsActivity extends AppCompatActivity {

    @Inject
    ItemListDAO itemListDAO;

    private CreateDialog createDialog;

    public ListItemsActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);     // injection is done here

        setContentView(R.layout.fragment_view_list_activity);

        ListWithItemModel item = (ListWithItemModel) getIntent()
                .getSerializableExtra("item");

        TextView title = findViewById(R.id.title_text_view);
        title.setText(item.getList().getName());

        Button backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(v -> onBackPressed());

        RecyclerView recyclerView = findViewById(R.id.tasksRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        var adapter = new ListItemAdapter(this, itemListDAO, item);
        recyclerView.setAdapter(adapter);

        var itemTouch = new ItemTouchHelper(new RecyclerItemListTouchHelper(adapter));
        itemTouch.attachToRecyclerView(recyclerView);

        createDialog = new CreateDialog("Input new item name", s -> {
            itemListDAO.insert(new ItemListModel(0, item.getList().getListId(), s, false));
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> addButtonAction(item));

    }

    private void addButtonAction(ListWithItemModel item) {
        createDialog.show(getSupportFragmentManager(), "");
    }

}
