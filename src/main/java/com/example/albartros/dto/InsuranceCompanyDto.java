package com.example.albartros.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InsuranceCompanyDto {

    private Long id;
    @NotBlank(message = "companyName cannot be blank, empty or null")
    private String companyName;
    @NotBlank(message = "description cannot be blank, empty or null")
    private String description;
    private List<InsurancePlanDto> planList;

}
