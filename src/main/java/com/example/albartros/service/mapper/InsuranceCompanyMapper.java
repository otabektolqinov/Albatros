package com.example.albartros.service.mapper;

import com.example.albartros.dto.InsuranceCompanyDto;
import com.example.albartros.model.InsuranceCompany;
import com.example.albartros.repository.InsurancePlanRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;

import java.util.List;

@Primary
@Mapper(componentModel = "spring")
public abstract class InsuranceCompanyMapper {

    @Autowired
    public InsurancePlanRepository insurancePlanRepository;
    @Autowired
    public InsurancePlanMapper insurancePlanMapper;

    @Mapping(target = "planList", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    public abstract InsuranceCompany toEntity(InsuranceCompanyDto dto);

    @Named("toDto")
    @Mapping(target = "planList", ignore = true)
    public abstract InsuranceCompanyDto toDto(InsuranceCompany company);

    @Mapping(target = "planList",
            expression =
                    "java(insurancePlanMapper.toDtoList(insurancePlanRepository.findAllByDeletedAtIsNullAndInsuranceCompany_Id(company.getId())))")
    public abstract InsuranceCompanyDto toDtoWithAllEntity(InsuranceCompany company);

    @IterableMapping(qualifiedByName = "toDto")
    public abstract List<InsuranceCompanyDto> toDtoList(List<InsuranceCompany> insuranceCompanies);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract InsuranceCompany updateAllFields(@MappingTarget InsuranceCompany company, InsuranceCompanyDto dto);

}
