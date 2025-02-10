package com.example.albartros.model;

import com.example.albartros.enums.BookingStatus;
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
@Entity
public class Bookings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime bookingDate;
    @Enumerated(EnumType.STRING)
    private BookingStatus status;
    @ManyToOne
    private Destination destination;
    @ManyToOne
    private Tours tours;
    @ManyToOne
    private User user;
    @ManyToOne
    private Hotel hotel;
    @OneToMany
    private List<InsurancePurchase> insuranceList;
    @ManyToOne
    private Agency agency;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

}
