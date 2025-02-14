package com.example.albartros.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InsurancePlanDto {

    private Long id;
    @NotBlank(message = "plan Name cannot be blank, empty or null")
    private String planName;
    private Double price;
    private String description;
    @Positive(message = "insurance Company id cannot be negative or zero")
    private Long insuranceCompanyId;
    private Long coverageDetailsId;

}
