package com.example.albartros.dto;


import lombok.*;


import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DiscountDto {
    private Long id;
    private Long tourId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer percent;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
