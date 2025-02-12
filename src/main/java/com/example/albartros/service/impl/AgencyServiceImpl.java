package com.example.albartros.service.impl;

import com.example.albartros.dto.AgencyDto;
import com.example.albartros.dto.HttpApiResponse;
import com.example.albartros.dto.ToursDto;
import com.example.albartros.exception.ContentNotFoundException;
import com.example.albartros.exception.DatabaseException;
import com.example.albartros.model.Agency;
import com.example.albartros.repository.AgencyRepository;
import com.example.albartros.service.AgencyService;
import com.example.albartros.service.mapper.AgencyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AgencyServiceImpl implements AgencyService {
    private final AgencyRepository agencyRepository;
    private final AgencyMapper agencyMapper;

    @Override
    public HttpApiResponse<AgencyDto> createAgency(AgencyDto dto) {
        try {
            Agency entity = this.agencyMapper.toEntity(dto);

            this.agencyRepository.save(entity);

            return HttpApiResponse.<AgencyDto>builder()
                    .status(HttpStatus.CREATED)
                    .message("OK")
                    .content(this.agencyMapper.toDto(entity))
                    .build();
        } catch (Exception e) {
            throw new DatabaseException("Unable to create new agency");
        }
    }

    @Override
    public HttpApiResponse<AgencyDto> getAgencyById(Long id) {
        try {
            if (this.agencyRepository.existsByIdAndDeletedAtIsNull(id)) {
                Optional<Agency> agency = this.agencyRepository.findById(id);
                if (agency.isPresent()) {
                    return HttpApiResponse.<AgencyDto>builder()
                            .status(HttpStatus.OK)
                            .message("OK")
                            .content(this.agencyMapper.toDto(agency.get()))
                            .build();
                }
            }
            throw new ContentNotFoundException("Agency with id " + id + " not found");
        } catch (Exception e) {
            throw new DatabaseException("Unable to get agency by id");
        }
    }

    @Override
    public HttpApiResponse<List<AgencyDto>> getAllAgency() {
        try {
            if (!this.agencyRepository.findAllByDeletedAtIsNull().isEmpty()) {
                List<AgencyDto> agencyDtoList = this.agencyMapper.toDtoList(this.agencyRepository.findAllByDeletedAtIsNull());

                return HttpApiResponse.<List<AgencyDto>>builder()
                        .status(HttpStatus.OK)
                        .message("OK")
                        .content(agencyDtoList)
                        .build();
            }
            throw new ContentNotFoundException("No agencies found");
        } catch (Exception e) {
            throw new DatabaseException("Unable to get all agencies");
        }
    }

    @Override
    public HttpApiResponse<AgencyDto> updateTourById(Long id, AgencyDto dto) {
        try {
            if (this.agencyRepository.existsByIdAndDeletedAtIsNull(id)) {
                Optional<Agency> agency = this.agencyRepository.findById(id);
                if (agency.isPresent()) {
                    Agency updateAgency = this.agencyMapper.updateAgency(agency.get(), dto);

                    this.agencyRepository.save(updateAgency);

                    return HttpApiResponse.<AgencyDto>builder()
                            .status(HttpStatus.OK)
                            .message("OK")
                            .content(this.agencyMapper.toDto(updateAgency))
                            .build();
                }
            }
            throw new ContentNotFoundException("Agency with id " + id + " not found");
        } catch (Exception e) {
            throw new DatabaseException("Unable to update tour with id " + id);
        }
    }

    @Override
    public HttpApiResponse<String> deleteTourById(Long id) {
        try {
            if (this.agencyRepository.existsByIdAndDeletedAtIsNull(id)) {
                Optional<Agency> agency = this.agencyRepository.findById(id);
                if (agency.isPresent()) {
                    agency.get().setDeletedAt(LocalDateTime.now());
                    return HttpApiResponse.<String>builder()
                            .status(HttpStatus.OK)
                            .message("Agency with id " + id + " is deleted")
                            .build();
                }
            }
            throw new ContentNotFoundException("Agency with id " + id + " not found");
        } catch (Exception e) {
            throw new DatabaseException("Unable to delete tour with id " + id);
        }
    }
}
