package com.example.albartros.service.mapper;

import com.example.albartros.dto.AgencyDto;
import com.example.albartros.model.Agency;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class AgencyMapper {

    @Named("toDto")
    public abstract AgencyDto toDto(Agency agency);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "bookings", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    public abstract Agency toEntity(AgencyDto agencyDto);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "bookings", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Agency updateAgency(@MappingTarget Agency agency, AgencyDto agencyDto);

    @IterableMapping(qualifiedByName = "toDto")
    public abstract List<AgencyDto> toDtoList(List<Agency> agencies);
}
