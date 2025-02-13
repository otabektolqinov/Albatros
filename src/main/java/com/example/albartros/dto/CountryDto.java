package com.example.albartros.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CountryDto {

    private Long id;
    @NotNull(message = "name cannot be null")
    private String name;
    @NotNull(message = "capital cannot be null")
    private String capital;
    @NotNull(message = "languages cannot be null")
    private List<String> languages;
    @NotNull(message = "currency cannot be null")
    private String currency;
    @NotNull(message = "timeFormat cannot be null")
    private String timeFormat;
    private String description;
    @NotNull(message = "location cannot be null")
    private String location;
    @NotNull(message = "climate cannot be null")
    private String climate;
    private List<DestinationDto> destinations;
    private List<FactDto> facts;
    private List<FoodDto> foods;
    private List<HotelDto> hotels;
    private List<MemoDto> memos;

}
