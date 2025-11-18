package com.javamicroservice.restaurantlisting.service;

import com.javamicroservice.restaurantlisting.dto.RestaurantDTO;
import com.javamicroservice.restaurantlisting.entity.Restaurant;
import com.javamicroservice.restaurantlisting.mapper.RestaurantMapper;
import com.javamicroservice.restaurantlisting.repository.RestaurantRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;

    public List<RestaurantDTO> fetchAllRestaurantDetails() {
        List<Restaurant> restaurants = restaurantRepository.findAll();

        //Convert from Entity to DTO
        return restaurants.stream()
                .map(RestaurantMapper.RESTAURANT_MAPPER::restaurantToRestaurantDTO)
                .collect(Collectors.toList());
    }

    public List<RestaurantDTO> createRestaurant(RestaurantDTO restaurantDTO) {
        Restaurant restaurant = RestaurantMapper.RESTAURANT_MAPPER.mapRestaurantDTOToRestaurant(restaurantDTO);
        restaurantRepository.save(restaurant);

        List<Restaurant> restaurants = restaurantRepository.findAll();

        //Convert from Entity to DTO
        return restaurants.stream()
                .map(RestaurantMapper.RESTAURANT_MAPPER::restaurantToRestaurantDTO)
                .collect(Collectors.toList());
    }

    public ResponseEntity<RestaurantDTO> restaurantDetailById(Integer id) {
        Optional<Restaurant> optionalRestaurant =  restaurantRepository.findById(id);
        if(optionalRestaurant.isPresent()) {
            return new ResponseEntity<>(RestaurantMapper.RESTAURANT_MAPPER.restaurantToRestaurantDTO(optionalRestaurant.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(new RestaurantDTO(), HttpStatus.NOT_FOUND);
    }
}
