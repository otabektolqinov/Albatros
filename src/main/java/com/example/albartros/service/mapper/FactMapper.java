package com.example.albartros.service.mapper;

import com.example.albartros.dto.FactDto;
import com.example.albartros.model.Facts;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FactMapper {

    public FactDto toDto(Facts entity) {
        return FactDto.builder()
                .id(entity.getId())
                .description(entity.getDescription())
                .countryId(entity.getCountry().getId())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    public Facts toEntity(FactDto dto) {
        return Facts.builder()
                .description(dto.getDescription())
                .build();
    }

    public Facts updateFact(FactDto dto, Facts entity) {
        if (dto.getDescription() != null) {
            entity.setDescription(dto.getDescription());
        }
        return entity;
    }

    public List<FactDto> toDtoList(List<Facts> facts) {
        return facts.stream().map(this::toDto).toList();
    }
}
