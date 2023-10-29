package com.solutions.medilab.backend.config;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.solutions.medilab.backend.utils.Constant;
import com.solutions.medilab.backend.utils.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final ApplicationProperties applicationProperties;

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(Constant.AUTHORIZATION_HEADER);
        if (Utils.isNotBlank(bearerToken) && bearerToken.startsWith(Constant.BEARER_TOKEN_STARTS_WITH)) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public UsernamePasswordAuthenticationToken getAuthentication(String jwt) {
        Algorithm algorithm = Algorithm.HMAC256(applicationProperties.getAuthenticationSecret());
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(jwt);
        String username = decodedJWT.getSubject();
        String[] roles = decodedJWT.getClaim(Constant.TOKEN_ROLES_ATTRIBUTE_NAME).asArray(String.class);
        Collection<GrantedAuthority> authorities = Arrays.stream(roles).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return new UsernamePasswordAuthenticationToken(username, null, authorities);
    }

    public boolean validateToken(String jwt, HttpServletResponse httpServletResponse) {
        String code;
        String message;
        try {
            Algorithm algorithm = Algorithm.HMAC256(applicationProperties.getAuthenticationSecret());
            JWTVerifier jwtVerifier = JWT.require(algorithm).build();
            jwtVerifier.verify(jwt);
            return true;
        } catch (TokenExpiredException e) {
            message = e.getMessage();
            code = Constant.TOKEN_EXPIRED;
            log.error(code, message);
        } catch (InvalidClaimException | IllegalArgumentException | AlgorithmMismatchException e) {
            message = e.getMessage();
            code = Constant.TOKEN_INVALID;
            log.error(code, message);
        }
        httpServletResponse.setHeader(Constant.ERROR_MESSAGE, message);
        httpServletResponse.setHeader(Constant.ERROR_CODE, code);
        return false;
    }
}
