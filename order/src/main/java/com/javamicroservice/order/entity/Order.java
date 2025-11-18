package com.javamicroservice.order.entity;

import com.javamicroservice.order.dto.FoodCatalogueDTO;
import com.javamicroservice.order.dto.RestaurantDTO;
import com.javamicroservice.order.dto.UserInformationDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@Document("order")
public class Order {

    @Id
    private Integer orderId;
    private List<FoodCatalogueDTO> foodCatalogueDTOS;
    private UserInformationDTO userInformationDTO;
    private RestaurantDTO restaurant;
    private Integer userId;

    public Order(Integer orderId, List<FoodCatalogueDTO> foodCatalogueDTOS, UserInformationDTO userInformationDTO, RestaurantDTO restaurant) {
        this.orderId = orderId;
        this.foodCatalogueDTOS = foodCatalogueDTOS;
        this.userInformationDTO = userInformationDTO;
        this.restaurant = restaurant;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public List<FoodCatalogueDTO> getFoodCatalogueDTOS() {
        return foodCatalogueDTOS;
    }

    public void setFoodCatalogueDTOS(List<FoodCatalogueDTO> foodCatalogueDTOS) {
        this.foodCatalogueDTOS = foodCatalogueDTOS;
    }

    public UserInformationDTO getUserInformationDTO() {
        return userInformationDTO;
    }

    public void setUserInformationDTO(UserInformationDTO userInformationDTO) {
        this.userInformationDTO = userInformationDTO;
    }

    public RestaurantDTO getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantDTO restaurant) {
        this.restaurant = restaurant;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
