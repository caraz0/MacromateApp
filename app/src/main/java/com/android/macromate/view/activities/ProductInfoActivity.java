package com.android.macromate.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.macromate.R;
import com.android.macromate.data.model.MockProduct;
import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import me.gujun.android.taggroup.TagGroup;

public class ProductInfoActivity extends AppCompatActivity {

    private ArrayAdapter<String> adapter;
    private ArrayList<String> productInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);

        MockProduct product = (MockProduct) getIntent().getSerializableExtra("product");

        Button backButton = findViewById(R.id.btn_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick (View v) {
                onBackPressed();
            }
        });

        TextView title = findViewById(R.id.info_product_title);
        TextView brand = findViewById(R.id.info_product_brand);
        TextView scoreDesc = findViewById(R.id.info_product_score_desc);

        title.setText(product.getProductName());
        brand.setText(product.getBrand());
        scoreDesc.setText(getScoreDesc(product.getNutritionalScore()));

        ImageView img = findViewById(R.id.info_product_image);
        ImageView scoreImg = findViewById(R.id.info_product_score_image);
        Glide.with(img.getContext())
                .load(product.getImageUrl())
                .into(img);
        Glide.with(scoreImg.getContext())
                .load(Uri.parse("file:///android_asset/nutri_score_images/" + getScoreImage(product.getNutritionalScore())))
                .into(scoreImg);

        TagGroup mTagGroup = (TagGroup) findViewById(R.id.tag_group);
        mTagGroup.setTags(product.getCategories());

        TextView ingredientsText = findViewById(R.id.info_product_ingredients_text);
        ingredientsText.setText(product.getIngredientsTextList());

        List<String> result = new ArrayList<String>();
        for (Map.Entry<String, String> entry : product.getNutritionalInfo().entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            switch(key) {
                case "carbohydrates":
                    result.add("\uD83C\uDF5C Carbohidratos\t\t\t" + value);
                    break;
                case "fiber":
                    result.add("\uD83C\uDF3E Fibra\t\t\t" + value);
                    break;
                case "salt":
                    result.add("\uD83E\uDDC2 Sal\t\t\t" + value);
                    break;
                case "sugars":
                    result.add("\uD83C\uDF6C Azúcar\t\t\t" + value);
                    break;
                case "energy-kcal":
                    result.add("\uD83D\uDD25 Valor energético\t\t\t" + value);
                    break;
                case "proteins":
                    result.add("\uD83D\uDCAA Proteínas\t\t\t" + value);
                    break;
                case "saturated-fat":
                    result.add("\uD83C\uDF54 Grasas saturadas\t\t\t" + value);
                    break;
            }
        }
        String textNutritionalInfo = "";
        for(String s : result) {
            textNutritionalInfo += s + "\n";
        }
        textNutritionalInfo = textNutritionalInfo.substring(0, textNutritionalInfo.length() - 1);

        TextView nutritionalInfoText = findViewById(R.id.info_product_nutritional_info_text);
        nutritionalInfoText.setText(textNutritionalInfo);

    }

    public String getScoreImage(String score) {
        String fileName = "nutri_score_a.jpeg";
        switch (score) {
            case "A":
            case "a":
                fileName = "nutri_score_a.jpeg";
                break;
            case "B":
            case "b":
                fileName = "nutri_score_b.jpeg";
                break;
            case "C":
            case "c":
                fileName = "nutri_score_c.jpeg";
                break;
            case "D":
            case "d":
                fileName = "nutri_score_d.jpeg";
                break;
            case "E":
            case "e":
                fileName = "nutri_score_e.jpeg";
                break;
            default:
                fileName = "";
        }
        return fileName;
    }

    public String getScoreDesc(String score) {
        String desc = "Excelente";
        switch (score) {
            case "A":
            case "a":
                desc = "Excelente";
                break;
            case "B":
            case "b":
                desc = "Bueno";
                break;
            case "C":
            case "c":
                desc = "Mediocre";
                break;
            case "D":
            case "d":
                desc = "Malo";
                break;
            case "E":
            case "e":
                desc = "Muy malo";
                break;
            default:
                desc = "Sin puntuación";
        }
        return desc;
    }
}