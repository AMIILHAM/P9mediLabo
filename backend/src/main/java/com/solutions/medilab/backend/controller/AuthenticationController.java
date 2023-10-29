package com.solutions.medilab.backend.controller;

import com.solutions.medilab.backend.dto.token.JwtRequestDto;
import com.solutions.medilab.backend.dto.token.JwtResponseDto;
import com.solutions.medilab.backend.dto.token.JwtTokenDto;
import com.solutions.medilab.backend.dto.token.RefreshTokenDto;
import com.solutions.medilab.backend.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController @Slf4j
@RequiredArgsConstructor
@RequestMapping(path = "auth", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

    private final AuthenticationService authenticationService;


    @PostMapping(value = "login")
    public ResponseEntity<JwtResponseDto> createAuthenticationTokenLogin(@Validated @RequestBody JwtRequestDto authenticationRequest, HttpServletRequest request) throws ServiceException {
        log.info("===================== Request for Login =====================");
        JwtResponseDto jwtResponse = authenticationService.createAuthenticationTokenLogin(authenticationRequest, request.getRequestURL().toString());
        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping(value = "refreshToken")
    public ResponseEntity<JwtTokenDto> refreshToken(@Validated @RequestBody RefreshTokenDto token, HttpServletRequest request) throws ServiceException {
        log.info("===================== Request Refresh Token : {} =====================",token);
        JwtTokenDto tokens = authenticationService.refreshToken(token, request.getRequestURL().toString());
        return ResponseEntity.ok(tokens);
    }
}
