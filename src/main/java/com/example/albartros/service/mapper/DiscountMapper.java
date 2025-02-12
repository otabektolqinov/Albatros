package com.example.albartros.service.mapper;

import com.example.albartros.dto.DiscountDto;
import com.example.albartros.model.Discount;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class DiscountMapper {

    @Named("toDto")
    public abstract DiscountDto toDto(Discount discount);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    public abstract Discount toEntity(DiscountDto discountDto);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tour", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Discount updateDiscount(@MappingTarget Discount discount, DiscountDto discountDto);

    @IterableMapping(qualifiedByName = "toDto")
    public abstract List<DiscountDto> toDtoList(List<Discount> discounts);

}
