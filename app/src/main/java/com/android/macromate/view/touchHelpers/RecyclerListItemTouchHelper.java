package com.android.macromate.view.touchHelpers;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.android.macromate.R;
import com.android.macromate.view.adapters.ListAdapter;
import com.android.macromate.view.dialogs.CreateDialog;
import com.android.macromate.view.nav_tabs.ListsPageFragment;

public class RecyclerListItemTouchHelper extends ItemTouchHelper.SimpleCallback {

    private final ListsPageFragment listsPageFragment;
    private final ListAdapter listAdapter;

    public RecyclerListItemTouchHelper(ListsPageFragment listsPageFragment,
                                       ListAdapter listAdapter) {
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.listAdapter = listAdapter;
        this.listsPageFragment = listsPageFragment;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull final RecyclerView.ViewHolder viewHolder, int direction) {
        final int position = viewHolder.getAdapterPosition();

        if (direction == ItemTouchHelper.LEFT) {
            AlertDialog.Builder builder = new AlertDialog.Builder(listAdapter.getContext());
            builder.setTitle("Eliminar Lista");
            builder.setMessage("Estas seguro de que quieres eliminar esta lista?");
            builder.setPositiveButton("Confirmar", (dialog, which) -> listAdapter.deleteItem(position));
            builder.setNegativeButton("Cancelar", (dialog, which) -> {
                listAdapter.notifyItemChanged(position);        // update previous view
            });
            AlertDialog dialog = builder.create();
            dialog.show();

        } else {
            new CreateDialog("Introduce nuevo nombre",
                    s -> listAdapter.updateName(s, position),
                    () -> listAdapter.notifyItemChanged(position))
                    .show(listsPageFragment.getParentFragmentManager(), "");
        }
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView,
                            @NonNull RecyclerView.ViewHolder viewHolder, float dX,
                            float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

        Drawable icon;
        GradientDrawable background = new GradientDrawable();
        background.setShape(GradientDrawable.RECTANGLE);

        View itemView = viewHolder.itemView;
        int backgroundCornerOffset = 70;


        if (dX > 0) {
            icon = ContextCompat.getDrawable(listAdapter.getContext(), R.drawable.ic_baseline_edit);
            background.setCornerRadii(new float[]{75, 75, 0, 0, 0, 0, 75, 75});
            background.setColor(ContextCompat.getColor(listAdapter.getContext(), R.color.purple_500));
        } else {
            icon = ContextCompat.getDrawable(listAdapter.getContext(), R.drawable.ic_baseline_delete);
            background.setCornerRadii(new float[]{0, 0, 75, 75, 75, 75, 0, 0});
            background.setColor(Color.RED); // Set the background color
        }

        int iconMargin = (itemView.getHeight() - icon.getIntrinsicHeight()) / 2;
        int iconTop = itemView.getTop() + (itemView.getHeight() - icon.getIntrinsicHeight()) / 2;
        int iconBottom = iconTop + icon.getIntrinsicHeight();

        if (dX > 0) { // Swiping to the right
            int iconLeft = itemView.getLeft() + iconMargin;
            int iconRight = itemView.getLeft() + iconMargin + icon.getIntrinsicWidth();
            icon.setBounds(iconLeft, iconTop, iconRight, iconBottom);

            background.setBounds(itemView.getLeft(), itemView.getTop(),
                    itemView.getLeft() + ((int) dX) + backgroundCornerOffset, itemView.getBottom());
        } else if (dX < 0) { // Swiping to the left
            int iconLeft = itemView.getRight() - iconMargin - icon.getIntrinsicWidth();
            int iconRight = itemView.getRight() - iconMargin;
            icon.setBounds(iconLeft, iconTop, iconRight, iconBottom);

            background.setBounds(itemView.getRight() + ((int) dX) - backgroundCornerOffset,
                    itemView.getTop(), itemView.getRight(), itemView.getBottom());
        } else { // view is unSwiped
            background.setBounds(0, 0, 0, 0);
        }

        background.draw(c);
        icon.draw(c);
    }
}
