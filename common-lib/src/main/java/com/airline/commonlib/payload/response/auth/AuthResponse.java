package com.airline.commonlib.payload.response.auth;

import com.airline.commonlib.dto.UserDTO;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private String message;
    private String title;
    private UserDTO user;
}
