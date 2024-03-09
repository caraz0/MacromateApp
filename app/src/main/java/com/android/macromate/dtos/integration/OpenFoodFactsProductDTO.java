package com.android.macromate.dtos.integration;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class OpenFoodFactsProductDTO {

    public static OpenFoodFactsProductDTOBuilder builder() {
        return new OpenFoodFactsProductDTOBuilder();
    }

    private final String name;
    private final String imageUrl;
    private final String barcode;
    private final List<String> brands;
    private final List<String> categories;
    private final List<String> stores;
    private final String quantity;
    private final String nutritionalScore;
    private final Map<String, String> nutritionalInfo;
    private final List<String> ingredients;
    private final Map<String, String> nutrientLevels;

    public OpenFoodFactsProductDTO(String name,
                                   String imageUrl,
                                   String barcode,
                                   List<String> brands,
                                   List<String> categories,
                                   List<String> stores,
                                   String quantity,
                                   String nutritionalScore,
                                   Map<String, String> nutritionalInfo,
                                   List<String> ingredients,
                                   Map<String, String> nutrientLevels
    ) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.barcode = barcode;
        this.brands = brands;
        this.categories = categories;
        this.stores = stores;
        this.quantity = quantity;
        this.nutritionalScore = nutritionalScore;
        this.nutritionalInfo = nutritionalInfo;
        this.ingredients = ingredients;
        this.nutrientLevels = nutrientLevels;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getBarcode() {
        return barcode;
    }

    public List<String> getBrands() {
        return brands;
    }

    public List<String> getCategories() {
        return categories;
    }

    public List<String> getStores() {
        return stores;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getNutritionalScore() {
        return nutritionalScore;
    }

    public Map<String, String> getNutritionalInfo() {
        return nutritionalInfo;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public Map<String, String> getNutrientLevels() {
        return nutrientLevels;
    }

    public static final class OpenFoodFactsProductDTOBuilder {
        private String name;
        private String imageUrl;
        private String barcode;
        private List<String> brands;
        private List<String> categories;
        private List<String> stores;
        private String quantity;
        private String nutritionalScore;
        private Map<String, String> nutritionalInfo;
        private List<String> ingredients;
        private Map<String, String> nutrientLevels;

        private OpenFoodFactsProductDTOBuilder() {
        }

        public OpenFoodFactsProductDTOBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public OpenFoodFactsProductDTOBuilder withImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public OpenFoodFactsProductDTOBuilder withBarcode(String barcode) {
            this.barcode = barcode;
            return this;
        }

        public OpenFoodFactsProductDTOBuilder withBrands(List<String> brands) {
            this.brands = brands;
            return this;
        }

        public OpenFoodFactsProductDTOBuilder withCategories(List<String> categories) {
            this.categories = categories;
            return this;
        }

        public OpenFoodFactsProductDTOBuilder withStores(List<String> stores) {
            this.stores = stores;
            return this;
        }

        public OpenFoodFactsProductDTOBuilder withQuantity(String quantity) {
            this.quantity = quantity;
            return this;
        }

        public OpenFoodFactsProductDTOBuilder withNutritionalScore(String nutritionalScore) {
            this.nutritionalScore = nutritionalScore;
            return this;
        }

        public OpenFoodFactsProductDTOBuilder withNutritionalInfo(Map<String, String> nutritionalInfo) {
            this.nutritionalInfo = nutritionalInfo;
            return this;
        }

        public OpenFoodFactsProductDTOBuilder withIngredients(List<String> ingredients) {
            this.ingredients = ingredients;
            return this;
        }

        public OpenFoodFactsProductDTOBuilder withNutritionalQuality(Map<String, String> nutrientLevels) {
            this.nutrientLevels = nutrientLevels;
            return this;
        }

        public OpenFoodFactsProductDTO build() {
            return new OpenFoodFactsProductDTO(name,
                    imageUrl,
                    barcode,
                    brands,
                    categories,
                    stores,
                    quantity,
                    nutritionalScore,
                    nutritionalInfo,
                    ingredients,
                    nutrientLevels
            );
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OpenFoodFactsProductDTO that = (OpenFoodFactsProductDTO) o;
        return Objects.equals(that.nutritionalScore, nutritionalScore) && Objects.equals(name, that.name) && Objects.equals(imageUrl, that.imageUrl) && Objects.equals(categories, that.categories) && Objects.equals(stores, that.stores) && Objects.equals(quantity, that.quantity) && Objects.equals(nutritionalInfo, that.nutritionalInfo) && Objects.equals(ingredients, that.ingredients) && Objects.equals(nutrientLevels, that.nutrientLevels);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, imageUrl, categories, stores, quantity, nutritionalScore, nutritionalInfo, ingredients, nutrientLevels);
    }

    @Override
    public String toString() {
        return "OpenFoodFactsProductDTO{" +
                "name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", brands='" + brands +
                ", categories=" + categories +
                ", stores=" + stores +
                ", quantity='" + quantity + '\'' +
                ", nutritionalScore=" + nutritionalScore +
                ", nutritionalInfo=" + nutritionalInfo +
                ", ingredients=" + ingredients +
                ", nutrientLevels=" + nutrientLevels +
                '}';
    }
}
