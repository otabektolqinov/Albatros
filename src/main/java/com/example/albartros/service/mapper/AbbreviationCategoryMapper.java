package com.example.albartros.service.mapper;

import com.example.albartros.dto.AbbreviationCategoryDto;
import com.example.albartros.model.Abbreviation;
import com.example.albartros.model.AbbreviationCategory;
import org.mapstruct.*;
import org.springframework.context.annotation.Primary;

@Primary
@Mapper(componentModel = "spring")
public abstract class AbbreviationCategoryMapper {

    @Mapping(target = "abbreviations", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    public abstract AbbreviationCategory toEntity(AbbreviationCategoryDto dto);

    @Mapping(target = "abbreviations", ignore = true)
    public abstract AbbreviationCategoryDto toDto(AbbreviationCategory category);

    @Mapping(target = "abbreviations", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract AbbreviationCategory updateAllFields(@MappingTarget AbbreviationCategory category, AbbreviationCategoryDto dto);
}
