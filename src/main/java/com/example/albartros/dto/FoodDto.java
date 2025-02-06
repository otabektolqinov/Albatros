package com.example.albartros.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FoodDto {

    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private Long countryId;

}
