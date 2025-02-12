package com.example.albartros.service.impl;

import com.example.albartros.dto.DiscountDto;
import com.example.albartros.dto.HttpApiResponse;
import com.example.albartros.exception.ContentNotFoundException;
import com.example.albartros.exception.DatabaseException;
import com.example.albartros.model.Discount;
import com.example.albartros.model.Tours;
import com.example.albartros.repository.DiscountRepository;
import com.example.albartros.repository.TourRepository;
import com.example.albartros.service.DiscountService;
import com.example.albartros.service.mapper.DiscountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DiscountServiceImpl implements DiscountService {
    private final DiscountRepository discountRepository;
    private final TourRepository tourRepository;
    private final DiscountMapper discountMapper;

    @Override
    public HttpApiResponse<DiscountDto> createDiscount(DiscountDto dto) {
        try {
            if (this.tourRepository.findByIdAndDeletedAtIsNull(dto.getTourId()).isPresent()) {
                Tours tours = this.tourRepository.findByIdAndDeletedAtIsNull(dto.getTourId()).get();
                Discount entity = this.discountMapper.toEntity(dto);

                entity.setTour(tours);
                tours.setDiscount(entity);

                tours.setPrice(tours.getPrice() * (entity.getPercent() / 100.0));
                tourRepository.saveAndFlush(tours);

                this.discountRepository.save(entity);

                return HttpApiResponse.<DiscountDto>builder()
                        .status(HttpStatus.CREATED)
                        .message("OK")
                        .content(this.discountMapper.toDto(entity))
                        .build();
            }
            throw new ContentNotFoundException("Tour not found");
        } catch (Exception e) {
            throw new DatabaseException("unable to create discount");
        }
    }

    @Override
    public HttpApiResponse<DiscountDto> getDiscountById(Long id) {
        try {
            if (this.discountRepository.existsByIdAndDeletedAtIsNull(id)) {
                Optional<Discount> optional = this.discountRepository.findById(id);
                if (optional.isPresent()) {

                    return HttpApiResponse.<DiscountDto>builder()
                            .status(HttpStatus.OK)
                            .message("OK")
                            .content(this.discountMapper.toDto(optional.get()))
                            .build();
                }
            }
            throw new ContentNotFoundException("Discount not found");
        } catch (Exception e) {
            throw new DatabaseException("unable to get discount by id");
        }
    }

    @Override
    public HttpApiResponse<List<DiscountDto>> getAllDiscounts() {
        try {
            if (this.tourRepository.findAllByDeletedAtIsNull().isEmpty()) {
                throw new ContentNotFoundException("Discounts not found");
            }
            List<DiscountDto> dtoList = this.discountMapper.toDtoList(this.discountRepository.findAllByDeletedAtIsNull());

            return HttpApiResponse.<List<DiscountDto>>builder()
                    .status(HttpStatus.OK)
                    .message("OK")
                    .content(dtoList)
                    .build();
        } catch (Exception e) {
            throw new DatabaseException("unable to get discounts");
        }
    }

    @Override
    public HttpApiResponse<DiscountDto> updateDiscountById(Long id, DiscountDto dto) {
        try {
            if (this.discountRepository.existsByIdAndDeletedAtIsNull(id)) {
                Optional<Discount> optional = this.discountRepository.findById(id);
                if (optional.isPresent()) {
                    Discount updateDiscount = this.discountMapper.updateDiscount(optional.get(), dto);
                    this.discountRepository.save(updateDiscount);
                    return HttpApiResponse.<DiscountDto>builder()
                            .status(HttpStatus.OK)
                            .message("OK")
                            .content(this.discountMapper.toDto(updateDiscount))
                            .build();
                }
            }
            throw new ContentNotFoundException("Discount not found");
        } catch (Exception e) {
            throw new DatabaseException("unable to update discount");
        }
    }

    @Override
    public HttpApiResponse<String> deleteDiscountById(Long id) {
        try {
            if (this.discountRepository.existsByIdAndDeletedAtIsNull(id)) {
                Optional<Discount> optional = this.discountRepository.findById(id);
                if (optional.isPresent()) {
                    optional.get().setDeletedAt(LocalDateTime.now());
                    discountRepository.save(optional.get());
                    return HttpApiResponse.<String>builder()
                            .status(HttpStatus.OK)
                            .message("Deleted successfully")
                            .build();
                }
            }
            throw new ContentNotFoundException("Discount not found");
        } catch (Exception e) {
            throw new DatabaseException("unable to delete discount");
        }
    }
}
