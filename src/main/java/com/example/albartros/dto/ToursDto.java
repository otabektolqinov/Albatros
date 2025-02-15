package com.example.albartros.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ToursDto {
    private Long id;
    @NotBlank(message = "Name must not be blank")
    private String name;
    @NotNull(message = "FromDestinationId must not be blank")
    private Long fromDestinationId;
    @NotNull(message = "ToDestinationId must not be blank")
    private Long toDestinationId;
    @NotNull(message = "StartDate must not be blank")
    private LocalDateTime startDate;
    @NotNull(message = "EndDate must not be blank")
    private LocalDateTime endDate;
    @NotNull(message = "Price must not be blank")
    private Double price;
    @NotBlank(message = "Description must not be blank")
    private String description;
    @NotBlank(message = "AirCompany must not be blank")
    private String airCompany;
    private Long discountId;


    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
