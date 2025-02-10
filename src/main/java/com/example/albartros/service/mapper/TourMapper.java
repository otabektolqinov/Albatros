package com.example.albartros.service.mapper;

import com.example.albartros.dto.ToursDto;
import com.example.albartros.model.Tours;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class TourMapper {
    @Named("toDto")
    public abstract ToursDto toDto(Tours tours);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    public abstract Tours toEntity(ToursDto toursDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Tours updateTour(@MappingTarget Tours tours, ToursDto toursDto);

    @IterableMapping(qualifiedByName = "toDto")
    public abstract List<ToursDto> toursDtoList(List<Tours> tours);


}
