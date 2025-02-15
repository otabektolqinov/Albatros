package com.example.albartros.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "Name must not be blank")
    private String name;
    @Email
    @NotBlank(message = "Email must not be blank")
    private String email;
    @NotBlank(message = "Phone must not be blank")
    private String phone;
    @NotBlank(message = "Description must not be blank")
    private String description;
    private String imageUrl;
    @NotBlank(message = "Location must not be blank")
    private String location;

    private List<BookingDto> bookings;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

}
