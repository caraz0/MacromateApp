package com.android.macromate.data.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@Entity(tableName = "mock_products")
public class MockProduct implements Serializable {

    public static MockProductBuilder builder() {
        return new MockProductBuilder();
    }

    @Ignore
    public MockProduct() {
    }

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "barcode")
    @NonNull
    private String barcode;

    @ColumnInfo(name = "product_name")
    @NonNull
    private String productName;

    @ColumnInfo(name = "brands")
    @NonNull
    private String brand;

    @ColumnInfo(name = "image_url")
    @NonNull
    private String imageUrl;

    @ColumnInfo(name = "nutritional_score")
    @NonNull
    private String nutritionalScore;

    @ColumnInfo(name = "quantity")
    private String quantity;

    @ColumnInfo(name = "categories")
    private List<String> categories;

    @ColumnInfo(name = "nutritional_info")
    private Map<String, String> nutritionalInfo;

    @ColumnInfo(name = "ingredients")
    private List<String> ingredients;

    @ColumnInfo(name = "nutrient_levels")
    private Map<String, String> nutrientLevels;

    @ColumnInfo(name = "scanned_date")
    @NonNull
    private Date scannedDate;

    public MockProduct(@NonNull String barcode,
                       @NonNull String productName,
                       String brand,
                       @NonNull String imageUrl,
                       @NonNull String nutritionalScore,
                       @NonNull Date scannedDate,
                       String quantity,
                       List<String> categories,
                       Map<String, String> nutritionalInfo,
                       List<String> ingredients,
                       Map<String, String> nutrientLevels

    ) {
        this.barcode = barcode;
        this.productName = productName;
        this.brand = brand;
        this.imageUrl = imageUrl;
        this.nutritionalScore = nutritionalScore;
        this.scannedDate = scannedDate;
        this.quantity = quantity;
        this.categories = categories;
        this.nutritionalInfo = nutritionalInfo;
        this.ingredients = ingredients;
        this.nutrientLevels = nutrientLevels;
    }

    @NonNull
    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(@NonNull String quantity) {
        this.quantity = quantity;
    }

    @NonNull
    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(@NonNull List<String> categories) {
        this.categories = categories;
    }

    @NonNull
    public Map<String, String> getNutritionalInfo() {
        return nutritionalInfo;
    }

    public void setNutritionalInfo(@NonNull Map<String, String> nutritionalInfo) {
        this.nutritionalInfo = nutritionalInfo;
    }

    @NonNull
    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(@NonNull List<String> ingredients) {
        this.ingredients = ingredients;
    }

    @NonNull
    public Map<String, String> getNutrientLevels() {
        return nutrientLevels;
    }

    public void setNutrientLevels(@NonNull Map<String, String> nutrientLevels) {
        this.nutrientLevels = nutrientLevels;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(@NonNull String barcode) {
        this.barcode = barcode;
    }

    @NonNull
    public String getBrand() {
        return brand;
    }

    public void setBrand(@NonNull String brand) {
        this.brand = brand;
    }

    @NonNull
    public String getProductName() {
        return productName;
    }

    public void setProductName(@NonNull String productName) {
        this.productName = productName;
    }

    @NonNull
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(@NonNull String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @NonNull
    public String getNutritionalScore() {
        return nutritionalScore;
    }

    public void setNutritionalScore(@NonNull String nutritionalScore) {
        this.nutritionalScore = nutritionalScore;
    }

    @NonNull
    public Date getScannedDate() {
        return scannedDate;
    }

    public void setScannedDate(@NonNull Date scannedDate) {
        this.scannedDate = scannedDate;
    }


    public static final class MockProductBuilder {
        private int id;
        private String barcode;
        private String productName;
        private String brand;
        private String imageUrl;

        private String nutritionalScore;

        private String quantity;

        private List<String> categories;

        private Map<String, String> nutritionalInfo;

        private List<String> ingredients;

        private Map<String, String> nutrientLevels;

        private Date scannedDate;

        private MockProductBuilder() {
        }



        public MockProductBuilder withCategories(List<String> categories) {
            this.categories = categories;
            return this;
        }

        public MockProductBuilder withNutritionalInfo(Map<String, String> nutritionalInfo) {
            this.nutritionalInfo = nutritionalInfo;
            return this;
        }

        public MockProductBuilder withIngredients(List<String> ingredients) {
            this.ingredients = ingredients;
            return this;
        }

        public MockProductBuilder withNutrientLevels(Map<String, String> nutrientLevels) {
            this.nutrientLevels = nutrientLevels;
            return this;
        }

        public MockProductBuilder withQuantity(String quantity) {
            this.quantity = quantity;
            return this;
        }



        public MockProductBuilder withBarcode(String barcode) {
            this.barcode = barcode;
            return this;
        }

        public MockProductBuilder withProductName(String productName) {
            this.productName = productName;
            return this;
        }

        public MockProductBuilder withBrands(String brand) {
            this.brand = brand;
            return this;
        }

        public MockProductBuilder withImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public MockProductBuilder withNutritionalScore(String nutritionalScore) {
            this.nutritionalScore = nutritionalScore;
            return this;
        }

        public MockProductBuilder withScannedDate(Date scannedDate) {
            this.scannedDate = scannedDate;
            return this;
        }

        public MockProduct build() {
            MockProduct mockProduct = new MockProduct();
            mockProduct.setId(id);
            mockProduct.setBarcode(barcode);
            mockProduct.setProductName(productName);
            mockProduct.setBrand(brand);
            mockProduct.setImageUrl(imageUrl);
            mockProduct.setNutritionalScore(nutritionalScore);

            mockProduct.setCategories(categories);
            mockProduct.setQuantity(quantity);
            mockProduct.setNutritionalInfo(nutritionalInfo);
            mockProduct.setIngredients(ingredients);
            mockProduct.setNutrientLevels(nutrientLevels);

            mockProduct.setScannedDate(scannedDate);
            return mockProduct;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MockProduct that = (MockProduct) o;
        return id == that.id && barcode.equals(that.barcode) && productName.equals(that.productName) && brand.equals(that.brand) && imageUrl.equals(that.imageUrl) && nutritionalScore.equals(that.nutritionalScore) && scannedDate.equals(that.scannedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, barcode, productName, brand, imageUrl, nutritionalScore, scannedDate);
    }

    @Override
    public String toString() {
        return "MockProduct{" +
                "id=" + id +
                ", barcode='" + barcode + '\'' +
                ", productName='" + productName + '\'' +
                ", brand='" + brand + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", nutritionalScore='" + nutritionalScore + '\'' +
                ", scannedDate=" + scannedDate +
                '}';
    }

    public String getIngredientsTextList() {
        String result = "";
        for(String s : ingredients) {
            result += s + ", ";
        }
        result = result.substring(0, result.length() - 2);
        return result;
    }
}
