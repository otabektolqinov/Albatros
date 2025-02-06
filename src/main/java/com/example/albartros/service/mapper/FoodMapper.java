package com.example.albartros.service.mapper;

import com.example.albartros.dto.FoodDto;
import com.example.albartros.model.Food;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class FoodMapper {

    @Mapping(target = "country", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    public abstract Food toEntity(FoodDto dto);

    @Named("toDto")
    @Mapping(target = "countryId", ignore = true)
    public abstract FoodDto toDto(Food food);

    @Mapping(target = "country", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Food updateAllFields(@MappingTarget Food food, FoodDto dto);

    @IterableMapping(qualifiedByName = "toDto")
    public abstract List<FoodDto> toDtoList(List<Food> foods);

}
