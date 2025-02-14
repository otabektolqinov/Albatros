package com.example.albartros.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoverageDetailsDto {

    private Long id;
    @Positive(message = "planId cannot be zero or negative")
    @NotNull(message = "planId cannot be null")
    private Long planId;
    @NotBlank(message = "coverage type cannot be null, blank or empty")
    private String coverageType;
    private Double coverageLimit;
    private String exclusions;

}
