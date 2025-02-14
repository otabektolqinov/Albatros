package com.example.albartros.service.mapper;

import com.example.albartros.dto.CoverageDetailsDto;
import com.example.albartros.model.CoverageDetails;
import com.example.albartros.repository.InsurancePlanRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;


@Mapper(componentModel = "spring")
public abstract class CoverageDetailsMapper {

    @Autowired
    public InsurancePlanRepository planRepository;

    @Mapping(target = "planId", expression = "java(details.getInsurancePlan().getId())")
    public abstract CoverageDetailsDto toDto(CoverageDetails details);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "insurancePlan",
            expression = "java(planRepository.findByIdAndDeletedAtIsNull(details.getPlanId()).orElse(null))")
    public abstract CoverageDetails toEntity(CoverageDetailsDto details);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "insurancePlan", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract CoverageDetails updateAllFields(@MappingTarget CoverageDetails details, CoverageDetailsDto dto);

}
