package com.airline.commonlib.payload.request.auth;

import com.airline.commonlib.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "Full name must be required")
    private String fullName;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;
    private String phone;
    private UserRole role;

    @Builder.Default
    private LocalDateTime lastLogin =  LocalDateTime.now();
}
