package com.example.albartros.service.mapper;

import com.example.albartros.dto.EventDto;
import com.example.albartros.model.Event;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class EventMapper {

    @Mapping(target = "photos", ignore = true)
    public abstract Event toEntity(EventDto dto);

    @Named("toDto")
    @Mapping(target = "photos", ignore = true)
    public abstract EventDto toDto(Event event);

    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "photos", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Event updateAllFields(@MappingTarget Event event, EventDto dto);

    @IterableMapping(qualifiedByName = "toDto")
    public abstract List<EventDto> toDtoList(List<Event> events);

}
