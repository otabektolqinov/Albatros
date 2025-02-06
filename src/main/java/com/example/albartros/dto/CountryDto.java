package com.example.albartros.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CountryDto {

    private Long id;
    private String name;
    private String capital;
    private List<String> languages;
    private String currency;
    private String timeFormat;
    private String description;
    private String location;
    private String climate;
    private List<DestinationDto> destinations;
    private List<FactDto> facts;
    private List<FoodDto> foods;
    private List<HotelDto> hotels;
    private List<MemoDto> memos;

}
