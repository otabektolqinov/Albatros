package com.example.albartros.dto;


import jakarta.validation.constraints.NotNull;
import lombok.*;


import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DiscountDto {
    private Long id;
    @NotNull(message = "TourID must not be null")
    private Long tourId;
    @NotNull(message = "StartDate must not be null")
    private LocalDateTime startDate;
    @NotNull(message = "EndDate must not be null")
    private LocalDateTime endDate;
    @NotNull(message = "Percent must not be null")
    private Integer percent;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
