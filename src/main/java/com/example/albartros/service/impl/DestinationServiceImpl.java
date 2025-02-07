package com.example.albartros.service.impl;

import com.example.albartros.dto.DestinationDto;
import com.example.albartros.dto.HttpApiResponse;
import com.example.albartros.exception.ContentNotFoundException;
import com.example.albartros.exception.DatabaseException;
import com.example.albartros.model.Country;
import com.example.albartros.model.Destination;
import com.example.albartros.repository.CountryRepository;
import com.example.albartros.repository.DestinationRepository;
import com.example.albartros.service.DestinationService;
import com.example.albartros.service.mapper.DestinationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DestinationServiceImpl implements DestinationService {
    private final DestinationRepository destinationRepository;
    private final CountryRepository countryRepository;
    private final DestinationMapper destinationMapper;


    @Override
    public HttpApiResponse<DestinationDto> createDestination(DestinationDto dto) {
        if (!countryRepository.existsByIdAndDeletedAtIsNull(dto.getCountryId())) {
            throw new ContentNotFoundException("Country not found");
        }
        try {
            Optional<Country> country = countryRepository.findByIdAndDeletedAtIsNull(dto.getCountryId());
            if (country.isPresent()) {
                Destination entity = this.destinationMapper.toEntity(dto);

                entity.setCountry(country.get());

                this.destinationRepository.save(entity);

                return HttpApiResponse.<DestinationDto>builder()
                        .status(HttpStatus.CREATED)
                        .message("OK")
                        .content(this.destinationMapper.toDto(entity))
                        .build();
            }
            return HttpApiResponse.<DestinationDto>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Country not found")
                    .build();
        } catch (Exception e) {
            throw new DatabaseException("Unable to create a new destination");
        }
    }

    @Override
    public HttpApiResponse<DestinationDto> getDestinationById(Long id) {
        try {
            if (this.destinationRepository.existsByIdAndDeletedAtIsNull(id)) {
                Optional<Destination> destination = this.destinationRepository.findByIdAndDeletedAtIsNull(id);
                if (destination.isPresent()) {
                    return HttpApiResponse.<DestinationDto>builder()
                            .status(HttpStatus.OK)
                            .message("OK")
                            .content(this.destinationMapper.toDto(destination.get()))
                            .build();
                }
            }
            return HttpApiResponse.<DestinationDto>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Destination Not Found")
                    .build();
        } catch (Exception e) {
            throw new ContentNotFoundException("Destination not found");
        }

    }

    @Override
    public HttpApiResponse<List<DestinationDto>> getAllDestinations() {
        try {
            if (!this.destinationRepository.findAllByDeletedAtIsNull().isEmpty()) {
                return HttpApiResponse.<List<DestinationDto>>builder()
                        .status(HttpStatus.OK)
                        .message("OK")
                        .content(this.destinationMapper.toDtoList(getAllDestinationList()))
                        .build();
            }
            return HttpApiResponse.<List<DestinationDto>>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Destination List is empty")
                    .build();
        } catch (ContentNotFoundException e) {
            throw new ContentNotFoundException("Destination List is empty");
        }
    }

    @Override
    public HttpApiResponse<DestinationDto> updateDestination(Long id, DestinationDto dto) {
        try {
            if (this.destinationRepository.existsByIdAndDeletedAtIsNull(id)) {
                Optional<Destination> destination = this.destinationRepository.findByIdAndDeletedAtIsNull(id);
                if (destination.isPresent()) {
                    Destination updateDestination = this.destinationMapper.updateDestination(destination.get(), dto);
                    this.destinationRepository.saveAndFlush(updateDestination);
                    return HttpApiResponse.<DestinationDto>builder()
                            .status(HttpStatus.OK)
                            .message("OK")
                            .content(this.destinationMapper.toDto(updateDestination))
                            .build();
                }
            }
            return HttpApiResponse.<DestinationDto>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Destination Not Found")
                    .build();
        } catch (Exception e) {
            throw new DatabaseException("Unable to update destination");
        }

    }

    @Override
    public HttpApiResponse<String> deleteDestinationById(Long id) {
        try {
            if (this.destinationRepository.existsByIdAndDeletedAtIsNull(id)) {
                Optional<Destination> destination = this.destinationRepository.findByIdAndDeletedAtIsNull(id);
                if (destination.isPresent()) {
                    destination.get().setDeletedAt(LocalDateTime.now());
                    this.destinationRepository.saveAndFlush(destination.get());
                    return HttpApiResponse.<String>builder()
                            .status(HttpStatus.OK)
                            .message("Destination deleted successfully")
                            .build();
                }
            }
            return HttpApiResponse.<String>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Destination Not Found")
                    .build();
        } catch (Exception e) {
            throw new ContentNotFoundException("Destination not found");
        }
    }

    public List<Destination> getAllDestinationList() {
        return this.destinationRepository.findAllByDeletedAtIsNull();
    }
}
