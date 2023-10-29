package com.solutions.medilab.backend.service.implimentation;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.solutions.medilab.backend.config.ApplicationProperties;
import com.solutions.medilab.backend.dto.token.JwtTokenDto;
import com.solutions.medilab.backend.service.JwtService;
import com.solutions.medilab.backend.utils.Constant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    private final ApplicationProperties properties;

    @Override
    public JwtTokenDto jwtTokens(List<String> authorities, String url, String username) {
        JwtTokenDto tokens = new JwtTokenDto();
        tokens.setAccessToken(generateToken(authorities, url, username, true));
        tokens.setRefreshToken(generateToken(null, url, username, false));
        tokens.setAccessTokenExpiration(LocalDateTime.now().plus(Duration.ofMillis(properties.getAccessTokenExpirationDuration())));
        tokens.setIdToken(generateToken(authorities, url, username, false));
        return tokens;
    }

    @Override
    public String generateToken(List<String> authorities, String url, String username, boolean isAccessToken) {
        JWTCreator.Builder jwtBuilder = JWT.create().withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + (isAccessToken ? properties.getAccessTokenExpirationDuration() : properties.getRefreshTokenExpirationDuration())))
                .withIssuer(url);

        if (isAccessToken)
            jwtBuilder.withArrayClaim(Constant.TOKEN_AUTHORITIES, authorities.toArray(new String[0]));

        return jwtBuilder.sign(getAlgorithm());
    }

    private Algorithm getAlgorithm(){
        return Algorithm.HMAC256(properties.getAuthenticationSecret());
    }

}
