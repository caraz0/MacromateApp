package com.android.macromate.dtos.integration;


import java.util.Objects;

public class OpenFoodFactsProductNutritionalInfoDTO {

    public static OpenFoodFactsProductNutritionalInfoDTOBuilder builder() {
        return new OpenFoodFactsProductNutritionalInfoDTOBuilder();
    }

    public final double energy;
    public final String energyUnit;
    public final double fats;
    public final String factsUnit;
    public final double saturatedFat;
    public final String saturatedFatUnit;
    public final String proteins;
    public final String proteinsUnit;
    public final double fiber;
    public final String fiberUnit;

    public OpenFoodFactsProductNutritionalInfoDTO(double energy,
                                                  String energyUnit,
                                                  double fats,
                                                  String factsUnit,
                                                  double saturatedFat,
                                                  String saturatedFatUnit,
                                                  String proteins,
                                                  String proteinsUnit,
                                                  double fiber,
                                                  String fiberUnit
    ) {
        this.energy = energy;
        this.energyUnit = energyUnit;
        this.fats = fats;
        this.factsUnit = factsUnit;
        this.saturatedFat = saturatedFat;
        this.saturatedFatUnit = saturatedFatUnit;
        this.proteins = proteins;
        this.proteinsUnit = proteinsUnit;
        this.fiber = fiber;
        this.fiberUnit = fiberUnit;
    }

    public double getEnergy() {
        return energy;
    }

    public String getEnergyUnit() {
        return energyUnit;
    }

    public double getFats() {
        return fats;
    }

    public String getFactsUnit() {
        return factsUnit;
    }

    public double getSaturatedFat() {
        return saturatedFat;
    }

    public String getSaturatedFatUnit() {
        return saturatedFatUnit;
    }

    public String getProteins() {
        return proteins;
    }

    public String getProteinsUnit() {
        return proteinsUnit;
    }

    public double getFiber() {
        return fiber;
    }

    public String getFiberUnit() {
        return fiberUnit;
    }

    public static final class OpenFoodFactsProductNutritionalInfoDTOBuilder {
        private double energy;
        private String energyUnit;
        private double fats;
        private String factsUnit;
        private double saturatedFat;
        private String saturatedFatUnit;
        private String proteins;
        private String proteinsUnit;
        private double fiber;
        private String fiberUnit;

        private OpenFoodFactsProductNutritionalInfoDTOBuilder() {
        }

        public static OpenFoodFactsProductNutritionalInfoDTOBuilder anOpenFoodFactsProductNutritionalInfoDTO() {
            return new OpenFoodFactsProductNutritionalInfoDTOBuilder();
        }

        public OpenFoodFactsProductNutritionalInfoDTOBuilder withEnergy(double energy) {
            this.energy = energy;
            return this;
        }

        public OpenFoodFactsProductNutritionalInfoDTOBuilder withEnergyUnit(String energyUnit) {
            this.energyUnit = energyUnit;
            return this;
        }

        public OpenFoodFactsProductNutritionalInfoDTOBuilder withFats(double fats) {
            this.fats = fats;
            return this;
        }

        public OpenFoodFactsProductNutritionalInfoDTOBuilder withFactsUnit(String factsUnit) {
            this.factsUnit = factsUnit;
            return this;
        }

        public OpenFoodFactsProductNutritionalInfoDTOBuilder withSaturatedFat(double saturatedFat) {
            this.saturatedFat = saturatedFat;
            return this;
        }

        public OpenFoodFactsProductNutritionalInfoDTOBuilder withSaturatedFatUnit(String saturatedFatUnit) {
            this.saturatedFatUnit = saturatedFatUnit;
            return this;
        }

        public OpenFoodFactsProductNutritionalInfoDTOBuilder withProteins(String proteins) {
            this.proteins = proteins;
            return this;
        }

        public OpenFoodFactsProductNutritionalInfoDTOBuilder withProteinsUnit(String proteinsUnit) {
            this.proteinsUnit = proteinsUnit;
            return this;
        }

        public OpenFoodFactsProductNutritionalInfoDTOBuilder withFiber(double fiber) {
            this.fiber = fiber;
            return this;
        }

        public OpenFoodFactsProductNutritionalInfoDTOBuilder withFiberUnit(String fiberUnit) {
            this.fiberUnit = fiberUnit;
            return this;
        }

        public OpenFoodFactsProductNutritionalInfoDTO build() {
            return new OpenFoodFactsProductNutritionalInfoDTO(energy, energyUnit, fats, factsUnit, saturatedFat, saturatedFatUnit, proteins, proteinsUnit, fiber, fiberUnit);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OpenFoodFactsProductNutritionalInfoDTO that = (OpenFoodFactsProductNutritionalInfoDTO) o;
        return Double.compare(that.energy, energy) == 0 && Double.compare(that.fats, fats) == 0 && Double.compare(that.saturatedFat, saturatedFat) == 0 && Double.compare(that.fiber, fiber) == 0 && Objects.equals(energyUnit, that.energyUnit) && Objects.equals(factsUnit, that.factsUnit) && Objects.equals(saturatedFatUnit, that.saturatedFatUnit) && Objects.equals(proteins, that.proteins) && Objects.equals(proteinsUnit, that.proteinsUnit) && Objects.equals(fiberUnit, that.fiberUnit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(energy, energyUnit, fats, factsUnit, saturatedFat, saturatedFatUnit, proteins, proteinsUnit, fiber, fiberUnit);
    }

    @Override
    public String toString() {
        return "OpenFoodFactsProductNutritionalInfoDTO{" +
                "energy=" + energy +
                ", energyUnit='" + energyUnit + '\'' +
                ", fats=" + fats +
                ", factsUnit='" + factsUnit + '\'' +
                ", saturatedFat=" + saturatedFat +
                ", saturatedFatUnit='" + saturatedFatUnit + '\'' +
                ", proteins='" + proteins + '\'' +
                ", proteinsUnit='" + proteinsUnit + '\'' +
                ", fiber=" + fiber +
                ", fiberUnit='" + fiberUnit + '\'' +
                '}';
    }

}
