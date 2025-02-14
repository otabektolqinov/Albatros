package com.example.albartros.service.mapper;

import com.example.albartros.dto.ErrorDto;
import com.example.albartros.dto.InsurancePurchaseDto;
import com.example.albartros.model.InsurancePurchase;
import com.example.albartros.repository.InsurancePlanRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class InsurancePurchaseMapper {

    @Autowired
    public InsurancePlanRepository insurancePlanRepository;

    @Mapping(target = "bookings", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "insurancePlan",
            expression = "java(insurancePlanRepository.findById(dto.getInsurancePlanId()).orElse(null))")
    public abstract InsurancePurchase toEntity(InsurancePurchaseDto dto);

    // bookings should be set
    @Mapping(target = "bookingsId", ignore = true)
    @Mapping(target = "insurancePlanId", expression = "java(insurancePurchase.getInsurancePlan().getId())")
    public abstract InsurancePurchaseDto toDto(InsurancePurchase insurancePurchase);

    @Mapping(target = "bookings", ignore = true)
    @Mapping(target = "insurancePlan", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract InsurancePurchase updateAllFields(@MappingTarget InsurancePurchase purchase, InsurancePurchaseDto dto);
}
