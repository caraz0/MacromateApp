package com.android.macromate.data.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "ShopLocations")
public class ShopLocationModel {

    public static ShopLocationModelBuilder builder() {
        return new ShopLocationModelBuilder();
    }

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private String name;

    @NonNull
    private Double latitude;

    @NonNull
    private Double longitude;

    public ShopLocationModel(int id, String name, @NonNull Double latitude, @NonNull Double longitude) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NonNull
    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(@NonNull Double latitude) {
        this.latitude = latitude;
    }

    @NonNull
    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(@NonNull Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShopLocationModel that = (ShopLocationModel) o;
        return id == that.id && Objects.equals(name, that.name) && latitude.equals(that.latitude) && longitude.equals(that.longitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, latitude, longitude);
    }

    @Override
    public String toString() {
        return "ShopLocationModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }


    public static final class ShopLocationModelBuilder {
        private int id;
        private String name;
        private Double latitude;
        private Double longitude;

        private ShopLocationModelBuilder() {
        }

        public ShopLocationModelBuilder withId(int id) {
            this.id = id;
            return this;
        }

        public ShopLocationModelBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ShopLocationModelBuilder withLatitude(Double latitude) {
            this.latitude = latitude;
            return this;
        }

        public ShopLocationModelBuilder withLongitude(Double longitude) {
            this.longitude = longitude;
            return this;
        }

        public ShopLocationModel build() {
            return new ShopLocationModel(id, name, latitude, longitude);
        }
    }

}
