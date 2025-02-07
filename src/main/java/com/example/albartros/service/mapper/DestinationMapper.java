package com.example.albartros.service.mapper;

import com.example.albartros.dto.DestinationDto;
import com.example.albartros.model.Destination;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class DestinationMapper {

    public DestinationDto toDto(Destination destination) {
        return DestinationDto.builder()
                .id(destination.getId())
                .name(destination.getName())
                .description(destination.getDescription())
                .image_url(destination.getImage_url())
                .createdAt(destination.getCreatedAt())
                .updatedAt(destination.getUpdatedAt())
                .deletedAt(destination.getDeletedAt())
//                .countryId(destination.getCountry().getId())
                .build();
    }

    public Destination toEntity(DestinationDto dto) {
        return Destination.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .image_url(dto.getImage_url())
                .build();
    }

    public Destination updateDestination(Destination destination, DestinationDto dto) {
        if (dto.getName() != null) {
            destination.setName(dto.getName());
        }
        if (dto.getDescription() != null) {
            destination.setDescription(dto.getDescription());
        }
        if (dto.getImage_url() != null) {
            destination.setImage_url(dto.getImage_url());
        }
//        if (dto.getCountryId() != null) {
//            destination.setCountry();
//        }
        return destination;
    }

    public List<DestinationDto> toDtoList(List<Destination> destinations) {
        List<DestinationDto> destinationDtos = new ArrayList<>();
        for (Destination destination : destinations) {
            destinationDtos.add(toDto(destination));
        }
        return destinationDtos;
    }
}
