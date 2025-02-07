package com.example.albartros.dto;


import lombok.*;


import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AgencyDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String description;
    private String imageUrl;
    private String location;
    private List<BookingDto> bookings;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

}
