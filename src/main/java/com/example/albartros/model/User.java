package com.example.albartros.model;

import com.example.albartros.enums.UserRole;
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
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String email;
    private String phone;
    @OneToOne
    private Address address;
    @OneToOne
    @JoinColumn(name = "auth_user_id", unique = true, nullable = false)
    private AuthUser authUser;

    @OneToMany
    private List<Tours> toursList;
    @OneToMany
    private List<Bookings> bookingsList;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
