package com.javamicroservice.restaurantlisting.controller;

import com.javamicroservice.restaurantlisting.dto.RestaurantDTO;
import com.javamicroservice.restaurantlisting.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    RestaurantService restaurantService;

    @GetMapping("/fetchAllRestaurants")
    public ResponseEntity<List<RestaurantDTO>> fetchAllRestaurants() {
        List<RestaurantDTO> restaurantDTOS = restaurantService.fetchAllRestaurantDetails();
        return new ResponseEntity<>(restaurantDTOS, HttpStatus.OK);
    }

    @PostMapping("/createRestaurant")
    public ResponseEntity<List<RestaurantDTO>> createRestaurant(@RequestBody RestaurantDTO restaurantDTO) {
        System.out.println("Received DTO >>> " + restaurantDTO);
        List<RestaurantDTO> restaurantDTOS = restaurantService.createRestaurant(restaurantDTO);
        return new ResponseEntity<>(restaurantDTOS, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDTO> restaurantDetailById(@PathVariable Integer id) {
        return restaurantService.restaurantDetailById(id);
    }

}
