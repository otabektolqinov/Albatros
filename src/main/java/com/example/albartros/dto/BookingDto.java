package com.example.albartros.dto;

import com.example.albartros.enums.BookingStatus;
import com.example.albartros.model.Agency;
import com.example.albartros.model.Hotel;
import com.example.albartros.model.InsurancePurchase;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingDto {
    private Long id;
    private LocalDateTime bookingDate;
    private BookingStatus status;
    @NotNull(message = "HotelId must not be null")
    private Long hotelId;
    @NotNull(message = "TourId must not be null")
    private Long tourId;
    @NotNull(message = "UserId must not be null")
    private Long userId;
    @NotNull(message = "HotelId must not be null")
    private Long destinationId;
    private List<InsurancePurchase> insuranceList;
    @NotNull(message = "AgencyId must not be null")
    private Long agencyId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

}
