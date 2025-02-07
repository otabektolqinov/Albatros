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
    @NotBlank(message = "name cannot be blank")
    @NotNull(message = "name cannot be null")
    private String name;
    @NotBlank(message = "capital cannot be blank")
    @NotNull(message = "capital cannot be null")
    private String capital;
    @NotBlank(message = "languages cannot be blank")
    @NotNull(message = "languages cannot be null")
    private List<String> languages;
    @NotBlank(message = "currency cannot be blank")
    @NotNull(message = "currency cannot be null")
    private String currency;
    @NotBlank(message = "timeFormat cannot be blank")
    @NotNull(message = "timeFormat cannot be null")
    private String timeFormat;
    private String description;
    @NotBlank(message = "location cannot be blank")
    @NotNull(message = "location cannot be null")
    private String location;
    @NotBlank(message = "climate cannot be blank")
    @NotNull(message = "climate cannot be null")
    private String climate;
    private List<DestinationDto> destinations;
    private List<FactDto> facts;
    private List<FoodDto> foods;
    private List<HotelDto> hotels;
    private List<MemoDto> memos;

}
