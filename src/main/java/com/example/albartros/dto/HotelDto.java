package com.example.albartros.dto;

import com.example.albartros.model.Country;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HotelDto {
    private Long id;
    @NotBlank(message = "Name must not be blank")
    @NotEmpty(message = "Message must not be empty")
    private String name;
    @NotBlank(message = "CityName must not be blank")
    @NotEmpty(message = "CityName must not be empty")
    private String cityName;
    @NotNull(message = "Starts must not be null")
    private Integer starts;
    @NotNull(message = "CountryId must not be null")
    private Long countryId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
