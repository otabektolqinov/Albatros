package com.example.albartros.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventDto {

    private Long id;
    @NotBlank(message = "title cannot be empty, null or blank")
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<String> photos;

}
