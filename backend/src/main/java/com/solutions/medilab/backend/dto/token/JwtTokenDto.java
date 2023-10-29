package com.solutions.medilab.backend.dto.token;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class JwtTokenDto {
    private String accessToken;
    private String refreshToken;
    private String idToken;
    private LocalDateTime accessTokenExpiration;
}
