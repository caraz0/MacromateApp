package com.android.macromate.view.holders;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.macromate.R;
import com.android.macromate.data.model.ItemListModel;
import com.android.macromate.data.model.ListWithItemModel;
import com.android.macromate.view.activities.ListItemsActivity;
import com.android.macromate.view.activities.ProductInfoActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ListAdapterHolder extends RecyclerView.ViewHolder {

    private static final List<String> colorPicker = List.of(
            "#fcffab",
            "#cefcff",
            "#fbe7ff",
            "#f1ffc4"
    );

    private static final SimpleDateFormat dateFormat =
            new SimpleDateFormat("dd/MM/yyyy HH:mm");

    private CardView cardView;
    private TextView titleView;
    private TextView itemDescriptionView;
    private TextView timeStampView;

    public ListAdapterHolder(@NonNull View itemView) {
        super(itemView);

        cardView = itemView.findViewById(R.id.card_view);
        titleView = itemView.findViewById(R.id.title_view);
        itemDescriptionView = itemView.findViewById(R.id.items_description);
        timeStampView = itemView.findViewById(R.id.items_list_date);
    }

    public void bind(ListWithItemModel list, int pos) {
        titleView.setText(list.getList().getName());
        itemDescriptionView.setText(generateItemDescription(list));
        timeStampView.setText(dateFormat.format(list.getList().getCreatedAt()));

        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setCornerRadii(new float[] { 75, 75, 75, 75, 75, 75, 75, 75 }); // Specify the corner radii
        drawable.setColor(chooseRandomColor(pos)); // Set the background color

        cardView.setBackground(drawable);

        cardView.setOnClickListener(view -> onClickAction(list));
    }

    private String generateItemDescription(ListWithItemModel list) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 3 && i < list.getItems().size(); i++) {
            builder.append(list.getItems().get(i).getName()).append(", ");
        }
        builder.append("...");

        return builder.toString();
    }

    private int chooseRandomColor(int pos) {
        int listOnPos = pos % colorPicker.size();

        return Color.parseColor(colorPicker.get(listOnPos));
    }

    private void onClickAction(ListWithItemModel item) {
        Intent intent = new Intent(cardView.getContext(), ListItemsActivity.class);
        intent.putExtra("item", item);
        cardView.getContext().startActivity(intent);
    }

}
