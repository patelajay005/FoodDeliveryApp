package com.javamicroservice.foodcatalogue.mapper;

import com.javamicroservice.foodcatalogue.dto.FoodCatalogueDTO;
import com.javamicroservice.foodcatalogue.entity.FoodCatalogue;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FoodCatalogueMapper {

    FoodCatalogueMapper FOOD_CATALOGUE_MAPPER = Mappers.getMapper(FoodCatalogueMapper.class);

    FoodCatalogue foodCatalogueDTOToEntity(FoodCatalogueDTO foodCatalogueDTO);

    FoodCatalogueDTO foodCatalogueEntityToDTO(FoodCatalogue foodCatalogue);
}
