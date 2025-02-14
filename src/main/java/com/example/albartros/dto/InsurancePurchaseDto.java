package com.example.albartros.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InsurancePurchaseDto {

    private Long id;
    private Long bookingsId;
    private Long insurancePlanId;
    private LocalDateTime purchaseDate;
    private String status;
}
