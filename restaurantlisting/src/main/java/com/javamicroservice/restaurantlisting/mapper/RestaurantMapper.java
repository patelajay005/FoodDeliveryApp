package com.javamicroservice.restaurantlisting.mapper;

import com.javamicroservice.restaurantlisting.dto.RestaurantDTO;
import com.javamicroservice.restaurantlisting.entity.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RestaurantMapper {

    RestaurantMapper RESTAURANT_MAPPER = Mappers.getMapper(RestaurantMapper.class);

    Restaurant mapRestaurantDTOToRestaurant(RestaurantDTO restaurantDTO);

    RestaurantDTO restaurantToRestaurantDTO(Restaurant restaurant);
}
