package com.example.albartros.service.impl;

import com.example.albartros.dto.FoodDto;
import com.example.albartros.dto.HttpApiResponse;
import com.example.albartros.exception.ContentNotFoundException;
import com.example.albartros.exception.DatabaseException;
import com.example.albartros.model.Country;
import com.example.albartros.model.Food;
import com.example.albartros.repository.CountryRepository;
import com.example.albartros.repository.FoodRepository;
import com.example.albartros.service.FoodService;
import com.example.albartros.service.mapper.FoodMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;
    private final CountryRepository countryRepository;
    private final FoodMapper foodMapper;

    @Override
    public HttpApiResponse<FoodDto> createFood(FoodDto dto) {
        try {
            Food entity = foodMapper.toEntity(dto);
            if (!countryRepository.existsByIdAndDeletedAtIsNull(dto.getCountryId())){
                throw new ContentNotFoundException(String.format("There is no country with %d id",dto.getCountryId()));
            }
            Country country = countryRepository.findByIdAndDeletedAtIsNull(dto.getCountryId()).get();
            entity.setCountry(country);
            Food saved = foodRepository.save(entity);
            return HttpApiResponse.<FoodDto>builder()
                    .status(HttpStatus.CREATED)
                    .message("ok")
                    .content(foodMapper.toDto(saved))
                    .build();
        } catch (ContentNotFoundException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public HttpApiResponse<FoodDto> getFoodById(Long id) {
        try {
            Optional<Food> optional = foodRepository.findById(id);
            if (optional.isEmpty()){
                throw new ContentNotFoundException(String.format("Food with %d id is not found", id));
            }
            Food food = optional.get();
            return HttpApiResponse.<FoodDto>builder()
                    .content(foodMapper.toDto(food))
                    .message("OK")
                    .status(HttpStatus.OK)
                    .build();
        } catch (ContentNotFoundException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public HttpApiResponse<FoodDto> updateFoodById(Long id, FoodDto dto) {
        try {
            Optional<Food> optional = foodRepository.findById(id);
            if (optional.isEmpty()){
                throw new ContentNotFoundException(String.format("Food with %d id is not found", id));
            }
            Food food = optional.get();
            Food updated = foodMapper.updateAllFields(food, dto);
            return HttpApiResponse.<FoodDto>builder()
                    .status(HttpStatus.OK)
                    .message("Successfully updated")
                    .content(foodMapper.toDto(updated))
                    .build();
        } catch (ContentNotFoundException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public HttpApiResponse<FoodDto> deleteFoodById(Long id) {
        try {
            Optional<Food> optional = foodRepository.findById(id);
            if (optional.isEmpty()){
                throw new ContentNotFoundException(String.format("Food with %d id is not found", id));
            }
            Food food = optional.get();
            food.setDeletedAt(LocalDateTime.now());
            foodRepository.save(food);
            return HttpApiResponse.<FoodDto>builder()
                    .message("Successfully deleted")
                    .status(HttpStatus.OK)
                    .build();
        } catch (ContentNotFoundException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public HttpApiResponse<List<FoodDto>> getAllFoodByCountry(Long countryId) {
        try {
            List<Food> allByCountryId = foodRepository.findAllByCountry_Id(countryId);
            if (allByCountryId.isEmpty()){
                throw new ContentNotFoundException("There is no food associated with this country");
            }
            List<FoodDto> dtoList = foodMapper.toDtoList(allByCountryId);
            return HttpApiResponse.<List<FoodDto>>builder()
                    .status(HttpStatus.OK)
                    .message("OK")
                    .content(dtoList)
                    .build();
        } catch (ContentNotFoundException e) {
            throw new DatabaseException(e.getMessage());
        }

    }
}
