package com.example.albartros.service.impl;

import com.example.albartros.dto.HttpApiResponse;
import com.example.albartros.dto.ToursDto;
import com.example.albartros.exception.ContentNotFoundException;
import com.example.albartros.exception.DatabaseException;
import com.example.albartros.model.Destination;
import com.example.albartros.model.Tours;
import com.example.albartros.repository.DestinationRepository;
import com.example.albartros.repository.TourRepository;
import com.example.albartros.service.TourService;
import com.example.albartros.service.mapper.TourMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TourServiceImpl implements TourService {
    private final TourRepository tourRepository;
    private final DestinationRepository destinationRepository;
    private final TourMapper tourMapper;

    @Override
    public HttpApiResponse<ToursDto> createTour(ToursDto dto) {
        Destination fromDestination = destinationRepository.findByIdAndDeletedAtIsNull(
                dto.getFromDestinationId()).orElseThrow(() -> new ContentNotFoundException("Destination not found"));

        Destination toDestination = destinationRepository.findByIdAndDeletedAtIsNull(
                dto.getToDestinationId()).orElseThrow(() -> new ContentNotFoundException("Destination not found"));


        Tours entity = this.tourMapper.toEntity(dto);

        entity.setFromDestination(fromDestination);
        entity.setToDestination(toDestination);

            this.tourRepository.save(entity);

            return HttpApiResponse.<ToursDto>builder()
                    .status(HttpStatus.CREATED)
                    .message("OK")
                    .content(this.tourMapper.toDto(entity))
                    .build();

    }

    @Override
    public HttpApiResponse<ToursDto> getTourById(Long id) {
        try {
            if (this.tourRepository.existsByIdAndDeletedAtIsNull(id)) {
                Optional<Tours> entity = this.tourRepository.findById(id);
                if (entity.isPresent()) {
                    return HttpApiResponse.<ToursDto>builder()
                            .status(HttpStatus.OK)
                            .message("OK")
                            .content(this.tourMapper.toDto(entity.get()))
                            .build();
                }
            }
            throw new ContentNotFoundException("Unable to find tour with id " + id);
        } catch (Exception e) {
            throw new ContentNotFoundException("Unable to find tour");
        }
    }

    @Override
    public HttpApiResponse<List<ToursDto>> getAllTours() {
        try {
            if (this.tourRepository.findAllByDeletedAtIsNull().isEmpty()) {
                List<ToursDto> toursDtoList = this.tourMapper.toursDtoList(this.tourRepository.findAllByDeletedAtIsNull());
                return HttpApiResponse.<List<ToursDto>>builder()
                        .status(HttpStatus.OK)
                        .message("OK")
                        .content(toursDtoList)
                        .build();
            }
            throw new ContentNotFoundException("Unable to find tours");
        } catch (Exception e) {
            throw new ContentNotFoundException("Unable to get all tours");
        }
    }

    @Override
    public HttpApiResponse<ToursDto> updateTourById(Long id, ToursDto dto) {
        try {
            if (this.tourRepository.existsByIdAndDeletedAtIsNull(id)) {
                Optional<Tours> entity = this.tourRepository.findById(id);
                if (entity.isPresent()) {
                    Tours updateTour = this.tourMapper.updateTour(entity.get(), dto);

                    this.tourRepository.save(updateTour);

                    return HttpApiResponse.<ToursDto>builder()
                            .status(HttpStatus.OK)
                            .message("OK")
                            .content(this.tourMapper.toDto(updateTour))
                            .build();
                }
            }
            throw new ContentNotFoundException("Unable to find tour with id " + id);
        } catch (Exception e) {
            throw new DatabaseException("Unable to update tour with id " + id);
        }
    }

    @Override
    public HttpApiResponse<String> deleteTourById(Long id) {
        try {
            if (this.tourRepository.existsByIdAndDeletedAtIsNull(id)) {
                Optional<Tours> entity = this.tourRepository.findById(id);
                if (entity.isPresent()) {
                    entity.get().setDeletedAt(LocalDateTime.now());

                    tourRepository.save(entity.get());

                    return HttpApiResponse.<String>builder()
                            .status(HttpStatus.OK)
                            .message("Tour deleted successfully")
                            .build();
                }
            }
            throw new ContentNotFoundException("Unable to find tour with id " + id);
        } catch (Exception e) {
            throw new DatabaseException("Unable to delete tour with id " + id);
        }
    }
}
