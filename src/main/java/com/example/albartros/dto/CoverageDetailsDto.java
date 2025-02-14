package com.example.albartros.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoverageDetailsDto {

    private Long id;
    private Long planId;
    private String coverageType;
    private Double coverageLimit;
    private String exclusions;

}
