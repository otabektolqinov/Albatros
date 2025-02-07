package com.example.albartros.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class InsurancePurchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Bookings bookings;
    @OneToOne
    private InsurancePlan insurancePlan;
    @CreationTimestamp
    private LocalDateTime purchaseDate;
    @Enumerated(value = EnumType.STRING)
    private InsuranceStatus status;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
