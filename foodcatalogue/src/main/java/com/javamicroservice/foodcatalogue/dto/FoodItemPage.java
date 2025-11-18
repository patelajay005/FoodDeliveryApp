package com.javamicroservice.foodcatalogue.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodItemPage {

    private List<FoodCatalogueDTO> foodCatalogueDTOS;
    private Restaurant restaurant;

    public List<FoodCatalogueDTO> getFoodCatalogueDTOS() {
        return foodCatalogueDTOS;
    }

    public void setFoodCatalogueDTOS(List<FoodCatalogueDTO> foodCatalogueDTOS) {
        this.foodCatalogueDTOS = foodCatalogueDTOS;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
