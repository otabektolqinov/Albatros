package com.example.albartros.dto;

import com.example.albartros.enums.UserRole;
import com.example.albartros.model.Bookings;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;


import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String fullName;
    @Email
    @NotEmpty(message = "Email must not be null")
    @NotBlank(message = "Email must not be null")
    private String email;
    @NotBlank(message = "Phone must not be blank")
    @NotEmpty(message = "Phone must not be empty")
    private String phone;
    private UserRole role;
    private Long addressId;
    private Long authUserId;
    private List<ToursDto> toursList;
    private List<BookingDto> bookingsList;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
