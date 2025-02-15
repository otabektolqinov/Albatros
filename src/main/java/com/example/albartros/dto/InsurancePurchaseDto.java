package com.example.albartros.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InsurancePurchaseDto {

    private Long id;
    @Positive(message = "bookings id cannot be negative or zero")
    @NotNull(message = "bookingsId cannot be null")
    private Long bookingsId;
    @Positive(message = "insurancePlanId id cannot be negative or zero")
    @NotNull(message = "insurancePlanId cannot be null")
    private Long insurancePlanId;
    private LocalDateTime purchaseDate;
    private String status;
}
