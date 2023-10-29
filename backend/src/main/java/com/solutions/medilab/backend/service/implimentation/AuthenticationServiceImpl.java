package com.solutions.medilab.backend.service.implimentation;

import com.auth0.jwt.JWT;
import com.solutions.medilab.backend.config.ApplicationProperties;
import com.solutions.medilab.backend.dto.token.JwtRequestDto;
import com.solutions.medilab.backend.dto.token.JwtResponseDto;
import com.solutions.medilab.backend.dto.token.JwtTokenDto;
import com.solutions.medilab.backend.dto.token.RefreshTokenDto;
import com.solutions.medilab.backend.service.AuthenticationService;
import com.solutions.medilab.backend.service.JwtService;
import com.solutions.medilab.backend.service.UserService;
import com.solutions.medilab.backend.utils.Constant;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.solutions.medilab.backend.utils.Constant.*;


@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;
    private final ApplicationProperties properties;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtResponseDto createAuthenticationTokenLogin(JwtRequestDto authenticationRequest, String requestUrl) {

        List<String> authorities;

        Authentication authentication = authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        authorities = ((User) authentication.getPrincipal())
                .getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        return generateTokensAndSetResponse(authorities, requestUrl, authenticationRequest.getUsername());
    }

    @Override
    public JwtTokenDto refreshToken(RefreshTokenDto refreshTokenDto, String requestUrl) {
        String username = JWT.decode(refreshTokenDto.getRefreshToken()).getSubject();
        Date dateExp = JWT.decode(refreshTokenDto.getRefreshToken()).getExpiresAt();

        if (dateExp == null || dateExp.toInstant().isBefore(Instant.now())) {
            throw new ServiceException(Constant.REFRESH_TOKEN_EXPIRED);
        }

        return jwtService.jwtTokens(List.of("USER"), requestUrl, username);
    }

    private JwtResponseDto generateTokensAndSetResponse(List<String> authorities, String requestURL, String username) {
        JwtTokenDto tokens = jwtService.jwtTokens(authorities, requestURL, username);
        JwtResponseDto jwtResponce = new JwtResponseDto();
        jwtResponce.getToken().put(ID_TOKEN, tokens.getIdToken());
        jwtResponce.getToken().put(ACCESS_TOKEN, tokens.getAccessToken());
        jwtResponce.getToken().put(REFRESH_TOKEN, tokens.getRefreshToken());
        jwtResponce.getToken().put(DATE_EXPIRATION, tokens.getAccessTokenExpiration());

        return jwtResponce;
    }
    private Authentication authenticate(String username, String password) {
        try {
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (BadCredentialsException e) {
            throw new ServiceException(Constant.INVALID_CREDENTIALS);
        }
    }
}
