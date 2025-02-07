package com.example.albartros.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String headline;
    @Column(columnDefinition = "TEXT")
    private String text;
    @Column(columnDefinition = "TEXT")
    private String image_url;
    @ManyToOne
    private Destination destination;


    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
