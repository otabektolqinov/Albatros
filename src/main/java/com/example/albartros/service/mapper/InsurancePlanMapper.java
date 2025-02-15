package com.example.albartros.service.mapper;

import com.example.albartros.dto.InsurancePlanDto;
import com.example.albartros.model.InsurancePlan;
import com.example.albartros.repository.InsuranceCompanyRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;

import java.util.List;

@Primary
@Mapper(componentModel = "spring")
public abstract class InsurancePlanMapper {

    @Autowired
    public InsuranceCompanyRepository companyRepository;

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "insuranceCompany",
            expression = "java(companyRepository.findByIdAndDeletedAtIsNull(dto.getInsuranceCompanyId()).orElse(null))")
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "coverageDetails", ignore = true)
    public abstract InsurancePlan toEntity(InsurancePlanDto dto);

    @Named("toDto")
    @Mapping(target = "coverageDetailsId", ignore = true)
    @Mapping(target = "insuranceCompanyId", expression = "java(insurancePlan.getInsuranceCompany().getId())")
    public abstract InsurancePlanDto toDto(InsurancePlan insurancePlan);

    @Mapping(target = "insuranceCompany", ignore = true)
    @Mapping(target = "coverageDetails", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract InsurancePlan updateAllFields(@MappingTarget InsurancePlan insurancePlan, InsurancePlanDto dto);

    @IterableMapping(qualifiedByName = "toDto")
    public abstract List<InsurancePlanDto> toDtoList(List<InsurancePlan> insurancePlans);
}
