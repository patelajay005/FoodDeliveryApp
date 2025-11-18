package com.javamicroservice.foodcatalogue.repository;

import com.javamicroservice.foodcatalogue.entity.FoodCatalogue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FoodCatalogueRepository extends JpaRepository<FoodCatalogue, Integer> {

    List<FoodCatalogue> findByRestaurantId(Integer restaurantId);
}
