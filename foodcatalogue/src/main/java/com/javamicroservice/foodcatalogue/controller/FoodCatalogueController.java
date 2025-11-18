package com.javamicroservice.foodcatalogue.controller;

import com.javamicroservice.foodcatalogue.dto.FoodCatalogueDTO;
import com.javamicroservice.foodcatalogue.dto.FoodItemPage;
import com.javamicroservice.foodcatalogue.service.FoodCatalogueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/foodCatalogue")
public class FoodCatalogueController {

    @Autowired
    FoodCatalogueService foodCatalogueService;

    @PostMapping("/addFoodItem")
    public ResponseEntity<List<FoodCatalogueDTO>> addFoodItem(@RequestBody FoodCatalogueDTO foodCatalogueDTO) {
        List<FoodCatalogueDTO> foodCatalogueDTOS = foodCatalogueService.addFoodItem(foodCatalogueDTO);
        return new ResponseEntity<>(foodCatalogueDTOS, HttpStatus.CREATED);
    }

    @GetMapping("/fetchRestaurantAndFoodItemsById/{restaurantId}")
    public ResponseEntity<FoodItemPage> fetchRestaurantAndFoodItemsById(@PathVariable Integer restaurantId) {
        return foodCatalogueService.fetchRestaurantWithFoodDetails(restaurantId);
    }
}
