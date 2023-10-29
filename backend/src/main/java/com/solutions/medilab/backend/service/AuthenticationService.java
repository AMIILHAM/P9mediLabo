package com.solutions.medilab.backend.service;

import com.solutions.medilab.backend.dto.token.JwtRequestDto;
import com.solutions.medilab.backend.dto.token.JwtResponseDto;
import com.solutions.medilab.backend.dto.token.JwtTokenDto;
import com.solutions.medilab.backend.dto.token.RefreshTokenDto;

public interface AuthenticationService {
    JwtResponseDto createAuthenticationTokenLogin(JwtRequestDto authenticationRequest, String requestUrl);
    JwtTokenDto refreshToken(RefreshTokenDto refreshTokenDto, String requestUrl);

}