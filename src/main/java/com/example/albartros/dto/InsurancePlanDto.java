package com.example.albartros.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InsurancePlanDto {

    private Long id;
    private String planName;
    private Double price;
    private String description;
    private Long insuranceCompanyId;
    private Long coverageDetailsId;

}
