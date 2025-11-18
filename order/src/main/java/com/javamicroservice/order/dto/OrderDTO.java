package com.javamicroservice.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private List<FoodCatalogueDTO> foodCatalogueDTOS;
    private Integer userId;
    private RestaurantDTO restaurant;

    public List<FoodCatalogueDTO> getFoodCatalogueDTOS() {
        return foodCatalogueDTOS;
    }

    public void setFoodCatalogueDTOS(List<FoodCatalogueDTO> foodCatalogueDTOS) {
        this.foodCatalogueDTOS = foodCatalogueDTOS;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public RestaurantDTO getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantDTO restaurant) {
        this.restaurant = restaurant;
    }
}