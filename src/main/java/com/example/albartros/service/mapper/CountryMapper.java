package com.example.albartros.service.mapper;

import com.example.albartros.dto.CountryDto;
import com.example.albartros.model.Country;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class CountryMapper {

    @Mapping(target = "memos", ignore = true)
    @Mapping(target = "hotels", ignore = true)
    @Mapping(target = "facts", ignore = true)
    @Mapping(target = "foods", ignore = true)
    @Mapping(target = "destinations", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    public abstract Country toEntity(CountryDto dto);

    @Named("toDto")
    @Mapping(target = "memos", ignore = true)
    @Mapping(target = "hotels", ignore = true)
    @Mapping(target = "facts", ignore = true)
    @Mapping(target = "foods", ignore = true)
    @Mapping(target = "destinations", ignore = true)
    public abstract CountryDto toDto(Country country);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "memos", ignore = true)
    @Mapping(target = "hotels", ignore = true)
    @Mapping(target = "facts", ignore = true)
    @Mapping(target = "foods", ignore = true)
    @Mapping(target = "destinations", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Country updateAllFields(@MappingTarget Country country, CountryDto dto);

    @IterableMapping(qualifiedByName = "toDto")
    public abstract List<CountryDto> toDtoList(List<Country> countries);
}
