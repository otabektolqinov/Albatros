package com.example.albartros.service.mapper;

import com.example.albartros.dto.BookingDto;
import com.example.albartros.model.Bookings;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class BookingMapper {

    @Named("toDto")
    @Mapping(target = "insuranceList", ignore = true)
    public abstract BookingDto toDto(Bookings bookings);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "insuranceList", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    public abstract Bookings toEntity(BookingDto bookingDto);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "insuranceList", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Bookings updateBooking(@MappingTarget Bookings bookings, BookingDto bookingDto);

    @IterableMapping(qualifiedByName = "toDto")
    public abstract List<BookingDto> toDto(List<Bookings> bookings);
}
