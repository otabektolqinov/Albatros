package com.example.albartros.dto;


import lombok.*;


import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ToursDto {
    private Long id;
    private String name;
    private Long fromDestinationId;
    private Long toDestinationId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Double price;
    private String description;
    private String airCompany;
    private Long discountId;
    private Long destinationId;


    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
