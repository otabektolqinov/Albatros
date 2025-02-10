package com.example.albartros.dto;

import com.example.albartros.enums.BookingStatus;
import com.example.albartros.model.Agency;
import com.example.albartros.model.Hotel;
import com.example.albartros.model.InsurancePurchase;
import jakarta.persistence.*;
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
    private Long hotelId;
    private Long tourId;
    private Long userId;
    private Long destinationId;
    private List<InsurancePurchase> insuranceList;
    private Long agencyId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

}
