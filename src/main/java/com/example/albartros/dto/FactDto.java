package com.example.albartros.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FactDto {
    private Long id;
    private String description;
    private Long countryId;
}
