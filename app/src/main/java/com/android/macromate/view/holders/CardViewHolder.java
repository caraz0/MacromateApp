package com.android.macromate.view.holders;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.macromate.R;
import com.android.macromate.view.activities.ProductInfoActivity;
import com.android.macromate.data.model.MockProduct;
import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CardViewHolder extends RecyclerView.ViewHolder {
    private ImageView imageView;
    private TextView titleView;

    private TextView nutriScoreView;

    private TextView scannedDateView;

    private CardClickListener listener;
    private MockProduct product;

    public interface CardClickListener {
        void onCardClick(MockProduct product);
    }

    public CardViewHolder(View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.image_view);
        titleView = itemView.findViewById(R.id.title_view);
        nutriScoreView = itemView.findViewById(R.id.nutri_score_view);
        scannedDateView = itemView.findViewById(R.id.scanned_date_view);
    }

    public void bind(MockProduct product) {
        Glide.with(imageView.getContext())
                .load(product.getImageUrl())
                .into(imageView);

        List<String> scores = new ArrayList<>(Arrays.asList("a", "b", "c", "d", "e"));
        titleView.setText(product.getProductName());
        nutriScoreView.setText(scores.contains(product.getNutritionalScore()) ? product.getNutritionalScore().toUpperCase() : "?");
        scannedDateView.setText(new SimpleDateFormat("dd-MM-yyyy").format(product.getScannedDate()));

        ImageView circle = itemView.findViewById(R.id.circle);
        circle.setBackgroundResource(R.drawable.circle_shape);
        circle.setColorFilter(getScoreColor(product.getNutritionalScore().toUpperCase()), PorterDuff.Mode.SRC_IN);
        this.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onCardClick(product);
                }
                Intent intent = new Intent(view.getContext(), ProductInfoActivity.class);
                intent.putExtra("product", product);
                view.getContext().startActivity(intent);
            }
        });
    }

    public int getScoreColor(String score) {
        int color = Color.parseColor("#e63e11");
        switch (score) {
            case "A":
            case "a":
                color = Color.parseColor("#038141");
                break;
            case "B":
            case "b":
                color = Color.parseColor("#85bb2f");
                break;
            case "C":
            case "c":
                color = Color.parseColor("#fecb02");
                break;
            case "D":
            case "d":
                color = Color.parseColor("#ee8100");
                break;
            case "E":
            case "e":
                color = Color.parseColor("#e63e11");
                break;
            default:
                color = Color.parseColor("#000000");
        }
        return color;
    }


}