package com.javamicroservice.foodcatalogue.service;

import com.javamicroservice.foodcatalogue.dto.FoodCatalogueDTO;
import com.javamicroservice.foodcatalogue.dto.FoodItemPage;
import com.javamicroservice.foodcatalogue.dto.Restaurant;
import com.javamicroservice.foodcatalogue.entity.FoodCatalogue;
import com.javamicroservice.foodcatalogue.mapper.FoodCatalogueMapper;
import com.javamicroservice.foodcatalogue.repository.FoodCatalogueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodCatalogueService {

    @Autowired
    FoodCatalogueRepository foodCatalogueRepository;

    @Autowired
    RestTemplate restTemplate;

    public List<FoodCatalogueDTO> addFoodItem(FoodCatalogueDTO foodCatalogueDTO) {
        foodCatalogueRepository.save(FoodCatalogueMapper.FOOD_CATALOGUE_MAPPER.foodCatalogueDTOToEntity(foodCatalogueDTO));

        List<FoodCatalogue> foodCatalogues  = foodCatalogueRepository.findAll();
        //Convert from Entity to DTOs
        return foodCatalogues.stream()
                .map(FoodCatalogueMapper.FOOD_CATALOGUE_MAPPER::foodCatalogueEntityToDTO)
                .collect(Collectors.toList());
    }

    public ResponseEntity<FoodItemPage> fetchRestaurantWithFoodDetails(Integer restaurantId) {
        List<FoodCatalogue> foodCatalogueDTOList =  foodCatalogueRepository.findByRestaurantId(restaurantId);
        Restaurant restaurant = fetchRestaurantDetailsFromMS(restaurantId);

        FoodItemPage foodItemPage = new FoodItemPage();
        foodItemPage.setRestaurant(restaurant);
        foodItemPage.setFoodCatalogueDTOS(foodCatalogueDTOList.stream()
                .map(FoodCatalogueMapper.FOOD_CATALOGUE_MAPPER :: foodCatalogueEntityToDTO)
                .collect(Collectors.toList()));
        return new ResponseEntity<FoodItemPage>(foodItemPage, HttpStatus.OK);
    }

    public Restaurant fetchRestaurantDetailsFromMS(Integer restaurantId) {
        return restTemplate.getForObject("https://RESTAURANT-SERVICE/restaurant/" + restaurantId, Restaurant.class);
    }
}
