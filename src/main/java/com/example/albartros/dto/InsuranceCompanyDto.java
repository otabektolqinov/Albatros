package com.example.albartros.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InsuranceCompanyDto {

    private Long id;
    private String companyName;
    private String description;
    private List<InsurancePlanDto> planList;

}
