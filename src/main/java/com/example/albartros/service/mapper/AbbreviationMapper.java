package com.example.albartros.service.mapper;

import com.example.albartros.dto.AbbreviationDto;
import com.example.albartros.model.Abbreviation;
import com.example.albartros.repository.AbbreviationCategoryRepository;
import jdk.jfr.Name;
import lombok.RequiredArgsConstructor;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class AbbreviationMapper {

    @Autowired
    public AbbreviationCategoryRepository categoryRepository;

    @Named("toDto")
    @Mapping(target = "categoryId", expression = "java(abbreviation.getCategory().getId())")
    public abstract AbbreviationDto toDto(Abbreviation abbreviation);

    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "category",
            expression = "java(categoryRepository.findByIdAndDeletedAtIsNull(dto.getCategoryId()).orElse(null))")
    public abstract Abbreviation toEntity(AbbreviationDto dto);

    @Mapping(target = "category", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Abbreviation updateAllFields(@MappingTarget Abbreviation abbreviation, AbbreviationDto dto);

    @IterableMapping(qualifiedByName = "toDto")
    public abstract List<AbbreviationDto> toDtoList(List<Abbreviation> abbreviations);
}
