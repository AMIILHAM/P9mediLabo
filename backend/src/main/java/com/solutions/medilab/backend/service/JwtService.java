package com.solutions.medilab.backend.service;

import com.solutions.medilab.backend.dto.token.JwtTokenDto;

import java.util.List;

public interface JwtService {

    String generateToken(List<String> roles, String url, String userName, boolean isAccessToken);
    JwtTokenDto jwtTokens(List<String> authorities, String url, String username);

}
