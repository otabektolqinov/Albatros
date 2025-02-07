package com.example.albartros.service.impl;

import com.example.albartros.dto.DestinationDto;
import com.example.albartros.dto.HttpApiResponse;
import com.example.albartros.model.Destination;
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
    private final DestinationMapper destinationMapper;


    @Override
    public HttpApiResponse<DestinationDto> createDestination(DestinationDto dto) {
        // check country
        try {
            return HttpApiResponse.<DestinationDto>builder()
                    .status(HttpStatus.CREATED)
                    .message("OK")
                    .content(this.destinationMapper.toDto(this.destinationRepository.saveAndFlush(
                            this.destinationMapper.toEntity(dto)
                    )))
                    .build();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public HttpApiResponse<DestinationDto> getDestinationById(Long id) {
        try {
            if (this.destinationRepository.existsById(id)) {
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
            throw new RuntimeException();
        }

    }

    @Override
    public HttpApiResponse<List<DestinationDto>> getAllDestinations() {
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
    }

    @Override
    public HttpApiResponse<DestinationDto> updateDestination(Long id, DestinationDto dto) {
        try {
            if (this.destinationRepository.existsById(id)) {
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
            throw new RuntimeException();
        }

    }

    @Override
    public HttpApiResponse<String> deleteDestinationById(Long id) {
        try {
            if (this.destinationRepository.existsById(id)) {
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
            throw new RuntimeException();
        }
    }

    public List<Destination> getAllDestinationList() {
        return this.destinationRepository.findAllByDeletedAtIsNull();
    }
}
