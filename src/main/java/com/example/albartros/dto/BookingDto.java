package com.example.albartros.dto;

import com.example.albartros.enums.BookingStatus;
import com.example.albartros.model.Agency;
import com.example.albartros.model.Hotel;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

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
//    private Long insuranceId;
    private Long agencyId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

}
